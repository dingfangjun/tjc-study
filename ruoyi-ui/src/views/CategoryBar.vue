<template>
  <div>
    <el-row>
      <el-col :span="6">
        <el-date-picker
          v-model="selectMonth"
          type="month"
          value-format="yyyy-MM"
          placeholder="选择月">
        </el-date-picker>
      </el-col>
      <el-col :span="6">
        <el-select v-model="countType">
          <el-option
            v-for="item in typeOptions"
            :key="item.id"
            :label="item.name"
            :value="item.value"
          />
        </el-select>
      </el-col>
      <el-col :span="6">
        <el-button type="primary" @click="searchData">查询</el-button>
        <el-button type="primary" @click="exportPdf">导出pdf</el-button>
      </el-col>
    </el-row>
    <div id="pdfDom" style="width:100%;height:500px"></div>
  </div>
</template>
<script>
import {listRecordsLogsByMonth} from "@/api/system/records";
import {listSaturdayLogsByMonth} from "@/api/system/logs";
import moment from "moment"
import echarts from 'echarts'

export default ({
  name: 'CategoryBar',
  dicts: ['sys_user_list'],
  data() {
    return {
      userListOption: [],
      selectMonth: '',
      countType: "everyDayMark",
      typeOptions: [{id: 1, name: "每日读经打卡", value: 'everyDayMark'}, {
        id: 2,
        name: "周六聚会打卡",
        value: 'saturDayMark'
      }]
    }
  },
  methods: {
    /** 查询每日读经列表 */
    getEveryDayLogList() {
      listRecordsLogsByMonth({pageNum: 1, pageSize: 40, date: this.selectMonth}).then(response => {
        if (response.total > 0) {
          let tmpArray = []
          response.rows.forEach(item => {
            let thisArray = item.userlist.split(',')
            tmpArray = tmpArray.concat(thisArray)
          })
          this.createEcharts(tmpArray)
        }
      });
    },
    /** 查询周六记录列表 */
    getSaturdayLogList() {
      listSaturdayLogsByMonth({date: this.selectMonth, pageNum: 1, pageSize: 40}).then(response => {
        if (response.total > 0) {

          let tmpArray = []
          response.rows.forEach(item => {
            let thisArray = item.userList.split(',')
            tmpArray = tmpArray.concat(thisArray)
          })
          this.createEcharts(tmpArray)
        }
      });
    },
    searchData() {
      let thisMonth = moment(new Date()).format("yyyy-MM")
      if (this.selectMonth === "") {
        this.selectMonth = thisMonth
      }
      if (this.countType === "everyDayMark") {
        this.getEveryDayLogList()
      } else {
        this.getSaturdayLogList()
      }
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
    getUserNameById(id) {
      let name = ""
      this.userListOption.forEach(
        item => {
          if (item.dictValue == id) {
            name = item.dictLabel
          }
        }
      )
      return name
    },
    exportPdf() {
      this.getPdf()
    }
  },
  mounted() {
    this.getDicts("sys_user_list").then(response => {
      let tmpArray = response.data
      this.userListOption = tmpArray
      this.searchData()
    });
  }
})
</script>
