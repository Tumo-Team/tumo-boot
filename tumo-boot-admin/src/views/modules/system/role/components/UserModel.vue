<template>
  <div v-if="visible">
    <!-- 用户列表弹窗 - Begin -->
    <a-drawer
      title="该角色所属用户列表"
      :visible.sync="visible"
      placement="right"
      :keyboard="false"
      :mask-closable="false"
      width="40%"
      :destroy-on-close="true"
      @close="handleClose"
    >
      <a-list item-layout="horizontal" :data-source="list">
        <a-list-item slot="renderItem" slot-scope="item">
          <a-list-item-meta>
            <a slot="title">{{ item.username }}</a>
            <a-avatar slot="avatar" shape="square" :src="item.avatar" />
            <span slot="description">
              <a-icon type="user" /> :&nbsp;{{ item.sex }}&nbsp;&nbsp;
              <a-icon type="phone" /> :&nbsp;{{ item.phone }}&nbsp;&nbsp;
              <a-icon type="mail" /> :&nbsp;{{ item.email }}
            </span>
          </a-list-item-meta>
        </a-list-item>
      </a-list>
    </a-drawer>
    <!-- 用户列表弹窗 - End -->
  </div>

</template>

<script>
import { roleUserList } from '@/api/modules/system/role'

export default {
  name: 'UserModel',
  data() {
    return {
      visible: false,
      list: []
    }
  },
  methods: {
    handleClose() {
      this.visible = false
    },

    init(id) {
      if (id !== undefined) {
        roleUserList(id).then(res => {
          this.list = res.data
          this.visible = true
        })
      }
    }
  }
}
</script>

<style scoped>
.ant-avatar {
  border: 1px dashed #d9d9d9;
  width: 50px;
  height: 50px;
  padding: 2px;
}
</style>
