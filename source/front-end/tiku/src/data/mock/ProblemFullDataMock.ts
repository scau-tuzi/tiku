import ProblemFullData from '@/data/model/ProblemFullData'

let porblems : ProblemFullData[] =[{
    problem:{
        id: 1,
        problemText: '小明没有去吃饭，那小明现在饿不饿',
        parentId: 0,
        answerId: 1
    },
    answer: {
        id: 1,
        value: '饿了就吃饭了'  
    },
    tags:[{ 
        id: 2,
        value: '一年级',
        parentId: 0
      }, {
          id: 4,
          value: '基础',
          parentId: 0,
    }],
    extData: any,
    status: 1
}]
export default porblems;
