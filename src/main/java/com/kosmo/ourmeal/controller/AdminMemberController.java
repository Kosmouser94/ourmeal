/**
 * 관리자 회원 컨트롤러
 * 정지원
 */

package com.kosmo.ourmeal.controller;

import com.kosmo.ourmeal.service.MemberJpaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AdminMemberController {

    private final MemberJpaService memberJpaService;


}
