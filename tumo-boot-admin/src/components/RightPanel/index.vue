<template>
  <div class="setting-drawer">
    <a-drawer
      width="300"
      placement="right"
      :closable="false"
      :visible="visible"
      :drawer-style="{ position: 'absolute' }"
      @close="onClose"
    >
      <slot />
      <div slot="handle" class="setting-drawer-index-handle" @click="toggle">
        <a-icon v-if="!visible" type="setting" />
        <a-icon v-else type="close" />
      </div>
    </a-drawer>
  </div>
</template>

<script>

export default {
  components: {
  },
  mixins: [],
  data() {
    return {
      visible: false
    }
  },
  watch: {},
  methods: {
    showDrawer() {
      this.visible = true
    },
    onClose() {
      this.visible = false
    },
    toggle() {
      this.visible = !this.visible
    }
  }
}
</script>

<style lang="less" scoped>

  .setting-drawer-index-handle {
    position: absolute;
    top: 240px;
    background: #1890ff;
    width: 48px;
    height: 48px;
    right: 300px;
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
    pointer-events: auto;
    z-index: 1001;
    text-align: center;
    font-size: 16px;
    border-radius: 4px 0 0 4px;

    i {
      color: rgb(255, 255, 255);
      font-size: 20px;
    }
  }
</style>
