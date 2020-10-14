<template>
  <div class="app-container">
    <a-card>
      <a-table
        :columns="columns"
        :row-key="record => record.id"
        :pagination="pagination"
        :loading="loading"
        :data-source="list"
        bordered
      >
        <a slot="username" slot-scope="username">{{ username }}</a>
        <template slot="sex" slot-scope="sex">{{ sex }}</template>
        <!--<span slot="roles" slot-scope="roles">
          <a-tag
            v-for="role in roles"
            :key="role"
            color="geekblue"
          >
            {{ role }}
          </a-tag>
        </span>-->
        <a-avatar slot="avatar" slot-scope="avatar" shape="square" :src="avatar" :size="64" />
      </a-table>
    </a-card>
  </div>
</template>

<script>
import { userList } from '@/api/user'

export default {
  name: 'Index',
  data() {
    return {
      list: [],
      columns: [
        { title: 'Username', dataIndex: 'username', key: 'username', width: '100px' },
        { title: 'Sex', dataIndex: 'sex', key: 'sex', width: '60px' }
      ],
      pagination: { defaultPageSize: 2 },
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
        this.pagination.total = res.data.total
      })
    }
  }
}
</script>

<style scoped>

</style>
