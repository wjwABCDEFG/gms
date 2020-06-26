<template>
    <div>
        <div class="block">
            <!-- <span class="demonstration">默认 Hover 指示器触发</span> -->
            <el-carousel height="450px" type="card">
            <el-carousel-item v-for="item in images" :key="item">
                <img v-bind:src="item.src" class="image">
                <!-- <h4>{{item.title}}</h4> -->
            </el-carousel-item>
            </el-carousel>
        </div>
    </div>
</template>
<script>
export default {
    data(){
        return{
            images:[
                {src:require("../img/image1.jpeg"),title:"热烈祝贺成功举办第一届篮球杯"},
                {src:require("../img/image2.jpg"),title:"热烈祝贺成功举办第一届篮球杯"},
                {src:require("../img/image3.jpeg"),title:"热烈祝贺成功举办第一届篮球杯"},
                {src:require("../img/image4.jpg"),title:"热烈祝贺成功举办第一届篮球杯"},
            ]
        }
    }
}
</script>
<style lang="less" scoped>

.el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }

.el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
}
  
.el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
}

.image{
    width:850px;
    height:450px;
}

</style>