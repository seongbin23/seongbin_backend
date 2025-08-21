package com.likelion.seongbin.entity;

import jakarta.persistence.*;
import lombok.*;

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
}