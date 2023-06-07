<template>
    <div>
        <div class="line-text-box" style="position:relative" >
            <div @click="showDetailPoi">
            <span style="display:block;float:left;width:18px">{{indexNumber}}.</span><span >名称：</span><span :title="itemData.name">{{itemData.name}}</span><br>
            <span class="titlename">类型：</span><span :title="itemData.type">{{itemData.type}}</span><br>
            </div>

            <div @click="sureSelect" icon="ok" :size="'small'" style="width:45px;height:55px;position:absolute;right:0px;top:0px;padding:5px 6px;background-color:rgb(67,208,202);color:#ffff00">
                <span>确认</span><br>
                <span>选择</span>
            </div>
        </div>
    </div>
</template>
<script>
export default ({
 name:'PoiItemContent',
 props:{
     indexNumber:{
         type:Number,
         default:0
     },
     itemData:{
         type:Object,
         default:{
             name:"名称",
             type:"类型",
             address:"地址"
         }
     }
 },
 data(){
     return {

     }
 },
 methods:{
     showDetailPoi(){
         this.$emit("showDetailPoi",this.itemData)
     },
     sureSelect(){
         this.$emit("sureSelect",this.itemData)
     }
 }
})
</script>
<style scoped>
 .line-text-box{
    width:320px;
    height:55px;
    font-size:14px;
    border:1px solid rgb(33,164,211);
    border-radius:5px;
    background:linear-gradient(to right,rgb(60,158,254), rgb(53,117,249));
    margin-top:8px;
    padding:5px;
    color:#fff;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
}
.line-text-box span{
     cursor:pointer
 }
 .titlename{
     margin-left:18px
 }
</style>
