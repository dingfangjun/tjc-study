<template>
  <div  style="background: url(https://images.unsplash.com/photo-1509599589979-3b5a15d2816e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=2000&q=80) 0 / cover fixed;">
    <el-row style="margin-top:5px" :gutter="20">
      <el-col :span="1.3" :key="index" v-for="(item,index) in chapterList" style="margin-top:5px">
        <el-button :type="item.chapterName===tmpChapterName?'success':'primary'" @click="reRenderData(item,index)">{{item.chapterName}}</el-button>
      </el-col>
    </el-row>
    <el-row :gutter="20" >
      <el-col :span="0.6" :key="index" v-for="(item,index) in tmpMaxParagraph" style="margin-top:5px">
        <el-button :type="item==tmpParagraph?'success':'info'" @click="paragraphChange(item)">第{{item}}章</el-button>
      </el-col>
    </el-row>
    <div style="width:100%;height:90vh;overflow-y: scroll;" >
      <div class="main">
        <blockquote>
          <span :key="index" v-for="(i,index) in tmpChapterData" >
            <span v-if="index.split(':')[0]==tmpParagraph">{{index}}</span>
            <span v-if="index.split(':')[0]==tmpParagraph">{{i}}</span>
            <!-- <span>{{tmpChapterData[i]}}</span> -->
          </span>



          <footer>—
            <cite>
              {{tmpChapterName}}第{{tmpParagraph}}章
            </cite>
          </footer>
        </blockquote>
      </div>
    </div>
  </div>
</template>

