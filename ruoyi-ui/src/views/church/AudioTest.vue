<!---地图模拟沙盘页面------>
<template>
    <div>
        <div id="container"></div>
        <div>
            <el-button type="primary" size="mini" style="position:absolute;right:200px;top:0px" @click="showUserListMarks">显示图标</el-button>
            <el-button type="primary" size="mini" style="position:absolute;right:100px;top:0px" @click="hideUserListMarks">隐藏图标</el-button>
            <el-button type="primary" size="mini" style="position:absolute;right:0px;top:0px" @click="drawerVisible=true">展开人员</el-button></div>
        <el-drawer
            :visible.sync="drawerVisible"
            title="人员选择"
            size="300px"
            >
            <div style="background-color:rgb(3,52,71);padding:5px">
            <el-row :gutter="10">
                <el-col :span="16">
                    <el-slider title="区域版块透明度" v-model="alphaValue" style="margin-left:10px"></el-slider>
                    </el-col>
                <el-col :span="6">
                    <el-button @click="alphaValueChange" type="success" style="margin-top:5px">渲染</el-button>
                </el-col>
            </el-row>
               <el-card style="margin:10px;min-height:100px;background-color:rgba(55,255,255,0.3);padding-bottom:15px" shadow="always">
                总是显示
                </el-card>
                <el-card style="margin:10px;min-height:100px;background-color:rgba(55,255,255,0.3);;padding-bottom:15px" shadow="always">
                    <user-select-on-map v-for="(item,index) in userList" :key="index" :dictLabel="item.dictLabel" :dictValue="item.dictValue" :remark="item.remark" @setMapCenter="setMapCenter"></user-select-on-map>
                </el-card>
            </div>
            </el-drawer>
    </div>
