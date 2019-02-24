package com.vaadin.starter.skeleton.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import java.util.ArrayList;
import java.util.List;

@Route
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {

    public MainView(@Autowired MessageBean bean) {
        Button button = new Button("Click me",
                e -> Notification.show(bean.getMessage()));
        add(button);
        
        Grid<String> grid = new Grid<>();
        grid.addColumn(source -> source);
        grid.setItems(createItems(bean));
        grid.setSizeFull();
        add(grid);
    }

    private List<String> createItems(MessageBean bean) {
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            items.add(bean.getMessage());
        }
        return items;
    }

}
