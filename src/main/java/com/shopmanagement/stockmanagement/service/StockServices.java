package com.shopmanagement.stockmanagement.service;


import com.shopmanagement.stockmanagement.dto.request.AddStockRequestDto;
import com.shopmanagement.stockmanagement.dto.request.ItemListDto;
import org.springframework.http.ResponseEntity;

public interface StockServices {

    ResponseEntity<?> addStock(AddStockRequestDto dto);

    ResponseEntity<?> getAllItemStockList(ItemListDto itemListDto);

    ResponseEntity<?> getItemStock(String searchString);

}
