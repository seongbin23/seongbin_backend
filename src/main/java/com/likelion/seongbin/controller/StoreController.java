package com.likelion.seongbin.controller;

import com.likelion.seongbin.entity.Store;
import com.likelion.seongbin.repository.StoreRepository;
import com.likelion.seongbin.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5500")
public class StoreController {

    private final StoreRepository storeRepository;
    private final StoreService storeService;

    // 1. 매장 등록
    @PostMapping
    public Store createStore(@RequestBody Store store) {
        if (store.getStoreHours() != null) {
            store.getStoreHours().forEach(hour -> hour.setStore(store));
        }
        return storeRepository.save(store);
    }

    // 2. 매장 목록 조회
    @GetMapping
    public List<Store> getStores(@RequestParam(required = false) Double userLat,
                                 @RequestParam(required = false) Double userLng) {

        List<Store> stores;

        if (userLat != null && userLng != null) {
            stores = storeService.getStoresByDistance(userLat, userLng, 1);
            stores.sort((a, b) -> Double.compare(
                    storeService.distance(userLat, userLng, a.getLatitude(), a.getLongitude()),
                    storeService.distance(userLat, userLng, b.getLatitude(), b.getLongitude())
            ));
        } else {
            stores = storeRepository.findAll();
        }

        return stores;
    }

    // 3. 매장 상세 조회
    @GetMapping("/{id}")
    public Store getStore(@PathVariable Long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("매장을 찾을 수 없습니다."));
    }
}
