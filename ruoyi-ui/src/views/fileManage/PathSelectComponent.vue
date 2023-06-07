<template>
    <div>
        <el-row :span="24">
            <el-col :span="18"><el-tag style="width:100%;height:35px;line-height:35px">{{selectPath}}</el-tag></el-col>
            <el-col :span="4">
                <el-button @click="sureMoveFIle" style="margin-left:30px" type="primary">
                确定
                </el-button>
            </el-col>
        </el-row>
        
        
         <el-tag style="margin-top:5px;margin-right:10px" type="success" v-for="(item,index) in fileList" :key="index"  @click="clickPath(item)">{{item}}</el-tag>
         <el-tag style="margin-top:5px;margin-right:10px" type="success" @click="goToRootPath">跳至最上级</el-tag>
    </div>
</template>
<script>
import request from '@/utils/request'
export default ({
     name:"PathSelectComponent",
     props:{
            rootPath:{
                type:String,
                default:'D:'
            }
            },
     data(){
         return{
             selectPath:'D:',
             fileList:[],
             rootPath:'D:'
         }
     },
     watch:{
            rootPath:{ 
                immediate:true,
                handler:   function(newVal,oldVal){
                                this.fileName = newVal
                                this.getFileList()
                                }
            }
     },
     methods:{
        getFileList(directorName){
            let _this = this 
            let filePath = ""
            if(directorName === null || directorName === undefined || directorName === ""){
                filePath = "D:"
            }
            else{
                filePath = directorName.startsWith("D:") ?  directorName : _this.recentPath + directorName
            }
            request({
                        url: '/fileManage/getFileListByPath',
                        method: 'get',
                        params:{'path':filePath}
                    }).then(res =>{
                        debugger
                           if(res.length>0){ _this.fileList = res.filter(filterFileName)
                           }
                           else{
                               _this.$message.info("未发现子目录")
                           }

                    }) 
            //避免操作到系统文件，过滤掉含特殊字符的文件名        
            function filterFileName(fileName){
                let spec=/[,.<>{}~!@#$%^&*]/;
                return (!spec.test(fileName))
            }        
        },
            clickPath(val){
                let path = this.selectPath + '\\' + val
                this.selectPath = path
                this.getFileList(path)
            },
            goToRootPath(){
                this.selectPath = this.rootPath
                this.getFileList(this.rootPath)
            },
           //获取的目录列表没有id值，随机生成id值
            uuid() {
                var s = [];
                var hexDigits = "0123456789abcdef";
                for (var i = 0; i < 36; i++) {
                    s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
                }
                s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
                s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
                s[8] = s[13] = s[18] = s[23] = "-";

                var uuid = s.join("");
                return uuid;
            },  
            sureMoveFIle(){
                this.$emit("sureMoveFile",this.selectPath)
            }
     },
     mounted(){
         this.getFileList(this.rootPath)
     }
})
</script>
