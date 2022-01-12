package ru.horologer.db.relation.model;


import ru.horologer.base.StringConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class FlatValue {
    String name;
    String value;

    public String getName() {
        return name;
    }

    public FlatValue setName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public FlatValue setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlatValue)) return false;
        FlatValue that = (FlatValue) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }


}
