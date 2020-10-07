<template>
  <div class="app-container">
    <a-card>
      <a-collapse v-model="activeKey" :bordered="false">
        <a-collapse-panel key="0" header="项目介绍">
          <a :href="thisProject" target="_blank">ant-design-vue-pro</a>
          是基于<a :href="elementUrl" target="_blank">vue-element-admin</a>开发的，
          请开发者先阅读<a :href="elementDocUrl" target="_blank">vue-element-admin项目文档。</a>
          <br>
          本项目在<a :href="elementUrl" target="_blank">vue-element-admin</a>的基础上将组件完全替换为AntV，
          当然Api肯定会有不同，但项目中对于vue-router、axios、vuex的封装基本相同。
        </a-collapse-panel>
        <a-collapse-panel key="1" header="项目源码">
          <p>
            Ant Design Vue Pro（@TyCoding 本项目）：
            <a :href="thisProject" target="_blank">{{ thisProject }}</a>
          </p>
          <p>
            Vue Element Admin（@PanJiaChen）：
            <a :href="elementUrl" target="_blank">{{ elementUrl }}</a>
          </p>
          <p>
            Ant Design Vue Pro（@sendya 官方推荐版本）：
            <a :href="antvpUrl" target="_blank">{{ antvpUrl }}</a>
          </p>
        </a-collapse-panel>
        <a-collapse-panel key="2" header="项目相关文档">
          <p>
            Ant Design Vue官方文档：
            <a :href="antvDocUrl" target="_blank">{{ antvDocUrl }}</a>
          </p>
          <p>
            Vue Element Admin项目文档：
            <a :href="elementDocUrl" target="_blank">{{ elementDocUrl }}</a>
          </p>
        </a-collapse-panel>
        <a-collapse-panel key="3" header="联系作者">
          <p>
            Blog:
            <a href="https://tycoding.cn" target="_blank">https://tycoding.cn</a>
          </p>
          <p>
            Github:
            <a href="https://github.com/TyCoding" target="_blank">https://github.com/TyCoding</a>
          </p>
          <p>
            QQ Group:
            <a href="#">671017003</a>
          </p>
        </a-collapse-panel>
      </a-collapse>
    </a-card>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Dashboard',
  components: {},
  data() {
    return {
      activeKey: ['0', '1', '2', '3'],
      antvDocUrl: 'https://www.antdv.com/docs/vue/introduce-cn/',
      antvpUrl: 'https://github.com/vueComponent/ant-design-vue-pro',
      elementUrl: 'https://github.com/PanJiaChen/vue-element-admin',
      elementDocUrl: 'https://panjiachen.gitee.io/vue-element-admin-site/zh/',
      thisProject: 'https://github.com/TyCoding/ant-design-vue-pro'
    }
  },
  computed: {
    ...mapGetters([
      'roles'
    ])
  },
  created() {
    // if (!this.roles.includes('admin')) {
    //   this.currentRole = 'editorDashboard'
    // }
  }
}
</script>

<style lang="less" scoped>
  a {
    color: #1890ff;
  }
</style>
