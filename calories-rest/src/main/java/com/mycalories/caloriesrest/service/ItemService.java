package com.mycalories.caloriesrest.service;

import com.mycalories.model2.item.Item;
import com.mycalories.model2.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public Item createItem(Item item) {
        return this.itemRepository.save(item);
    }
}
