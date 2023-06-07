<template>
  <div>
    <div v-if="fileName.indexOf('.') == -1" class="item-top-folder" @dblclick="openFolder"></div>
    <div v-if="(fileName.indexOf('.') != -1)&&(fileName.indexOf('.jpg') == -1 && fileName.indexOf('.png') == -1)" class="item-top-file" @dblclick="downloadFile"></div>
    <div v-if="(fileName.indexOf('.') != -1)&&(fileName.indexOf('.jpg') != -1 || fileName.indexOf('.png') != -1)" class="item-top-image" @dblclick="downloadFile"></div>
    {{fileName}}
  </div>
</template>
<script>

export default ({
   name: 'FileItem',
   props: {
     fileName: {
      type: String,
      required: true,
      default:''
    },
    pathName: {
      type: String,
      required: true,
      default:'/'
    },
  },
  data () {
    return {
    }
  },
    methods: {
      openFolder(){
        let folderName = this.fileName
        this.$emit('openFolder',folderName)
      },
      downloadFile(){
        let fileName = this.fileName
        this.$emit('downloadFile',fileName)
      }
  },
  mounted(){
  }
})
</script>
  <style>
.item-top-folder{
  width:90px;
  height:90px;
  margin-left: 5px;
  background: url(../../assets/img/folder.png) no-repeat;
  background-size:100% 100%;
}
.item-top-file{
  width:90px;
  height:90px;
  margin-left: 5px;
  background: url(../../assets/img/file.png) no-repeat;
  background-size:100% 100%;
}
.item-top-image{
  width:90px;
  height:90px;
  margin-left: 5px;
  background: url(../../assets/img/image.png) no-repeat;
  background-size:100% 100%;
}
  </style>
