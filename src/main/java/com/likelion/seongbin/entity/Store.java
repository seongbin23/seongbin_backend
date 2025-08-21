package com.likelion.seongbin.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_seq_generator")
    @SequenceGenerator(
            name = "store_seq_generator",
            sequenceName = "STORE_SEQ",
            allocationSize = 1
    )
    private Long id;

    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String imageUrl;
    private LocalDateTime createdAt;

    // Store와 StoreHours 1:N 관계
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StoreHours> storeHours;
}
