<template>
  <div class="app-container">
    <a-card>
      <!-- 搜索条件部分 - Begin -->
      <a-row>
        <a-input-search
          v-model="query.name"
          placeholder="请输入名称查询"
          style="width: 200px"
          @search="fetchData()"
        />
        <a-popover content="新增">
          <a-button type="dashed" icon="plus" @click="$refs.model.init()" />
        </a-popover>
        <a-popover content="刷新">
          <a-button type="dashed" icon="redo" @click="fetchData()" />
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
      <!-- Table列表部分 - End -->

      <!-- 新增/修改弹窗 -->
      <edit-form ref="model" @refresh="fetchData()" />
    </a-card>
  </div>
</template>

<script>
import EditForm from './components/EditForm'
import { deptTree, delDept } from '@/api/modules/system/dept'

export default {
  name: 'Index',
  components: { EditForm },
  data() {
    return {
      list: [],
      columns: [
        { title: '上级部门ID', dataIndex: 'parentId', key: 'parentId' },
        { title: '部门名称', dataIndex: 'name', key: 'name' },
        { title: '描述', dataIndex: 'des', key: 'des' },
        { title: '创建时间', dataIndex: 'createTime', key: 'createTime' },
        { title: '操作', key: 'action', scopedSlots: { customRender: 'action' }, fixed: 'right', width: 148 }
      ],
      query: {},
      loading: true
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.loading = true
      deptTree(this.query).then(res => {
        this.list = res.data
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
          delDept(id).then(res => {
            if (res.code === 200) {
              _this.$message
                .success(res.msg)
            } else {
              _this.$message
                .error(res.msg)
            }
            _this.fetchData()
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
