package com.shopmanagement.stockmanagement.service.impl;

import com.shopmanagement.response.Response;
import com.shopmanagement.stockmanagement.dto.request.AddStockRequestDto;
import com.shopmanagement.stockmanagement.dto.request.ItemListDto;
import com.shopmanagement.stockmanagement.entity.ItemMaster;
import com.shopmanagement.stockmanagement.entity.ItemStock;
import com.shopmanagement.stockmanagement.repository.ItemMasterRepo;
import com.shopmanagement.stockmanagement.repository.ItemStockRepo;
import com.shopmanagement.stockmanagement.service.StockServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockServices {

    @Autowired
    private ItemMasterRepo itemMasterRepo;

    @Autowired
    private ItemStockRepo itemStockRepo;

    @Override
    @Transactional
    public ResponseEntity<?> addStock(AddStockRequestDto dto) {

        ItemMaster itemMaster=new ItemMaster();

        Optional<ItemMaster> itemOptional= Optional.ofNullable(itemMasterRepo.findByItemName(dto.getItemName()));

        if (itemOptional.isPresent()){
            itemMaster.setId(itemOptional.get().getId());
            itemMaster.setItemCode(itemOptional.get().getItemCode());
            itemMaster.setAvailableQuantity(itemOptional.get().getAvailableQuantity()+dto.getQuantity());
            itemMaster.setCreateDateTime(itemOptional.get().getCreateDateTime());
            itemMaster.setCreateBy(itemOptional.get().getCreateBy());
            itemMaster.setUpdatedBy(null);
            itemMaster.setLastUpdatedDateTime(LocalDateTime.now());
        }else {
            itemMaster.setItemCode(itemMasterRepo.getItemCode());
            System.out.println("itemMaster.getItemCode()=="+itemMaster.getItemCode());
            itemMaster.setAvailableQuantity(dto.getQuantity());
            itemMaster.setCreateBy(null);
            itemMaster.setCreateDateTime(LocalDateTime.now());
            itemMaster.setUpdatedBy(null);
            itemMaster.setLastUpdatedDateTime(LocalDateTime.now());
        }
        itemMaster.setItemName(dto.getItemName());
        itemMaster.setDate(LocalDateTime.now());
        ItemMaster savedItem = itemMasterRepo.save(itemMaster);
        System.out.println("savedItem--"+savedItem);
        ItemStock itemStock=new ItemStock();
        System.out.println("itemStock.getId()="+itemStock.getId());
        System.out.println("dto.getItemBatch()="+dto.getItemBatch());
        Optional<ItemStock> itemStockOptional=Optional.ofNullable(itemStockRepo.findByItemMasterAndItemBatch(savedItem, dto.getItemBatch()));
//        System.out.println("itemStockOptional=="+itemStockOptional.get());
        if (itemStockOptional.isPresent()){
            System.out.println("in is present");
            itemStock.setId(itemStockOptional.get().getId());
            itemStock.setItemMaster(savedItem);
//            itemStock.setItemMaster(itemOptional.get());
            itemStock.setItemQuantity(itemStockOptional.get().getItemQuantity()+dto.getQuantity());
            itemStock.setBatchExpiryDate(itemStockOptional.get().getBatchExpiryDate());
            itemStock.setCreateDateTime(itemStockOptional.get().getCreateDateTime());
            itemStock.setCreateBy(itemStockOptional.get().getCreateBy());
            itemStock.setUpdatedBy(null);
            itemStock.setLastUpdatedDateTime(LocalDateTime.now());
            itemStock.setItemBatch(itemStockOptional.get().getItemBatch());
        }else {
            itemStock.setItemMaster(savedItem);
            itemStock.setItemBatch(dto.getItemBatch());
            itemStock.setBatchExpiryDate(dto.getBatchExpiryDate());
            itemStock.setCreateDateTime(LocalDateTime.now());
            itemStock.setCreateBy(null);
            itemStock.setUpdatedBy(null);
            itemStock.setLastUpdatedDateTime(LocalDateTime.now());
            itemStock.setItemQuantity(dto.getQuantity());
        }
        itemStock.setPurchasePrice(dto.getPurchasePrice());
        itemStock.setItemMrp(dto.getItemMrp());
        itemStockRepo.save(itemStock);
        var response=new Response<>();
        response.setMessage("Item Added Successfully.");
        response.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getAllItemStockList(ItemListDto itemListDto) {
        var response=new Response<>();
        response.setResult(itemMasterRepo.getItemList(itemListDto.getItemId(),itemListDto.getPage(),itemListDto.getSize()));
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Item List Retrieved Successfully..");
        return ResponseEntity.ok(response);
    }


    @Override
    public ResponseEntity<?> getItemStock(String searchString) {
        var response=new Response<>();
        response.setResult(itemMasterRepo.getItemStockSearch(searchString));
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Item Searched Successfully..");
        return ResponseEntity.ok(response);
    }
}
