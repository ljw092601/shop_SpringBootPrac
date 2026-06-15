package com.example.shop.Item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(String name, String price, String category, String image) {
        Item item = new Item();
        item.setName(name);
        int value;
        try {
            value = Integer.parseInt(price);
            System.out.println("변환 성공: " + value);
        } catch (NumberFormatException e) {
            System.out.println("변환 불가: " + price);
            return;
        }
        item.setPrice(value);
        item.setCategory(category);
        item.setImage(image);
        itemRepository.save(item);
    }

    @Transactional(readOnly = true)
    public Item getOne(Integer id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("아이템 없음: " + id));
    }

    @Transactional
    public void updateItem(Integer id, String name, Integer price, String category, String image) {
        Item item = getOne(id);
        if (name != null) item.setName(name);
        if (price != null) item.setPrice(price);
        if (category != null) item.setCategory(category);
        if (image != null) item.setImage(image);
    }

    public void deleteItem(String id) {
        int new_id;
        try {
            new_id = Integer.parseInt(id);
            System.out.println("변환 성공: " + new_id);
        } catch (NumberFormatException e) {
            System.out.println("변환 불가: " + id);
            return;
        }
        itemRepository.deleteById(new_id);
    }
}
