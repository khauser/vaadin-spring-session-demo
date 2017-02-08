package com.example;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Alejandro Duarte.
 */
@SpringUI
@Theme(ValoTheme.THEME_NAME)
@Push
public class VaadinUI extends UI {

    private HorizontalLayout layout = new HorizontalLayout();

    @Override
    protected void init(VaadinRequest request) {
        layout.addComponent(new Button("Test", e -> e.getButton().setCaption("Ok")));
        setContent(layout);

        buildPushSomething();
    }


    private void buildPushSomething() {
        class FeederThread extends Thread {
            @Override
            public void run() {
                try {
                    getUI().access(() -> layout.addComponent(new Label("Hallo")));
                    Thread.sleep(10000);
                    getUI().access(() -> layout.addComponent(new Label("Du")));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        new FeederThread().start();
    }

}
