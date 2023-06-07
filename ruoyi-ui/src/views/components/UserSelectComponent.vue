<template>
       <div>
           <el-row :gutter="20" style="margin:5px 0px; ">
               <el-col :span="16">
                   <el-input v-model="markListStr" clearable placeholder="请输入某日打卡记录智能解析"></el-input>
               </el-col>
               <el-col :span="6">
                   <el-button type="success" @click="getMarkUserList">解析</el-button>
               </el-col>
           </el-row>
        <el-checkbox-group v-model="userListArray" size="small"  style="margin-top:20px">
            <el-checkbox v-for="(item,index) in userListOptions" style="width:80px;margin-top:8px;margin-left:10px" :key="index" :label="item.dictValue" border>{{item.dictLabel}}</el-checkbox>
           
        </el-checkbox-group>
        </div>
</template>
<script>
import { listUser2} from "@/api/system/user";
export default ({
    name:'UserSelectComponent',
    props:{
        userListStr:{
            type:String,
            default:''
        }
    },
    data(){
        return {
            userListArray:[],
            userListOptions:[],
            markListStr:''
        }
    },
    watch:{
       userListStr:{ 
            immediate:true,
            handler:   function(newVal,oldVal){
                          let tmpArray = []
                          if(newVal && newVal.length > 0){
                              tmpArray = newVal.split(',')  
                          } 
                          this.userListArray = tmpArray
                        }
        },
        userListArray:{ 
            immediate:true,
            handler:   function(newVal,oldVal){
                        let tmpStr = newVal.toString()
                            this.$emit('change',tmpStr)
                        }
        },
    },
    model: {
    prop: "value",
    event: "change"
    },
    methods:{
        getMarkUserList(){
            
            debugger
            let _this = this
            let reg = /[0-9]{1,2}/;
            let tmpArr = _this.markListStr.split(reg);
            if(Array.isArray(tmpArr)&&tmpArr.length>1){
                let markIdList = []
                tmpArr.forEach(item=>{
                    if(item !==null && item !== 'undefined' && item !== ""){
                        let res = _this.getUserIdByName(item)
                        if(res && markIdList.indexOf(res+'')<0){
                            markIdList.push(res+'')
                        }
                        
                    }
                    
                })
                debugger
                if(markIdList.length>0){
                    //最后赋值给userListArray就行，自动监听处理
                    _this.userListArray = markIdList
                    _this.markListStr =""
                    debugger
                }
                
            }
            else{
                this.$message.info("文字格式不正确，参考格式： 1小明2小红3小方....")
            }
        },
        getUserIdByName(userName){
            let userId = null
            this.userListOptions.forEach(item =>{
                if(item.dictLabel === userName.trim()){
                    debugger
                    userId = item.dictValue
                }
            })
            if(!userId){
                this.$message.info("用户"+userName +"信息未录入系统，请及时更新")
            }
            return userId
        }
    },
    mounted(){
     this.getDicts("sys_user_list").then(response => {
        let tmpArray = response.data
        tmpArray.sort(function(a,b){
            return a.dictLabel.localeCompare(b.dictLabel)
        })
        this.userListOptions = tmpArray
        });

        listUser2({pageNum:1,pageSize:200}).then(response => {
            debugger
        })
  }
})
</script>
