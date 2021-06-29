module.exports = {
	devServer: {
		port: 9090, // 端口号
		host: 'localhost',
		proxy: {
			'/api': {
				target: 'http://localhost:8080',
				changOrigin: true
			}
		}
	}
}
