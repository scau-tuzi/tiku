// vue.config.js
module.exports = {
    // 修改的配置
    // baseUrl: '/web/',
    devServer: {
        proxy:'http://203.195.239.100:10010/'
        // proxy:'http://localhost:10010/'
    },
    outputDir:"../../back-end/tiku-copy/src/main/resources/static"
};
