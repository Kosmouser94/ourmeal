/**
 * 회원 엔티티
 * 정지원
 */

package com.kosmo.ourmeal.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter @Setter
public class Member {

    @Id
    @Column(name = "memID")
    private String memID;       //  회원 아이디

    private String memPW;       // 회원 비밀번호
    private String memName;     // 회원 이름
    private String memPhone;    // 회원 전화번호
    private String memEmail;    // 회원 이메일

    private LocalDateTime memRegDate;   // 가입일
    private int memType;        // 회원 타입 [1: 일반 2: 관리자]
}
