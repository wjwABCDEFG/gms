<template>
  <div id="banner">
    <!--动态将图片轮播图的容器高度设置成与图片一致-->
    <el-carousel :height="bannerHeight + 'px'">
      <!--遍历图片地址,动态生成轮播图-->
      <el-carousel-item v-for="item in img_list" :key="item">
        <img :src="item" alt />
      </el-carousel-item>
    </el-carousel>
  </div>
</template>
<script>
export default {
  name: "Banner",
  data() {
    return {
      // 图片地址数组
      img_list: [
        "https://www.gdou.edu.cn/__local/1/51/6F/EC2EDC0699DAC3EF3834276A525_6656F7F0_175ED.jpg",
        "https://www.gdou.edu.cn/__local/C/DC/68/7B106189F47CC99111322C624FF_27821928_CB67C.jpg",
        "https://www.gdou.edu.cn/__local/A/BE/03/82217B4436F1721E2E04DBECCF6_F90A9F52_154F8.jpg",
        "https://www.gdou.edu.cn/__local/C/1E/B0/0BB7F898C026CA90FE63CD64235_C4315AC8_284D9.jpg?e=.jpg"
      ],
      // 图片父容器高度
      bannerHeight: 1000,
      // 浏览器宽度
      screenWidth: 0
    };
  },
  methods: {
    setSize: function() {
      // 通过浏览器宽度(图片宽度)计算高度
      this.bannerHeight = (700 / 1920) * this.screenWidth;
    }
  },
  mounted() {
    // 首次加载时,需要调用一次
    this.screenWidth = window.innerWidth;
    this.setSize();
    // 窗口大小发生改变时,调用一次
    window.onresize = () => {
      this.screenWidth = window.innerWidth;
      this.setSize();
    };
  }
};
</script>
<style lang="less" scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
img {
  /*设置图片宽度和浏览器宽度一致*/
  width: 100%;
  height: inherit;
}
</style>