# 本工程对外接口说明

## 登录接口
### 接口地址
/api/account/platform/login
### 参数
#### 类型：form-data
#### 参数列表
name
password 需要md5(32位小写)
### 响应
```json
{
    "code": 200,
    "message": "OK",
    "data": {
        "user": {
            "gym_id": 1, 
            "password": "******",
            "phone": "123456789",
            "name": "hello",
            "id": 1
        },
        "authorities": [
            "ROLE_GYM_ADMIN"
        ]
    }
}

```
（注：gym_id是用户是店铺管理员的时候会有）
## 健身房信息相关接口
### 接口地址
/api/platform/gym_info
### 系统管理员接口支持操作
/add,/update,/get,/list_all

### 店铺管理员接口支持操作
/update_me,/get_me
### 单个实体示例
```json
{
    "code": 200,
    "data": {
        "address": "XX省",
        "city": "XX省-XX市-XX区",
        "phone": "XX",
        "logo_url": "https://abc.com/abc.jpg",
        "mini_program_code_url": "https://abc.com/abc.jpg",
        "display_img_urls": "[\"https://abc.com/abc.jpg\"]",
        "name": "测试",
        "id": 1,
        "label": "热情,人多",
        "member_count": 1024
    },
    "message": "OK"
}
```

### 列表实体示例
```json
{
    "code": 200,
    "data": [
        {
            "gym_info": {
                "address": "sdeewq",
                "city": "zhenchenxccd",
                "phone": "23232",
                "logo_url": "https://link-imgs.oss-cn-hangzhou.aliyuncs.com/link-platform/1552294756406.jpg",
                "mini_program_code_url": "https://link-imgs.oss-cn-hangzhou.aliyuncs.com/link-platform/1552297474062125.jpg",
                "name": "test2",
                "id": 2,
                "label": "sde,sdwe",
                "member_count": 0
            },
            "gym_admin_user": {
                "gym_id": 2,
                "password": "******",
                "phone": "17012341236",
                "name": "test2",
                "id": 5
            }
        },
        {
            "gym_info": {
                "address": "3",
                "city": "2",
                "phone": "122",
                "logo_url": "",
                "mini_program_code_url": "",
                "name": "sd",
                "id": 5,
                "label": "",
                "member_count": 0
            }
        }
    ],
    "message": "OK"
}
```

## 健身课程信息相关接口
### 接口地址
/api/platform/gym_class
### 系统管理员接口支持操作
/add,/update,/get,/list
> get 接口使用自身id, list 接口使用gym_id(健身房id)
### 店铺管理员接口支持操作
/update_me,/get_me,/add_me,/delete_me,/list_me
> 增删改查只需携带 课程id， list_me不需要参数
### 单个实体
```json
{
    "code": 200,
    "data": {
        "price_info": "{\"test\":1000}",
        "gym_id": 1,
        "id": 2,
        "state": 0,
        "title": "第一堂体验课",
        "content": "<div>第一节</div>"
    },
    "message": "OK"
}
```

### 列表实体
```json
{
    "code": 200,
    "data": [
        {
            "price_info": "{\"test\":1000}",
            "gym_id": 1,
            "id": 2,
            "state": 0,
            "title": "第一堂体验课"
        },
        {
            "price_info": "{\"test\":300}",
            "gym_id": 1,
            "id": 3,
            "state": 0,
            "title": "第二堂"
        }
    ],
    "message": "OK"
}
```
> state 0 代表下线 1代表上线
> price_info {\"test\":300} test 表示体验价，暂时没有其他意义


## 健身教练信息相关接口
### 接口地址
/api/platform/gym_coach
### 系统管理员接口支持操作
/add,/update,/get,/list
> get 接口使用自身id, list 接口使用gym_id(健身房id)
### 店铺管理员接口支持操作
/update_me,/get_me,/add_me,/delete_me,/list_me

### 实体(单个和列表相同)
```json
{
    "code": 200,
    "data": [
        {
            "gym_id": 1,
            "name": "鹧鸪哨",
            "id": 1,
            "label": "搬山,打怪"
        }
    ],
    "message": "OK"
}
```