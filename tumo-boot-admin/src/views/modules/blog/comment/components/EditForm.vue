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
        <a-form-model-item has-feedback prop="articleId" label="账户">
          <a-input v-model="form.articleId" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="articleTitle" label="账户">
          <a-input v-model="form.articleTitle" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="pid" label="账户">
          <a-input v-model="form.pid" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="name" label="账户">
          <a-input v-model="form.name" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="email" label="账户">
          <a-input v-model="form.email" />
        </a-form-model-item>
        <a-form-model-item has-feedback prop="content" label="账户">
          <a-input v-model="form.content" />
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
import { addComment, checkCommentName, findByCommentId, updateComment } from '@/api/modules/blog/comment'

export default {
  name: 'EditForm',
  data() {
    const validateName = (rule, value, callback) => {
      if (value === undefined || value.trim() === '') {
        callback(new Error('请输入名称'))
      } else {
        checkCommentName(this.form).then(res => {
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
        articleId: [{ required: true, message: '请输入文章ID', trigger: 'blur' }],
        articleTitle: [{ required: true, message: '请输入文章标题', trigger: 'blur' }],
        pid: [{ required: true, message: '请输入父级ID', trigger: 'blur' }],
        name: [{ validator: validateName, required: true, message: '请输入评论人名称', trigger: 'blur' }],
        email: [{ required: true, message: '请输入评论人邮箱', trigger: 'blur' }],
        content: [{ required: true, message: '请输入评论内容', trigger: 'blur' }],
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
        findByCommentId(id).then(res => {
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
              addComment(this.form).then(res => {
                if (res.code === 200) {
                  this.$message.success('新增成功')
                  this.handleClose()
                  this.$emit('refresh')
                }
              })
            } else {
              // 修改
              updateComment(this.form).then(res => {
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
