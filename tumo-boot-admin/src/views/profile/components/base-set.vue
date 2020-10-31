<template>
  <div class="view-container">
    <div class="view-left">
      <a-form-model layout="horizontal" :model="form">
        <a-form-model-item label="邮箱">
          <a-input v-model="form.email" placeholder="please input email" />
        </a-form-model-item>
        <a-form-model-item label="用户名">
          <a-input v-model="form.username" placeholder="plese input username" />
        </a-form-model-item>
        <a-form-model-item label="个人简介">
          <a-input v-model="form.username" type="textarea" placeholder="plese input username" />
        </a-form-model-item>
        <a-form-model-item label="联系电话">
          <a-input v-model="form.username" placeholder="plese input username" />
        </a-form-model-item>
        <a-form-model-item>
          <a-button type="primary">
            修改
          </a-button>
        </a-form-model-item>
      </a-form-model>
    </div>
    <div class="view-right">
      <a-row>头像</a-row>
      <div class="view-right-img">
        <a-avatar :src="avatar" :size="144" />
      </div>
      <div class="view-right-btn">
        <a-upload
          name="file"
          :multiple="true"
          action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
          :headers="headers"
          @change="handleChange"
        >
          <a-button>
            <a-icon type="upload" />
            修改头像
          </a-button>
        </a-upload>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'BaseSet',
  data() {
    return {
      form: {
        username: this.name
      },
      headers: {
        authorization: 'authorization-text'
      }
    }
  },
  computed: {
    ...mapGetters([
      'avatar',
      'name'
    ])
  },
  methods: {
    handleChange(info) {
      if (info.file.status !== 'uploading') {
        console.apiLog(info.file, info.fileList)
      }
      if (info.file.status === 'done') {
        this.$message.success(`${info.file.name} file uploaded successfully`)
      } else if (info.file.status === 'error') {
        this.$message.error(`${info.file.name} file upload failed.`)
      }
    }
  }
}
</script>

<style lang="less" scoped>
  .view-container {
    display: flex;
    padding-top: 12px;

    .view-left {
      min-width: 300px;
      max-width: 448px;
    }

    .view-right {
      flex: 1 1;
      padding-left: 104px;

      .view-right-img {
        width: 144px;
        height: 144px;
        margin-bottom: 12px;
        overflow: hidden;
      }

      .view-right-btn {
        display: inline-block;
        width: 144px;
        text-align-last: center;
      }
    }
  }

</style>
