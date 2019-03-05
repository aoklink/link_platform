# Platform
# 接口约定说明
- get 方法需要提供对应数据id来查询对应信息
- get_me 方法需要用户登录后，服务器端会自动匹配对应的id，客户端不需要携带参数
- list 方法需要通过gymId作为索引来查询相关数据
- list_me方法需要用户登录后，服务器端会自动匹配对应的gymId，客户端不需要携带参数
- list_group方法需要用户（需要是Group类型的用户）登录后，服务器端会自动匹配对应的gymIdList，客户端不需要携带参数
- list_all方法是查询所有，登录用户均可查询,客户端不需要携带参数
# 接口术语
## gym_admin_user 后台管理员
## gym_class 健身课程
## gym_coach 健身教练
## gym_info 健身房信息
## gym_common 健身相关信息的一体化查询和删除工具
## gym_group_user 可以管理多个健身房的用户
## user 我们自己使用的系统用户

# 接口列表
- "/api/user/add",
- "/api/user/verify",
- "/api/gym_admin_user/update",
- "/api/gym_admin_user/add",
- "/api/gym_admin_user/verify",
- "/api/gym_class/add",
- "/api/gym_class/get",
- "/api/gym_class/update",
- "/api/gym_class/delete",
- "/api/gym_class/list",
- "/api/gym_class/list_all",
- "/api/gym_class/list_me",
- "/api/gym_coach/add",
- "/api/gym_coach/get",
- "/api/gym_coach/update",
- "/api/gym_coach/delete",
- "/api/gym_coach/list",
- "/api/gym_coach/list_all",
- "/api/gym_coach/list_me",
- "/api/gym_common/get",
- "/api/gym_common/delete",
- "/api/gym_common/get_me",
- "/api/gym_group_user/update",
- "/api/gym_group_user/add",
- "/api/gym_group_user/verify",
- "/api/gym_info/add",
- "/api/gym_info/list_group",
- "/api/gym_info/update",
- "/api/gym_info/list_all",
- "/api/gym_info/get_me",
- "/api/gym_info/get",
- "/api/gym_info/delete",