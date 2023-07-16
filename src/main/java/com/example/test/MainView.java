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

    private int textValue = 0;

    public MainView() {
        TextField ftextField = new TextField();
        ftextField.setValue(String.valueOf(textValue));
        Button faddButton = new Button("Change");
        ftextField.addValueChangeListener(change->{
            textValue = Integer.parseInt(ftextField.getValue());
        });
        faddButton.addClickListener(click -> {
            textValue++;
            ftextField.setValue(String.valueOf(textValue));
        });
        faddButton.addClickShortcut(Key.ENTER);

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
//                item.setValue(Integer.parseInt(textField.getValue()));
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
                        ftextField,
                        faddButton
                ),
                new HorizontalLayout(
                        textField,
                        addButton
                )
        );
    }
}
