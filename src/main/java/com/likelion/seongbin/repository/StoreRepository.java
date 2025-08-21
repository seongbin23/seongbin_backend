package com.likelion.seongbin.repository;

import com.likelion.seongbin.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    // 추가로 커스텀 쿼리 만들고 싶으면 여기서 정의 가능
}