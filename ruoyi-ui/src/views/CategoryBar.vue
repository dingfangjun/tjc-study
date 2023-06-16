<template>
  <div >
    <div id="totalCount">
    <el-row>
      <el-col :span="6">
        <el-date-picker
        v-model="selectTime"
        type="daterange"
        :align="'right'"
        unlink-panels
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
        :picker-options="pickerOptions">
        </el-date-picker>
      </el-col>
      <el-col :span="6">
        <el-select v-model="searchParams.deptId" placeholder="请选择部门">
          <!-- <el-option label="所有人" value=""></el-option> -->
          <el-option label="社青" value="103"></el-option>
          <el-option label="新青年" value="105"></el-option>
          
        </el-select>
      </el-col>
      <el-col :span="2">
        <el-button type="primary" @click="searchData">查询</el-button>
      </el-col>
      <el-col :span="2">
        <el-button type="primary" @click="exportPdf">导出pdf</el-button></el-col>
      <el-col :span="3">
        <download-excel 
            style="width: 60px;"
            class = "export-excel-wrapper"
            :data="tableData"
            :fields="fieldsArray"
            :name="'统计表.xls'"
            :before-generate="startDownload"
        >
            <el-button size="small" type="primary" plain >导出excel</el-button>
        </download-excel>
      </el-col>
    </el-row>
    <div id="pdfDom" style="width:100%;height:500px;margin-top: 20px;margin-bottom: 20px;"></div>
    <a-table bordered :columns="tableColumns"  :data-source="tableData">
      <span slot="renderText" slot-scope="text"><span style="color:rgb(49,126,246)" v-if="text == '考勤'">考勤</span><span style="color:rgb(250, 16, 16)" v-if="text == '缺勤'">缺勤</span></span>
    </a-table>
    <el-dialog title="个人考勤详情" :visible.sync="dialogVisible"  width="30%" :center="true">
      <el-table
          :data="userTimeList"
          style="width: 100%">
          <el-table-column
            prop="userName"
            label="姓名"
            width="180">
          </el-table-column>
          <el-table-column
            prop="checkTime"
            label="考勤时间">
          </el-table-column>
        </el-table>
    </el-dialog>
  </div>
  </div>
