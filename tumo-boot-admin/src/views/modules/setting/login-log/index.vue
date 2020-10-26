<template>
  <div class="app-container">
    <a-card>
      <!-- 搜索条件部分 - Begin -->
      <a-row>
        <a-input-search
          v-model="query.name"
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
        <span slot="action" slot-scope="text, record">
          <a-popover content="修改">
            <a-button
              type="dashed"
              size="small"
              @click="$refs.model
                .init(record.id)"
            >
              <a-icon type="edit" theme="twoTone" two-tone-color="#52c41a" />
            </a-button>
          </a-popover>
          <a-popover content="删除">
            <a-button type="dashed" size="small" @click="handleDel(record.id)">
              <a-icon type="delete" theme="twoTone" two-tone-color="#f5222d" />
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
    </a-card>
  </div>
</template>

<script>
import Pagination from '@/components/Pagination'
import { delLoginLog, loginLogList } from '@/api/modules/setting/loginLog'

export default {
  name: 'Index',
  components: { Pagination },
  data() {
    return {
      list: [],
      columns: [
        { title: '用户名', dataIndex: 'username', key: 'username' },
        { title: 'IP地址', dataIndex: 'ip', key: 'ip' },
        { title: '登录地点', dataIndex: 'location', key: 'location' },
        { title: '登录时间', dataIndex: 'createTime', key: 'createTime' },
        { title: '登录设备', dataIndex: 'device', key: 'device' },
        { title: '操作', key: 'action', scopedSlots: { customRender: 'action' }, fixed: 'right', width: 148 }
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
      loginLogList(this.pageConf, this.query).then(res => {
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
          delLoginLog(id).then(res => {
            if (res.code === 200) {
              _this.$message
                .success(res.msg)
            } else {
              _this.$message
                .error(res.msg)
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
