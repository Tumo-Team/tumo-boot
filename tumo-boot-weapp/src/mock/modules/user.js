const Mock = require('better-mock/dist/mock.mp.js')

/**
 * 登录接口
 */
Mock.mock('/dev-api/login', 'post', {
    code: 200,
    msg: 'success',
    data: {
        username: 'admin',
        password: 'admin',
        phone: '1898238989'
    }
})

/**
 * 微信登录接口
 */
Mock.mock('/dev-api/login/wx', 'get', {
  code: 200,
  msg: 'success',
  data: {
    token: 'xxxxxxxxxxxx',
    username: 'admin',
    password: 'admin',
    phone: '1898238989'
  }
})
export default Mock