</template>
<script>
import moment from "moment"
import echarts from 'echarts'
import request from '@/utils/request'
export default ({
  name: 'CategoryBar',
  dicts: ['sys_user_list'],
  data() {
    return {
      selectTime:[],
      searchParams:{'userId':null,'deptId':'103','startTime':"2023-05-01 00:00:00","endTime":"2023-06-07 23:59:59"},
      dialogVisible:false,
      userTimeList:[],
      pickerOptions: {
          shortcuts: [{
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
        echartsArrayData:[],
        tableData:[],
        tableColumns:[],
        fieldsArray:{}
    }
  },
  methods: {
    searchData() {
      let _this = this
      let thisMonth = moment(new Date()).format("yyyy-MM")
     // let timeArray = this.selectTime
      this.searchParams.startTime = moment(this.selectTime[0]).format("yyyy-MM-DD 00:00:00")
      this.searchParams.endTime = moment(this.selectTime[1]).format("yyyy-MM-DD 23:59:59")
      console.log(this.searchParams.startTime)
      console.log(this.searchParams.endTime)
      request({
          url: '/check-record/listCheckUser',
          method: 'post',
          data: this.searchParams
        }).then(res =>{
          if(res.data && res.data.length>0){
            let resData = res.data[0]
            let deptName = resData.deptName
            let resArray = resData.checkUserList
            _this.echartsArrayData = resArray
            _this.renderEcharts(deptName,resArray)
            _this.renderTable(resArray)
          }
          else{
            _this.tableData = []
            _this.echartsArrayData = []
            _this.renderEcharts('无数据',[])
          }
        })
    },
    renderTable(arrayData){
      let columnsNameArray = []
      let userArray = []
      arrayData.forEach(item =>{
        userArray.push(item.userName)
        item.records.forEach(jtem =>{
          columnsNameArray.push(jtem.checkTime.substring(0,10))
        })
      })
      columnsNameArray =Array.from(new Set(columnsNameArray)).sort() 
      let columns = []
      let fieldsArray = {"人员":"userName"}
      columns.push({title:'人员',
      dataIndex: 'userName'})
      columnsNameArray.forEach(item =>{
        let tmpObj ={
          dataIndex: item,
            title: item,
            scopedSlots: { customRender: 'renderText' },
          }
          columns.push(tmpObj) 
          fieldsArray[item] = item
      })  
      console.dir(fieldsArray)
      console.dir(columns)
      this.tableColumns =  columns 
      this.fieldsArray = fieldsArray
      let tableData = []
      arrayData.forEach(item =>{
        let tmpObj = {'userName':item.userName,key:item.userName}
        columnsNameArray.forEach((jtem,index) =>{
              tmpObj[jtem] = '缺勤'
        })
        item.records.forEach(jtem =>{
          let tmpTime = jtem.checkTime.substring(0,10)
          tmpObj[tmpTime] = '考勤'
        })
        tableData.push(tmpObj)
      })
      debugger
      this.tableData = tableData
    },
    renderEcharts(deptName,arrayData){
      let _this = this
      let tmpXArray = []
      let tmpYArray = []
      arrayData.forEach(item =>{
        tmpXArray.push(item.userName)
        tmpYArray.push(item.count)
      })
      let chartDom = document.getElementById("pdfDom")
      let myChart = echarts.init(chartDom);
      let option  = {
            backgroundColor: '#081736',
              tooltip: {},
              grid: {
                  left: '5%',
                  right: '6%',
                  bottom: '15%',
                  top: '28%',
                  containLabel: true
              },
              xAxis: {
                  type: 'category',
                  data: tmpXArray,

                  axisLine: {
                      show: true,
                      lineStyle: {
                          color: '#05598F',
                          // width: 1
                      },
                  },
                  axisLabel: {
                      interval: 0,
                      rotate: 0,
                      textStyle: {
                          color: '#fff',
                          fontSize: 12
                      },
                  },
                  axisTick: {
                      show: false,
                  },
              },
              yAxis: {
                  type: 'value',
                  // min: 0,
                  // max: 140,
                  splitLine: { //分割线配置
                      lineStyle: {
                          color: "#05598F",
                      }
                  },
                  axisLine: {
                      show: true,
                      lineStyle: {
                          color: '#758FA6'
                      }
                  },
                  axisTick: {
                      show: false,
                  },
                  axisLabel: {
                      textStyle: {
                          color: '#fff',
                          fontSize: 16
                      },
                  },
              },
              series: [{
                  barWidth: 14,
                  data: tmpYArray,
                  type: 'bar',
                  itemStyle: {
                      normal: {
                          color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                              offset: 0,
                              color: 'rgba(0,244,255,1)' // 0% 处的颜色
                          }, {
                              offset: 1,
                              color: 'rgba(0,77,167,1)' // 100% 处的颜色
                          }], false),
                          barBorderRadius: [30, 30, 30, 30],
                      }
                  },
              }]
          };
      myChart.setOption(option)    
      myChart.on('click',function(params){
          _this.showDetail(params.name)
      })  
    },
    showDetail(name){
      let echartsArrayData = this.echartsArrayData
      this.dialogVisible = true
      let tmpArray = []
      echartsArrayData.forEach(item =>{
        if(item.userName === name){
          let recordsList = item.records
          recordsList.forEach(jtem =>{
            let tmpObj = {userName:name,checkTime:jtem.checkTime}
            tmpArray.push(tmpObj)
          })
        }
      })
      this.userTimeList = tmpArray
    },
    createEcharts(dataRows) {

      let countDataArray = dataRows.sort();
      let x_array = [...new Set(countDataArray)]
      let y_array = []
      let x_name_array = []
      for (let i = 0; i < x_array.length; i++) {
        let total = countDataArray.lastIndexOf(x_array[i]) - countDataArray.indexOf(x_array[i]) + 1
        y_array.push(total)
        x_name_array.push(this.getUserNameById(x_array[i]))
      }

      let chartDom = document.getElementById('pdfDom');
      let myChart = echarts.init(chartDom);
      let option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        xAxis: {
          axisLabel: { //设置x轴的字
            show: true,
            interval: 0,//使x轴横坐标全部显示
            textStyle: {//x轴字体样式
              //  color: "rgba(219,225,255,1)",
              margin: 15
            }
          },
          type: 'category',
          data: x_name_array
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: y_array,
            type: 'bar',
            itemStyle: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {offset: 0, color: '#74c2da'},
                {offset: 0.5, color: '#0097c3'},
                {offset: 1, color: '#014a91'}
              ])
            },
          }
        ]
      }
      myChart.setOption(option)
    },
    exportPdf() {
      this.getPdf()
    }
  },
  mounted() {
    const end = new Date();
    const start = new Date();
    start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
    this.selectTime = [start, end]
    this.searchData()
  }
})
</script>
