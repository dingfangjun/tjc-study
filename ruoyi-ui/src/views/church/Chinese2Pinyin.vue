<template>
  <div>
    <el-row >
      <el-col :span="5">
        <el-button type="primary" class="el-col-item"  :size="'small'" @click="chinese2pinyin">转换拼音</el-button>
        <span class="el-col-item el-col-item-text">显示左侧：</span>
        <el-switch class="el-col-item el-col-item-text"
            v-model="isShowInput"
            active-color="#13ce66"
            inactive-color="#ff4949">
        </el-switch>
        <el-button class="el-col-item" type="primary" icon="el-icon-delete-solid"  :size="'small'" @click="clearWords"></el-button>
        <div class="el-col-item pdf-icon" type="primary"  title="导出PDF"  :size="'small'" @click="exportPdf"></div>
      </el-col>
    </el-row>
      <el-container>
        <el-aside width="300px" v-show="isShowInput">
          <div :style="{'height':'800px'}">
           <el-input
              type="textarea"
              :autosize="{ minRows: 30, maxRows: 40}"
              placeholder="请输入内容"
              v-model="textarea1">
            </el-input>
          </div>
        </el-aside>
        <el-main>
          <div :style="{'height':'800px'}" id="pdfDom">
            <div v-for="(item,i) in resultWord" :key="i" :style="{'float':'left','margin':'5px'}" >
              <div v-show="item.pinyin != ''" :style="{'width':'35px','height':'40px'}">
                <div :style="{'width':'35px','height':'20px','float':'left','margin':'0px','padding':'0px','text-align':'center'}">
                {{item.pinyin}}
              </div>
              <div :style="{'width':'35px','height':'20px','float':'left','margin':'0px','padding':'0px','text-align':'center'}">
                {{item.chinese}}
              </div>
              </div>
              <div v-show="item.pinyin == ''" :style="{'width':'5px','height':'40px'}">
                <div :style="{'width':'5px','height':'20px','float':'left','margin':'0px','padding':'0px','text-align':'center'}">
                {{item.pinyin}}
                </div>
                <div :style="{'width':'5px','height':'20px','float':'left','margin':'0px','padding':'0px','text-align':'center'}">
                  {{item.chinese}}
                </div>
              </div>

            </div>
          </div>
        </el-main>
      </el-container>
  </div>
</template>
<script>


export default ({
   name: 'Chinese2Pinying',
  data () {
    return {
      isShowInput:true,
      textarea1:'',
      resultWord:[],
    }
  },
      methods: {
        chinese2pinyin(){
      let _this = this
       var pinyin = require("chinese-to-pinyin")
       let tmpStr = _this.textarea1
       for (let i=0;i<tmpStr.length;i++){
         let res =  pinyin(tmpStr[i])
         _this.addWord(tmpStr[i],res)
       }
    },
    //一个一个添加带拼音的汉字
    addWord(chinese,pinyin){
      let item = chinese == pinyin ? {chinese:chinese,pinyin:''} : {chinese:chinese,pinyin:pinyin}
      this.resultWord.push(item)
    },
    //清空右侧内容
    clearWords(){
      this.resultWord = []
    },
    exportPdf(){
      let _this = this
      if(_this.resultWord && _this.resultWord.length>0){
        _this.getPdf("拼音")
      }
    }
  },
  mounted(){
    var pinyin = require("chinese-to-pinyin")
  //  alert(pinyin('是的'))
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
    .el-aside{
      background-color:rgba(24,144,255,0.5)
    }
    .el-main {
      background-color: #e9eef3;
      color: #333;
      height:820px;
    }
    .el-row {
    margin-top: 5px;
    margin-bottom: 5px;
  }
    .el-col {
      border-radius: 4px;
      margin-right:20px;
    }
    .el-col-item{
      margin-right:10px
    }
    .el-col-item-text{
      height:32px;
      line-height:32px
    }
    .pdf-icon{
    float:left;
    cursor: pointer;
    width: 32px;
    height:32px;  
    background: url('~@/assets/images/PDF.svg')  center no-repeat;
    
   background-size: cover;
}
  </style>
