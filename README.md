# tiku
一个题库系统



## build

```bash
# 构建前端静态文件
cd web
npm build #或 yarn build

# 打包
cd ..
mvn package
#在target目录下生成jar包,直接执行即可
java -jar ./target/tiku-xxx.jar  
```



## 目录结构

* `\web` 目录为前端项目的根目录

