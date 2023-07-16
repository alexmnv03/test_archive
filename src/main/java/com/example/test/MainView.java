package com.example.test;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("main")
public class MainView extends VerticalLayout {

    private int textValue = 0;

    public MainView() {
        TextField textField = new TextField();
        textField.setValue(String.valueOf(textValue));
        Button addButton = new Button("Change");
        textField.addValueChangeListener(change->{
            textValue = Integer.parseInt(textField.getValue());
        });
        addButton.addClickListener(click -> {
            textValue++;
            textField.setValue(String.valueOf(textValue));
        });
        addButton.addClickShortcut(Key.ENTER);

        add(
                new H1("Тестовое задание"),
                new HorizontalLayout(
                        textField,
                        addButton
                )
        );
    }
}
