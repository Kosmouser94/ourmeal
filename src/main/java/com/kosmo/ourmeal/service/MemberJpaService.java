/**
 * 관리자 페이지용 회원 서비스
 * 정지원
 */

package com.kosmo.ourmeal.service;

import com.kosmo.ourmeal.entity.Member;
import com.kosmo.ourmeal.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberJpaService {

    private final MemberJpaRepository memberJpaRepository;


    /**
     * 회원 저장
     * 정지원
     */
    public void saveMember(Member member) {
        memberJpaRepository.save(member);
    }

    /**
     * 회원 리스트 가져오기. 페이징 포함.
     * 정지원
     */
    public Page<Member> getMemberList(int page) {
        return memberJpaRepository.findAll(PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "memID")));
    }

    /**
     * 회원 하나 찾기
     * 정지원
     */
    public Member findOne(String memID) {
        return memberJpaRepository.findBymemID(memID);
    }

}
