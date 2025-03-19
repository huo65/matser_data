package cn.xidian.master_data;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.xidian.master_data.mapper")
public class MasterDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasterDataApplication.class, args);
    }

}
