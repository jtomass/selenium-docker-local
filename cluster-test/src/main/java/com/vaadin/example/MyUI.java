package com.vaadin.example;

import java.util.function.Function;

import org.vaadin.teemusa.undertow.UndertowLauncher;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class MyUI extends UI {

    public static void main(String[] args) {
        UndertowLauncher.withUI(MyUI.class).run();
    }

    public static Function<String, String> GREETER = name -> "Thanks " + name
            + ", it works!";

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener(e -> {
            layout.addComponent(new Label(GREETER.apply(name.getValue())));
        });

        layout.addComponents(name, button);

        setContent(layout);
    }
}
