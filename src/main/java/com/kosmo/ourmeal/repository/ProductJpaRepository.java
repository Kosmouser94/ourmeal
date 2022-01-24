/**
 * JpaRepository 를 상속한 관리자 페이지용 상품 리포지토리
 * 정지원
 */

package com.kosmo.ourmeal.repository;

import com.kosmo.ourmeal.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {

    List<Product> findBypNameContaining(String keyword);
}
