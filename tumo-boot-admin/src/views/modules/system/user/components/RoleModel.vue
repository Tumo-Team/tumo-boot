<template>
  <div v-if="visible">
    <!-- 分配权限弹窗 - Begin -->
    <a-drawer
      title="用户角色分配"
      :visible.sync="visible"
      placement="right"
      :keyboard="false"
      :mask-closable="false"
      width="40%"
      :destroy-on-close="true"
      @close="handleClose"
    >
      <a-tree
        v-model="userRoleList"
        checkable
        check-strictly
        :expanded-keys.sync="expandedKey"
        :tree-data="roleTree"
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
          <a-dropdown>
            <a-menu slot="overlay" @click="handleMenuClick">
              <a-menu-item key="1">全部勾选</a-menu-item>
              <a-menu-item key="2">取消全选</a-menu-item>
              <a-menu-item key="3">展开所有</a-menu-item>
              <a-menu-item key="4">合并所有</a-menu-item>
            </a-menu>
            <a-button> 树操作
              <a-icon type="down" />
            </a-button>
          </a-dropdown>
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
import { userRoleList, userAddRole } from '@/api/modules/system/user'
import { roleBaseTree } from '@/api/modules/system/role'

export default {
  name: 'RoleModel',
  data() {
    return {
      visible: false,
      id: undefined,
      userRoleList: [],
      treeIds: [],
      roleTree: [],

      expandedKey: [],
      expand: true
    }
  },
  methods: {
    handleClose() {
      this.id = undefined
      this.list = []
      this.userRoleList = []
      this.expandedKey = []
      this.visible = false
    },

    init(id) {
      if (id !== undefined) {
        this.id = id
        roleBaseTree().then(res => {
          this.roleTree = res.data.tree
          this.treeIds = res.data.ids
          userRoleList(id).then(res => {
            this.userRoleList = res.data
            this.expandedKey = res.data
            this.visible = true
          })
        })
      }
    },

    handleMenuClick(val) {
      switch (val.key) {
        case '1':
          // 全部勾选
          this.userRoleList = this.treeIds
          break
        case '2':
          // 取消全选
          this.userRoleList = []
          break
        case '3':
          // 展开所有
          this.expandedKey = this.treeIds
          break
        case '4':
          // 合并所有
          this.expandedKey = []
          break
      }
    },

    handleSubmit() {
      let data = []
      if (this.userRoleList instanceof Array) {
        data = this.userRoleList
      } else {
        data = this.userRoleList.checked
      }
      userAddRole(data, this.id).then(res => {
        if (res.code === 200) {
          this.$message.success('角色分配成功')
          this.handleClose()
        }
      })
    }
  }
}
</script>

<style scoped>
</style>
