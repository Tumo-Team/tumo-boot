let BASE_URL = ''
if (process.env.NODE_ENV === 'development') {
  // dev
	BASE_URL = 'http://192.168.1.165:8080'
} else {
	// prod
	BASE_URL = 'http://127.0.0.1:8080'
}

const config = {
	BASE_URL: BASE_URL
}

export {
	config
}
