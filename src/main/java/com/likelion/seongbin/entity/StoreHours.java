package com.likelion.seongbin.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreHours {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_hours_seq_generator")
    @SequenceGenerator(
            name = "store_hours_seq_generator",
            sequenceName = "STORE_HOURS_SEQ",
            allocationSize = 1
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "store_id")
    @JsonBackReference // 순환 참조 방지
    private Store store;

    private String dayOfWeek; // MONDAY, TUESDAY ...
    private Boolean isOpen;   // 해당 요일 영업 여부
    private String openTime;  // "09:00"
    private String closeTime; // "21:00"
}
