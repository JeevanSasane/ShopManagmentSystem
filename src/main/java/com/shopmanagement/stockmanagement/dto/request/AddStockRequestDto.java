package com.shopmanagement.stockmanagement.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AddStockRequestDto {

    private String itemName;

//    private String itemCode;

    private Long quantity;

    private String itemBatch;

    private LocalDateTime batchExpiryDate;

    private Double purchasePrice;

    private Double itemMrp;


}