<script>
import BibleHeader from './BibleHeader.vue';
import {genJson,exo,lev,num,deu,jos,jug,rut,sa1,sa2,ki1,ki2,ch1,ch2,ezr,neh,est,job,psm,pro,ecc,son,isa,jer,lam} from './bible.js'
export default {
  components: {BibleHeader },
  name: "Record_board",
  data() {
    return {
      chapterList:[{chapterName:"创世记",data:genJson,maxParagraph:50},{chapterName:"出埃及记",data:exo,maxParagraph:40},{chapterName:"利未记",data:lev,maxParagraph:40},{chapterName:"民数记",data:num,maxParagraph:35}
                    ,{chapterName:"申命记",data:deu,maxParagraph:40},{chapterName:"约书亚记",data:jos,maxParagraph:40},{chapterName:"士师记",data:jug,maxParagraph:40},{chapterName:"路得记",data:rut,maxParagraph:40},
                    {chapterName:"撒母耳记上",data:sa1,maxParagraph:40},{chapterName:"撒母耳记下",data:sa2,maxParagraph:29},{chapterName:"列王纪上",data:ki1,maxParagraph:29},{chapterName:"列王纪下",data:ki2,maxParagraph:40}
                    ,{chapterName:"历代志上",data:ch1,maxParagraph:40},{chapterName:"历代志下",data:ch2,maxParagraph:40},{chapterName:"以斯拉记",data:ezr,maxParagraph:29},{chapterName:"尼希米记",data:neh,maxParagraph:40}
                    ,{chapterName:"以斯帖记",data:est,maxParagraph:40},{chapterName:"约伯记",data:job,maxParagraph:40},{chapterName:"诗篇",data:psm,maxParagraph:29},{chapterName:"箴言",data:pro,maxParagraph:40}
                    ,{chapterName:"传道书",data:ecc,maxParagraph:40},{chapterName:"雅歌",data:son,maxParagraph:40},{chapterName:"以赛亚书",data:isa,maxParagraph:29},{chapterName:"耶利米书",data:jer,maxParagraph:40}
                    ,{chapterName:"耶利米哀歌",data:lam,maxParagraph:40}
                    // ,{chapterName:"以西结书",data:eze,maxParagraph:40},{chapterName:"但以理书",data:dan,maxParagraph:29},{chapterName:"何西阿书",data:hos,maxParagraph:40}
                    // ,{chapterName:"约珥书",data:joe,maxParagraph:40},{chapterName:"阿摩司书",data:amo,maxParagraph:40},{chapterName:"俄巴底亚书",data:oba,maxParagraph:29},{chapterName:"约拿书",data:jon,maxParagraph:40}
                    // ,{chapterName:"弥迦书",data:mic,maxParagraph:40},{chapterName:"那鸿书",data:nah,maxParagraph:40},{chapterName:"哈巴谷书",data:hab,maxParagraph:29},{chapterName:"西番雅书",data:zep,maxParagraph:40}
                    // ,{chapterName:"哈该书",data:hag,maxParagraph:40},{chapterName:"撒迦利亚书",data:zec,maxParagraph:40},{chapterName:"玛拉基书",data:mal,maxParagraph:29},{chapterName:"马太福音",data:mat,maxParagraph:40}
                    // ,{chapterName:"马可福音",data:mak,maxParagraph:40},{chapterName:"路加福音",data:luk,maxParagraph:40},{chapterName:"约翰福音",data:jhn,maxParagraph:29},{chapterName:"使徒行传",data:act,maxParagraph:40}
                    // ,{chapterName:"罗马书",data:rom,maxParagraph:40},{chapterName:"哥林多前书",data:co1,maxParagraph:40},{chapterName:"哥林多后书",data:co2,maxParagraph:29},{chapterName:"加拉太书",data:gal,maxParagraph:40}
                    // ,{chapterName:"以弗所书",data:eph,maxParagraph:40},{chapterName:"腓立比书",data:phl,maxParagraph:40},{chapterName:"歌罗西书",data:col,maxParagraph:29},{chapterName:"帖撒罗尼迦前书",data:ts1,maxParagraph:40}
                    // ,{chapterName:"帖撒罗尼迦后书",data:ts2,maxParagraph:40},{chapterName:"提摩太前书",data:ti1,maxParagraph:40},{chapterName:"提摩太后书",data:ti2,maxParagraph:29},{chapterName:"提多书",data:tit,maxParagraph:40}
                    // ,{chapterName:"腓利门书",data:phm,maxParagraph:40},{chapterName:"希伯来书",data:heb,maxParagraph:40},{chapterName:"雅各书",data:jas,maxParagraph:29},{chapterName:"彼得前书",data:pe1,maxParagraph:40}
                    // ,{chapterName:"彼得后书",data:pe2,maxParagraph:40},{chapterName:"约翰一书",data:jn1,maxParagraph:40},{chapterName:"约翰二书",data:jn2,maxParagraph:29},{chapterName:"约翰三书",data:jn3,maxParagraph:40}
                    // ,{chapterName:"犹大书",data:jud,maxParagraph:40},{chapterName:"启示录",data:rev,maxParagraph:40}
                  ],
      tmpChapterName:"创世记",
      tmpChapterData:genJson,
      tmpMaxParagraph:50,
      tmpParagraph:1
    };
  },
  methods: {
    reRenderData(item,index){

      this.tmpChapterName = item.chapterName
      this.tmpChapterData = item.data
      this.tmpMaxParagraph = item.maxParagraph
    },
    paragraphChange(number){

      this.tmpParagraph = number
    }
  },
  mounted(){
  }
};
</script>
<style scoped>

  .main {
    margin: 100px auto;
    position: relative;
    padding: 10px 5px;
    background: hsla(0,0%,100%,.6);
    font-size: 20px;
    font-family: 'Lora', serif;
    line-height: 1.5;
    border-radius: 10px;
    width: 60%;
    box-shadow: 5px 3px 30px black;
    overflow: hidden;
  }

  .main::before {
    content: '';
    margin: -35px;
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    filter: blur(6px);
    z-index: -1;
  }

  blockquote {
    font-style:  normal;
    font-weight: bold;
  }

  footer {
    padding-top: 20px;
    font-weight: bold;
  }

  cite {
    font-style: italic;
    font-size: 22px;
  }

  @import url(https://fonts.googleapis.com/css?family=Lora);
</style>
