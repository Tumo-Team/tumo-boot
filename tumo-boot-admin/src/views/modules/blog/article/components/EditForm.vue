<template>
  <div v-if="visible">
    <!-- 新增/修改弹窗 - Begin -->
    <a-drawer
      :title="form.id === undefined ? '新增' : '修改'"
      :visible.sync="visible"
      :keyboard="false"
      :mask-closable="false"
      width="80%"
      :destroy-on-close="true"
      @close="handleClose"
    >
      <a-form-model
        ref="form"
        :model="form"
        :rules="rules"
        :label-col="{ span: 2 }"
        :wrapper-col="{ span: 21 }"
      >
        <a-form-model-item has-feedback prop="title" label="文章标题">
          <a-input v-model="form.title" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="des" label="文章介绍">
          <a-input v-model="form.des" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="content" label="文章内容">
          <mavon-editor v-model="form.content" :ishljs="true" @change="handleEditorChange" />
        </a-form-model-item>
        <a-form-model-item prop="cover" label="封面图片">
          <a-upload
            name="cover"
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
import { mavonEditor } from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import { addArticle, findByArticleId, updateArticle } from '@/api/modules/blog/article'

export default {
  name: 'EditForm',
  components: { mavonEditor },
  data() {
    return {
      visible: false,
      loading: false,
      uploadLoading: false,
      form: {},
      rules: {
        title: [{ required: true, message: '请输入文章标题', trigger: 'blur' }],
        des: [{ required: true, message: '请输入文章简介', trigger: 'blur' }],
        content: [{ required: true, message: '请输入文章内容', trigger: 'blur' }]
      }
    }
  },
  methods: {
    handleClose() {
      this.visible = false
      this.loading = false
      this.uploadLoading = false
      this.form = {}
    },

    init(id) {
      if (id !== undefined) {
        // 修改操作
        findByArticleId(id).then(res => {
          this.form = res.data
          this.visible = true
        })
      } else {
        this.visible = true
      }
    },

    // Editor编辑区发生变化
    handleEditorChange(value, render) {
      this.form.contentHtml = render
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
      console.apiLog(info)
    },

    handleSubmit() {
      this.loading = true
      this.$refs.form
        .validate(valid => {
          if (valid) {
            if (this.form.id === undefined || this.form.id === 0) {
              // 新增
              addArticle(this.form).then(res => {
                if (res.code === 200) {
                  this.$message.success('新增成功')
                  this.handleClose()
                  this.$emit('refresh')
                }
              })
            } else {
              // 修改
              updateArticle(this.form).then(res => {
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
.ant-upload.ant-upload-select-picture-card, img {
  width: 269.98px !important;
  height: 250px !important;
}
</style>
