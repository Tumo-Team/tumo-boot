<template>
  <div class="index-container">
    <van-skeleton title row="6" :loading="loading">
      <Article :list="list"/>
    </van-skeleton>
    <van-skeleton title row="6" :loading="loading"/>
    <van-skeleton title row="6" :loading="loading"/>
  </div>
</template>

<script>
import VanButton from '@/wxcomponents/vant/button'
import VanSkeleton from '@/wxcomponents/vant/skeleton'
import Article from '@/components/article/index'
import {titleList} from "@/api/article"

export default {
  components: {
    VanButton,
    VanSkeleton,
    Article
  },
  name: "index",
  data() {
    return {
      loading: true,
      list: []
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      titleList().then(res => {
        this.list = res.data
        this.loading = false
      })
    }
  }
}
</script>

<style scoped>
.index-container {
  background-color: #fff;
  margin: 12px 12px 0;
  min-height: -webkit-fill-available;
}

.index-container van-skeleton {
  padding-left: 10px;
}
</style>
