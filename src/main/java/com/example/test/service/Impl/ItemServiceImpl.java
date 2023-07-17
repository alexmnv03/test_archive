package com.example.test.service.Impl;

import com.example.test.model.Item;
import com.example.test.repo.ItemRepository;
import com.example.test.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

//@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);
    private final ItemRepository itemRepository;


    @Override
    public Item create(Item object) {
        LOGGER.debug("create...{}", object.getValue());
        return itemRepository.save(object);
    }

    @Override
    public List<Item> getAll() {
        LOGGER.debug("getAll...");
        return itemRepository.findAll();
    }

}
