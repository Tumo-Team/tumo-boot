<template>
  <div class="app-container">
    <a-card>
      <!-- 搜索条件部分 - Begin -->
      <a-row>
        <a-input-search v-model="query.username" placeholder="请输入名称查询" style="width: 200px" @search="fetchData(pageConf)" />
        <a-button type="dashed" icon="plus" @click="$refs.model.init()" />
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
        bordered
      >
        <span slot="roles" slot-scope="roles">
          <a-tag
            v-for="role in roles"
            :key="role"
            color="green"
          >
            {{ role }}
          </a-tag>
        </span>
        <span slot="action" slot-scope="text, record">
          <a-button type="link" size="small" icon="edit" @click="$refs.model.init(record.id)">
            编辑
          </a-button>
          <a-button type="link" size="small" icon="delete" @click="handleDel(record.id)">
            删除
          </a-button>
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
      <edit-form ref="model" @refresh="modelRefresh" />
    </a-card>
  </div>
</template>

<script>
import Pagination from '@/components/Pagination'
import EditForm from './components/EditForm'
import { userList, delUser } from '@/api/modules/system/user'

export default {
  name: 'Index',
  components: { Pagination, EditForm },
  data() {
    return {
      list: [],
      columns: [
        { title: '用户名', dataIndex: 'username', key: 'username', width: 140 },
        { title: '手机', dataIndex: 'phone', key: 'phone', width: 120 },
        { title: '角色', dataIndex: 'roles', key: 'roles', scopedSlots: { customRender: 'roles' }},
        { title: '创建时间', dataIndex: 'createTime', key: 'createTime', width: 150 },
        { title: '操作', key: 'action', scopedSlots: { customRender: 'action' }, width: 200 }
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
    /**
     * 子组件更新
     * @param status 是否需要刷新Table
     */
    modelRefresh(status) {
      if (status) {
        this.fetchData(this.pageConf)
      }
    },
    fetchData(page) {
      this.pageConf.page = page.page
      this.pageConf.limit = page.limit
      userList(this.pageConf, this.query).then(res => {
        this.list = res.data.rows
        this.pageConf.total = res.data.total
        this.loading = false
      })
    },
    handleDel(id) {
      this.$confirm({
        title: '您确定要删除此条数据吗?',
        content: 'Some descriptions',
        okText: '确定',
        okType: 'danger',
        cancelText: '取消',
        onOk() {
          delUser(id).then(res => {
            if (res.code === 200) {
              this.$message.success(res.msg)
            } else {
              this.$message.error(res.msg)
            }
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
