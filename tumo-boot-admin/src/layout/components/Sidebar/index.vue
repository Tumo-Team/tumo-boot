<template>
  <a-layout-sider v-model="isCollapse" :theme="theme" width="256px" :trigger="null" collapsible>
    <logo v-if="showLogo" :theme="theme" :collapse="isCollapse" />
    <s-menu :collapsed="isCollapse" :theme="theme" :menu="permission_routes" style="padding: 16px 0px;" @select="onSelect" />
  </a-layout-sider>
</template>

<script>
import { mapGetters } from 'vuex'
import Logo from './Logo'
import variables from '@/styles/variables.less'
import SMenu from './Menu/index'

export default {
  components: {
    Logo,
    SMenu
  },
  data() {
    return { }
  },
  computed: {
    ...mapGetters([
      'permission_routes',
      'sidebar',
      'theme'
    ]),
    showLogo() {
      return this.$store.state.settings.sidebarLogo
    },
    variables() {
      return variables
    },
    isCollapse() {
      return !this.sidebar.opened
    }
  },
  methods: {
    onSelect(obj) {
      this.$emit('menuSelect', obj)
    }
  }
}
</script>
