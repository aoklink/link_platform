package com.linkfeeling.platform.data.link;

import com.alibaba.fastjson.JSONObject;
import com.linkfeeling.platform.data.link.bean.GymBraceletBindItem;
import com.linkfeeling.platform.data.link.bean.MemberItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ：zl
 * @date ：Created in 2019/4/4 13:24
 * @description：link
 * @modified By：
 * @version: 0.0.1$
 */
@Repository
public class LinkPlatformComponent {
    Logger logger = LoggerFactory.getLogger(LinkPlatformComponent.class);
    //健身房会员表
    private final String GYM_BIND_MEMBERS = "gym_bind_members";
    //健身房手环配置库
    private final String GYM_BRACELET_TABLE = "gym_bracelet_list";
    //健身房手环绑定信息
    private final String GYM_BRACELET_BIND_USER = "gym_bracelet_bind_user";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean isBraceletBeyondGym(String gym_name, String bracelet_id) {
        final StringBuilder builder = new StringBuilder();
        try {
            builder.append("select *  from ");
            builder.append(GYM_BRACELET_TABLE);
            builder.append(" where gym_name = ").append("'").append(gym_name).append("'");
            builder.append(" and bracelet_id = ").append("'").append(bracelet_id).append("'");

            List<String> list = jdbcTemplate.query(builder.toString(), new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getString("bracelet_id");
                }
            });
            if (null == list || list.isEmpty()) {
                return false;
            }
        } catch (Exception e) {
            logger.error("查询手环是否存在异常,exception:{}", e);
            return false;
        }
        return true;
    }

    /**
     * 验证手环是否已经绑定
     *
     * @param gym_name
     * @param bracelet_id
     * @return
     */
    public String verifyBraceletidBind(String gym_name, String bracelet_id) {
        final StringBuilder builder = new StringBuilder();
        try {
            builder.append("select *  from ");
            builder.append(GYM_BRACELET_BIND_USER);
            builder.append(" where gym_name = ").append("'").append(gym_name).append("'").append(" and ")
                    .append(" bracelet_id = ").append("'").append(bracelet_id).append("'").append(" ORDER BY bind_time DESC limit 1");
            logger.info("verifyBraceletidBind, query bind info sql:{}", builder.toString());
            List<JSONObject> list = jdbcTemplate.query(builder.toString(), new RowMapper<JSONObject>() {
                @Override
                public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("bracelet_id", resultSet.getString("bracelet_id"));
                    jsonObject.put("gym_name", resultSet.getString("gym_name"));
                    String status = resultSet.getString("status");
                    if ("0".equals(status)) {
                        jsonObject.put("status", true);
                    } else {
                        jsonObject.put("status", false);
                    }
                    jsonObject.put("user_name", resultSet.getString("user_name"));
                    jsonObject.put("phone_num", resultSet.getString("phone_num"));
                    jsonObject.put("uid", resultSet.getString("uid"));
                    jsonObject.put("bind_time", resultSet.getString("bind_time"));
                    jsonObject.put("unbind_time", resultSet.getString("unbind_time"));
                    jsonObject.put("calorie", resultSet.getString("calorie"));
                    return jsonObject;
                }
            });
            if (!list.isEmpty()) {
                return list.get(0).toJSONString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 绑定手环后数据入库
     */
    @Async
    public void saveBindInfoToSQL(String gym_name, String braceletId, String userName, String phoneNum, String uid, String bindTime, long timeStamp, String unbindTime, String calorie) {
        try {
            final StringBuilder builder = new StringBuilder();
            builder.append("insert into ");
            builder.append(GYM_BRACELET_BIND_USER);
            builder.append("(");
            builder.append(GymBraceletBindItem.GYM_NAME);
            builder.append(",");
            builder.append(GymBraceletBindItem.BRACELET_ID);
            builder.append(",");
            builder.append(GymBraceletBindItem.STATUS);
            builder.append(",");
            builder.append(GymBraceletBindItem.USER_NAME);
            builder.append(",");
            builder.append(GymBraceletBindItem.PHONE_NUM);
            builder.append(",");
            builder.append(GymBraceletBindItem.UID);
            builder.append(",");
            builder.append(GymBraceletBindItem.BIND_TIME);
            builder.append(",");
            builder.append(GymBraceletBindItem.BIND_TIMESTAMP);
            builder.append(",");
            builder.append(GymBraceletBindItem.UNBIND_TIME);
            builder.append(",");
            builder.append(GymBraceletBindItem.CALORIE);
            builder.append(") values ");
            builder.append("(");
            builder.append("'");
            builder.append(gym_name);
            builder.append("'");
            builder.append(",");
            builder.append("'");
            builder.append(braceletId);
            builder.append("'");
            builder.append(",");
            builder.append("'");
            builder.append(1);
            builder.append("'");
            builder.append(",");
            builder.append("'");
            builder.append(userName);
            builder.append("'");
            builder.append(",");
            builder.append("'");
            builder.append(phoneNum);
            builder.append("'");
            builder.append(",");
            builder.append("'");
            builder.append(uid);
            builder.append("'");
            builder.append(",");
            builder.append("'");
            builder.append(bindTime);
            builder.append("'");
            builder.append(",");
            builder.append("'");
            builder.append(timeStamp);
            builder.append("'");
            builder.append(",");
            builder.append("'");
            builder.append(unbindTime);
            builder.append("'");
            builder.append(",");
            builder.append("'");
            builder.append(calorie);
            builder.append("'");
            builder.append(")");

            logger.info("saveBindInfoToSQL,sql:{}", builder.toString());
            jdbcTemplate.execute(builder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据UID查询信息
     *
     * @param uid
     * @return
     */
    public String getUserBindBraceletInfo(String uid, String bracelet_id) {
        final StringBuilder builder = new StringBuilder();
        try {
            builder.append("select *  from ");
            builder.append(GYM_BRACELET_BIND_USER);
            builder.append(" where uid = ").append("'").append(uid).append("'").append(" and bracelet_id = ").append("'").append(bracelet_id).append("'").append(" ORDER BY bind_time DESC");
            logger.info("getUserBindBraceletInfo, sql:" + builder.toString());
            List<JSONObject> list = jdbcTemplate.query(builder.toString(), new RowMapper<JSONObject>() {
                @Override
                public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("bracelet_id", resultSet.getString("bracelet_id"));
                    jsonObject.put("gym_name", resultSet.getString("gym_name"));
                    String status = resultSet.getString("status");
                    if ("0".equals(status)) {
                        jsonObject.put("status", true);
                    } else {
                        jsonObject.put("status", false);
                    }
                    jsonObject.put("user_name", resultSet.getString("user_name"));
                    jsonObject.put("phone_num", resultSet.getString("phone_num"));
                    jsonObject.put("uid", resultSet.getString("uid"));
                    jsonObject.put("bind_time", resultSet.getString("bind_time"));
                    jsonObject.put("bind_timestamp", resultSet.getString("bind_timestamp"));
                    jsonObject.put("unbind_time", resultSet.getString("unbind_time"));
                    jsonObject.put("calorie", resultSet.getString("calorie"));
                    return jsonObject;
                }
            });
            if (!list.isEmpty()) {
                return list.get(0).toJSONString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解绑手环时 数据入库
     */
    @Async
    public void updateUnbindInfoToSQL(String gym_name, String braceletId, String phoneNum, String bindTime, String unbindTime, String calorie) {
        try {
            final StringBuilder builder = new StringBuilder();
            builder.append("update ");
            builder.append(GYM_BRACELET_BIND_USER);
            builder.append(" set ");
            builder.append(GymBraceletBindItem.UNBIND_TIME);
            builder.append(" = ");
            builder.append("'");
            builder.append(unbindTime);
            builder.append("'");
            builder.append(",");
            builder.append(GymBraceletBindItem.STATUS);
            builder.append(" = ");
            builder.append(0);
            builder.append(",");
            builder.append(GymBraceletBindItem.IS_READ);
            builder.append(" = ");
            builder.append(1);
            builder.append(",");
            builder.append(GymBraceletBindItem.CALORIE);
            builder.append(" = ");
            builder.append(calorie);

            builder.append(" where ");
            builder.append(GymBraceletBindItem.GYM_NAME);
            builder.append(" = ");
            builder.append("'");
            builder.append(gym_name);
            builder.append("'");
            builder.append(" and ");
            builder.append(GymBraceletBindItem.STATUS);
            builder.append(" = ");
            builder.append(1);
            builder.append(" and ");
            builder.append(GymBraceletBindItem.BRACELET_ID);
            builder.append(" = ");
            builder.append("'");
            builder.append(braceletId);
            builder.append("'");
            builder.append(" and ");
            builder.append(GymBraceletBindItem.PHONE_NUM);
            builder.append(" = ");
            builder.append("'");
            builder.append(phoneNum);
            builder.append("'");
            builder.append(" and ");
            builder.append(GymBraceletBindItem.BIND_TIME);
            builder.append(" = ");
            builder.append("'");
            builder.append(bindTime);
            builder.append("'");

            logger.info("updateUnbindInfoToSQL,sql:{}", builder.toString());
            jdbcTemplate.execute(builder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取给定健身房的所有会员信息
     **/
    public List<JSONObject> getMembers(String gym_name) {
        final StringBuilder queryBuilder = new StringBuilder();
        try {
            queryBuilder.append("select * from ");
            queryBuilder.append(GYM_BIND_MEMBERS).append(" where ");
            queryBuilder.append(MemberItem.GYM_NAME);
            queryBuilder.append(" = ").append("'").append(gym_name).append("'");

            List<JSONObject> list = jdbcTemplate.query(queryBuilder.toString(), new RowMapper<JSONObject>() {
                @Override
                public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                    MemberItem memberItem = new MemberItem();
                    memberItem.setId(resultSet.getString(MemberItem.ID));
                    memberItem.setUid(resultSet.getString(MemberItem.UID));
                    memberItem.setNickName(resultSet.getString(MemberItem.NICK_NAME));
                    memberItem.setPhoneNum(resultSet.getString(MemberItem.PHONE_NUM));
                    //memberItem.setGymName(gym_name);
                    memberItem.setBindTime(resultSet.getString(MemberItem.BIND_TIME));
                    return memberItem.toJSONObject();
                }
            });
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("SqlComponent sql:{},getMembers gym_name:{} exception:{}:", queryBuilder.toString(), gym_name, e.getMessage());
        }
        return null;
    }
}
