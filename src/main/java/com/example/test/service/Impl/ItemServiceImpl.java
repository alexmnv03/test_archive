package com.example.test.service.Impl;

import com.example.test.model.Item;
import com.example.test.repo.ItemRepository;
import com.example.test.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public Item create(Item object) {
        log.debug("create {}", object);
        return itemRepository.save(object);
    }

    @Override
    public List<Item> getAll() {
        log.debug("getAll");
        return itemRepository.findAll();
    }

}
