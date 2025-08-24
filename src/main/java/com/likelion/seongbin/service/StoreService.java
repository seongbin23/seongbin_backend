package com.likelion.seongbin.service;

import com.likelion.seongbin.entity.Store;
import com.likelion.seongbin.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    // 반경 km 내 매장 조회
    public List<Store> getStoresByDistance(double userLat, double userLng, double radiusKm) {
        return storeRepository.findAll().stream()
                .filter(store -> distance(userLat, userLng, store.getLatitude(), store.getLongitude()) <= radiusKm)
                .collect(Collectors.toList());
    }

    // 두 좌표 간 거리 계산 (단위: km)
    public double distance(double lat1, double lng1, double lat2, double lng2) {
        final int R = 6371; // 지구 반경 km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
