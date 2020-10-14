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
        <span slot="action" slot-scope="text, record">
          <a @click="handleUpdate(record.id)">修改</a>
          <a @click="handleDel(record.id)">删除</a>
        </span>
      </a-table>
    </a-card>
  </div>
</template>

<script>
import { articleList } from '@/api/article'

export default {
  name: 'Index',
  data() {
    return {
      list: [],
      query: {},
      columns: [
        { title: '编号', dataIndex: 'id', key: 'id', width: '100px' },
        { title: '作者', dataIndex: 'author', key: 'author', width: '150px' },
        { title: '标题', dataIndex: 'title', key: 'title' },
        { title: '浏览量', dataIndex: 'eyes', key: 'eyes', width: '150px' },
        { title: '创建时间', dataIndex: 'createTime', key: 'createTime', width: '150px' },
        { title: '操作', key: 'action', fixed: 'right', width: '100px' }
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
      articleList(this.pagination, this.query).then(res => {
        this.list = res.data.rows
        this.pagination.total = res.data.total
      })
    },
    handleUpdate(id) {
      console.log('del: ' + id)
    },
    handleDel(id) {
      console.log('del: ' + id)
    }
  }
}
</script>

<style scoped>

</style>
