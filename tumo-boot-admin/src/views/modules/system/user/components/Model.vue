<template>
  <div v-if="visible">
    <!-- 新增/修改弹窗 - Begin -->
    <a-modal
      title="新增/修改"
      :visible.sync="visible"
      :confirm-loading="editLoading"
      :destroy-on-close="true"
      @ok="handleSubmit"
      @cancel="handleClose"
    />
    <!-- 新增/修改弹窗 - End -->
  </div>

</template>

<script>
export default {
  name: 'Model',
  data() {
    return {
      visible: false,
      editLoading: true,
      form: {}
    }
  },
  methods: {
    /**
     * 子组件关闭（默认）：是否刷新Table（false）
     */
    handleClose() {
      this.visible = false
      this.$emit('refresh', false)
    },

    handleSubmit() {
      this.$refs.form.validate(valid => {
        if (valid) {
          alert('submit!')
          this.$emit('refresh', true)
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>

<style scoped>
.ant-upload-select-picture-card i {
  font-size: 32px;
  color: #999;
}

.ant-upload-select-picture-card .ant-upload-text {
  margin-top: 8px;
  color: #666;
}
</style>
