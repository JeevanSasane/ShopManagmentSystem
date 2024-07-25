package com.shopmanagement.stockmanagement.controller;

import com.shopmanagement.stockmanagement.dto.request.AddStockRequestDto;
import com.shopmanagement.stockmanagement.dto.request.ItemListDto;
import com.shopmanagement.stockmanagement.service.StockServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockServices stockServices;

    @PostMapping("/addStock")
    public ResponseEntity<?> addStock(@RequestBody AddStockRequestDto dto){
        return stockServices.addStock(dto);
    }

    @PostMapping("/getAllItemStockList")
    public ResponseEntity<?> getAllItemStockList(@RequestBody ItemListDto dto){
        return stockServices.getAllItemStockList(dto);
    }

    @GetMapping("/getItemStock/{searchString}")
    public ResponseEntity<?> getItemStock(@PathVariable String searchString){
        return stockServices.getItemStock(searchString);
    }
}
