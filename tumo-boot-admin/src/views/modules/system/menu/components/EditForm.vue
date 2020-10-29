<template>
  <div v-if="visible">
    <!-- 新增/修改弹窗 - Begin -->
    <a-drawer
      :title="form.id === undefined ? '新增' : '修改'"
      :visible.sync="visible"
      placement="right"
      :keyboard="false"
      :mask-closable="false"
      width="40%"
      :destroy-on-close="true"
      @cancel="handleClose"
    >
      <a-form-model
        ref="form"
        :model="form"
        :rules="rules"
        :label-col="{ span: 4 }"
        :wrapper-col="{ span: 20 }"
      >
        <a-form-model-item has-feedback prop="name" label="菜单名称">
          <a-input v-model="form.name" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="path" label="菜单路径">
          <a-input v-model="form.path" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="perms" label="权限标识">
          <a-input v-model="form.perms" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="type" label="菜单类型">
          <a-radio-group v-model="form.type" name="type">
            <a-radio value="menu">菜单</a-radio>
            <a-radio value="button">按钮</a-radio>
          </a-radio-group>
        </a-form-model-item>
        <a-form-model-item has-feedback prop="icon" label="菜单图标">
          <a-input v-model="form.icon" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="component" label="组件路径">
          <a-input v-model="form.component" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="hidden" label="是否隐藏">
          <a-radio-group v-model="form.hidden" name="hidden">
            <a-radio :value="true">是</a-radio>
            <a-radio :value="false">否</a-radio>
          </a-radio-group>
        </a-form-model-item>
        <a-form-model-item has-feedback prop="frame" label="是否为外链">
          <a-radio-group v-model="form.frame" name="frame">
            <a-radio :value="true">是</a-radio>
            <a-radio :value="false">否</a-radio>
          </a-radio-group>
        </a-form-model-item>
      </a-form-model>

      <div
        :style="{
          position: 'absolute',
          right: 0,
          bottom: 0,
          width: '100%',
          borderTop: '1px solid #e9e9e9',
          padding: '10px 16px',
          background: '#fff',
          zIndex: 1,
        }"
      >
        <div style="text-align: right">
          <a-button :style="{ marginRight: '8px' }" @click="handleClose">
            关闭
          </a-button>
          <a-button type="primary" @click="handleSubmit">
            确定
          </a-button>
        </div>
      </div>
    </a-drawer>
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
      form: {},
      rules: {
        name: [{ validator: validateName, required: true, message: '请输入菜单名称', trigger: 'blur' }],
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
