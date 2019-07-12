// vue.config.js
module.exports = {
    // 修改的配置
    // baseUrl: '/web/',
    devServer: {
        proxy:'http://localhost:10010/'
    },
    outputDir:"../../back-end/tiku/src/main/resources/static"
};
