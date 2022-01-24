/**
 * 상품 서비스
 * 정지원
 */

package com.kosmo.ourmeal.service;

import com.kosmo.ourmeal.entity.Product;
import com.kosmo.ourmeal.repository.ProductJpaRepository;
import com.kosmo.ourmeal.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public void saveProduct(Product product) {      // 제품 저장
        productRepository.save(product);
    }

    public List<Product> findProducts() {       // 제품 전체 찾기
        return productRepository.findAll();
    }

    public Product findOne(Long pID) {      // 제품 하나 찾기
        return productRepository.findOne(pID);
    }

    public void updateProduct(Product product) {    // DB의 객체를 꺼내와 변경 사항만 반영한다. dirty check.
        Product dbItem = findOne(product.getPID());
        dbItem.setPName(product.getPName());
        dbItem.setPCategory(product.getPCategory());
        dbItem.setPPrice(product.getPPrice());
        dbItem.setPStock(product.getPStock());
        dbItem.setPContent(product.getPContent());
        dbItem.setPFileName(product.getPFileName());
    }

    public void removeProduct(Long pID) {
        productRepository.delete(pID);
    }
}
