<template>
  <div :class="{'hidden':hidden}" class="pagination-container">
    <a-pagination
      show-quick-jumper
      show-size-changer
      :background="background"
      :current.sync="currentPage"
      :page-size-options="pageSizes"
      :page-size.sync="pageSize"
      :show-total="(total) => `共 ${total} 条`"
      :total="total"
      @showSizeChange="handleSizeChange"
      @change="handleCurrentChange"
    />
  </div>
</template>

<script>
import { scrollTo } from '@/utils/scroll-to'

export default {
  name: 'Pagination',
  props: {
    total: {
      required: true,
      type: Number
    },
    page: {
      type: Number,
      default: 1
    },
    limit: {
      type: Number,
      default: 20
    },
    pageSizes: {
      type: Array,
      default() {
        return ['5', '15', '20', '30']
      }
    },
    layout: {
      type: String,
      default: 'total, sizes, prev, pager, next, jumper'
    },
    background: {
      type: Boolean,
      default: true
    },
    autoScroll: {
      type: Boolean,
      default: true
    },
    hidden: {
      type: Boolean,
      default: false
    }
  },
  computed: {
    currentPage: {
      get() {
        return this.page
      },
      set(val) {
        this.$emit('update:page', val)
      }
    },
    pageSize: {
      get() {
        return this.limit
      },
      set(val) {
        this.$emit('update:limit', val)
      }
    }
  },
  methods: {
    handleSizeChange(current, pageSize) {
      this.$emit('pagination', { page: current, limit: pageSize })
      if (this.autoScroll) {
        scrollTo(0, 800)
      }
    },
    handleCurrentChange(page, pageSize) {
      this.$emit('pagination', { page: page, limit: pageSize })
      if (this.autoScroll) {
        scrollTo(0, 800)
      }
    }
  }
}
</script>

<style scoped>
</style>
