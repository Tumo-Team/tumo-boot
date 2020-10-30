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
        <a-form-model-item has-feedback prop="name" label="部门名称">
          <a-input v-model="form.name" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="des" label="部门描述">
          <a-input v-model="form.des" />
        </a-form-model-item>
        <a-form-model-item prop="parentId" label="上级部门">
          <a-tree-select
            v-model="form.parentId"
            allow-clear
            style="width: 100%"
            :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
            :replace-fields="{title: 'name', key: 'id', value: 'id'}"
            :tree-data="deptTree"
            tree-default-expand-all
            placeholder="请选择上级部门"
          />
        </a-form-model-item>
      </a-form-model>
    </a-modal>
    <!-- 新增/修改弹窗 - End -->
  </div>

</template>

<script>
import { deptTree, findByDeptId, addDept, checkDeptName, updateDept } from '@/api/modules/system/dept'

export default {
  name: 'EditForm',
  data() {
    const validateName = (rule, value, callback) => {
      if (value === undefined || value.trim() === '') {
        callback(new Error('请输入部门名称'))
      } else {
        checkDeptName(this.form).then(res => {
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
        name: [{ validator: validateName, required: true, trigger: 'blur' }],
        des: [{ required: true, message: '请输入描述', trigger: 'blur' }]
      },
      deptTree: []
    }
  },
  methods: {
    handleClose() {
      this.visible = false
      this.loading = false
      this.form = {}
    },

    init(id, type) {
      // 部门Tree
      deptTree().then(res => {
        this.deptTree = res.data
      })
      if (type === 'child') {
        // 新增下级节点操作
        this.form.parentId = id
        this.visible = true
        return
      }
      if (id !== undefined) {
        // 修改操作
        findByDeptId(id).then(res => {
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
              addDept(this.form).then(res => {
                if (res.code === 200) {
                  this.$message.success('新增成功')
                  this.handleClose()
                  this.$emit('refresh')
                }
              })
            } else {
              // 修改
              updateDept(this.form).then(res => {
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
