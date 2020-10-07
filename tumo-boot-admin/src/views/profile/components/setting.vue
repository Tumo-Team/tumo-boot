<template>
  <div class="app-container page-header-index-wide">
    <a-card :bordered="false" :body-style="{ padding: '16px 0', height: '100%' }" :style="{ height: '100%' }">
      <div class="account-settings-info-main" :class="{ 'mobile': isMobile !== 'desktop'}">
        <div class="account-settings-info-left">
          <a-menu
            v-model="current"
            :mode="isMobile !== 'desktop' ? 'horizontal' : 'inline'"
            :style="{ border: '0', width: isMobile !== 'desktop' ? '560px' : 'auto'}"
            type="inner"
            @select="onSelect"
          >
            <a-menu-item key="base">
              基本设置
            </a-menu-item>
            <a-menu-item key="safe">
              安全设置
            </a-menu-item>
          </a-menu>
        </div>
        <div class="account-settings-info-right">
          <div class="account-settings-info-title">
            <span>{{ pageTitle }}</span>
          </div>
          <base-set v-if="pageTitle === '基本设置'" />
          <safe-set v-if="pageTitle === '安全设置'" />
        </div>
      </div>
    </a-card>
  </div>
</template>

<script>
import safeSet from './safe-set'
import baseSet from './base-set'

export default {
  components: {
    baseSet,
    safeSet
  },
  data() {
    return {
      isMobile: this.$store.getters.device,
      mode: 'horizontal',
      current: ['base'],
      pageTitle: '基本设置'
    }
  },
  methods: {
    onSelect(val) {
      if (val.key === 'base') {
        this.pageTitle = '基本设置'
      }
      if (val.key === 'safe') {
        this.pageTitle = '安全设置'
      }
    }
  }
}
</script>

<style lang="less" scoped>
  .account-settings-info-main {
    width: 100%;
    display: flex;
    height: 100%;
    overflow: auto;

    &.mobile {
      display: block;

      .account-settings-info-left {
        border-right: unset;
        border-bottom: 1px solid #e8e8e8;
        width: 100%;
        height: 50px;
        overflow-x: auto;
        overflow-y: scroll;
      }

      .account-settings-info-right {
        padding: 20px 40px;
      }
    }

    .account-settings-info-left {
      border-right: 1px solid #e8e8e8;
      width: 224px;
    }

    .account-settings-info-right {
      flex: 1 1;
      padding: 8px 40px;

      .account-settings-info-title {
        color: rgba(0, 0, 0, .85);
        font-size: 20px;
        font-weight: 500;
        line-height: 28px;
        margin-bottom: 12px;
      }

      .account-settings-info-view {
        padding-top: 12px;
      }
    }
  }
</style>
