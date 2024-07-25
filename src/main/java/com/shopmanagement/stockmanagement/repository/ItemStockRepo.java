package com.shopmanagement.stockmanagement.repository;

import com.shopmanagement.stockmanagement.entity.ItemMaster;
import com.shopmanagement.stockmanagement.entity.ItemStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemStockRepo extends JpaRepository<ItemStock,Long> {

    ItemStock findByItemMasterAndItemBatch(ItemMaster itemMaster, String itemBatch);
}
