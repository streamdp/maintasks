package com.epam.streamdp.lerning.optional;

import java.util.Optional;

public class OptionalOrder {
    private long id;
    private Optional<String> description = Optional.empty();

    public Optional<String> getDescription() {
        return description;
    }

    public void setDescription(String descriptionString) {
        this.description = Optional.ofNullable(descriptionString);
    }
}
