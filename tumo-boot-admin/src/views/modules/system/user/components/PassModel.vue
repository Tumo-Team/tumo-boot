<template>
  <div v-if="visible">
    <!-- 修改密码弹窗 - Begin -->
    <a-modal
      title="修改密码"
      :visible.sync="visible"
      :confirm-loading="loading"
      :keyboard="false"
      :mask-closable="false"
      width="40%"
      :destroy-on-close="true"
      @ok="handleSubmit"
      @cancel="handleClose"
    >
      <a-form-model
        ref="form"
        :model="form"
        :rules="rules"
        :label-col="{ span: 3 }"
        :wrapper-col="{ span: 21 }"
      >
        <a-form-model-item has-feedback prop="password" label="密码">
          <a-input v-model="form.password" type="password" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="repassword" label="确认密码">
          <a-input v-model="form.repassword" type="password" />
        </a-form-model-item>
      </a-form-model>
    </a-modal>
    <!-- 修改密码弹窗 - End -->
  </div>

</template>

<script>
import { resetPass } from '@/api/modules/system/user'

export default {
  name: 'PassModel',
  data() {
    return {
      visible: false,
      loading: false,
      form: {},
      rules: {
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
        repassword: [{ required: true, message: '请再次输入密码', trigger: 'blur' }]
      }
    }
  },
  methods: {
    handleClose() {
      this.visible = false
      this.loading = false
      this.form = {}
    },

    init(id) {
      this.visible = true
      this.form.id = id
    },

    handleSubmit() {
      this.loading = true
      if (this.form.password !== this.form.repassword) {
        this.$message.error('两次输入的密码不一致，请重新输入')
        this.loading = false
        return false
      }
      this.$refs.form.validate(valid => {
        if (valid) {
          // 修改
          resetPass(this.form).then(res => {
            if (res.code === 200) {
              this.$message.success('修改成功')
              this.handleClose()
              this.$emit('refresh')
            }
          })
        } else {
          this.loading = false
          this.$message.error('请填写表单内容')
          return false
        }
      })
    }
  }
}
</script>

<style scoped>
</style>
