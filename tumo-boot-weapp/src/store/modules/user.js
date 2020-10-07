import {login, wxLogin, logout, getInfo} from '@/api/user.js'
import {getToken, setToken, removeToken} from '@/utils/auth'

const state = {
  token: getToken(),
  name: '',
  avatar: ''
}

const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  }
}

const actions = {
  // wx login
  /**
   * 响应数据结构
   * {
   *     "nickName": ""
   *     "gender": 1
   *     "language": ""
   *     "city": ""
   *     "province": ""
   *     "country": ""
   *     "avatarUrl": ""
   * }
   *
   * `uni.authorize`: 在用户未授权情况下调用会弹出授权框，已授权情况下不会弹出
   * `uni.getUserInfo`: 在未授权情况下直接走fail方法不会弹窗，已授权情况下才能正常拿到数据
   */
  wxLogin({commit}) {
    return new Promise((resolve, reject) => {
      uni.authorize({
        scope: 'scope.userInfo',
        success(res) {
          // get wx code
          uni.login({
            provider: 'weixin',
            success: function (codeRes) {
              console.log(codeRes)
              // to request wxApi and get openID
              wxLogin(codeRes.code).then(loginRes => {
                console.log(loginRes)
                if (loginRes.code === 200) {
                  uni.getUserInfo({
                    success(info) {
                      console.log('用户信息', info.userInfo)
                      commit('SET_TOKEN', loginRes.data.token)
                      commit('SET_NAME', info.userInfo.nickName)
                      commit('SET_AVATAR', info.userInfo.avatarUrl)
                    }
                  })
                } else {
                  // get openId error
                  uni.showToast({
                    title: loginRes.data.msg,
                    duration: 5000
                  })
                  reject(loginRes)
                }
              })
              resolve()
            },
            fail: function (error) {
              console.log('wx login error', error)
              uni.showToast({
                title: '微信登录出错，请稍后重试~',
                duration: 5000
              })
              reject(error)
            }
          })
          resolve()
        },
        fail(error) {
          console.log(error)
          reject(error)
        }
      })
    })
  },

  // user login
  login({commit}, userInfo) {
    const {username, password} = userInfo
    return new Promise((resolve, reject) => {
      login({
        username: username,
        password: password
      }).then(res => {
        if (res.code === 200 && res.data != null) {
          commit('SET_TOKEN', res.data.token)
          commit('SET_NAME', res.data.user.username)
          commit('SET_AVATAR', res.data.user.avatar)
          setToken(res.data.token)
        }
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // get user info
  getInfo({commit}) {
    return new Promise((resolve, reject) => {
      getInfo().then(response => {
        const {data} = response
        if (!data) {
          reject('Verification failed, please Login again.')
        }
        commit('SET_NAME', data.user.username)
        commit('SET_AVATAR', data.user.avatar)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({commit}) {
    return new Promise((resolve, reject) => {
      logout().then(() => {
        commit('SET_TOKEN', '')
        commit('SET_NAME', '')
        commit('SET_AVATAR', '')
        removeToken()
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({commit}) {
    return new Promise(resolve => {
      commit('SET_TOKEN', '')
      removeToken()
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
