/**
 * 상품 엔티티
 * 정지원
 */

package com.kosmo.ourmeal.entity;

import com.kosmo.ourmeal.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "product")
@Getter @Setter
public class Product {

    @Id @GeneratedValue
    @Column(name = "pID")
    private Long pID;        // 상품 고유번호

    private String pName;   // 상품명
    private int pPrice;     // 가격
    private int pStock;     // 재고량
    private LocalDateTime pDate;     // 등록일
    private String pContent;    // 상품 설명
    private int pCategory;      // 상품 분류
    // private int pDiscount;      // 할인율
    private String pFileName;   // 파일명


    /**
     * 재고 추가
     * 정지원
     */
    public void addStock(int quantity) {
        this.pStock += quantity;
    }

    /**
     * 재고 감소
     * 정지원
     */
    public void removeStock(int quantity) {
        int restStock = this.pStock - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("재고가 부족합니다.");  // 재고가 부족할 경우 예외를 발생한다.
        }
        this.pStock = restStock;
    }

}
