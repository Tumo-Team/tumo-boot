<template>
  <div class="article-container" v-if="list.length > 0">
    <div v-for="i in list" :key="i.id" class="article-item">
      <div class="article-item-title">{{ i.title }}</div>
      <div class="article-item-introduction">{{ i.introduction }}</div>
      <div class="article-item-time">{{ i.createTime }}</div>
      <div class="article-item-tags">
        <van-tag v-for="tag in i.tags" :key="tag" type="primary">{{ tag }}</van-tag>
      </div>
      <div class="article-item-read">
        <label @click="handleReadMore">READ MORE »</label>
      </div>
      <hr/>
      <br/>
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
    handleReadMore() {
      console.apiLog('read more')
    }
  }
}
</script>

<style scoped>
.article-container {
  overflow: hidden;
  border-radius: 8px;
  box-sizing: border-box;
  padding: 10px 16px;
  color: #323233;
  font-size: 14px;
  line-height: 25px;
  background-color: #fff;
}

.article-item {
  margin-bottom: 2px;
}

.article-item hr {
  margin: 0.2rem 0;
  height: 2px;
  border: none;
  background-color: #ddd;
  background-image: repeating-linear-gradient(-45deg, #fff, #fff 4px, transparent 4px, transparent 8px);
}

.article-item-title {
  margin-top: 5px;
  font-size: 20px;
  font-weight: 400;
  text-align: left;
  color: #4c4948;
  border-bottom: none;
  line-height: 1.2;
  vertical-align: top;
}

.article-item-introduction {
}

.article-item-time {
  font-size: 13px;
  color: #999;
}

.article-item-tags {
}

.article-item-tags van-tag {
  padding-right: 2px;
}

.article-item-read {
  font-size: 13px;
  background: 0 0;
  border-radius: 0;
  line-height: 2;
  margin-top: 5px;
}

.article-item-read label {
  color: #42b983;

}
</style>
