package com.lnk.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        //livereload 설정 > jsp, css 변경 시 새로고침 없이 바로 적용
        System.setProperty("spring.devtools.restart.enabled","false");
        System.setProperty("spring.devtools.livereload.enabled","true");

        //메인 실행
        SpringApplication.run(MainApplication.class, args);
    }
}
