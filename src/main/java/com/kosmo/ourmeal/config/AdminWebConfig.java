/**
 * 이미지 파일 로드를 위한 설정 파일
 * 정지원
 */
package com.kosmo.ourmeal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdminWebConfig implements WebMvcConfigurer {

    private String resourcePath = "file:///c:/web/img/";
    private String uploadPath = "/pImg/**";

    /**
     * 클라이언트의 요청 url이 uploadPath 로 시작될 경우 resourcePath로 요청을 전달한다.
     */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(uploadPath)
                .addResourceLocations(resourcePath);
    }

}
