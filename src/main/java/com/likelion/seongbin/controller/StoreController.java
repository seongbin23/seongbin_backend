package com.likelion.seongbin.controller;

import com.likelion.seongbin.entity.Store;
import com.likelion.seongbin.repository.StoreRepository;
import com.likelion.seongbin.service.StoreService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private final StoreRepository storeRepository;
    private final StoreService storeService;

    public StoreController(StoreRepository storeRepository, StoreService storeService) {
        this.storeRepository = storeRepository;
        this.storeService = storeService;
    }

    // 전체 매장 조회
    @GetMapping
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    // 거리 기준 매장 조회

    @GetMapping("/by-distance")
    public List<Store> getStoresByDistance(@RequestParam double userLat, @RequestParam double userLng) {
        return storeService.getStoresByDistance(userLat, userLng);
    }

    // 매장 등록
    @PostMapping
    public Store createStore(@RequestBody Store store) {
        return storeRepository.save(store);
    }

    // 매장 상세 조회
    @GetMapping("/{id}")
    public Store getStore(@PathVariable Long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("매장을 찾을 수 없습니다."));
    }
}
