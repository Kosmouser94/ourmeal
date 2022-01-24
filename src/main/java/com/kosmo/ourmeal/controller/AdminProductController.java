/**
 * 관리자 상품 컨트롤러
 * 정지원
 */

package com.kosmo.ourmeal.controller;

import com.kosmo.ourmeal.entity.Product;
import com.kosmo.ourmeal.service.ProductJpaService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductJpaService productService;



    /**
     * 상품 목록
     * 정지원
     */
    @GetMapping(value = "/admin/productList")
    public String productList(Model model, @RequestParam(required = false, defaultValue = "0", value = "page") int page) {
        Page<Product> productList = productService.getProductList(page);

        int totalPage = productList.getTotalPages();

        model.addAttribute("totalPage", totalPage);
        model.addAttribute("pList", productList.getContent());

        return "admin/product/productList";
    }



    /**
     * 상품 검색
     * 정지원
     */
    @GetMapping("/admin/productSearch")
    public String search(@RequestParam(value = "keyword") String keyword, Model model) {
        List<Product> pList = productService.searchProducts(keyword);
        model.addAttribute("pList", pList);

        return "admin/product/searchList";
    }



    /**
     * 상품 등록
     * 정지원
     */
    @GetMapping(value = "/admin/productNew")
    public String productNew(Model model) {
        model.addAttribute("pItem", new Product());
        return "admin/product/productNew";
    }

    @PostMapping(value = "/admin/productNew")
    public String productInsert(Product product,
                @RequestParam(value="img", required = false)MultipartFile files) throws IllegalStateException, IOException, Exception {
        // form 태그에서 전달한 키 값들이 setter를 통해 자동으로 해당 클래스의 필드에 매핑된다.

        Product insertItem = new Product();
        insertItem.setPName(product.getPName());
        insertItem.setPPrice(product.getPPrice());
        insertItem.setPStock(product.getPStock());
        insertItem.setPCategory(product.getPCategory());
        insertItem.setPContent(product.getPContent());
        insertItem.setPDate(LocalDateTime.now());

        if (files.isEmpty() == false) {

            String originalName = files.getOriginalFilename();  // 원본 파일명
            String extension = FilenameUtils.getExtension(originalName).toLowerCase();  // 확장자 추출

            if (extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg")) {
                // 그림 파일일 경우에만 저장 진행

                File saveFile;  // 저장할 파일 객체
                String saveFileName;    // 저장될 파일명 (랜덤 숫자 + 확장자)
                String saveURL = "c:/web/img/";   // 저장 경로


                do {
                    // 저장될 파일명 생성 (랜덤 숫자 + 확장자)
                    saveFileName = RandomStringUtils.randomAlphanumeric(32) + "." + extension;
                    saveFile = new File(saveURL + saveFileName);
                } while (saveFile.exists());

                saveFile.getParentFile().mkdirs();  // 디렉토리
                files.transferTo(saveFile);

                insertItem.setPFileName(saveFileName);   // 저장된 파일명을 테이블에 저장
            }
        }

        productService.saveProduct(insertItem);
        return "redirect:/admin/productList";
    }



    /**
     * 상품 수정
     * 정지원
     */
    @GetMapping("/admin/productEdit/{id}")
    public String productEdit(@PathVariable("id") Long pID, Model model) {
        Product dbItem = productService.findOne(pID);

        Product pItem = new Product();
        pItem.setPName(dbItem.getPName());
        pItem.setPID(dbItem.getPID());
        pItem.setPPrice(dbItem.getPPrice());
        pItem.setPStock(dbItem.getPStock());
        pItem.setPCategory(dbItem.getPCategory());
        pItem.setPContent(dbItem.getPContent());
        pItem.setPDate(dbItem.getPDate());
        pItem.setPFileName(dbItem.getPFileName());

        model.addAttribute("pItem", pItem);
        return "admin/product/productEdit";
    }

    @PostMapping("/admin/productEdit/{id}")
    public String productUpdate(@ModelAttribute("pItem") Product pItem,
                                @RequestParam(value="img", required = false)MultipartFile files) throws IllegalStateException, IOException, Exception {

        if (files.isEmpty() == false) {

            String originalName = files.getOriginalFilename();  // 원본 파일명
            String extension = FilenameUtils.getExtension(originalName).toLowerCase();  // 확장자 추출

            if (extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg")) {
                // 그림 파일일 경우에만 저장 진행

                File saveFile;  // 저장할 파일 객체
                String saveFileName;    // 저장될 파일명 (랜덤 숫자 + 확장자)
                String saveURL = "c:/web/img/";   // 저장 경로

                do {
                    // 저장될 파일명 생성 (랜덤 숫자 + 확장자)
                    saveFileName = RandomStringUtils.randomAlphanumeric(32) + "." + extension;
                    saveFile = new File(saveURL + saveFileName);
                } while (saveFile.exists());

                saveFile.getParentFile().mkdirs();  // 디렉토리
                files.transferTo(saveFile);

                pItem.setPFileName(saveFileName);   // 저장된 파일명을 테이블에 저장
            }
        }

        productService.updateProduct(pItem);

        return "redirect:/admin/productList";
    }



    /**
     * 상품 삭제
     */
    @GetMapping("/admin/productRemove/{id}")
    public String productRemove(@PathVariable("id") Long pID) {     // 해당 수정 페이지의 pID 값을 받아 삭제 한다.

        productService.removeProduct(pID);

        return "redirect:/admin/productList";
    }
}
