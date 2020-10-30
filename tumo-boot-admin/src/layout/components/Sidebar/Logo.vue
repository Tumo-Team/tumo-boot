<template>
  <div class="sidebar-logo-container" :class="{'collapse':collapse}">
    <transition name="sidebarLogoFade">
      <router-link v-if="collapse" key="collapse" class="sidebar-logo-link" to="/">
        <svg-icon v-if="logo" icon-class="tumo-team" style="width: 32px;height: 32px;" class="sidebar-logo" />
        <h1 v-else :class="'sidebar-title ' + theme">{{ title }} </h1>
      </router-link>
      <router-link v-else key="expand" class="sidebar-logo-link" to="/">
        <svg-icon v-if="logo" icon-class="tumo-team" style="width: 32px;height: 32px;" class="sidebar-logo" />
        <h1 :class="'sidebar-title ' + theme">{{ title }} </h1>
      </router-link>
    </transition>
  </div>
</template>

<script>
export default {
  name: 'SidebarLogo',
  props: {
    collapse: {
      type: Boolean,
      required: true
    },
    theme: {
      type: String,
      required: false,
      default: 'dark'
    }
  },
  data() {
    return {
      title: 'Tumo Boot',
      logo: '/logo.png'
    }
  }
}
</script>

<style lang="less" scoped>
.sidebarLogoFade-enter-active {
  transition: opacity 1.5s;
}

.sidebarLogoFade-enter,
.sidebarLogoFade-leave-to {
  opacity: 0;
}

.sidebar-logo-container {
  position: relative;
  width: 100%;
  height: 64px;
  line-height: 64px;
  text-align: center;
  overflow: hidden;

  & .sidebar-logo-link {
    height: 100%;
    width: 100%;

    & .sidebar-logo {
      width: 32px;
      height: 32px;
      vertical-align: middle;
      margin-right: 12px;
    }

    & .sidebar-title {
      display: inline-block;
      margin: 0;
      font-weight: 600;
      line-height: 50px;
      font-size: 21px;
      font-family: Avenir, Helvetica Neue, Arial, Helvetica, sans-serif;
      vertical-align: middle;
    }
    & .sidebar-title.dark {
      color: #fff
    }
    & .sidebar-title.light {
      color: #1890ff
    }
  }

  &.collapse {
    .sidebar-logo {
      margin-right: 0px;
    }
  }
}
</style>
