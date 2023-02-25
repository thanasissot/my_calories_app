package com.mycalories.repo.services;

import com.mycalories.model2.item.Item;
import com.mycalories.model2.item.ItemRepository;
import com.mycalories.model2.marketplace.Marketplace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public Item createItem(Item item) {
        return this.itemRepository.save(item);
    }

    public List<Item> createItems(List<Item> items) {
        return this.itemRepository.saveAll(items);
    }

    public List<Item> getItemsByMarketplace(Marketplace marketplace) {
        return this.itemRepository.findAllByMarketplace(marketplace).orElse(null);
    }
}
