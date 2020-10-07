const Mock = require('better-mock/dist/mock.mp.js')

/**
 * 商品列表接口
 */
Mock.mock('/dev-api/goods/list', 'post', {
    code: 200,
    msg: 'success',
    data: [

    ]
})

export default Mock
