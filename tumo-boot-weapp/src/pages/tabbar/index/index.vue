<template>
  <div class="bg-grey">
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
import Article from './components/article/index'
import {articleFilterList} from "@/api/article"

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
      articleFilterList({}).then(res => {
        this.list = res.data
        this.loading = false
      })
    }
  }
}
</script>

<style scoped>


</style>
