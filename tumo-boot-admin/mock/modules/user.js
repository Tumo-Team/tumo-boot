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
      return {
        code: 200,
        data: {
          token: 'xxxxxxxxxxxxxxxxxxx'
        }
      }
    }
  },

  // 获取用户信息接口
  {
    url: '/user/info\.*',
    type: 'get',
    response: config => {
      return {
        code: 200,
        data: {
          username: 'Super Admin',
          phone: '193813283928',
          roles: ['ADMIN'],
          status: '激活',
          avatar: 'https://tycoding.cn/images/avatar.jpg'
        }
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
  },

  // 用户列表数据
  {
    url: '/user/list',
    type: 'post',
    response: _ => {
      return {
        code: 200,
        data: {
          total: 4,
          rows: [
            {
              id: 1,
              username: 'admin',
              avatar: 'https://tycoding.cn/images/avatar.jpg',
              roles: ['管理员', '架构师'],
              sex: '男'
            },
            {
              id: 2,
              username: '涂陌',
              avatar: 'https://tycoding.cn/images/avatar.jpg',
              roles: ['管理员', '架构师'],
              sex: '男'
            },
            {
              id: 3,
              username: 'TyCoding',
              avatar: 'https://tycoding.cn/images/avatar.jpg',
              roles: ['管理员', '架构师'],
              sex: '男'
            },
            {
              id: 4,
              username: 'TyCoding2',
              avatar: 'https://tycoding.cn/images/avatar.jpg',
              roles: ['管理员', '架构师'],
              sex: '男'
            }
          ]
        }
      }
    }
  }
]
