const Mock = require('better-mock/dist/mock.mp.js')

/**
 * 获取文章标题列表接口
 */
Mock.mock('/dev-api/article/title/list', 'get', {
    code: 200,
    msg: 'success',
    data: [
      {
        id: 1,
        title: 'SpringBoot入门开发',
        tags: ["SpringBoot", "Spring"],
        createTime: '2020-10-01 00:00:00'
      },
      {
        id: 2,
        title: 'SpringBoot整合Mybatis',
        tags: ["SpringBoot", "Mybatis"],
        createTime: '2020-10-01 00:00:00'
      },
      {
        id: 3,
        title: 'SpringBoot整合Mybatis-Plus',
        tags: ["SpringBoot", "Mybatis", "Mybatis-Plus"],
        createTime: '2020-10-01 00:00:00'
      },
      {
        id: 4,
        title: 'SpringBoot整合Thymeleaf',
        tags: ["SpringBoot", "Thymeleaf", "Mybatis", "Mybatis-Plus"],
        createTime: '2020-10-01 00:00:00'
      },
      {
        id: 5,
        title: 'SpringBoot整合Spring Data JPA',
        tags: ["SpringBoot", "JPA"],
        createTime: '2020-10-01 00:00:00'
      },
      {
        id: 6,
        title: 'SpringBoot整合Spring Data Redis',
        tags: ["SpringBoot", "Redis"],
        createTime: '2020-10-01 00:00:00'
      },
      {
        id: 7,
        title: 'SpringBoot整合WebSocket',
        tags: ["SpringBoot", "WebSocket"],
        createTime: '2020-10-01 00:00:00'
      },
      {
        id: 8,
        title: 'SpringBoot整合Mail',
        tags: ["SpringBoot", "Mail"],
        createTime: '2020-10-01 00:00:00'
      },
      {
        id: 9,
        title: 'SpringBoot整合Spring Security',
        tags: ["SpringBoot", "Mybatis", "Spring Security"],
        createTime: '2020-10-01 00:00:00'
      }
    ]
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
