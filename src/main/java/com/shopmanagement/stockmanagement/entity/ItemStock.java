package com.shopmanagement.stockmanagement.entity;

import com.shopmanagement.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "mt_item_stock")
public class ItemStock extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id",nullable = false)
    private ItemMaster itemMaster;

    private String itemBatch;

    private LocalDateTime batchExpiryDate;

    private Long itemQuantity;

    private Double purchasePrice;

    private Double itemMrp;

}
