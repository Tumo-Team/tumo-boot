module.exports = [
  // 文章列表数据
  {
    url: '/article/list',
    type: 'post',
    response: _ => {
      return {
        code: 200,
        data: {
          total: 3,
          rows: [
            {
              id: 1,
              username: 'admin',
              avatar: 'https://tycoding.cn/images/avatar.jpg',
              sex: '男'
            },
            {
              id: 2,
              username: '涂陌',
              avatar: 'https://tycoding.cn/images/avatar.jpg',
              sex: '男'
            },
            {
              id: 3,
              username: 'TyCoding',
              avatar: 'https://tycoding.cn/images/avatar.jpg',
              sex: '男'
            },
            {
              id: 4,
              username: 'TyCoding2',
              avatar: 'https://tycoding.cn/images/avatar.jpg',
              sex: '男'
            }
          ]
        }
      }
    }
  }
]
