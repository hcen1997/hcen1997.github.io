---
layout: post
---
##  如何导入excel数据为json格式?
#####  1. 定义json数据值
> excelData: {<br>
> &nbsp;&nbsp;aaaaTable: {<br>
> &nbsp;&nbsp;&nbsp;&nbsp;index: 1,<br>
> &nbsp;&nbsp;&nbsp;&nbsp;title: 'title',<br>
> &nbsp;&nbsp;&nbsp;&nbsp;test_1: {m_1: 0, m_2: 0, v_1: 0, v_2: 0, r_f: 0},<br>
> &nbsp;&nbsp;&nbsp;&nbsp;test_2: {m_1: 0, m_2: 0, v_1: 0, v_2: 0, r_f: 0},<br>
> &nbsp;&nbsp;&nbsp;&nbsp;ava: 0,<br>
> &nbsp;&nbsp;&nbsp;&nbsp;diff: 0<br>
> &nbsp;&nbsp;}<br>
> }<br>
##### 2. 更改excel cell中的字符为对应json的路径<br>
!!!!!前面记得加上prefix<br>
例: json路径为:<br>
> excelData.aaaaTable.title<br>

则excel cell中写为<br>
> str_excelData.aaaaTable.title

tips: 可以使用idea中的快捷键copy reference path (ctrl+shift+alt+c)
##### 3. 导入excel数据为rawJson
> https://github.com/PanJiaChen/vue-element-admin/blob/master/src/views/excel/upload-excel.vue#handleSuccess
##### 4. 使用神奇函数
```
    let strPrefix = 'str_'
    let root = 'this.'
    /**
     * search excelData from gaven json.
     * @param obj obj to search
     * @param parent obj'name
     */
    export function dfs (obj, parent) {
      if (typeof obj === 'string' && (obj.startsWith(strPrefix))) {
        console.log(`${root}${obj.slice(strPrefix.length, obj.length)} = ${parent}`)
      } else if (obj instanceof Array) {
        for (let i = 0; i < obj.length; i++) {
          dfs(obj[i], `${parent}[${i}]`)
        }
      } else if (obj instanceof Object) {
        Object.keys(obj).forEach(key => {
          dfs(obj[key], `${parent}['${key}']`)
        })
      }
    }
```

##### 4. 打印导入代码

```
this.excelData.kfmdsyTable.title = result[0]['__EMPTY_1']
this.excelData.kfmdsyTable.test_1.m_1 = result[3]['__EMPTY_2']
this.excelData.kfmdsyTable.test_2.m_1 = result[4]['__EMPTY_2']
```

#### 5. 直接复制打印出来的代码
