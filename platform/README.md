# Platform
# 关于登录
客户端登录需要对用户的密码进行32位小写的md5加密
# 接口约定说明
- get 方法需要提供对应数据id来查询对应信息
- get_me 方法需要用户登录后，服务器端会自动匹配对应的id，客户端不需要携带参数，但是请求课程和教练信息时需要携带其对应id
- update_me 方法需要用户登录后，服务器端会自动匹配对应的id，客户端不需要携带参数，但是请求课程和教练信息时需要携带其对应id
- delete_me 方法需要用户登录后，服务器端会自动匹配对应的id，客户端不需要携带参数，但是请求课程和教练信息时需要携带其对应id
- add_me 方法需要用户登录后，服务器端会自动匹配对应的id，客户端不需要携带参数，但是请求课程和教练信息时需要携带其对应id
- list 方法需要通过gymId作为索引来查询相关数据
- list_me方法需要用户登录后，服务器端会自动匹配对应的gymId，客户端不需要携带参数
- list_group方法需要用户（需要是Group类型的用户）登录后，服务器端会自动匹配对应的gymIdList，客户端不需要携带参数
- list_all方法是查询所有，仅系统用户可查
# 接口术语
## gym_admin_user 后台管理员
## gym_class 健身课程
## gym_coach 健身教练
## gym_info 健身房信息
## gym_common 健身相关信息的一体化查询和删除工具
## gym_group_user 可以管理多个健身房的用户
## user 我们自己使用的系统用户

# 字段举例
```
健身房信息
+----+--------------------------------------------+-------------------------------+-----------------------------+---------------+--------------------------+------------------------------+--------+-------+--------------+
| id | address                                    | city                          | display_img_urls            | label         | logo_url                 | mini_program_code_url        | name   | phone | member_count |
+----+--------------------------------------------+-------------------------------+-----------------------------+---------------+--------------------------+------------------------------+--------+-------+--------------+
|  1 | 广东省深圳市南山区高新南四道               | 广东省-深圳市-南山区          | ["https://abc.com/log.jpg"] | 热情,人多     | https://abc.com/logo.jpg | https://abc.com/min_code.jpg | 测试   | 10086 |         1024 |
+----+--------------------------------------------+-------------------------------+-----------------------------+---------------+--------------------------+------------------------------+--------+-------+--------------+
健身课 state 0 为下线 1为上线 price_info-test代表体验价格
+----+----------------------+--------+---------------+--------------------+-------+
| id | content              | gym_id | price_info    | title              | state |
+----+----------------------+--------+---------------+--------------------+-------+
|  2 | <div>第一节</div>    |      1 | {"test":1000} | 第一堂体验课       |     0 |
+----+----------------------+--------+---------------+--------------------+-------+
健身教练
+----+--------+---------------------------+--------+
| id | gym_id | label                     | name   |
+----+--------+---------------------------+--------+
|  1 |      1 | 力大无穷,搬山卸岭         | 皮特   |
+----+--------+---------------------------+--------+
```

# 接口列表(具体参数值参考字段举例)
> 一期开发系统用户后台系统，只需要关注不是me和group结尾的接口  

> 二期开发健身房管理员,只关注me结尾的接口
- "[/login]:[String name, String password]", 需要对用户的密码进行32位小写的md5加密
- "[/api/user/add]:[String name, String password]",
- "[/api/user/verify]:[String name, String password]",
- "[/api/gym_admin_user/add]:[String name, String phone, String password, Long gym_id]",
- "[/api/gym_admin_user/update]:[Long id, String name, String phone, String password, Long gym_id]",
- "[/api/gym_admin_user/verify]:[Long gym_id, String name, String password]",
- "[/api/gym_class/add]:[String title, String price_info, String content, Long gym_id]",
- "[/api/gym_class/get]:[Long id]",
- "[/api/gym_class/update]:[Long id, String title, String price_info, String content, Long gym_id, Integer state]",
- "[/api/gym_class/delete]:[Long id]",
- "[/api/gym_class/list]:[Long gym_id]",
- "[/api/gym_class/get_me]:[Long id]",
- "[/api/gym_class/add_me]:[String title, String price_info, String content]",
- "[/api/gym_class/delete_me]:[Long id]",
- "[/api/gym_class/update_me]:[Long id, String title, String price_info, String content, Integer state]",
- "[/api/gym_class/list_all]:[]",
- "[/api/gym_class/list_me]:[]",
- "[/api/gym_coach/add]:[String name, String label, Long gym_id]",
- "[/api/gym_coach/get]:[Long id]",
- "[/api/gym_coach/update]:[Long id, String name, String label, Long gym_id]",
- "[/api/gym_coach/delete]:[Long id]",
- "[/api/gym_coach/list]:[Long gym_id]",
- "[/api/gym_coach/get_me]:[Long id]",
- "[/api/gym_coach/add_me]:[String name, String label]",
- "[/api/gym_coach/delete_me]:[Long id]",
- "[/api/gym_coach/update_me]:[Long id, String name, String label]",
- "[/api/gym_coach/list_all]:[]",
- "[/api/gym_coach/list_me]:[]",
- "[/api/gym_common/get]:[Long id]",
- "[/api/gym_common/delete]:[Long id]",
- "[/api/gym_common/get_me]:[]",
- "[/api/gym_group_user/add]:[String name, String phone, String password, String gym_id_array]",
- "[/api/gym_group_user/update]:[Long id, String name, String phone, String password, String gym_id_array]",
- "[/api/gym_group_user/verify]:[String name, String password]",
- "[/api/gym_info/add]:[String name, String city, String address, String label, String phone, String logo_url, String display_img_urls, String mini_program_code_url]",
- "[/api/gym_info/get]:[Long id]",
- "[/api/gym_info/get_me]:[]",
- "[/api/gym_info/list_group]:[]",
- "[/api/gym_info/list_all]:[]",
- "[/api/gym_info/update_me]:[Long member_count, String name, String city, String address, String label, String phone, String logo_url, String display_img_urls, String mini_program_code_url]",
- "[/api/gym_info/update]:[Long id, Long member_count, String name, String city, String address, String label, String phone, String logo_url, String display_img_urls, String mini_program_code_url]",
- "[/api/gym_info/delete]:[Long id]",