<template>
    <div>
        <div  @contextmenu.prevent="clickRight()" ref="showPanel" style="position:relative;width:150px;height:150px;padding:10px;margin:10px;float:left;text-align:center;border:1px solid rgb(92,194,237);border-radius:5px;cursor:pointer" @dblclick="dbClickFile">
            
            <img  v-if="fileType === 'director'" style="width:60px;height:60px;margin:20px 35px 10px 35px" src="@/assets/images/director.png" />
            <img  v-if="fileType === 'img'" style="width:60px;height:60px;margin:20px 35px 10px 35px" src="@/assets/images/img.png" />
            <img  v-if="fileType === 'movie'" style="width:60px;height:60px;margin:20px 35px 10px 35px" src="@/assets/images/movie.png" />
            <img  v-if="fileType === 'audio'" style="width:60px;height:60px;margin:20px 35px 10px 35px" src="@/assets/images/audio.png" />
            <img  v-if="fileType === 'zip'" style="width:60px;height:60px;margin:20px 35px 10px 35px" src="@/assets/images/zip.png" />
            <img  v-if="fileType === 'file'" style="width:60px;height:60px;margin:20px 35px 10px 35px" src="@/assets/images/director.png" />
            <img  v-if="fileType === 'word'" style="width:60px;height:60px;margin:20px 35px 10px 35px" src="@/assets/images/word.png" />
            <img  v-if="fileType === 'excel'" style="width:60px;height:60px;margin:20px 35px 10px 35px" src="@/assets/images/excel.png" />
            <p><span style="" :title="fileName">{{fileName}}</span></p>
            <div v-if="showMenu" class="fileMenu" style="z-index:9;width:100px;height:108px;position:absolute;top:100px;left:50px;background-color:rgb(5,187,209);color:#fff;border:1px solid #666;border-radius:3px;">
                    <i class="el-icon-arrow-left"></i><span @click="showRenameDialog">重命名</span>
                    <el-divider></el-divider>
                    <i class="el-icon-arrow-left"></i><span @click="showFileMoveDialog">移动到</span>
                    <el-divider></el-divider>
                    <i class="el-icon-arrow-left"></i><span @click="deleteFile">删除</span>
                    <el-divider></el-divider>
                    <i class="el-icon-arrow-left"></i><span @click="copyResourceUrl">复制资源链接</span>
            </div>
            <el-dialog
                title="修改为"
                :visible.sync="reNameDialogVisible"
                width="15%"
                height="'100px'">
                <el-input  v-model="reNameString" ></el-input><el-button type="primary" style="margin-top:10px" @click="sureRename">确定</el-button>
            </el-dialog>
            <el-dialog
                title="移动到"
                :visible.sync="fileMoveDialogVisible"
                width="40%"
                height="'400px'">
                  <pathSelectComponent @sureMoveFile="sureMoveFile" ></pathSelectComponent>
            </el-dialog>
        </div>
    </div>
