/**
 * 상품 리포지토리
 * 정지원
 */

package com.kosmo.ourmeal.repository;

import com.kosmo.ourmeal.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final EntityManager em;

    public void save(Product product) {     // 저장
        if (product.getPID() == null)
            em.persist(product);
    }

    public Product findOne(long id) {       // 하나 찾기
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {        // 전부 찾기
        return em.createQuery("select p from Product p", Product.class).getResultList();
    }

    public void delete(long id) {       // 삭제
        Product removeItem = findOne(id);
        em.remove(removeItem);
    }

}
