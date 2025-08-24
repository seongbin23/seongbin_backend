package com.likelion.seongbin.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private String crowdLevel;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference  // 순환 참조 방지
    private List<StoreHours> storeHours;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
