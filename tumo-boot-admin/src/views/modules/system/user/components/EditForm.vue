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
        <a-form-model-item v-if="form.id === undefined" has-feedback prop="password" type="password" label="密码">
          <a-input v-model="form.password" />
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
        <a-form-model-item label="角色">
          <a-tree-select
            v-model="selectRole"
            style="width: 100%"
            tree-checkable
            :dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
            :replace-fields="{title: 'name', key: 'id', value: 'id'}"
            tree-check-strictly
            :tree-data="roleTree"
            tree-default-expand-all
            placeholder="请选择角色"
          />
        </a-form-model-item>
        <a-form-model-item prop="avatar" label="头像">
          <a-upload
            action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
            list-type="picture-card"
            :file-list="fileList"
            @preview="handlePreview"
            @change="handleChange"
          >
            <div v-if="fileList.length < 8">
              <a-icon type="plus" />
              <div class="ant-upload-text">
                Upload
              </div>
            </div>
          </a-upload>
          <a-modal :visible="previewVisible" :footer="null" @cancel="previewVisible=false">
            <img alt="example" style="width: 100%" :src="previewImage">
          </a-modal>
        </a-form-model-item>
      </a-form-model>
    </a-modal>
    <!-- 新增/修改弹窗 - End -->
  </div>

</template>

<script>
import { findByUserId, addUser, updateUser, checkUserName } from '@/api/modules/system/user'
import { deptTree } from '@/api/modules/system/dept'
import { roleTree } from '@/api/modules/system/role'

function getBase64(file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader()
    reader.readAsDataURL(file)
    reader.onload = () => resolve(reader.result)
    reader.onerror = error => reject(error)
  })
}

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
      roleTree: [],
      selectRole: [],
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
      previewVisible: false,
      previewImage: '',
      fileList: [
        {
          uid: '-1',
          name: 'image.png',
          status: 'done',
          url: 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png'
        }
      ]
    }
  },
  methods: {
    handleClose() {
      this.visible = false
      this.loading = false
      this.form = {}
      this.selectRole = []
    },

    init(id) {
      deptTree().then(res => {
        this.deptTree = res.data
      })
      roleTree().then(res => {
        this.roleTree = res.data
      })
      if (id !== undefined) {
        // 修改操作
        findByUserId(id).then(res => {
          this.form = res.data
          if (res.data.roles !== undefined) {
            res.data.roles.forEach(r => {
              this.selectRole.push({ value: r.id, label: r.name })
            })
          }
          this.visible = true
        })
      } else {
        this.visible = true
      }
    },

    async handlePreview(file) {
      if (!file.url && !file.preview) {
        file.preview = await getBase64(file.originFileObj)
      }
      this.previewImage = file.url || file.preview
      this.previewVisible = true
    },
    handleChange({ fileList }) {
      this.fileList = fileList
    },
    handleSubmit() {
      this.loading = true
      this.form.roles = []
      this.selectRole.forEach(r => {
        this.form.roles.push({ id: r.value, name: r.label })
      })
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
.ant-upload-select-picture-card i {
  font-size: 32px;
  color: #999;
}

.ant-upload-select-picture-card .ant-upload-text {
  margin-top: 8px;
  color: #666;
}
</style>