</template>
<script>
import AMapLoader from '@amap/amap-jsapi-loader';
import UserSelectOnMap from "@/views/components/UserSelectOnMap"
export default ({
    name:'AudioTest',
    components:{UserSelectOnMap},
    data(){
        return {
            AMap:null,
             map:null,
             drawerVisible:false,
             autoComplete:null,
             //区域版块透明度
             alphaValue:100,
             disProvince:null,
             userList:[],
             userMarkList:[],
             //交通路况图层
             trafficLayer:null
        }
    },
    methods:{
        initMap(){
            let _this = this
            AMapLoader.load({
                key:"df59d6b3b5367c5f872e30948f0f1dfd",             // 申请好的Web端开发者Key，首次调用 load 时必填
                version:"2.0",      // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
                plugins:['AMap.AutoComplete'],       // 需要使用的的插件列表，如比例尺'AMap.Scale'等
            }).then((AMap)=>{
                _this.AMap = AMap
                let  map = new AMap.Map("container",{  //设置地图容器id
                    viewMode:"3D",    //是否为3D地图模式
                    zoom:12,           //初始化地图级别
                    pitch:45,          //3D模式下的俯视角度
                    mapStyle: 'amap://styles/blue',
                    center:[117.30723202228546,31.88490057332741], //初始化地图中心点位置
                });
                _this.map = map
                    let autoOptions = {
                        city: '3401'
                    };
                    let autoComplete= new AMap.AutoComplete(autoOptions);
                    _this.autoComplete = autoComplete
                    let trafficLayer = new AMap.TileLayer.Traffic({
                        zIndex: 10,
                        zooms: [7, 22],
                    });
                    this.trafficLayer = trafficLayer
                    trafficLayer.setMap(map)
                    let disProvince = new AMap.DistrictLayer.Province({
                    zIndex:12,
                    adcode:['340100'],
                    depth:2,
                    styles:{
                        'fill':function(properties){

                            let colorArray = []
                            colorArray.push("rgba(3,52,71,1)")
                            colorArray.push("rgba(162,232,200,1)")
                            colorArray.push("rgba(255,182,118,1)")
                            colorArray.push("rgba(37,184,240,1)")
                            colorArray.push("rgba(205,78,86,1)")
                            let codeArray = ["340101","340102","340103","340104","340111"]
                            let  adcode = properties.adcode + '';
                            let index = codeArray.indexOf(adcode)
                       //     index = -1
                            let color = index > -1 ? colorArray[index] : "rgb(3,52,71)"
                            return color;
                        },
                        'province-stroke':'cornflowerblue',
                        'city-stroke': 'rgba(255,255,255,0.5)',//中国地级市边界
                        'county-stroke':
                        //中国区县边界
                        function(properties){

                            let colorArray = ["rgba(255,255,255,1)","rgba(255,255,255,1)","rgba(255,255,255,1)","rgba(255,255,255,1)","rgba(255,255,255,1)"]
                            let codeArray = ["340101","340102","340103","340104","340111"]
                            let  adcode = properties.adcode + '';
                            let index = codeArray.indexOf(adcode)
                        //    index = -1
                            let color = index > -1 ? colorArray[index] : "rgba(3,52,71,0)"
                            return color;
                        },

                    }
                })
                _this.disProvince = disProvince
                _this.initUserList()
                 disProvince.setMap(map);
                disProvince.setOpacity(0)
                this.alphaValue = 0
                    }).catch(e=>{
                        console.log(e);
                    })
            },
            // 根据关键字进行搜索
            searchPlace(placeName){
                    this.autoComplete.search(placeName, function(status, result) {

                    })
            },
            alphaValueChange(){
                let alphaVlue = this.alphaValue * 0.01
                this.disProvince.setOpacity(alphaVlue)
            //    this.disProvince.setIndex(100)
            },
            clearMap(){

            },
            initUserList(){
                let _this = this
                _this.getDicts("sys_user_list").then(response => {
                    let tmpArray = response.data
                    tmpArray.sort(function(a,b){
                        return a.dictLabel.localeCompare(b.dictLabel)
                    })
                    _this.userMarkList = []
                    tmpArray.forEach(item => {
                        _this.createUserMark(item)
                    })
                    _this.userList = tmpArray
                    });
            },
            createUserMark(item){
                let _this = this
                let AMap = _this.AMap
                let username = item.dictLabel
                let tmpArray  = null
                if(item.remark){
                    tmpArray = item.remark.split(',')
                }
                if(Array.isArray(tmpArray) && tmpArray.length === 3 ){
                    let locationName =  tmpArray[0]
                    let  marker = new AMap.Marker({
                        position: new  AMap.LngLat(parseFloat(tmpArray[1]),parseFloat(tmpArray[2])),
                        icon: '//vdata.amap.com/icons/b18/1/7.png',
                        anchor: 'bottom-center',
                    });
                    let lngLat = []
                    lngLat.push(tmpArray[1])
                    lngLat.push(tmpArray[2])
                    let infoWindow = new AMap.InfoWindow({
                                anchor: 'bottom-center',
                                offset:[0,-40],
                                content: "<div style='width:230px;height:40px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;'>"+
                                             "<b>位置名称:<span title='"+locationName+"' style='margin-left:10px;'>"+locationName+"</span></b><br/>" +
                                                "<b>人员:<span title='"+username+"' style='margin-left:10px;'>"+username+"</span></b><br/>" +
                                             "</div>",
                        })
                    marker.on('click',()=>{
                            infoWindow.open(_this.map,lngLat);
                    })
                    _this.userMarkList.push(marker)
                    _this.map.add(marker)
                }
            },
            showUserListMarks(){
                let tmpArray = this.userMarkList
                tmpArray.forEach(item =>{
                    item.show()
                })
                this.trafficLayer.show()
            },
            hideUserListMarks(){
                let tmpArray = this.userMarkList
                tmpArray.forEach(item =>{
                    item.hide()
                })
                this.trafficLayer.hide()
            },
            setMapCenter(zoom,lng,lat){
                if(this.map){
                    let tmpArray = []
                    tmpArray.push(parseFloat(lng))
                    tmpArray.push(parseFloat(lat))
                    this.map.setZoomAndCenter(zoom,tmpArray)
                }
            }
    },
    mounted(){
         window._AMapSecurityConfig = {
            securityJsCode:'11ff7aa88b2618bc094bfb45dcb2aed4',
        }
        this.initMap()
    }
})
</script>
<style  scoped>
    #container{
        padding:0px;
        margin: 0px;
        width: 100%;
        height: 900px;
    }
</style>
