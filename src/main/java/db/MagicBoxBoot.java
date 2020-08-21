package db;

import db.mapper.CURDMapper;
import model.UserInfo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author lmc
 * @date 2020/8/20 23:48
 */
@SpringBootApplication
@EnableScheduling
@MapperScan("db/mapper")
public class MagicBoxBoot {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(MagicBoxBoot.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);


    }
}
