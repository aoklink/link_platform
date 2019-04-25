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
### 1. 教练与健身房绑定关系
#### 数据结构-教练
```roomsql
CREATE TABLE `coach_account` (
  `phone_num` varchar(11) NOT NULL COMMENT '手机号',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `head_icon` varchar(256) DEFAULT NULL,
  `uid` varchar(32) NOT NULL COMMENT '教练id',
  `user_type` varchar(32) NOT NULL,
  `build_time` varchar(32) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
#### 数据结构-教练与健身房绑定关系
```
private String coach_uid;
private Long gym_id;
private Date bind_time;
```
#### 接口介绍
基础 path: /api/platform/gym/play/coach
-  /get 查询教练是否存在，若存在返回教练结构体
-  /bind 绑定教练至当前健身房，参数为{"coach_uid":""}
-  /unbind 解绑教练，参数为{"coach_uid":""}
-  /list 查询列表 请求无参数，响应为当前健身房的教练列表
####


### 2. 学员

#### 数据结构-学员
```
private Long id;
private String nick_name;
private String uid;
private String phone_num;
private String gym_name;
private Date bind_time;
```

#### 数据结构-教练与学员绑定关系
```
private String coach_uid;
private Long student_uid;
private String student_nick;
private Long gym_id;
private Date bind_time;
```
#### 接口介绍
- 基础 path: /api/platform/gym/play/member
- /list 查询当前健身房下的所有学员，无需参数
- /trans 学员转移，参数为{"student_uid":"","from_coach_uid":"(可空)","to_coach_uid":""}