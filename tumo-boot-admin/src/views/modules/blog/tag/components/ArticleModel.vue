<template>
  <div v-if="visible">
    <!-- 关联文章列表弹窗 - Begin -->
    <a-drawer
      title="该标签关联的文章"
      :visible.sync="visible"
      placement="right"
      :keyboard="false"
      :mask-closable="false"
      width="40%"
      :destroy-on-close="true"
      @close="handleClose"
    >
      <a-table
        size="small"
        :columns="columns"
        :row-key="record => record.id"
        :pagination="false"
        :loading="loading"
        :data-source="list"
        :scroll="{ x: 'calc(700px + 50%)'}"
        bordered
      />
      <pagination
        v-show="pageConf.total>0"
        :total="pageConf.total"
        :page.sync="pageConf.page"
        :limit.sync="pageConf.limit"
        @pagination="init(id, pageConf)"
      />
    </a-drawer>
    <!-- 关联文章列表弹窗 - End -->
  </div>

</template>

<script>
import Pagination from '@/components/Pagination'
import { tagArticleList } from '@/api/modules/blog/tag'

export default {
  name: 'UserModel',
  components: { Pagination },
  data() {
    return {
      visible: false,
      id: undefined,
      list: [],
      columns: [
        { title: '文章标题', dataIndex: 'title', key: 'title' },
        { title: '文章标题', dataIndex: 'author', key: 'author' },
        { title: '创建时间', dataIndex: 'createTime', key: 'createTime', align: 'center', width: 150 }
      ],
      loading: false,
      pageConf: {
        page: 1,
        limit: 5,
        total: 0
      }
    }
  },
  methods: {
    handleClose() {
      this.id = undefined
      this.visible = false
    },

    init(id, page) {
      if (page !== null) {
        this.pageConf.page = page.page
        this.pageConf.limit = page.limit
      }
      if (id !== undefined) {
        this.loading = true
        this.id = id
        tagArticleList(this.pageConf, id).then(res => {
          this.list = res.data.rows
          this.pageConf.total = res.data.total
          this.loading = false
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
