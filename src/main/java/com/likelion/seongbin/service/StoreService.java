package com.likelion.seongbin.controller;

import com.likelion.seongbin.entity.Store;
import com.likelion.seongbin.repository.StoreRepository;
import com.likelion.seongbin.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores/api") // REST API는 /stores/api로 분리
@RequiredArgsConstructor
public class StoreController {

    private final StoreRepository storeRepository;
    private final StoreService storeService;

    // 1. 매장 등록
    @PostMapping
    public Store createStore(@RequestBody Store store) {
        return storeRepository.save(store);
    }

    // 2. 매장 목록 조회 (거리순)
    @GetMapping
    public List<Store> getStores(@RequestParam(required = false) Double userLat,
                                 @RequestParam(required = false) Double userLng) {
        if (userLat != null && userLng != null) {
            // 기본 반경 0.5km
            List<Store> stores = storeService.getStoresByDistance(userLat, userLng, 0.5);
            stores.sort((a, b) -> Double.compare(
                    storeService.distance(userLat, userLng, a.getLatitude(), a.getLongitude()),
                    storeService.distance(userLat, userLng, b.getLatitude(), b.getLongitude())
            ));
            return stores;
        }
        return storeRepository.findAll();
    }

    // 3. 매장 상세 조회
    @GetMapping("/{id}")
    public Store getStore(@PathVariable Long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("매장을 찾을 수 없습니다."));
    }
}

// 뷰 컨트롤러는 REST API와 분리
@Controller
@RequestMapping("/stores")
@RequiredArgsConstructor
class StoreViewController {

    // 뷰 페이지 연결
    @GetMapping("/view")
    public String storeView() {
        return "stores"; // templates/stores.html
    }
}
