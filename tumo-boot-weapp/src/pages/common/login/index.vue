<template>
  <div class="login-container">
    <div class="login-form">
      <van-field
          v-model="form.username"
          placeholder="请输入用户名"
          input-align="center"
      />
      <van-field
          v-model="form.password"
          placeholder="请输入密码"
          type="password"
          input-align="center"
      />
      <van-button @click.native="handleLogin" :loading="loading" round block loading-text="登录中..." type="primary">登录</van-button>
    </div>
    <div class="login-footer">
      <label>微信授权或者登陆后可获得正常服务</label>
    </div>
    <van-notify id="van-notify" />
  </div>
</template>

<script>
import VanButton from '@/wxcomponents/vant/button'
import VanField from '@/wxcomponents/vant/field'
import VanNotify from '@/wxcomponents/vant/notify'

export default {
  components: {
    VanButton,
    VanField,
    VanNotify
  },
  name: "index",
  data() {
    return {
      loading: false,
      form: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    handleLogin() {
      this.loading = true
      if (this.form.username.trim() === "" || this.form.password.trim() === "") {
        this.loading = false
        VanNotify({
          type: 'warning',
          message: '请输入用户名或密码'
        })
        return;
      }
      this.$store.dispatch('user/login', this.form).then(res => {
        this.loading = false
        // 路由到首页
        this.$Router.replaceAll({
          name: 'index'
        })
      }).catch((error) => {
        this.loading = false
        console.apiLog('login error', error)
      })
    }
  }
}
</script>

<style scoped>
.login-container {
  padding: 20px;
}
.login-form{
  padding-top: 50px;
}
.login-form van-button{
  padding: 10px 0;
  padding-top: 20px;
  display: block;
  text-align: center;
}
.login-footer {
  padding-bottom: 20px;
  bottom: 0;
  position: absolute;
  width: 90%;
  text-align: center;
}
.login-footer label{
  padding-top: 4px;
  font-size: 13px;
  color: grey;
}
</style>
