package com.linkfeeling.common.jdbc.support;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JdbcSession<BEAN> {

    BEAN bean;
    private String tableName;
    private List<JdbcField> fieldList;

    public JdbcSession(BEAN bean) {
        this.bean = bean;
        PropertyNamingStrategy.SnakeCaseStrategy snakeCaseStrategy = new PropertyNamingStrategy.SnakeCaseStrategy();
        tableName = snakeCaseStrategy.translate(bean.getClass().getSimpleName());
        fieldList = new ArrayList<>();
    }

    public JdbcSession field(String name,Object value){
        checkFieldExist(name);
        fieldList.add(new JdbcField(name,value));
        return this;
    }

    private void checkFieldExist(String name){
        fieldList.forEach(item->{
            if(item.name.equals(name)){
                throw new RuntimeException(name + "already set");
            }
        });
    }


    public Number insert(JdbcTemplate jdbcTemplate)throws Exception{
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String insertSql = "insert into "+tableName+" ("+makeInsertNames()+") values ("+makeInsertValues()+")";
        jdbcTemplate.update(con -> con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS),keyHolder);
        return keyHolder.getKey();
    }

    public void insertNormal(JdbcTemplate jdbcTemplate)throws Exception{
        String insertSql = "insert into "+tableName+" ("+makeInsertNames()+") values ("+makeInsertValues()+")";
        jdbcTemplate.execute(insertSql);
    }


    public void update(JdbcTemplate jdbcTemplate)throws Exception{
        jdbcTemplate.update("update "+tableName+" set "+makeUpdateParams() +" where id="+getIdValue());
    }

    public void updateWithCustomId(JdbcTemplate jdbcTemplate,String idName)throws Exception{
        jdbcTemplate.update("update "+tableName+" set "+makeUpdateParams(idName) +" where "+idName+"='"+getIdValue(idName)+"'");
    }

    public void delete(JdbcTemplate jdbcTemplate)throws Exception{
        jdbcTemplate.execute("delete from "+tableName+" where "+makeDeleteParams());
    }

    private String makeInsertNames(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < fieldList.size(); i++) {
            JdbcField item = fieldList.get(i);
            stringBuilder.append(item.name);
            if(i == (fieldList.size()-1)){
                break;
            }else{
                stringBuilder.append(" , ");
            }
        }
        return stringBuilder.toString();
    }

    private String makeInsertValues() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < fieldList.size(); i++) {
            JdbcField item = fieldList.get(i);
            stringBuilder.append("'")
                    .append(genValue(item.value))
                    .append("'");
            if(i == (fieldList.size()-1)){
                break;
            }else{
                stringBuilder.append(" , ");
            }
        }
        return stringBuilder.toString();
    }

    private String genValue(Object value){
        if(value==null){
            return "NULL";
        }else{
            if(value instanceof Date){
                return new Timestamp(((Date) value).getTime()).toString();
            }

            return String.valueOf(value);
        }
    }

    private Object getIdValue(String idName){
        for(JdbcField field : fieldList){
            if(field.name.equals(idName)){
                return field.value;
            }
        }
        return null;
    }

    private Object getIdValue(){
       return getIdValue("id");
    }

    private String makeUpdateParams(String idName){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < fieldList.size(); i++) {
            JdbcField item = fieldList.get(i);
            if(item.name.equals(idName)) continue;
            if(item.value == null ) continue;
            stringBuilder.append(item.name)
                    .append("='")
                    .append(genValue(item.value))
                    .append("'");
            if(i == (fieldList.size()-1)){
                break;
            }else{
                stringBuilder.append(" , ");
            }
        }
        if(stringBuilder.charAt(stringBuilder.length()-2)==','){
            stringBuilder.setLength(stringBuilder.length()-2);
        }
        return stringBuilder.toString();
    }

    private String makeUpdateParams(){
        return makeUpdateParams("id");
    }

    private String makeDeleteParams(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < fieldList.size(); i++) {
            JdbcField item = fieldList.get(i);
            stringBuilder.append(item.name)
                    .append("='")
                    .append(item.value)
                    .append("'");
            if(i == (fieldList.size()-1)){
                break;
            }else{
                stringBuilder.append(" and ");
            }
        }
        return stringBuilder.toString();
    }
}
