<template>
  <div v-if="visible">
    <!-- 分配权限弹窗 - Begin -->
    <a-drawer
      title="分配权限"
      :visible.sync="visible"
      placement="right"
      :keyboard="false"
      :mask-closable="false"
      width="40%"
      :destroy-on-close="true"
      @close="handleClose"
    >
      <a-tree
        v-model="permissionList"
        checkable
        check-strictly
        default-expand-all
        :tree-data="menuTree"
        :replace-fields="{title: 'name', key: 'id'}"
      />
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
        <div style="text-align: left;float: left">
          <a-button type="primary" @click="handleSubmit">
            操作
          </a-button>
        </div>
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
    <!-- 分配权限弹窗 - End -->
  </div>

</template>

<script>
import { rolePermissionList, roleAddPermission } from '@/api/modules/system/role'
import { menuTree } from '@/api/modules/system/menu'

export default {
  name: 'Model',
  data() {
    return {
      visible: false,
      list: [],
      id: undefined,
      permissionList: [],
      menuTree: []
    }
  },
  methods: {
    handleClose() {
      this.id = undefined
      this.list = []
      this.permissionList = []
      this.visible = false
    },

    init(id) {
      if (id !== undefined) {
        this.id = id
        menuTree().then(res => {
          this.menuTree = res.data
          this.visible = true
        })
        rolePermissionList(id).then(res => {
          this.permissionList = res.data
        })
      }
    },

    handleSubmit() {
      let data = []
      if (this.permissionList instanceof Array) {
        data = this.permissionList
      } else {
        data = this.permissionList.checked
      }
      roleAddPermission(data, this.id).then(res => {
        if (res.code === 200) {
          this.$message.success('分配权限成功')
          this.handleClose()
        }
      })
    }
  }
}
</script>

<style scoped>
</style>
