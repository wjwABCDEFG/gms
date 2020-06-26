<template>
  <div class="block">
    <el-carousel :interval="0" arrow="never" height="500px" type="card">
        <el-carousel-item v-for="item in dataimg" :key="item">
            <div class="grid-content">
                <el-col :md="12" :offset="6">
                    <div>
                        <img :src="item.src">
                    </div>
                </el-col>
            </div>
        </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
export default {
    data(){
        return{
            dataimg: [{
                    src: require('../assets/img/1.jpg'),
                },
                {
                    src: require('../assets/img/2.jpg'),
                },
                {
                    src: require('../assets/img/3.jpg'),
                }
            ]
        }
    }
}
</script>

<style>
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
</style>