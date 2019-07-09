// vue.config.js
module.exports = {
    // 修改的配置
    baseUrl: '/web/',
    devServer: {
        proxy:'https://api.github.com/'
    },
    outputDir:"../../back-end/tiku/src/main/resources/static"
};
