const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      '/api/*': {
        target: 'http://localhost:8088'
      }
    }
  },
  configureWebpack: {
    entry: {
      app: './src/main.js',
      style: ['bootstrap/dist/css/bootstrap.min.css']
    }
  }
})
