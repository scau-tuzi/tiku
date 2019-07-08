// vue.config.js
module.exports = {
    // 修改的配置
    // 将baseUrl: '/api',改为baseUrl: '/',
    devServer: {
        proxy:'https://api.github.com/'
        }
    };
