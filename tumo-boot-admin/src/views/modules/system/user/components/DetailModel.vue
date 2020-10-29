<template>
  <div v-if="visible">
    <!-- 详情弹窗 - Begin -->
    <a-modal
      title="详情"
      :visible.sync="visible"
      :confirm-loading="loading"
      :keyboard="false"
      :mask-closable="false"
      width="40%"
      :destroy-on-close="true"
      @cancel="handleClose"
    >
      <a-row :gutter="20">
        <a-col :span="8">
          <a-avatar shape="square" :size="140" :src="form.avatar" />
        </a-col>
        <a-col :span="16">
          <a-form :label-col="{ span: 4 }" :wrapper-col="{ span: 20 }">
            <a-form-item>
              <span slot="label">
                <a-icon type="user" />&nbsp;账户&nbsp;
              </span>
              {{ form.username }}
            </a-form-item>
            <a-form-item>
              <span slot="label">
                <a-icon type="user" />&nbsp;性别&nbsp;
              </span>
              <a-tag color="blue">{{ form.sex }}</a-tag>
            </a-form-item>
            <a-form-item>
              <span slot="label">
                <a-icon type="mail" />&nbsp;邮箱&nbsp;
              </span>
              {{ form.email }}
            </a-form-item>
            <a-form-item>
              <span slot="label">
                <a-icon type="phone" />&nbsp;手机&nbsp;
              </span>
              {{ form.phone }}
            </a-form-item>
            <a-form-item>
              <span slot="label">
                <a-icon type="apartment" />&nbsp;部门&nbsp;
              </span>
              <a-tag color="cyan">{{ form.deptName }}</a-tag>
            </a-form-item>
            <a-form-item>
              <span slot="label">
                <a-icon type="audit" />&nbsp;角色&nbsp;
              </span>
              <a-tag
                v-for="role in form.roles"
                :key="role.id"
                color="green"
              >
                {{ role.name }}
              </a-tag>
            </a-form-item>
          </a-form>
        </a-col>
      </a-row>
    </a-modal>
    <!-- 详情弹窗 - End -->
  </div>

</template>

<script>
import { findByUserId } from '@/api/modules/system/user'

export default {
  name: 'PassModel',
  data() {
    return {
      visible: false,
      loading: false,
      form: {}
    }
  },
  methods: {
    handleClose() {
      this.visible = false
      this.loading = false
      this.form = {}
    },

    init(id) {
      findByUserId(id).then(res => {
        this.form = res.data
        this.visible = true
      })
    }
  }
}
</script>

<style scoped>
/deep/ .ant-form-item {
  margin-bottom: 0px;
}
</style>
