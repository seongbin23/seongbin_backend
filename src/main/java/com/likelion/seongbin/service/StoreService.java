package com.likelion.seongbin.controller;

import com.likelion.seongbin.entity.Store;
import com.likelion.seongbin.repository.StoreRepository;
import com.likelion.seongbin.service.StoreService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {
    private final StoreRepository StoreRepository;
    private final StoreService StoreService;

    // 생성자 주입
    public StoreController(StoreRepository storeRepository, StoreService StoreService) {
        this.StoreRepository = storeRepository;
        this.StoreService = StoreService;
    }

    // 전체 매장 조회
    @GetMapping
    public List<Store> getAllStores() {
        return StoreRepository.findAll();
    }

    // 거리 기준 매장 조회
    @GetMapping("/by-distance")
    public List<Store> getStoresByDistance(@RequestParam double userLat, @RequestParam double userLng) {
        return StoreService.getStoresByDistance(userLat, userLng);
    }

    // 매장 등록
    @PostMapping
    public Store createStore(@RequestBody Store store) {
        return StoreRepository.save(store);
    }

    // 매장 상세 조회
    @GetMapping("/{id}")
    public Store getStore(@PathVariable Long id) {
        return StoreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("매장을 찾을 수 없습니다."));
    }

    // 뷰 페이지 연결
    @GetMapping("/view")
    public String storeView() {
        return "stores"; // templates/stores.html
    }
}
