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
    >
      <a-form-model
        ref="form"
        :model="form"
        :rules="rules"
        v-bind="layout"
      >
        <a-form-model-item has-feedback prop="username" label="用户名">
          <a-input v-model="form.username" />
        </a-form-model-item>
        <a-form-model-item v-if="form.id === undefined" has-feedback prop="password" label="密码">
          <a-input v-model="form.password" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="phone" label="电话">
          <a-input v-model="form.phone" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="dept" label="部门">
          <a-select v-model="form.dept" placeholder="请选择部门">
            <a-select-option value="shanghai">
              Zone one
            </a-select-option>
            <a-select-option value="beijing">
              Zone two
            </a-select-option>
          </a-select>
        </a-form-model-item>
        <a-form-model-item has-feedback prop="roles" label="角色">
          <a-select v-model="form.roles" placeholder="请选择角色">
            <a-select-option value="shanghai">
              Zone one
            </a-select-option>
            <a-select-option value="beijing">
              Zone two
            </a-select-option>
          </a-select>
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
import { getUser } from '@/api/modules/system/user'
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
    return {
      visible: false,
      editLoading: true,
      form: {},
      rules: {
        username: [{ required: true, message: '请输入名称', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
        phone: [{ required: true, message: '请输入电话', trigger: 'blur' }],
        roles: [{ required: true, message: '请选择角色', trigger: 'change' }],
        dept: [{ required: true, message: '请选择部门', trigger: 'change' }]
      },
      layout: {
        labelCol: { span: 4 },
        wrapperCol: { span: 20 }
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
    /**
     * 子组件关闭（默认）：不刷新Table（false）
     * 弹窗的Visible状态由父组件维护
     */
    handleClose() {
      this.visible = false
      this.$emit('refresh', false)
    },

    init(id) {
      if (id !== undefined) {
        // 修改操作
        getUser(id).then(res => {
          this.form = res.data
          this.editLoading = false
        })
      }
      this.visible = true
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
