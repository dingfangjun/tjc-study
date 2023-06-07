<template>
  <div>
    <el-row type="flex"  justify="left">
      <el-col :span='2'><el-button type="primary" @click="goBackOneStep">返回上一层</el-button></el-col>
      <el-col :span='1'><el-button type="primary">上传</el-button></el-col>
      <el-col :span='1'><el-button type="primary">下载</el-button></el-col>
      <el-col :span='1'><el-button type="primary">预览</el-button></el-col>
      <el-col :span='2'><el-button type="primary" @click="makeFolder">新建文件夹</el-button></el-col>
      <el-col :span='2'><el-button type="primary" @click="getFileList('/')">返回至最上层</el-button></el-col>
    </el-row>
    <el-row>
      <el-col><el-tag style="float:left;margin:10px 20px;"><span style="letter-spacing:2px">当前路径：{{tmpSearchPath}}</span></el-tag></el-col>
    </el-row>
    
    <el-card class="box-card">
    <div v-for="(item,index) in fileNameList" :key="index" class="file-item">
      <file-item :fileName='item' :pathName='tmpSearchPath' @openFolder='openFolder' @downloadFile='downloadFile'></file-item>
    </div>
    </el-card>
  </div>
</template>
<script>
import FileItem from '@/views/church/FileItem'
var axios = require('axios')
axios.defaults.headers.post['Content-Type'] = 'application/json';
export default ({
   name: 'FileClient',
   components:{FileItem},
  data () {
    return {  
      fileNameList:[],
      tmpSearchPath : '/'
    }
  },
  methods: {
      getFileList(path){
      //单斜杠替换双斜杆
      path  = path.replace(/\/\//g,'/');//'xbcdx'
      let _this = this
 //     let fullpath = 'http://localhost:8083/path' + path
     let fullpath = 'http://119.29.187.162:8083/path' + path
      axios
      .get(fullpath)
      .then(response => {
        _this.fileNameList = response.data
        _this.tmpSearchPath = path
        })
      },
      //下载文件
      downloadFile(fileName){
        debugger
        let _this = this
        let tmpPath = _this.tmpSearchPath +'/'+fileName
        let fullpath = 'http://119.29.187.162:8083/path' + tmpPath
      //    let fullpath = 'http://localhost:8083/path' + tmpPath
        debugger
          axios({method:'GET',url:fullpath,responseType:'blob'})
          .then(res => {
              let name = fileName
              let fileDownload = require('js-file-download');
              fileDownload(res.data, name);
            })
      },
    goBackOneStep(){
        let _this = this
        let resultPath = '/'
        let tmpArr = _this.tmpSearchPath.split('/')
        if(tmpArr.length > 1){
          for (let i=0;i<tmpArr.length-1;i++){
          let item = tmpArr[i]
          resultPath = resultPath + '/'+item
          }
          //单斜杠替换双斜杆
          resultPath  = resultPath.replace(/\/\//g,'/');//'xbcdx'
        }
        _this.getFileList(resultPath)
    },
    //打开文件夹
    openFolder(folderName){
      let tmpPath = this.tmpSearchPath +'/'+folderName
      this.getFileList(tmpPath)
    },
    //新建文件夹
    makeFolder(){
      let _this = this
      let fullpath = 'http://localhost:8083/api/addDir'
    //   let fullpath = 'http://119.29.187.162:8083/api/addDir'
      let postData = this.$qs.stringify({
          path:_this.tmpSearchPath,
          name:'新建文件夹'
      });
      axios
      .post(fullpath,{
          path:_this.tmpSearchPath,
          name:'new-folder'
      })
      .then(result => {
        console.dir(result)
        })
    }
  },
  mounted(){
    this.getFileList('/')
  }
})
</script>
  <style>
   .file-item{
     width: 100px;
     height: 110px;
     float: left;
     margin-left: 30px;
     border: 1px solid #dddddd;
   }
  </style>
