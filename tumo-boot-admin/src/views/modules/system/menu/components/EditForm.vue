<template>
  <div v-if="visible">
    <!-- 新增/修改弹窗 - Begin -->
    <a-modal
      :title="form.id === undefined ? '新增' : '修改'"
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
        <a-form-model-item has-feedback prop="name" label="账户">
          <a-input v-model="form.name" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="parentId" label="账户">
          <a-input v-model="form.parentId" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="path" label="账户">
          <a-input v-model="form.path" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="perms" label="账户">
          <a-input v-model="form.perms" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="type" label="账户">
          <a-input v-model="form.type" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="icon" label="账户">
          <a-input v-model="form.icon" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="component" label="账户">
          <a-input v-model="form.component" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="hidden" label="账户">
          <a-input v-model="form.hidden" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="frame" label="账户">
          <a-input v-model="form.frame" />
        </a-form-model-item>
      </a-form-model>
    </a-modal>
    <!-- 新增/修改弹窗 - End -->
  </div>

</template>

<script>
import { addMenu, checkMenuName, findByMenuId, updateMenu } from '@/api/modules/system/menu'

export default {
  name: 'EditForm',
  data() {
    const validateName = (rule, value, callback) => {
      if (value === undefined || value.trim() === '') {
        callback(new Error('请输入名称'))
      } else {
        checkMenuName(this.form).then(res => {
          if (!res.data) {
            callback('当前名称已存在')
          }
          callback()
        })
      }
    }
    return {
      visible: false,
      loading: false,
      form: {},
      rules: {
        name: [{ validator: validateName, required: true, message: '请输入资源名称', trigger: 'blur' }],
        parentId: [{ required: true, message: '请输入父级ID', trigger: 'blur' }],
        path: [{ required: true, message: '请输入URL', trigger: 'blur' }],
        perms: [{ required: true, message: '请输入权限标识', trigger: 'blur' }],
        type: [{ required: true, message: '请输入类型：如button按钮 menu菜单', trigger: 'blur' }],
        icon: [{ required: true, message: '请输入菜单图标', trigger: 'blur' }],
        component: [{ required: true, message: '请输入Vue组件', trigger: 'blur' }],
        hidden: [{ required: true, message: '请输入是否隐藏', trigger: 'blur' }],
        frame: [{ required: true, message: '请输入是否是外链', trigger: 'blur' }]
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
      if (id !== undefined) {
        // 修改操作
        findByMenuId(id).then(res => {
          this.form = res.data
          this.visible = true
        })
      } else {
        this.visible = true
      }
    },

    handleSubmit() {
      this.loading = true
      this.$refs.form
        .validate(valid => {
          if (valid) {
            if (this.form.id === undefined || this.form.id === 0) {
              // 新增
              addMenu(this.form).then(res => {
                if (res.code === 200) {
                  this.$message.success('新增成功')
                  this.handleClose()
                  this.$emit('refresh')
                }
              })
            } else {
              // 修改
              updateMenu(this.form).then(res => {
                if (res.code === 200) {
                  this.$message.success('修改成功')
                  this.handleClose()
                  this.$emit('refresh')
                }
              })
            }
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
