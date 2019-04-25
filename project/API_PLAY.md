# 教练及学员相关接口
## 基本约定
### 1. 请求
POST + http://host/path + body(json)
### 2. 响应
json
```json
{
  "code": 200,
  "message": "OK",
  "data": {
  
  }
}
```

## 接口列表
### 1. 教练
#### 数据结构
```
private Long id;
private Long gym_id;
private String name;
private String phone;
private Date join_time;
private String member_array;
```
member_array示例，对应是学员的id列表
```json
["1","2","3"]
```
#### 接口介绍
基础 path: /api/platform/gym/play/coach
-  /add 增加 参数为教练的结构体（没有id）,响应data为带id的教练结构体
-  /delete 删除 参数为教练的id，普通响应
-  /update 更新 参数为教练的结构体，响应data为带id的教练结构体
-  /list 查询列表 请求无参数，响应为当前健身房的教练列表
####
### 2. 学员
#### 数据结构
```
private Long id;
private String nick_name;
private String uid;
private String phone_num;
private String gym_name;
private Date bind_time;
```

- 基础 path: /api/platform/gym/play/member
- /list 查询当前健身房下的所有学员，无需参数
>