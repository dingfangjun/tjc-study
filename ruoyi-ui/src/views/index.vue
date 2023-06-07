<template>
  <div class="app-container home">
    

      <category-bar></category-bar>    
  </div>
</template>

<script>
import echarts from 'echarts'
import CategoryBar from './CategoryBar.vue'
export default {
  components: { CategoryBar },
  name: "Index",
  data() {
    return {
    };
  },
  methods: {
  },
};
</script>

<style scoped lang="scss">

</style>

