package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @description 用户信息
 * @author lmc
 * @date 2020-08-02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private Integer id;

    /**
    * 姓名
    */
    private String name;

    /**
    * 手机
    */
    private String phone;

    /**
    * 状态：1-有效，0-无效
    */
    private Integer status;

    /**
    * 性别：0-男，1-女
    */
    private Integer sex;

    /**
    * 生日 ：yyyy-mm-dd
    */
    private String birthday;

    /**
    * 地址
    */
    private String address;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 更新时间
    */
    private Date updateTime;

}
