package db;

import base.StringMagicBox;
import db.mapper.CURDMapper;
import model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lmc
 * @date 2020/8/21 1:02
 */
@Component
public class Task {
    @Autowired
    private CURDMapper curdMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*
    批量插入实体数据到数据库
     */
    //@Scheduled(fixedRate = 100000000)
    public void batchInsert() {
        int count = 1000;
        for (int i = 0; i < count; i++) {
            UserInfo userInfo = new UserInfo();
            curdMapper.insert(userInfo);
        }
    }

    /*
    获取驼峰字段的select全部字段sql
    */
    //@Scheduled(fixedRate = 100000000)
    public void getAllSelectKey() {
        String tableName = "user_info";
        String sql = "select GROUP_CONCAT(COLUMN_NAME,'') from information_schema.columns where table_name='" + tableName + "'";
        String fields = jdbcTemplate.queryForObject(sql, String.class);
        String[] keys = fields.split(",");
        String result = "select ";
        for (String key : keys) {
            if(key.contains("_")) {
                String keyHump = StringMagicBox.changeToJavaFiled(key);
                key = key + " " +keyHump + ",";
            } else {
                key = key + ",";
            }
            result += key;
        }
        //去除末尾，
        result = result.substring(0, result.length() - 1);
        System.out.println(result + " from " + tableName);
    }

    /*
    获取字段随意拼接
    */
    @Scheduled(fixedRate = 100000000)
    public void getFieldListMagic() {
        String sql = "select id from user_info";
        List<Integer> list = jdbcTemplate.queryForList(sql, Integer.class);
        String result = "";
        for (Integer key : list) {
            result += key.toString()  + ",";
        }
        //去除末尾，
        result = result.substring(0, result.length() - 1);
        System.out.println(result);
    }


}
