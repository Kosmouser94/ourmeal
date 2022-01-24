/**
 * JpaRepository 를 상속한 관리자 페이지용 회원 리포지토리.
 * 정지원
 */

package com.kosmo.ourmeal.repository;

import com.kosmo.ourmeal.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    Member findBymemID(String memID);   // memID 로 찾기.

}
