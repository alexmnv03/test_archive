package com.example.test;

import com.example.test.model.Item;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.router.Route;

@Route("main")
public class MainView extends VerticalLayout {

    public MainView() {
        Binder<Item> binder = new Binder<>(Item.class);

        TextField textField = new TextField();
        Button addButton = new Button("Change");
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

        textField.addValueChangeListener(change->{
            if (binder.validate().isOk()) {
                try {
                    binder.writeBean(item);
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
                )
        );
    }
}
