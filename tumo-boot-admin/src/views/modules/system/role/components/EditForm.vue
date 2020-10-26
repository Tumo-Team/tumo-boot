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
        <a-form-model-item has-feedback prop="parentId" label="账户">
          <a-input v-model="form.parentId" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="name" label="账户">
          <a-input v-model="form.name" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="des" label="账户">
          <a-input v-model="form.des" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="createTime" label="账户">
          <a-input v-model="form.createTime" />
        </a-form-model-item>
      </a-form-model>
    </a-modal>
    <!-- 新增/修改弹窗 - End -->
  </div>

</template>

<script>
import { addRole, checkRoleName, findByRoleId, updateRole } from '@/api/modules/system/role'

export default {
  name: 'EditForm',
  data() {
    const validateName = (rule, value, callback) => {
      if (value === undefined || value.trim() === '') {
        callback(new Error('请输入名称'))
      } else {
        checkRoleName(this.form).then(res => {
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
        parentId: [{ required: true, message: '请输入上级节点', trigger: 'blur' }],
        name: [{ validator: validateName, required: true, message: '请输入角色名称', trigger: 'blur' }],
        des: [{ required: true, message: '请输入描述', trigger: 'blur' }],
        createTime: [{ required: true, message: '请输入创建时间', trigger: 'blur' }]
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
        findByRoleId(id).then(res => {
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
              addRole(this.form).then(res => {
                if (res.code === 200) {
                  this.$message.success('新增成功')
                  this.handleClose()
                  this.$emit('refresh')
                }
              })
            } else {
              // 修改
              updateRole(this.form).then(res => {
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
