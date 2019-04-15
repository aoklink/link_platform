package com.linkfeeling.account.data.account;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linkfeeling.account.data.account.bean.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ：zl
 * @date ：Created in 2019/4/4 14:12
 * @description：账号信息
 * @modified By：
 * @version: 0.0.1$
 */
@Repository
public class AccountComponent {
    Logger logger = LoggerFactory.getLogger(AccountComponent.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getUserAccount(String phone_num, String user_type) {

        final StringBuilder queryBuilder = new StringBuilder();
        try {
            queryBuilder.append("select * from user_account where phone_num = ").append("'").append(phone_num).append("'");
            queryBuilder.append(" and user_type = ").append("'").append(user_type).append("'");
            logger.info("查询account的sql为：{}", queryBuilder.toString());
            List<JSONObject> list = jdbcTemplate.query(queryBuilder.toString(), new RowMapper<JSONObject>() {
                @Override
                public JSONObject mapRow(ResultSet resultSet, int i) throws SQLException {
                    JSONObject userAccount = new JSONObject();
                    userAccount.put("user_name",resultSet.getString("user_name"));
                    userAccount.put("uid",resultSet.getString("uid"));
                    userAccount.put("phone_num",resultSet.getString("phone_num"));
                    userAccount.put("head_icon",resultSet.getString("head_icon"));
                    userAccount.put("user_type",resultSet.getString("user_type"));
                    return userAccount;
                }
            });

            if (list != null && list.size() > 0) {
                JSONObject account = list.get(0);
                return account.toString();
            }
        } catch (Exception e) {
            logger.info("SqlComponent sql:{},getUserAccount phone_num:{},user_type:{} exception:{}:", queryBuilder.toString(), phone_num, user_type, e);
        }
        return null;
    }

    public String getUserAccountInfo(String uid) {
        logger.info("getUserAccount uid is :{}", uid);
        final StringBuilder queryBuilder = new StringBuilder();
        try {
            queryBuilder.append("select * from user_account where uid = ").append("'").append(uid).append("'");
            logger.info("查询account的sql为：{}", queryBuilder.toString());
            List<UserAccount> list = jdbcTemplate.query(queryBuilder.toString(), new RowMapper<UserAccount>() {
                @Override
                public UserAccount mapRow(ResultSet resultSet, int i) throws SQLException {
                    UserAccount userAccount = new UserAccount();
                    userAccount.setUserName(resultSet.getString(UserAccount.DAO_FIELD_USER_NAME));
                    userAccount.setHeadIcon(resultSet.getString(UserAccount.DAO_FIELD_HEAD_ICON));
                    userAccount.setPhoneNum(resultSet.getString(UserAccount.DAO_FIELD_PHONE_NUM));
                    userAccount.setUid(resultSet.getString(UserAccount.DAO_FIELD_UID));
                    userAccount.setPwd(resultSet.getString(UserAccount.DAO_FIELD_PASSWORD));
                    userAccount.setWxId(resultSet.getString(UserAccount.DAO_FIELD_WX_OPENID));
                    userAccount.setUserType(resultSet.getString(UserAccount.DAO_FIELD_USER_TYPE));
                    userAccount.setAge(resultSet.getString(UserAccount.DAO_FIELD_AGE));
                    userAccount.setGender(resultSet.getString(UserAccount.DAO_FIELD_GENDER));
                    userAccount.setGoal(resultSet.getString(UserAccount.DAO_FIELD_GOAL));
                    userAccount.setWeight(resultSet.getString(UserAccount.DAO_FIELD_WEIGHT));
                    userAccount.setStature(resultSet.getString(UserAccount.DAO_FIELD_STATURE));
                    userAccount.setGymNames(resultSet.getString(UserAccount.DAO_FIELD_GYM_NAMES));
                    return userAccount;
                }
            });

            logger.info("getUserAccount is exist,account: {}", JSON.toJSONString(list));
            if (list != null && list.size() > 0) {
                UserAccount account = list.get(0);
                //stringRedisTemplate.opsForHash().put(RedisConstant.USER_ACCOUNT_KEY, uid, account.toString());
                return account.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("SqlComponent sql:{},getUserAccount uid:{} exception:{}:", queryBuilder.toString(), uid, e.getMessage());
        }
        logger.info("getUserAccount --end");
        return null;
    }
}
