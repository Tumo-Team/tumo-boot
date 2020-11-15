let BASE_URL = ''
if (process.env.NODE_ENV === 'development') {
  // dev
	BASE_URL = 'http://192.168.124.12:8080'
} else {
	// prod
	BASE_URL = 'http://39.105.46.235:8080'
}

const config = {
	BASE_URL: BASE_URL
}

export {
	config
}