</template>
<script>
import PathSelectComponent from './PathSelectComponent.vue'
export default ({
    name:'FileItem',
    components:{PathSelectComponent},
    props:{
        parentFileName:{
            type:String,
            default:'文件名'
        }
    },
    data(){
        return {
            fileName:'文件名',
            fileType:'',
            showMenu:false,
            reNameDialogVisible:false,//显示重命名输入框
            fileMoveDialogVisible:false,//移动文件目录弹出框
            reNameString:'',//重命名的文件名
            selectPath:'',//选择到的移动文件目标目录
        }
    },
    watch:{
       parentFileName:{ 
        immediate:true,
        handler:   function(newVal,oldVal){
                           this.fileName = newVal
                           this.getFileType()
                          }
      }
    },
    methods:{
        getFileType(){
            let fileType = ''
            let fileName = this.fileName
            if( fileName.indexOf('.') === -1){
                fileType = 'director'
            }
            else{
                let tmpArray = fileName.split('.')
                let fileTypeStr = tmpArray[1]
                let imgTypeArray=['jpg','jpeg','gif','svg','png','psd']
                let movieTypeArray=['mp4','avi','wmv','mpeg','mp4','m4v','mov','asf','flv','f4v','rmvb','rm','3gp','vob']
                let audioTypeArray = ['wav','midi','cda','mp3','mp3pro','wma']
                let excelTypeArray=['xls','xlsx','xlsm','xlsb','csv']
                let wordTypeArray = ['wps','doc','docx']
                let zipTypeArray = ['rar','7z','zip']
                if(imgTypeArray.indexOf(fileTypeStr) !== -1){
                    fileType = 'img'
                }
                else if(movieTypeArray.indexOf(fileTypeStr) !== -1){
                    fileType = 'movie'
                }
                else if(audioTypeArray.indexOf(fileTypeStr) !== -1){
                    fileType = 'audio'
                }
                else if(excelTypeArray.indexOf(fileTypeStr) !== -1){
                    fileType = 'excel'
                }
                else if(wordTypeArray.indexOf(fileTypeStr) !== -1){
                    fileType = 'word'
                }
                else if(zipTypeArray.indexOf(fileTypeStr) !== -1){
                    fileType = 'zip'
                }
                else{
                    fileType = 'file'
                }
            }
            this.fileType =  fileType
        },
        dbClickFile(){
            let option ={fileName:this.fileName,fileType:this.fileType}
            this.$emit('dbClickFile',option)
        },
        clickRight(){
            this.showMenu = true
        },
        hideMenu(){
            this.showMenu = false
        },
        hideeReNameDialog(){
            this.reNameDialogVisible = false
        },
        showRenameDialog(){
            this.reNameDialogVisible = true
        },
        showFileMoveDialog(){
            this.fileMoveDialogVisible = true
        },
        hideFileMoveDialog(){
            this.fileMoveDialogVisible = false
        },
        //避免操作到系统文件，过滤掉含特殊字符的文件名        
         filterFileName(fileName){
                let spec=/[\s,<>.{}~!@#$%^&*]/;
                return (spec.test(fileName))
            } ,
        checkFormat( fileNameStr){
            let trimStr = fileNameStr.trim()
            let resBool = false
            if(trimStr.length === 0 ){
                this.$message.info("请录入文件名")
                resBool = false
            }
            else if(this.filterFileName(fileNameStr)){
                this.$message.info("文件名称不可含特殊符号")
                resBool = false
            }
            else{
                resBool = true
            }
            return resBool
        },
        sureRename(){
            let nameStr = this.reNameString.trim()
            if(this.checkFormat(nameStr)){
                alert ('重命名'+nameStr)
                this.reNameFileName()
            }
        },
        reNameFileName(){
            let option ={fileName:this.fileName,newFileName:this.reNameString}
            this.hideMenu()
            this.hideeReNameDialog()
            this.$emit('reNameFileName',option)
        },
        deleteFile(){
            let option ={fileName:this.fileName}
            this.$emit('deleteFile',option)
        },
        sureMoveFile(targetPath){
        let targetFile = targetPath +"\\"+this.fileName
        debugger
        let option ={fileName:this.fileName,targetPath:targetFile}
        this.hideFileMoveDialog()
        this.hideMenu()
        this.$emit('sureMoveFile',option)
        },
        copyResourceUrl(){
            let tmpFileName = this.fileName

                var input1 = document.createElement("input");   // 直接构建input
                input1.value = "http:\/\/119.29.187.162:9001\/"+ tmpFileName;  // 设置内容
                alert(input1.value)
                document.body.appendChild(input1)
                input1.select();   // 选择实例内容
                document.execCommand("Copy");   // 执行复制
                console.log(input1.value)
                document.body.removeChild(input1); // 删除临时实例
        },
    },
    mounted(){
        document.addEventListener('click',e =>{
            if(this.$refs.showPanel){
                let isSelf = this.$refs.showPanel.contains(e.target)
                if(!isSelf){
                    this.showMenu = false
                }
            }
        })
        document.addEventListener('contextmenu',e =>{
            if(this.$refs.showPanel){
                let isSelf = this.$refs.showPanel.contains(e.target)
                if(!isSelf){
                    this.showMenu = false
                }
            }
        })
    },
    model: {
        prop: "value",
        event: "change"
        }  
})
</script>
<style scoped>
p{
 white-space:nowrap;
 overflow:hidden;
 text-overflow:ellipsis;}
.el-divider--horizontal{
 margin:3px 0px;
}
</style>