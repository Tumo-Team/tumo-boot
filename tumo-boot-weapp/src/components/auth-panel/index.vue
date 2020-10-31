<template>
  <div v-if="!token" class="unauthorized">
    <van-button @click.native="handleLogin" type="primary" round block>账户密码登录</van-button>
    <van-divider contentPosition="center" customStyle="border-color: #d9d9d9">或</van-divider>
    <van-button @click.native="handleWeChatLogin" :loading="wxLoading" open-type="getUserInfo" loading-text="登录中..." type="primary" round block>微信授权登录</van-button>
  </div>
</template>

<script>
import VanButton from '@/wxcomponents/vant/button'
import VanDivider from '@/wxcomponents/vant/divider'
export default {
  name: "index",
  components: {
    VanButton,
    VanDivider
  },
  props: {
    token: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      wxLoading: false
    }
  },
  methods: {
    handleLogin() {
      this.$Router.replaceAll('login')
    },
    handleWeChatLogin() {
      this.wxLoading = true
      this.$store.dispatch('user/wxLogin').then(res => {
        this.wxLoading = false
        // 路由到首页
        this.$Router.replaceAll({
          name: 'index'
        })
      }).catch((error) => {
        this.wxLoading = false
        console.apiLog('login error', error)
      })
    }
  }
}
</script>

<style scoped>
.unauthorized{
  text-align: center;
  margin: 50% 10%;
}
</style>
