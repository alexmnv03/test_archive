package com.example.test.service;

import com.example.test.model.Item;

import java.util.List;

public interface ItemService {

    Item create(Item object);

    List<Item> getAll();

}
