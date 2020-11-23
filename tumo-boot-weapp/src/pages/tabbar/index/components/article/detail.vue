<template>
  <div>
    <van-skeleton title row="6" :loading="loading">
      <div class="post-header">
        <h1 class="post-title">
          {{ form.title }}
        </h1>
        <div class="post-data">
        <span class="leancloud-visitors">
          <span class="leancloud-visitors-count">0</span> Views
        </span>
          <time>{{ form.createTime }}</time>
          <a href="/">springboot</a>
        </div>
      </div>
      <div class="post-content">
        <p class="post-tags"></p>
        <wemark :md="form.content" link highlight selectable type="wemark"></wemark>
      </div>
      <Comments ref="comments" />
    </van-skeleton>
  </div>
</template>

<script>
import Comments from '../comments/index'
import VanSkeleton from '@/wxcomponents/vant/skeleton'
import wemark from '@/wxcomponents/wemark/wemark'
import {findByArticleId} from "@/api/article"

export default {
  name: "detail",
  components: {
    Comments,
    VanSkeleton,
    wemark
  },
  data() {
    return {
      form: {},
      loading: true
    }
  },
  onLoad() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      findByArticleId(this.$Route.query.id).then(res => {
        if (res.code === 200) {
          this.form = res.data
          this.loading = false
          this.$refs.comments.fetchData(this.$Route.query.id)
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
