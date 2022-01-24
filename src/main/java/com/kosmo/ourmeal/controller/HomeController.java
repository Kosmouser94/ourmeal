/**
 * 기본 컨트롤러
 * 정지원
 */

package com.kosmo.ourmeal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * 임시 인덱스 페이지
     * 정지원
     */
    @GetMapping("/")
    public String tempIndx() {
        return "tempIndex";
    }



    /**
     * 관리자 메인 페이지
     * 정지원
     */
    @GetMapping(value = "/admin")
    public String adminHome() {
        return "admin/admin_home";
    }
}
