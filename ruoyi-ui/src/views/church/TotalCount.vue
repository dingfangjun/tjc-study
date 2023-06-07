<template>
            <!-- 布局容器 -->
        <el-container>
          <el-header style="margin: 0px; padding: 5px;height:90px">
            <el-row>
              <el-col :span="22">
                <span>已录入:</span>
                <el-button
                  style="margin: 5px; padding: 8px"
                  size="mini"
                  type="primary"
                  v-for="(item,index) in markDatas"
                  :key="index"
                  @click="showDetail(item)"
                  >{{item.ind}}日</el-button
                >
              </el-col>
              <el-col :span="2">
                <el-button size="mini" type="success" @click="startCalc"
                  >开始计算</el-button
                >
              </el-col>
            </el-row>
          </el-header>
          <el-main style="margin: 0px; padding: 0px">
            <el-row :gutter="24" style="margin: 0px; padding: 0px">
              <el-col
                :span="16"
                style="margin: 0px; padding: 0px; height: 430px"
              >
                <div class="grid-content bg-purple">
                  <el-button
                    style="margin: 5px; padding: 8px"
                    size="mini"
                    type="success"
                    v-for="(item,index) in resultDat"
                    :key="index"
                    >{{item.name}}:{{item.count}}次</el-button
                  >
                </div>
              </el-col>
              <el-col
                :span="8"
                style="
                  margin: 0px;
                  padding: 0px;
                  height: 430px;
                  border: 1px solid #0088cc;
                "
              >
                <div
                  class="grid-content bg-purple"
                  style="text-align: left; padding: 15px"
                >
                  <span class="demonstration">请选择日期:</span>
                  <el-date-picker
                    v-model="dateTime"
                    type="date"
                    placeholder="选择日期"
                  >
                  </el-date-picker>
                  <div style="margin: 5px 0px; height: 300px">
                    <el-input
                      type="textarea"
                      :rows="2"
                      placeholder="请输入打卡人员"
                      v-model="textarea"
                      :autosize="true"
                    >
                    </el-input>
                  </div>
                  <el-button
                    type="primary"
                    icon="el-icon-check"
                    @click="addData"
                    >确定添加</el-button
                  >
                </div>
              </el-col>
            </el-row>
            <el-dialog
              title="打卡详情"
              :visible="dialogVisible"
              width="80%"
            >
			<div style="width:100%;height:200px">
              <el-row>
                <el-button
                  v-for="(item,index) in selectedData.arr"
                  :key="index"
                  type="primary"
                  style="margin: 3px; padding: 5px"
                  >{{item}}</el-button
                >
              </el-row>
			  <el-input
                v-show="editType == '1'"
                type="textarea"
                placeholder="请输入修改内容"
                v-model="editTextArea"
                maxlength="600"
                show-word-limit
              >
              </el-input>

			</div>
              <span slot="footer" class="dialog-footer">
				<el-row :gutter="24">
					<el-col :span="6">
					  <span>是否需要修改:</span>
					  </el-radio-group>
					</el-col>
					<el-col :span="10"></el-col>
					<el-col :span="6">
						<el-button @click="dialogVisible = false">取 消</el-button>
                		<el-button v-show="editType == '1'" type="primary" @click='editMarkDatas'>确定修改</el-button>
					</el-col>
				  </el-row>

              </span>
            </el-dialog>
          </el-main>
          <el-footer>
            <el-row>
              <span style="font-size: x-small;margin-left: 20px;">
              使用方法：1.在右侧选择日期，输入打卡人员，参考格式为 1张三2李四3王五...
            </span>
            <span style="font-size: x-small;margin-left: 20px;">
              2.在右上角点击开始计算，自动进行排序统计
            </span>
            <span style="font-size: x-small;margin-left: 20px;">
              3.如需修改，点击头部日期，修改文本点击确定后自动更新数据进行统计排序
            </span>
          </el-row>

          </el-footer>
        </el-container>
</template>
<script>


