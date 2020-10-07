// 模拟用户数据相关接口

const tokens = {
  admin: {
    token: 'admin-token'
  },
  editor: {
    token: 'editor-token'
  }
}

const users = {
  'admin-token': {
    roles: ['admin'],
    introduction: 'I am a super administrator',
    avatar: 'https://tycoding.cn/images/avatar.jpg',
    name: 'Super Admin'
  },
  'editor-token': {
    roles: ['editor'],
    introduction: 'I am an editor',
    avatar: 'https://tycoding.cn/images/avatar.jpg',
    name: 'Normal Editor'
  }
}

module.exports = [
  // 用户登录接口
  {
    url: '/login',
    type: 'post',
    response: config => {
      const { username } = config.body
      // 根据username在tokens数组中拿到一个Token
      const token = tokens[username]

      // 如果请求拿不到token的情况
      if (!token) {
        return {
          code: 401,
          message: '用户名或密码错误.'
        }
      }

      return {
        code: 200,
        data: token
      }
    }
  },

  // 获取用户信息接口
  {
    url: '/user/info\.*',
    type: 'get',
    response: config => {
      const { token } = config.query
      // 从config.query中拿到Token值，再从users数组中拿到对应的info
      // 这个token是在用户登录后已经缓存到Cookie中的
      // config就是mock.js响应response
      const info = users[token]

      // 请求拿不到User info的情况
      if (!info) {
        return {
          code: 500,
          message: '登录失败，无法获取用户信息.'
        }
      }

      return {
        code: 200,
        data: info
      }
    }
  },

  // 注销
  {
    url: '/logout',
    type: 'post',
    response: _ => {
      return {
        code: 200,
        data: 'success'
      }
    }
  }
]
