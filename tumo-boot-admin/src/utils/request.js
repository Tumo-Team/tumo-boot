import axios from 'axios'
import { Modal, message } from 'ant-design-vue'
import store from '@/store'
import { getToken } from '@/utils/auth'

// create an axios instance
const service = axios.create({
  // process.env是从当前env中获取 // 在vue-cli中，可以在项目根目录设置`.env.xxx`来设置这个参数值
  baseURL: process.env.VUE_APP_BASE_API,
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 5000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    // 在请求之前拦截，判断store中是否存储了token
    if (store.getters.token) {
      // 如果存储了token，就将token设置在request header中，保证每次发送request，header中都包含token
      config.headers['Authorization'] = getToken()
    }
    return config
  },
  error => {
    // do something with request error
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  /**
   *  response format：
   *  {
   *    "code": "200",
   *    "data": {},
   *    "msg": "success"
   *  }
   *  如果response format不是上述，请自行修改下方：`res.code`、`res.data`、`res.msg`
   */
  response => {
    const res = response.data

    // 根据response code判断请求是否成功
    if (res.code !== 200) {
      message.error(
        res.msg || 'Error',
        4
      )

      // 对特殊的code自定义处理
      if (res.code === 401 || res.code === 403) {
        // 重新登录
        Modal.warning({
          title: 'Confirm logout',
          content: 'You have been logged out, you can cancel to stay on this page, or log in again',
          okText: 'Re-Login',
          cancelText: 'Cancle',
          onOk: () => {
            store.dispatch('user/resetToken').then(() => {
              location.reload()
            })
          }
        })
      }
      return Promise.reject(new Error(res.msg || 'Error'))
    } else {
      return res
    }
  },
  error => {
    console.log('err' + error) // for debug
    message.error(
      error.message,
      4
    )
    return Promise.reject(error)
  }
)

export default service
