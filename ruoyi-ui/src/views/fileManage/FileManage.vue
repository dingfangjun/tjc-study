<template>
    <div>
        <div style="padding-top:10px">
        <span style="margin-left:20px; word-spacing:16px"><span style=" word-spacing:16px"> 当前路径：</span><el-tag><span>{{recentPath}}</span></el-tag></span>
        <el-button style="margin-left:20px;" @click="getFileList('')">返回最上层</el-button>
        <el-button @click="getFileTree">测试移动</el-button>
        <el-button @click="addFile">测试上传</el-button>
        </div>
        <el-dialog title="文件上传"
            :visible.sync="dialogVisible"
            width="30%"
        >
        <el-form
        ref="ruleForm"
        label-position="right"
        label-width="80px"
      >
        <el-form-item label="上传文件:">
                <el-upload
                    class="upload-demo"
                    ref="upload"
                    action="111"
                    :multiple="false"
                    :on-change="photoHandleAvatarSuccess"
                    :auto-upload="false">
                    <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                </el-upload>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit()"
            >提交</el-button
          >
        </el-form-item>
        </el-form>
        </el-dialog>
        <file-item v-for="(item,index) in fileNameList" :key="index" :parentFileName="item" @dbClickFile="dbClickFile" @deleteFile="deleteFile" @reNameFileName="reNameFileName" @sureMoveFile="sureMoveFile"></file-item>
    </div>
</template>
<script>
import axios from 'axios'
import { getToken } from '@/utils/auth'
import request from '@/utils/request'
import uploadAction from '@/utils/request'
let baseURL = process.env.VUE_APP_BASE_API
import FileItem from './FileItem.vue'
export default ({
    name:"FileManage",
    components:{FileItem},
    data(){
        return {
            dialogVisible:false,
            //选择的文件和文件名
            selectedFileList:[],
            selectedFileName:'',
            fileNameList:[],
            recentPath:'',

        }
    },
    methods:{
        downloadFile(filePath,fileName){
        let queryData = {filePath:filePath,fileName:fileName}
        request({
                    url: '/fileManage/downloadFile',
                    method: 'get',
                    params:queryData,
                    headers:{
                        'Content-Type': 'application/json; charset=UTF-8',
                    },
                    responseType:'blob'
                }).then(response =>{

                    var blob = new Blob([response])
                    var downloadElement = document.createElement('a');
                    var href = window.URL.createObjectURL(blob); //创建下载的链接
                    downloadElement.href = href;
                    downloadElement.download = fileName; //下载后文件名
                    document.body.appendChild(downloadElement);
                    downloadElement.click(); //点击下载
                    document.body.removeChild(downloadElement); //下载完成移除元素
                    window.URL.revokeObjectURL(href); //释放掉blob对象
                    console.log(response);
                })
        },
        getFileList(directorName){
            let _this = this
            let filePath = ""
            if(directorName === null || directorName === undefined || directorName === ""){
                filePath = "C:\\resources"
            }
            else{
                filePath = directorName.startsWith("C:") ?  directorName : _this.recentPath + directorName
            }
            request({
                        url: '/fileManage/getFileListByPath',
                        method: 'get',
                        params:{'path':filePath}
                    }).then(res =>{

                           if(res.length>0){
                            _this.recentPath = filePath.endsWith('\\') ?  filePath : filePath+'\\'
                            _this.fileNameList = res.filter(filterFileName)
                           }
                           else{
                               _this.$message.info("未发现文件")
                           }

                    })
            //避免操作到系统文件，过滤掉含特殊字符的文件名
            function filterFileName(fileName){
                let spec=/[,<>{}~!@#$%^&*]/;
                return (!spec.test(fileName))
            }
        },
        addFile(){
            this.dialogVisible = true
        },
        onSubmit() {
            let _this =this
            let fileSafety = checkFileSafety(_this.selectedFileName)
            if(fileSafety){
                _this.$message.info("上传音频视频时，请勿中途取消或关闭弹窗！")
                let fd = new FormData();
                fd.append("multipartFiles", _this.selectedFileList[0]);
                fd.append("filePath", _this.recentPath+_this.selectedFileName);
                axios({
                    url:baseURL +'/fileManage/addFile',
                    method:'post',
                    data:fd,
                    headers:{
                                'Content-Type': 'multipart/form-data', // 文件上传
                                'Authorization': 'Bearer ' + getToken()
                            }
                }).then(res =>{
                    _this.dialogVisible = false
                    _this.getFileList(_this.recentPath)
                })
            }
            function checkFileSafety(fileName){
                let res = false
                let fileTypeArray = ['mp3','jpg','png']
                let tmpArray = fileName.split('.')
                if(tmpArray.length === 2 && fileTypeArray.indexOf(tmpArray[1])>-1){
                    res = true
                }
                if(res == false){
                    _this.$message.info("文件格式不符合要求！")
                }
                return res
            }
        },
        getFileTree(){
            let filePath  = this.recentPath
            request({
                        url: '/fileManage/getFileListByPathTemp',
                        method: 'get',
                        params:{'filePath':filePath}
                    }).then(res =>{

                    })
        },
        photoHandleAvatarSuccess(file, fileList) {
            this.selectedFileList=[]
            for (let i = 0; i < 1; i++) {
                let item = fileList[i];

                this.selectedFileName = item.name
                this.selectedFileList.push(item.raw);

            }
        },
        dbClickFile(option){

            //文件类型director文件夹  img图片    movie视频   audio音频   excel表格   word文档   zip压缩文件   file（未知类型）文件
            let {fileType,fileName} = option
            if(fileType === 'director'){
                this.getFileList(fileName)
            }
            else{
                this.downloadFile(this.recentPath,fileName)
            }

        },
        reNameFileName(option){

            //文件类型director文件夹  img图片    movie视频   audio音频   excel表格   word文档   zip压缩文件   file（未知类型）文件
            let {fileName,newFileName} = option
            let oldPathName = this.recentPath + fileName

            let tmpSplitArray  =fileName.split(".")
            let newName = ""
            if(Array.isArray(tmpSplitArray) && tmpSplitArray.length===2){
                newName = newFileName+"."+tmpSplitArray[1]
            }
            else{
               newName = newFileName
            }
                let data ={filePath:oldPathName,newName:newName}
                request({
                        url: '/fileManage/reNameFile',
                        method: 'get',
                        params:data
                    }).then(res =>{
                        if(res){
                            this.$message.info("重命名成功")
                            this.getFileList()
                        }
                    })
        },
        deleteFile(option){
            let {fileName} = option
            let oldPathName = this.recentPath + fileName
                let data ={filePath:oldPathName}
                request({
                        url: '/fileManage/deleteFile',
                        method: 'get',
                        params:data
                    }).then(res =>{
                        if(res){
                            this.$message.info("删除成功")
                            this.getFileList()
                        }
                    })
        },
        sureMoveFile(option){
            let {fileName,targetPath} = option
            let oldPathName = this.recentPath + fileName
            let data ={recentPath:oldPathName,targetPath:targetPath}
            request({
                        url: '/fileManage/moveFileTo',
                        method: 'get',
                        params:data
                    }).then(res =>{
                        if(res){
                            this.$message.info("移动文件成功")
                            this.getFileList()
                        }
                    })
        }
    },
    mounted(){
        this.getFileList("")
    }
})
</script>
