/**
 * ProductJpaRepository 를 사용하는 ProductJpaService
 * 정지원
 */

package com.kosmo.ourmeal.service;

import com.kosmo.ourmeal.entity.Product;
import com.kosmo.ourmeal.repository.ProductJpaRepository;
import com.kosmo.ourmeal.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductJpaService {

    private final ProductJpaRepository productRepository;


    /**
     * 상품 저장
     * 정지원
     */
    public void saveProduct(Product product) {      // 제품 저장
        productRepository.save(product);
    }

    /**
     * 상품 목록 가져오기 (페이징 처리 X)
     * 정지원
     */
    public List<Product> findProducts() {       // 제품 전체 찾기
        return productRepository.findAll();
    }

    /**
     * 상품 하나 찾기
     * 정지원
     */
    public Product findOne(Long pID) {      
        return productRepository.findById(pID).get();
    }

    /**
     * 상품 검색
     * 정지원
     */
    public List<Product> searchProducts(String keyword) {
        List<Product> pList = productRepository.findBypNameContaining(keyword);

        return pList;
    }


    /**
     * 상품 수정
     * 정지원
     */
    public void updateProduct(Product product) {    // DB의 객체를 꺼내와 변경 사항만 반영한다. dirty check.
        Product dbItem = findOne(product.getPID());
        dbItem.setPName(product.getPName());
        dbItem.setPCategory(product.getPCategory());
        dbItem.setPPrice(product.getPPrice());
        dbItem.setPStock(product.getPStock());
        dbItem.setPContent(product.getPContent());
        dbItem.setPFileName(product.getPFileName());
    }

    /**
     * 상품 삭제
     * 정지원
     */
    public void removeProduct(Long pID) {
        Product product = productRepository.findById(pID).get();
        productRepository.delete(product);
    }

    /**
     * 페이징 처리 가능한 상품 리스트
     * 정지원
     */
    public Page<Product> getProductList(int page) {
        return productRepository.findAll(PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "pID")));
    }
}
