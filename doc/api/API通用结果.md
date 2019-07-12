# API通用结果

所有API都会返回这个json

- 查询结果

完整样例



```json
{
  "status":"ok",
  "problems":[
    {
      "proplemId":123,
      "properties":{
        "grade":"一年级",
        "subject":"小学数学",
        "sorce":5.5,
        "stem":"请写出下列数字的相反数",
        "caption":"1. 5 \n 2. 6 \n 3. -1 \n",
        "answer":"[-5,-6,1]
      }
    },
    {
      "proplemId":124,
      "properties":{
        "grade":"一年级",
        "subject":"小学数学",
        "sorce":5.5,
        "stem":"请写出下列数字的相反数",
        "caption":"1. 5 \n 2. 6 \n 3. -1 \n",
        "answer":"[-5,-6,1]
      }
    }],
  "pagination":{
    "page":0,
    "size":10,
    "total":1
  }
}
```