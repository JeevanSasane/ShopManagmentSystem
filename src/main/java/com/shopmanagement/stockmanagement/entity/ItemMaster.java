package com.shopmanagement.stockmanagement.entity;

import com.shopmanagement.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "mt_item_master")
public class ItemMaster extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String itemName;

    @Column(nullable = false,unique = true)
    private String itemCode;

    @NotNull
    private LocalDateTime date;

    private Long availableQuantity;

}
