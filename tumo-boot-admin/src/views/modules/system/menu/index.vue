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
        <span slot="type" slot-scope="type">
          <a-tag color="blue">{{ type }}</a-tag>
        </span>
        <span slot="meta" slot-scope="meta">
          <a-icon v-if="meta !== null && meta.icon !== null" :type="meta.icon" />
        </span>
        <span slot="hidden" slot-scope="hidden">
          <a-popover :content="hidden ? '隐藏' : '显示'">
            <a-badge :status="hidden ? 'error' : 'success'" />
          </a-popover>
        </span>
        <span slot="frame" slot-scope="frame">
          <a-popover :content="frame ? '外链' : '内部'">
            <a-tag :color="frame ? '#f50' : '#87d068'">{{ frame ? '外链' : '内部' }}</a-tag>
          </a-popover>
        </span>
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
import { delMenu, menuTree } from '@/api/modules/system/menu'

export default {
  name: 'Index',
  components: { EditForm },
  data() {
    return {
      list: [],
      columns: [
        { title: '菜单名称', dataIndex: 'name', key: 'name' },
        { title: 'URL', dataIndex: 'path', key: 'path' },
        { title: '权限标识', dataIndex: 'perms', key: 'perms', width: 120, align: 'center' },
        { title: '类型', dataIndex: 'type', key: 'type', scopedSlots: { customRender: 'type' }, width: 80, align: 'center' },
        { title: '图标', dataIndex: 'meta', key: 'meta', scopedSlots: { customRender: 'meta' }, width: 80, align: 'center' },
        { title: 'Vue组件', dataIndex: 'component', key: 'component' },
        { title: '是否隐藏', dataIndex: 'hidden', key: 'hidden', scopedSlots: { customRender: 'hidden' }, width: 80, align: 'center' },
        { title: '是否外链', dataIndex: 'frame', key: 'frame', scopedSlots: { customRender: 'frame' }, width: 80, align: 'center' },
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
      menuTree(this.query).then(res => {
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
          delMenu(id).then(res => {
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
