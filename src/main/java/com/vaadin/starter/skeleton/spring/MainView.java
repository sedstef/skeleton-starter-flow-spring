package com.vaadin.starter.skeleton.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {

    public MainView(@Autowired MessageBean bean) {
        Button button = new Button("Click me",
                e -> Notification.show(bean.getMessage()));
        add(button);

        Grid<String> grid = new Grid<>();
        grid.setHeightByRows(true);
        grid.addColumn(source -> source);
        grid.setItemDetailsRenderer(new ComponentRenderer<Grid, String>(master -> {
            Grid<String> detailsGrid = new Grid<>();
            detailsGrid.setHeightByRows(true);
            detailsGrid.addColumn(source -> source);
            detailsGrid.setItems(bean.getDetails(master));
            return detailsGrid;
        }));
        grid.setItems(bean.getItems());
        add(grid);
    }
}
