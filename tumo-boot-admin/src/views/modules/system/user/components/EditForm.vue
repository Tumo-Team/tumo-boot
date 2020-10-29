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
        <a-form-model-item has-feedback prop="username" label="账户">
          <a-input v-model="form.username" />
        </a-form-model-item>
        <a-form-model-item v-if="form.id === undefined" has-feedback prop="password" label="密码">
          <a-input v-model="form.password" type="password" />
        </a-form-model-item>
        <a-form-model-item prop="sex" label="性别">
          <a-select v-model="form.sex" placeholder="请选择性别">
            <a-select-option value="男">男</a-select-option>
            <a-select-option value="女">女</a-select-option>
          </a-select>
        </a-form-model-item>
        <a-form-model-item has-feedback prop="phone" label="电话">
          <a-input v-model="form.phone" />
        </a-form-model-item>
        <a-form-model-item prop="email" label="邮箱">
          <a-input v-model="form.email" />
        </a-form-model-item>
        <a-form-model-item prop="status" label="状态">
          <a-radio-group v-model="form.status">
            <a-radio :value="false">
              禁用
            </a-radio>
            <a-radio :value="true">
              激活
            </a-radio>
          </a-radio-group>
        </a-form-model-item>
        <a-form-model-item has-feedback prop="deptId" label="部门">
          <a-tree-select
            v-model="form.deptId"
            style="width: 100%"
            :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
            :replace-fields="{title: 'name', key: 'id', value: 'id'}"
            :tree-data="deptTree"
            tree-default-expand-all
            placeholder="请选择部门"
          />
        </a-form-model-item>
        <a-form-model-item label="头像">
          <a-upload
            name="avatar"
            list-type="picture-card"
            class="avatar-uploader"
            :show-upload-list="false"
            action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
            :before-upload="beforeUpload"
            @change="handleUploadChange"
          >
            <img v-if="form.avatar" :src="form.avatar" alt="avatar">
            <div v-else>
              <a-icon :type="uploadLoading ? 'loading' : 'plus'" />
              <div class="ant-upload-text">
                Upload
              </div>
            </div>
          </a-upload>
        </a-form-model-item>
      </a-form-model>
    </a-modal>
    <!-- 新增/修改弹窗 - End -->
  </div>

</template>

<script>
import { findByUserId, addUser, updateUser, checkUserName } from '@/api/modules/system/user'
import { deptTree } from '@/api/modules/system/dept'

export default {
  name: 'EditForm',
  data() {
    const validateName = (rule, value, callback) => {
      if (value === undefined || value.trim() === '') {
        callback(new Error('请输入账户名'))
      } else {
        checkUserName(this.form).then(res => {
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
      deptTree: [],
      rules: {
        username: [{ required: true, validator: validateName, trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
        sex: [{ required: true, message: '请选择性别', trigger: 'change' }],
        email: [{ type: 'email', required: false, message: '请输入正确的邮箱地址', trigger: 'blur' }],
        phone: [{ pattern: /^(?:(?:\+|00)86)?1(?:(?:3[\d])|(?:4[5-7|9])|(?:5[0-3|5-9])|(?:6[5-7])|(?:7[0-8])|(?:8[\d])|(?:9[1|8|9]))\d{8}$/,
          required: true, message: '请输入电话号', trigger: 'blur' }],
        deptId: [{ required: true, message: '请选择部门', trigger: 'change' }],
        status: [{ required: true, message: '请选择状态', trigger: 'change' }]
      },
      uploadLoading: false
    }
  },
  methods: {
    handleClose() {
      this.visible = false
      this.loading = false
      this.form = {}
    },

    init(id) {
      deptTree().then(res => {
        this.deptTree = res.data
      })
      if (id !== undefined) {
        // 修改操作
        findByUserId(id).then(res => {
          this.form = res.data
          this.visible = true
        })
      } else {
        this.visible = true
      }
    },

    // 文件上传前
    beforeUpload(file) {
      this.uploadLoading = true
      const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png'
      if (!isJpgOrPng) {
        this.$message.error('请上传JPG或PNG格式文件')
      }
      return isJpgOrPng
    },

    // 文件上传后
    handleUploadChange(info) {
      this.uploadLoading = false
      console.log(info)
    },
    handleSubmit() {
      this.loading = true
      this.$refs.form.validate(valid => {
        if (valid) {
          if (this.form.id === undefined || this.form.id === 0) {
            // 新增
            addUser(this.form).then(res => {
              if (res.code === 200) {
                this.$message.success('新增成功')
                this.handleClose()
                this.$emit('refresh')
              }
            })
          } else {
            // 修改
            updateUser(this.form).then(res => {
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
img {
  width: 128px !important;
  height: 128px !important;
}
</style>
