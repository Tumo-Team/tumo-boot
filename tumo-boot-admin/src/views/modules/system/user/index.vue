<template>
  <div class="app-container">
    <a-card>
      <!-- 搜索条件部分 - Begin -->
      <a-row>
        <a-input-search
          v-model="query.username"
          placeholder="请输入名称查询"
          style="width: 200px"
          @search="fetchData(pageConf)"
        />
        <a-popover content="新增">
          <a-button type="dashed" icon="plus" @click="$refs.model.init()" />
        </a-popover>
        <a-popover content="刷新">
          <a-button type="dashed" icon="redo" @click="fetchData(pageConf)" />
        </a-popover>
      </a-row>
      <!-- 搜索条件部分 - End -->

      <!-- Table表列表部分 - Begin -->
      <a-table
        size="small"
        :columns="columns"
        :row-key="record => record.id"
        :pagination="false"
        :loading="loading"
        :data-source="list"
        :scroll="{ x: 'calc(700px + 50%)'}"
        bordered
      >
        <span slot="roles" slot-scope="roles">
          <a-tag
            v-for="role in roles"
            :key="role.id"
            color="green"
          >
            {{ role.name }}
          </a-tag>
        </span>
        <span slot="status" slot-scope="status">
          <a-popover :content="status ? '激活' : '禁用'">
            <a-badge :status="status ? 'success' : 'error'" />
          </a-popover>
        </span>
        <span slot="action" slot-scope="text, record">
          <a-popover content="分配角色">
            <a-button type="dashed" size="small" @click="$refs.model.init(record.id)">
              <a-icon type="idcard" theme="twoTone" two-tone-color="#2db7f5" />
            </a-button>
          </a-popover>
          <a-popover content="详细">
            <a-button type="dashed" size="small" @click="$refs.editForm.init(record.id)">
              <a-icon type="eye" theme="twoTone" two-tone-color="#1890ff" />
            </a-button>
          </a-popover>
          <a-popover content="修改">
            <a-button type="dashed" size="small" @click="$refs.editForm.init(record.id)">
              <a-icon type="edit" theme="twoTone" two-tone-color="#52c41a" />
            </a-button>
          </a-popover>
          <a-popover content="删除">
            <a-button type="dashed" size="small" @click="handleDel(record.id)">
              <a-icon type="delete" theme="twoTone" two-tone-color="#f5222d" />
            </a-button>
          </a-popover>
          <a-popover content="重置密码">
            <a-button type="dashed" size="small" @click="$refs.passModel.init(record.id)">
              <a-icon type="tool" theme="twoTone" two-tone-color="#f5222d" />
            </a-button>
          </a-popover>
        </span>
      </a-table>
      <pagination
        v-show="pageConf.total>0"
        :total="pageConf.total"
        :page.sync="pageConf.page"
        :limit.sync="pageConf.limit"
        @pagination="fetchData"
      />
      <!-- Table列表部分 - End -->

      <!-- 新增/修改弹窗 -->
      <edit-form ref="editForm" @refresh="fetchData(pageConf)" />

      <!-- 分配角色弹窗 -->
      <role-model ref="model" @refresh="fetchData(pageConf)" />

      <!-- 修改密码弹窗 -->
      <pass-model ref="passModel" @refresh="fetchData(pageConf)" />
    </a-card>
  </div>
</template>

<script>
import Pagination from '@/components/Pagination'
import EditForm from './components/EditForm'
import RoleModel from './components/RoleModel'
import PassModel from './components/PassModel'
import { userList, delUser } from '@/api/modules/system/user'

export default {
  name: 'Index',
  components: { Pagination, EditForm, RoleModel, PassModel },
  data() {
    return {
      list: [],
      columns: [
        { title: '账户', dataIndex: 'username', key: 'username', width: 140 },
        { title: '性别', dataIndex: 'sex', key: 'sex', width: 60, align: 'center' },
        { title: '电话', dataIndex: 'phone', key: 'phone', width: 120 },
        { title: '邮箱', dataIndex: 'email', key: 'email', width: 180 },
        { title: '角色', dataIndex: 'roles', key: 'roles', scopedSlots: { customRender: 'roles' }},
        { title: '部门', dataIndex: 'deptName', key: 'deptName', width: 120 },
        { title: '状态', dataIndex: 'status', key: 'status', width: 60, align: 'center', scopedSlots: { customRender: 'status' }},
        { title: '创建时间', dataIndex: 'createTime', key: 'createTime', align: 'center', width: 150 },
        { title: '操作', key: 'action', scopedSlots: { customRender: 'action' }, fixed: 'right', align: 'center', width: 188 }
      ],
      query: {},
      pageConf: {
        page: 1,
        limit: 5,
        total: 0
      },
      loading: true
    }
  },
  created() {
    this.fetchData(this.pageConf)
  },
  methods: {
    fetchData(page) {
      this.loading = true
      this.pageConf.page = page.page
      this.pageConf.limit = page.limit
      userList(this.pageConf, this.query).then(res => {
        this.list = res.data.rows
        this.pageConf.total = res.data.total
        this.loading = false
      })
    },
    handleDel(id) {
      const _this = this
      this.$confirm({
        title: '您确定要删除此条数据吗?',
        okText: '确定',
        okType: 'danger',
        cancelText: '取消',
        onOk() {
          delUser(id).then(res => {
            if (res.code === 200) {
              _this.$message.success(res.msg)
            } else {
              _this.$message.error(res.msg)
            }
            _this.fetchData(_this.pageConf)
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.ant-row {
  margin-bottom: 10px;
}
</style>
