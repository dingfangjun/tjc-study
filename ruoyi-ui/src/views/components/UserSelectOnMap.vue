<template>
  <div >
    <el-tag effect="dark" type="success" @click="setMapCenter" style="cursor:pointer;margin-top:5px;margin-left:5px;float:left">{{dictLabel}}<i class="el-icon-circle-check" v-show="showIcon" /></el-tag>
 
  </div>
</template>

<script>
export default {
    name:"UserSelectOnMap",
    props:{
        dictLabel:{
            type:String,
            default:''
        },
        dictValue:{
            type:String,
            default:''
        },
        remark:{
            type:String,
            default:''
        }
    },
    watch:{
        dictLabel:{ 
            immediate:true,
            handler:   function(newVal,oldVal){
                            }
        },
        dictValue:{ 
            immediate:true,
            handler:   function(newVal,oldVal){
                            }
        },        
        remark:{ 
            immediate:true,
            handler:   function(newVal,oldVal){
                                if(newVal){
                                    let tmpArray = newVal.split(",")
                                    if(tmpArray.length === 3){
                                        this.showIcon = true
                                    }
                                }
                            }
        }
    },
    data(){
        return {
           showIcon:false,
        }
    },
    methods:{
        setMapCenter(){
            if(this.remark){
                let tmpArray = this.remark.split(",")
                if(Array.isArray(tmpArray) && tmpArray.length===3 ){
                    this.$emit('setMapCenter', 19,tmpArray[1],tmpArray[2]);
                }
            }
        }
    },
    mounted(){

    }
}
</script>

<style>

</style>