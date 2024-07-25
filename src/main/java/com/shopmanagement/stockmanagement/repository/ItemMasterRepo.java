package com.shopmanagement.stockmanagement.repository;

import com.shopmanagement.stockmanagement.entity.ItemMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface ItemMasterRepo extends JpaRepository<ItemMaster,Long> {


    ItemMaster findByItemName(String itemName);

    @Query(value = "SELECT * FROM (SELECT item_code AS item_code FROM mt_item_master where item_code is not null ORDER BY id DESC LIMIT 1\n" +
            ") AS subquery UNION ALL SELECT '0001' AS item_code LIMIT 1;",nativeQuery = true)
    String getItemCode();

    @Query(value = "CALL RetrieveItemStockList(?1,?2,?3)",nativeQuery = true)
    List<Map<String,Object>> getItemList(Long itemId,Integer page,Integer size);

    @Query(value = "CALL RetrieveItemStockSearch(?1)", nativeQuery = true)
    List<Map<String, Object>> getItemStockSearch(String searchString);


}
