package com.example.test;

import com.example.test.model.Item;
import com.example.test.service.ItemService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Route("main")
public class MainView extends VerticalLayout {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainView.class);

    private final ItemService itemService;
    private final Grid<Item> grid;

    public MainView(ItemService itemService) {
        this.itemService = itemService;
        Binder<Item> binder = new Binder<>(Item.class);

        TextField textField = new TextField();
        Button addButton = new Button("Change");

        Button readButton = new Button("read");
        this.grid = new Grid<>(Item.class);
        grid.setColumns("id", "value");

        Item item = new Item(0);

        binder.forField(textField)
                .withConverter(
                        new StringToIntegerConverter("Вводить нужно только целые числа"))
                .bind(Item::getValue, Item::setValue);

        binder.readBean(item);

        addButton.addClickListener(e -> {
            if (binder.validate().isOk()) {
                item.setValue(item.getValue() + 1);
                binder.readBean(item);
            }
        });

        readButton.addClickListener(e -> {
            updateGrid();
        });

        textField.addValueChangeListener(change->{
            if (binder.validate().isOk()) {
                try {
                    binder.writeBean(item);
                    item.setId(0L);
                    this.itemService.create(item);
                } catch (ValidationException e) {
                    e.printStackTrace();
                }
            }
        });

        add(
                new H1("Тестовое задание"),
                new HorizontalLayout(
                        textField,
                        addButton
                ),
                new HorizontalLayout(
                        readButton
                ),
                new H3("Таблица с данными из БД"),
                new VerticalLayout(
                        grid
                )
        );
    }

    private void updateGrid() {
        LOGGER.debug("Обновление таблицы...");
        List<Item> items = this.itemService.getAll();
        grid.setItems(items);
        grid.setColumns("id", "value");
    }
}
