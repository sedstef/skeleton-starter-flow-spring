package com.vaadin.starter.skeleton.spring;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
public class MessageBean {

    private final static Master[] items = new Master[]{
        new Master("Item 1", Arrays.asList("Detail 1-1")),
        new Master("Item 2", Arrays.asList("Detail 2-1", "Detail 2-2")),
        new Master("Item 3", Collections.emptyList())
    };

    public Stream<String> getItems() {
        return Stream.of(items)
                .map(t -> t.name);
    }

    public List<String> getDetails(String item) {
        return Stream.of(items)
                .filter(t -> t.name.equals(item))
                .map(t -> t.details)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Item " + item + " not found"));
    }

    public String getMessage() {
        return "Button was clicked at " + LocalTime.now();
    }

    private static class Master {

        String name;
        List<String> details;

        public Master(String name, List<String> details) {
            this.name = name;
            this.details = details;
        }

    }
}
