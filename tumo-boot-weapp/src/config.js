// 判断开发环境和生产环境
let BASE_URL = ''
let BURY_URL = ''
if (process.env.NODE_ENV === 'development') {
	BASE_URL = 'http://127.0.0.1:8080' //dev
	BURY_URL = 'http://39.105.46.235:8080' //测试使用服务地址
} else {
	// 生产环境，这里用我自己的服务器做演示
	BASE_URL = 'http://39.105.46.235:8080' //prod
	BURY_URL = 'http://127.0.0.1:8080'
}

const config = {
	BASE_URL: BASE_URL,
	BURY_URL: BURY_URL
}

export {
	config
}
