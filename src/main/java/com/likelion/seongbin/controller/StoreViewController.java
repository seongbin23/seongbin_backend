package com.likelion.seongbin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stores")
public class StoreViewController {

    // 뷰 페이지 연결
    @GetMapping("/view")
    public String storeView() {
        return "stores"; // templates/stores.html
    }
}
