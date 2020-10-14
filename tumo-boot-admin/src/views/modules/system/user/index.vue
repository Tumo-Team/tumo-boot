<template>
  <div class="app-container">
    <a-card>
      <a-table
        size="small"
        :columns="columns"
        :row-key="record => record.id"
        :pagination="false"
        :loading="loading"
        :data-source="list"
        bordered
      >
        <a slot="username" slot-scope="username">{{ username }}</a>
        <template slot="sex" slot-scope="sex">{{ sex }}</template>
        <span slot="roles" slot-scope="roles">
          <a-tag
            v-for="role in roles"
            :key="role"
            color="green"
          >
            {{ role }}
          </a-tag>
        </span>
        <a-avatar slot="avatar" slot-scope="avatar" shape="square" :src="avatar" :size="64" />
      </a-table>
      <pagination
        v-show="pageConf.total>0"
        :total="pageConf.total"
        :page.sync="pageConf.page"
        :limit.sync="pageConf.limit"
        @pagination="fetchData"
      />
    </a-card>
  </div>
</template>

<script>
import Pagination from '@/components/Pagination'
import { userList } from '@/api/user'

export default {
  name: 'Index',
  components: { Pagination },
  data() {
    return {
      list: [],
      columns: [
        { title: '用户名', dataIndex: 'username', key: 'username', width: 140 },
        { title: '性别', dataIndex: 'sex', key: 'sex', width: 80 },
        { title: '角色', dataIndex: 'roles', key: 'roles', scopedSlots: { customRender: 'roles' }}
      ],
      pagination: {
        defaultPageSize: 3,
        showSizeChanger: true,
        size: 'default',
        showTotal: (total) => `共 ${total} 条`,
        showSizeChange: this.handleSizeChange,
        change: this.handleCurrentChange
      },
      pageConf: {
        page: 1,
        limit: 10,
        total: 0
      },
      loading: false
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      userList(this.pagination).then(res => {
        this.list = res.data.rows
        this.pageConf.total = res.data.total
      })
    },
    handleCurrentChange(page, size) {
      console.log(page, size)
    },
    handleSizeChange(current, size) {
      console.log(current, size)
    }
  }
}
</script>

<style scoped>

</style>
