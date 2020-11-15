<template>
  <div v-if="list.length > 0" class="main-content index-page clearfix ">
    <div class="post-lists">
      <div class="post-lists-body">
        <div v-for="i in list" :key="i.id" class="post-list-item">
          <div class="post-list-item-container">
            <div class="item-thumb bg-yellow" :style="'background-image:url(' + i.cover + ');'"></div>
            <div @click="handleReadMore(i.id)">
              <div class="item-desc">
                <p>{{ i.des }}</p>
              </div>
            </div>
            <div class="item-slant reverse-slant bg-blue"></div>
            <div class="item-slant"></div>
            <div class="item-label">
              <div class="item-title">
                <div @click="handleReadMore(i.id)">{{ i.title }}</div>
              </div>
              <div class="item-meta clearfix">
                <div class="item-meta-date" style="color: #313131; padding-top: 10px; font-size: 13px;">
								<span class="leancloud-visitors">
									<span class="leancloud-visitors-count">0</span> Views
								</span>
                </div>
                <div class="item-meta-ico bg-ico-game"
                     style="background: url(https://tycoding.cn/images/bg-ico.png) no-repeat;background-size: 40px auto;"></div>
                <div class="item-meta-cat">
                  <div @click="handleReadMore(i.id)">{{ i.tags }}</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import VanCell from '@/wxcomponents/vant/cell'
import VanTag from '@/wxcomponents/vant/tag'

export default {
  components: {
    VanCell,
    VanTag
  },
  name: "index",
  props: {
    list: {
      type: Array,
      required: true
    }
  },
  methods: {
    handleReadMore(id) {
      this.$Router.push({
        name: 'ArticleDetail',
        params: {id: id}
      })
    }
  }
}
</script>

<style scoped>
</style>
