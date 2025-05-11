package com.pzj.schoolrun;

import com.pzj.schoolrun.util.page.PageUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SchoolrunApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolrunApplication.class, args);
    }

}
