# Spring-vue3 연습

[참고소스](https://github.com/wikibook/spring-vuejs)

## 부트스트랩 설치
```bash
npm install jquery @popperjs/core bootstrap --save
```

`vue.config.js` 파일 변경
```js
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
```