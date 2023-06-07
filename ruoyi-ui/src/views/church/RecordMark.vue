<template>
  <div>
  <el-row style="margin-top:5px">
    <span style="margin-left:20px;float:left">考勤日期：</span>
    <el-date-picker
     style="margin-left:10px;float:left"
      v-model="selectedDate"
      type="date"
      value-format="yyyy-MM-dd"
      placeholder="选择日期">
    </el-date-picker>
    </el-row>
    <el-row style="margin-top:10px">
    <span style="float:left">周六考勤人员：</span><span style="color:rgb(0,255,89);float:left">{{checkList}}</span>
    </el-row>

    <el-card style="margin-top:50px">
      <el-checkbox-group v-model="checkList">
        <el-checkbox v-for="(item,index) in teammerList" :key="index" :label="item.name"></el-checkbox>
      </el-checkbox-group>
    </el-card>
    <el-row>
      <el-col  :span="6" :offset="18">
        <el-button type="primary" style="margin-top:25px" @click="getPostData">确认提交</el-button>
      </el-col>
    </el-row>
  </div>
</template>
<script>
var axios = require('axios')
axios.defaults.headers.post['Content-Type'] = 'application/json';
export default ({
   name: 'RecordMark',
  data () {
    return {
       //今日的日期
        today:'',
        //时间控件上的日期
        selectedDate: '',
        teammerList:[
                    {name:'焦菊',groudName:'焦菊'},{name:'宁萧杭',groudName:'焦菊'},{name:'完萍萍',groudName:'焦菊'},{name:'孙蓉蓉',groudName:'焦菊'},
                    {name:'罗香燕',groudName:'焦菊'},{name:'王敏',groudName:'焦菊'},{name:'王春燕',groudName:'焦菊'},{name:'曹俪扬',groudName:'焦菊'},
                    {name:'张蒙恩',groudName:'胡慧珍'},{name:'夏莉',groudName:'胡慧珍'},{name:'陈雪',groudName:'胡慧珍'},{name:'徐欣',groudName:'胡慧珍'},
                    {name:'王翠萍',groudName:'胡慧珍'},{name:'鲍令仪',groudName:'胡慧珍'},{name:'潘晓娇',groudName:'胡慧珍'},{name:'胡慧珍',groudName:'胡慧珍'},
                    {name:'郭倩岚',groudName:'胡慧珍'},{name:'孙贵婷',groudName:'胡慧珍'},{name:'葛咏莉',groudName:'胡慧珍'},{name:'宋亚磊',groudName:'余承彪'},
                    {name:'魏小东',groudName:'余承彪'},{name:'完绍强',groudName:'余承彪'},{name:'余承彪',groudName:'余承彪'},{name:'张磊',groudName:'余承彪'},
                    {name:'林浩东',groudName:'余承彪'},{name:'孙伟',groudName:'余承彪'},{name:'刘沁',groudName:'余承彪'},{name:'丁方军',groudName:'余承彪'},
                    {name:'王坤朋',groudName:'余承彪'},{name:'宋福林',groudName:'余承彪'},{name:'徐杰',groudName:'余承彪'},{name:'方陈',groudName:'余承彪'},
                    {name:'陈登基',groudName:'余承彪'},{name:'徐静蕾',groudName:'待定'},{name:'曹从义',groudName:'待定'},{name:'宋赵力',groudName:'待定'},
                    {name:'杨晨阳',groudName:'待定'},{name:'江祖正',groudName:'待定'},{name:'韩萍萍',groudName:'待定'},{name:'葛永莉',groudName:'待定'},
                    // {name:'徐静蕾',groudName:'待定'},{name:'徐静蕾',groudName:'待定'},{name:'徐静蕾',groudName:'待定'},{name:'徐静蕾',groudName:'待定'}
                    ],
        //选择的考情人员
        checkList:[],
        jsonData:[]
    }
    },
    watch: {
      selectedDate(val, newval) {
              this.getDataList()
          }
    },
    methods: {
      submitData(){
          let _this = this
          let isSaturday  =  _this.isSaturday()

          // if(!isSaturday){
          //   _this.$message('这一天不是星期六，不需要记录');
          //   return
          // }
          let tmpPostArray = []
          let postData = _this.jsonData
          let tmpObj = {date:_this.selectedDate,list:_this.checkList}

          if(postData.length == 0){
            tmpPostArray.push(tmpObj)
          }
          else{
            let flag = 0
            postData.forEach(item =>{
              if(item.date != tmpObj.date ){
                tmpPostArray.push(item)
              }
              else{
                if(flag == 0){
                  tmpPostArray.push(tmpObj)
                  flag = flag +1
                }
              }
            })
            if(flag == 0){
              tmpPostArray.push(tmpObj)
            }
          }

          let fullpath = 'http://localhost:8083/writejson'
      //    let fullpath = 'http://119.29.187.162:8083/writejson'
          axios
          .post(fullpath,{
              fileName:'weeklyTotal.json',
              list:tmpPostArray
          })
          .then(result => {
            console.dir(result)
            })
    },
      isSaturday(){
        let week = this.getweekday(this.selectedDate)
        let boolea =( week == "六")
        return boolea
      },
      //根据年月日获取周几
      getweekday(date){
      var weekArray = new Array("日", "一", "二", "三", "四", "五", "六");
      var week = weekArray[new Date(date).getDay()];//注意此处必须是先new一个Date
      return week;
    },
    getDataList(){
      let _this = this
       _this.checkList = []
      let fullpath = 'http://localhost:8083/readjson/weeklyTotal.json'
  //    let fullpath = 'http://119.29.187.162:8083/readjson/weeklyTotal.json'
      axios
      .get(fullpath)
      .then(response => {
        let tmpArray =response.data
        tmpArray.forEach(item => {
          if(item.date == _this.selectedDate){
            _this.checkList = item.list
          }
        });
        _this.jsonData = response.data
        })
    },
    //发现后端接口处理数据同步异步问题太麻烦，直接前台处理数据，后台执行持久化，避免地狱回调
     getPostData(){
      let _this = this
      let fullpath = 'http://localhost:8083/readjson/weeklyTotal.json'
  //    let fullpath = 'http://119.29.187.162:8083/readjson/weeklyTotal.json'
      axios
      .get(fullpath)
      .then(response => {
       _this.jsonData = response.data
       _this.submitData()
    })
    }

  },
  mounted(){
    //赋值当前日期
    let tmpArray = new Date().toLocaleDateString().split('/')
    let year = tmpArray[0]
    let month =( tmpArray[1]+'').length == 2 ? tmpArray[1]+'' : '0' +tmpArray[1]
    let day = ( tmpArray[2]+'').length == 2 ? tmpArray[2]+'' : '0' +tmpArray[2]
    let today =year + '-' +month +'-' +day
    this.selectedDate = today
    this.today = today
    this.getDataList()
  }
})
</script>
  <style>

  </style>
