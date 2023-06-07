<template>
    <div>
        <a-input-search v-model="addressValue" placeholder="请输入所在位置"  enter-button  @search="showMapModal"/>
        <a-modal v-model="modalVisible" destroyOnClose title="位置选择" width="60%" :bodyStyle="bodyStyle" :footer="null">
            <div style="width:100%;height:600px;position:relative">
            <a-input-search id="searchInput" enter-button v-model="poiSearchName" @search="poiSearch" style="width:320px;position:absolute;left:20px;top:20px;z-index:1000" />
            <div id="poiListContent"  style="width:320px;height:300px;overflow-x: hidden; overflow-y: auto;position:absolute;left:20px;top:60px;z-index:1000" >
                <poi-item-content v-for="(item,index) in markList" :key='index' :itemData="item" :indexNumber="index+1" @showDetailPoi="showDetailPoi" @sureSelect="sureSelect"></poi-item-content>
            </div>
            <div id="mapContainer" style="width:100%;height:600px;" ></div>
            </div>
        </a-modal>
    </div>
</template>
<script>
import AMapLoader from '@amap/amap-jsapi-loader';
import PoiItemContent from "@/views/components/PoiItemContent.vue"
export default ({
    name:'AddressSelectModal',
    components:{PoiItemContent},
    props:{addressName:{type:String,default:''}},
    data(){
        return {
            aMapUtil:null,
            bodyStyle:{height:'600px',padding:'0px'},
            addressValue:'',
            addressLngLat:{lng:'',lat:''},
            poiSearchName:'',
            modalVisible:false,
            map:null,
            //POI搜索插件
            placeSearch:null,
            autoComplete:null,
            //POI查询后存储覆盖物
            markList:[]
        }
    },
    methods:{
        showMapModal(){
            this.modalVisible = true
            this.initMap()
        },
        initMap(){
            let _this=this
            AMapLoader.load({
                key:"df59d6b3b5367c5f872e30948f0f1dfd",             // 申请好的Web端开发者Key，首次调用 load 时必填
                version:"2.0",      // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
                plugins:['AMap.AutoComplete','AMap.PlaceSearch'],       // 需要使用的的插件列表，如比例尺'AMap.Scale'等
            }).then((AMap)=>{
                _this.aMapUtil = AMap
                _this.map = new AMap.Map("mapContainer",{  //设置地图容器id
        //            mapStyle: 'amap://styles/blue',
                     zoom:12,           //初始化地图级别
                    viewMode:"3D",    //是否为3D地图模式
                    center:[117.30723202228546,31.88490057332741], //初始化地图中心点位置
                });
                let autoOptions = {
                        city: '3401'
                    };
                    let autoComplete= new AMap.AutoComplete(autoOptions);
                    _this.autoComplete = autoComplete
                _this.placeSearch = new AMap.PlaceSearch({
                    citylimit: true,  //是否强制限制在设置的城市内搜索
                    // city 指定搜索所在城市，支持传入格式有：城市名、citycode和adcode
                    city: '3401',
                    extensions: 'base', //返回基本地址信息
                    autoFitView: true // 是否自动调整地图视野使绘制的 Marker点都处于视口的可见范围
                });
                //初始化地图和插件后初始化查询
                _this.markList = []
                _this.poiSearch()
            }).catch(e=>{
                console.log(e);
            })
        },
        poiSearch(){
            
            let _this =this
            let isDoSearch = (_this.poiSearchName != '')
            if(isDoSearch){
                debugger
                let tmpStrArray = _this.poiSearchName.split(",")
                this.placeSearch.search(tmpStrArray[0], function (status, result) {
                    // 查询成功时，result即对应匹配的POI信息
                    debugger
                    if(result.info){
                        let poiList = result.poiList.pois
                        _this.markList = poiList
                        _this.clearMap()
                        poiList.forEach(element => {
                            _this.createMarker(element)
                        });
                    }        
                })
            }
        },
        createMarker(item){
            let _this = this
            if(_this.map){
                debugger
                let AMap = _this.aMapUtil
                let marker = new AMap.Marker({
                        position: new AMap.LngLat(item.location.lng,item.location.lat),   // 经纬度对象，也可以是经纬度构成的一维数组[116.39, 39.9]
                        title: item.name
                    });
                marker.on('click',(e)=>{
                    
                }) 
                _this.map.add(marker)   
            }
        },
        clearMap(){
            this.map.clearMap()
        },
        showDetailPoi(item){
            let position = []
            position.push(item.location.lng)
            position.push(item.location.lat)
            this.map.setCenter(position)
            this.map.setZoom(19)
        },
        sureSelect(item){
            
            let name = item.name
            this.addressLngLat.lng = item.location.lng
            this.addressLngLat.lat = item.location.lat
            let tmpStr = item.name+","+item.location.lng+","+item.location.lat
            this.addressValue = tmpStr
         //   let params = this.getLngLat()
        //     this.$emit('lngLatChange',params)
            this.modalVisible = false
        },
        getLngLat(){
            let params = {lng:this.addressLngLat.lng,lat:this.addressLngLat.lat}
            return params
        }
    },
    watch:{       
        addressName:{ 
        immediate:true,
        handler:   function(newVal,oldVal){
                           this.addressValue = newVal
                           this.poiSearchName = newVal
                          }
      },
       addressValue:{ 
        immediate:true,
        handler:   function(newVal,oldVal){
                          this.poiSearchName = newVal
                           this.$emit('change',newVal)
                          }
      }
    },
    model: {
    prop: "value",
    event: "change"
    },
    mounted(){
        window._AMapSecurityConfig = {
            securityJsCode:'11ff7aa88b2618bc094bfb45dcb2aed4',
        }
    }

})
</script>
<style scoped>
/* 隐藏滚动条，给滚动条添加伪类，宽度设为0 */
::-webkit-scrollbar{width:0;}
</style>
