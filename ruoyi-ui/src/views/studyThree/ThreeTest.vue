<template>
    <div>
        <div id="container" style="height:600px;width:100%"></div>
    </div>
</template>
<script>
import * as Three from 'three'
import {objloader} from '@/assets/utils/three/loader.js'
export default ({
    name:'ThreeTest',
    data(){
        return {
            scene:null,
            mesh:null,
            camera:null,
            renderer:null,
            group:null,
            loadObj:null
        }
    },
    methods:{
        init(){
            this.scene = new Three.Scene()
            let light = new Three.AmbientLight()
            let container = document.getElementById("container")
            this.camera = new Three.PerspectiveCamera(
                45,
                container.clientWidth / container.clientHeight,
                10,
                1000
            );
            this.camera.position.z = 80
            this.camera.position.x= 80
            this.camera.lookAt(0,0,0)
            this.scene.add(this.camera)
            this.scene.add(light)
            this.renderer = new Three.WebGLRenderer({ antialias: true })
         //   this.renderer.setClearColor(new Three.Color(0x666666, 1.0));
             this.renderer.setSize(container.clientWidth, container.clientHeight);
            container.appendChild(this.renderer.domElement)
            this.renderScene()
        },
        renderScene(){
            this.renderer.render(this.scene, this.camera);
        },
        addObjMtl(){
            let _this = this
            objloader("people").then(function(geo){

                // 加载后的一些编辑操作
                _this.loadObj = geo.obj
                geo.obj.scale.set(8,8,8);//网格模型缩放
                geo.obj.position.set(0,0,0)
                _this.scene.add(geo.obj);
                _this.renderScene()
            })
        },
        // 添加全局方法
        addmeth() {
            window.addEventListener("keydown", this.keydown, false);
        },
        keydown(e){

                let _this = this
                if(e.code === "ArrowLeft" && _this.loadObj){
                    _this.loadObj.rotation.y += (Math.PI/180)*5;
                    _this.renderScene()
                }
                else if (e.code === "ArrowRight" && _this.loadObj){
                    _this.loadObj.rotation.y -= (Math.PI/180)*5
                    _this.renderScene()
                }
        },
    // 监听鼠标移动
    mouseMove(event) {
      if (!this.isclick) {
        if (this.$refs.draw) {
          var list = this.countmouse(event);
          // 传出鼠标移动事件
          this.$emit("change", "move", list);
        }
      }
    },
    // 监听鼠标弹起
    mouseUp(event) {
      // 如果相等说明是点击事件
      if (
        (this.isclick.clientX === event.clientX) &
        (this.isclick.clientY === event.clientY)
      ) {
        // 传出点击事件
        this.isclick = null;
        if (this.$refs.draw) {
          var list = this.countmouse(event);
          this.$emit("change", "click", list);
        }
      } else {
        this.isclick = null;
      }
    },
    // 判断当前点击的事件
    countmouse(event) {
      let raycaster = new THREE.Raycaster();
      let mouse = new THREE.Vector2();
      //将鼠标点击位置的屏幕坐标转换成threejs中的标准坐标
      var dom = this.$refs.draw.getBoundingClientRect();
      if (
        event.clientX >= dom.left &&
        event.clientY >= dom.top &&
        event.clientX <= dom.left + this.$refs.draw.offsetWidth &&
        event.clientY <= dom.top + this.$refs.draw.offsetHeight
      ) {
        mouse.x = (event.clientX / window.innerWidth) * 2 - 1;
        mouse.y = -(event.clientY / window.innerHeight) * 2 + 1;
        // 通过鼠标点的位置和当前相机的矩阵计算出raycaster
        raycaster.setFromCamera(mouse, this.camera);
        // 获取raycaster直线和所有模型相交的数组集合
        var list = raycaster.intersectObjects(this.scene.children);
        if (list.length > 0) {
          return list;
        } else {
          return [];
        }
      } else {
        return [];
      }
    },
    // 设置动画
    tweenUpdate() {
      requestAnimationFrame(this.tweenUpdate);
      this.$tweener.update();
    },
    // 运动动画
    tweenobj(object, potion, time) {
      this.$tween.fade(
        object.position,
        potion,
        time,
        0,
        this.$tweener.Easing.Cubic.InOut
      )}

    },
    mounted(){
       this.init()
       this.addmeth()
    //   this.renderText()
     //  this.addSceneGroupGeo()
    //   this.animate()
    this.addObjMtl()

    }
})
</script>

<style scoped>

</style>