export default ({
   name: 'TotalCount',
  data () {
    return {
        dateTime: "",
          textarea: "",//新增数据的参数
          editTextArea:'',
          markDatas: [],
          resultDat: [],
          dialogVisible: false,
          selectedData: { arr: [], ind: -1 },
          editType:'1',
    }
  },
      methods: {
        addData() {
          let _this = this;
          let reg = /[0-9]{1,2}/;
          let tmpArr = this.textarea.split(reg);
          tmpArr = _this.deleteArrEmpty(tmpArr)
          let ind = _this.getDateIndex();
          let obj = { ind: "", arr: [] };
          obj.ind = ind;
          obj.arr = tmpArr;
          if(_this.checkData(obj)){
            _this.markDatas.push(obj);
            _this.dateTime = ''
            _this.textarea = ''
          }

          //		this.initData();
        },
        getDateIndex(data) {
          let _this = this;
          let time = _this.dateTime;
          if (data) {
            time = data;
          }
          let date = new Date(time);
          let d = date.getDate();
          let dateIndex = d;
          return dateIndex;
        },
        initData() {
          let _this = this;
          _this.markDatas = [];
        },
        editMarkDatas(){
          let _this =this
          let selectData = this.selectedData
          let ind = selectData.ind
          let reg = /[0-9]{1,2}/;
          let tmpArr = this.editTextArea.split(reg);
          tmpArr = _this.deleteArrEmpty(tmpArr)
          let arr = []
          let obj = {arr:[],ind:0}
              obj.ind = ind
              obj.arr = tmpArr

          for(let i= 0;i<_this.markDatas.length;i++){
            let item = _this.markDatas[i]
            if(item.ind == ind){
                arr.push(obj)
            }
            else{
              arr.push(item)
            }
          }
          _this.markDatas = arr

          this.startCalc()
          _this.dialogVisible = false
        },
        // 	handleClose(done) {
        //     this.$confirm('确认关闭？')
        //       .then(_ => {
        //         done();
        //       })
        //       .catch(_ => {});
        //   },
        checkData(obj) {
          let _this = this
          let markDatas = _this.markDatas
          if(obj.ind && obj.arr && (obj.arr.length>0) ){
            let bool = true
            if(markDatas && (markDatas.length>0)){
              for(let i=0;i<markDatas.length;i++){
                let item = markDatas[i]
                if(item.ind == obj.ind){
                  this.$message({
                  message:'该日期记录已添加',
                  type:'warning'
                  })
                  bool = false
                }
              }
            }
           return bool
          }
          else{
            this.$message({
              message:'请检测日期或文本是否已录入',
              type:'warning'
            })
            return false
          }


        },
        deleteArrEmpty(arr){
          let tmpArr = []
          if(arr && (arr.length>0)){
            for(let i=0;i<arr.length;i++){
              let item = arr[i]
              if(item && (item.length>0)){
                tmpArr.push(item)
              }
            }
          }
          return tmpArr
        },
        getCountOfArray(arr, item) {
          let count = 0;
          for (let i = 0; i < arr.length; i++) {
            if (item == arr[i]) {
              count = count + 1;
            }
          }
          return count;
        },
        showDetail(item) {
          let str = ''

          for(let i=0;i<item.arr.length;i++){
            let inde = i+1
            let strTmp = inde +item.arr[i]
            str = str+ strTmp
          }

          this.editTextArea = str
          this.selectedData = item;
          this.dialogVisible = true;
        },
        showEdit() {
          this.editType = '1';
        },
        startCalc() {
          let _this = this;
          let arrData = _this.markDatas;
          let str1 = [];
          let str2 = [];
          let resultData = [];
          for (let i = 0; i < arrData.length; i++) {
            let item = arrData[i];
            if (item.arr && item.arr.length > 0) {
              for (let j = 0; j < item.arr.length; j++) {
                let jtem = item.arr[j];
                str1.push(jtem);
              }
            }
          }
          let tmpData = str1.sort();
          str2.push(tmpData[0])
          if (tmpData.length > 1) {
            for (let i = 1; i < tmpData.length; i++) {
              if (tmpData[i] !== tmpData[i - 1]) {
                str2.push(tmpData[i]);
              }
            }
          }
          for (let i = 0; i < str2.length; i++) {
            let obj = { name: "", count: "" };
            let count = _this.getCountOfArray(str1, str2[i]);
            obj.name = str2[i];
            obj.count = count;
            resultData.push(obj);
            ;
          }
          resultData.sort(function (a, b) {
            return b.count - a.count;
          });
          ;
          _this.resultDat = resultData;
        },
    },
  mounted(){

  }
})
</script>
  <style>
    .el-header,
    .el-footer {
      background-color: #b3c0d1;
      color: #333;
      line-height: 40px;
    }

    .el-main {
      background-color: #e9eef3;
      color: #333;
      height: 430px;
    }
  </style>
