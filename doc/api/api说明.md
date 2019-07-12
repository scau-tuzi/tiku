这是华南农业大学2019年度暑期实训的 **题库系统** 管理后台使用文档与接口说明！

**注意：还没有形成正式文档，不保证接口的稳定性！**

## 二、增加题目

### 接口`/addProblem`

- 请求方法 `post`

参数：

```json
{
  "poolId":"4468c74d-759e-4d78-8c43-e1c5405f193b",
  "token":"06e599f3-78db-4c71-b4fa-2b496beab1f6",
  "properties":{
    "tags":["一年级","小学数学"],
    "sorce":5.5,
    "stem":"请写出下列数字的相反数\n1. 5 \n 2. 6 \n 3. -1 \n",
    "answer":"[-5,-6,1]"
  }
}
    
```

- 参数说明
- 

| 属性名     | 类型     | 样例                                                         | 说明                                              | 是否必须 |
| ---------- | -------- | ------------------------------------------------------------ | ------------------------------------------------- | -------- |
| poolId     | 字符串   | "4468c74d-759e-4d78-8c43-e1c5405f193b"                       | 题库对应的id，从管理平台获取                      | 是       |
| token      | 字符串   | "06e599f3-78db-4c71-b4fa-2b496beab1f6"                       | 验证用户权限的OAuth令牌，从管理平台申请，暂时无用 | 是       |
| properties | json对象 | "properties":{    "tags":["一年级","小学数学"],    "sorce":5.5,    "stem":"请写出下列数字的相反数\n1. 5 \n 2. 6 \n 3. -1 \n",    "answer":"[-5,-6,1]"  } | 新增题目的信息                                    | 是       |

返回结果：

通用结果

## 三、查询

### 接口 `/queryProblem`

- 请求方法 `post`

- 原型
  - 

```typescript
class UUID extends string{}

interface sorted {
   type:string;
    fieldName:string;
    next:sorted;
}
interface op {
   operator:string;
   argument1:op|string;
   argument2:op|string|number|any[];
}
interface query {
  poolId:UUID;
  token:UUID;
  querier:op; 
  random:bool;
  ordering:sorted;
  pagination:{
    page:number,
    size:number,
  }
}
```

* 样例

```
{
  "poolId":"4468c74d-759e-4d78-8c43-e1c5405f193b",
  "token":"06e599f3-78db-4c71-b4fa-2b496beab1f6",
  "querier":{
    "operator":"and",
    "argument1":{
      "operator":"==",
      "argument1":"grade",
      "argument2":"一年级"
    },
    "argument2":{
      "operator":"<=",
      "argument1":"sroce",
      "argument2":"10"
    }
  },
  "random":false,
  "ordering":{
    "type":"desc",
    "fieldName":"sroce",
    "next":{}
  },
  "pagination":{
    "page":0,
    "size":10,
  }
}
    
```

- 参数说明

| 属性名   | 类型      | 样例                                                         | 说明                                                         | 是否必须 |
| -------- | --------- | ------------------------------------------------------------ | ------------------------------------------------------------ | -------- |
| poolId   | 字符串    | "4468c74d-759e-4d78-8c43-e1c5405f193b"                       | 题库对应的id，从管理平台获取                                 | 是       |
| token    | 字符串    | "06e599f3-78db-4c71-b4fa-2b496beab1f6"                       | 验证用户权限的，从管理平台申请，暂时无用                     | 是       |
| querier  | json对象  | {   "operator":"and",   "argument1":{     "operator":"==",     "argument1":"grade",     "argument2":"一年级" 	},   "argument2":{     "operator":"<=",     "argument1":"sroce",     "argument2":"10"   } } | 要查询执行的操作，类型是一个 **操作表达式对象** ，每个 **操作表达式** 对象包括 operator (操作名),argument1/2 参数。同时参数也可以是另外的一个表达式对象。样例的查询等价于 ((grade == "一年级") and (sroce <= 10)) | 否       |
| ordering | jsond对象 | {   "type":"desc",   "fieldName":"sroce",   "next":{} },     | 排序方式，type 有desc 和asce 两种，next是下一级的排序项      | 否       |
| random   | boolean   | false                                                        | 结果是否随机，默认false，和ordering不能共存                  | 否       |

- 返回结果

