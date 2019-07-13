import FieldInfo from "@/data/model/FieldInfo"
export let AllFieldInfo:FieldInfo={
    "choice_A":{
        title:"A选项",
    },
    "choice_B":{
        title:"B选项",
    },
    "choice_C":{
        title:"C选项",
    },
    "choice_D":{
        title:"D选项",
    },
};

let BasicFieldList:FieldInfo={
    "problemText":{
        title:"题目",
        type:"LongText"
    },
    "answerText":{
        title:"答案",
        type:"LongText"
    },
    "tags":{
        title:"标签",
        type:"enum",
        ext:"tag"
    }
};

export default {
    AllFieldInfo,
    BasicFieldList
};