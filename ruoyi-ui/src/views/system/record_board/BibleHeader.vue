<template>
  <div>

  </div>
</template>

<script>
  import genJson from './bible.js'
export default {
  name:"BibleHeader",
  data(){
    return {
      chapterList:[]
    }
  },
  methods:{
    getData(){
       let tmpJson = genJson
       debugger
    }
  },
  mounted(){
    this.getData()
  }
}
</script>

<style>

</style>