<template>
  <div class="app-container page-header-index-wide page-header-wrapper-grid-content-main">
    <a-row :gutter="24">
      <a-col :md="24" :lg="7">
        <a-card :bordered="false">
          <div class="account-center-avatarHolder">
            <div class="avatar">
              <img :src="avatar">
            </div>
            <div class="username">{{ name }}</div>
            <div class="bio">兴趣使然的Coder</div>
          </div>
          <div class="account-center-detail">
            <p>
              <i class="title" />交互专家
            </p>
            <p>
              <i class="group" />蚂蚁金服－某某某事业群－某某平台部－某某技术部－UED
            </p>
            <p>
              <i class="address" />
              <span>浙江省</span>
              <span>杭州市</span>
            </p>
          </div>
          <a-divider />
          <div class="account-tags">
            <div class="account-tags-title">标签</div>
            <a-tag>中二</a-tag>
            <a-tag><a href="https://tycoding.cn">Coder</a></a-tag>
            <a-tag closable>
              摸鱼
            </a-tag>
          </div>
        </a-card>
      </a-col>
      <a-col :md="24" :lg="17">
        <a-card
          style="width:100%"
          :bordered="false"
        >
          <a-tabs default-active-key="1">
            <a-tab-pane key="1" tab="Tab 1">
              Content of Tab Pane 1
            </a-tab-pane>
            <a-tab-pane key="2" tab="Tab 2" force-render>
              Content of Tab Pane 2
            </a-tab-pane>
            <a-tab-pane key="3" tab="Tab 3">
              Content of Tab Pane 3
            </a-tab-pane>
          </a-tabs>
        </a-card>
      </a-col>
    </a-row>
  </div>
</template>

<script>

import { mapGetters } from 'vuex'

export default {
  components: {
  },
  data() {
    return {

    }
  },
  computed: {
    ...mapGetters(['name', 'avatar'])
  },
  methods: {

  }
}
</script>

<style lang="less" scoped>
  .page-header-wrapper-grid-content-main {
    width: 100%;
    height: 100%;
    min-height: 100%;
    transition: 0.3s;

    .account-center-avatarHolder {
      text-align: center;
      margin-bottom: 24px;

      & > .avatar {
        margin: 0 auto;
        width: 104px;
        height: 104px;
        margin-bottom: 20px;
        border-radius: 50%;
        overflow: hidden;
        img {
          height: 100%;
          width: 100%;
        }
      }

      .username {
        color: rgba(0, 0, 0, 0.85);
        font-size: 20px;
        line-height: 28px;
        font-weight: 500;
        margin-bottom: 4px;
      }
    }

    .account-tags{
      .account-tags-title{
        margin-bottom: 12px;
        color: rgba(0,0,0,.85);
        font-weight: 500;
      }
    }

    .account-center-detail {
      p {
        margin-bottom: 8px;
        padding-left: 26px;
        position: relative;
      }

      i {
        position: absolute;
        height: 14px;
        width: 14px;
        left: 0;
        top: 4px;
        background: url(https://gw.alipayobjects.com/zos/rmsportal/pBjWzVAHnOOtAUvZmZfy.svg);
      }

      .title {
        background-position: 0 0;
      }
      .group {
        background-position: 0 -22px;
      }
      .address {
        background-position: 0 -44px;
      }
    }
  }
</style>
