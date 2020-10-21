<template>
  <div class="app-container">
    <a-card>
      <!-- 搜索条件部分 - Begin -->
      <a-row>
        <a-input-search v-model="query.username" placeholder="请输入名称查询" style="width: 200px" @search="fetchData" />
        <a-button type="dashed" icon="plus" @click="editVisible = true" />
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
          <a-button type="link" size="small" icon="edit" @click="handleUpdate(record.id)">
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

      <!-- 新增/修改弹窗 - Begin -->
      <a-modal
        title="新增/修改"
        :visible="editVisible"
        :confirm-loading="editLoading"
        @ok="handleSubmit"
        @cancel="handleClose"
      />
      <!-- 新增/修改弹窗 - End -->
    </a-card>
  </div>
</template>

<script>
import Pagination from '@/components/Pagination'
import { userList, getUser } from '@/api/modules/system/user'

export default {
  name: 'Index',
  components: { Pagination },
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
      form: {},
      query: {},
      pageConf: {
        page: 1,
        limit: 5,
        total: 0
      },
      editVisible: false,
      editLoading: false,
      loading: false
    }
  },
  created() {
    this.fetchData(this.pageConf)
  },
  methods: {
    handleClose() {
      this.editVisible = false
    },
    fetchData(page) {
      this.pageConf.page = page.page
      this.pageConf.limit = page.limit
      userList(this.pageConf, this.query).then(res => {
        this.list = res.data.rows
        this.pageConf.total = res.data.total
      })
    },
    handleUpdate(id) {
      this.editLoading = true
      getUser(id).then(res => {
        this.form = res.data
        this.editVisible = true
        this.editLoading = false
      })
    },
    handleSubmit() {
    },
    handleDel(id) {

    }
  }
}
</script>

<style scoped>
.ant-row {
  margin-bottom: 10px;
}
</style>
