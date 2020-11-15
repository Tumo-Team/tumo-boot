<template>
  <div>
    <van-skeleton title row="6" :loading="loading">
      <div class="post-header">
        <h1 class="post-title">
          {{ form.title }}
        </h1>
        <div class="post-data">
        <span id="https://tycoding.cn/2020/07/01/project/tumo-recode/" class="leancloud-visitors">
          <span class="leancloud-visitors-count">0</span> Views
        </span>
          <time>{{ form.createTime }}</time>
          <a href="/">springboot</a>
        </div>
      </div>
      <div id="post-content" class="post-content" itemprop="articleBody">
        <p class="post-tags"></p>
        <towxml :nodes="article"></towxml>
      </div>
    </van-skeleton>
    <van-skeleton title row="6" :loading="loading"/>
    <van-skeleton title row="6" :loading="loading"/>
  </div>
</template>

<script>
import VanSkeleton from '@/wxcomponents/vant/skeleton'
import towxml from '@/wxcomponents/towxml/towxml'
import {findByArticleId} from "@/api/article"

export default {
  name: "detail",
  components: {
    VanSkeleton,
    towxml
  },
  data() {
    return {
      form: {},
      loading: true,
      article: {}
    }
  },
  onLoad() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      findByArticleId(this.$Route.query.id).then(res => {
        this.form = res.data
        this.article = this.towXmlUtil(res.data.content, 'markdown')
        this.loading = false
      })
    }
  }
}
</script>

<style scoped>

</style>
