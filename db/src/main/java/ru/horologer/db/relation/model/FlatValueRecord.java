package ru.horologer.db.relation.model;

import ru.horologer.base.StringConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FlatValueRecord {
    List<FlatValue> fieldList = new ArrayList<>();

    public List<FlatValue> getFieldList() {
        return fieldList;
    }

    public FlatValueRecord setFieldList(List<FlatValue> fieldList) {
        this.fieldList = fieldList;
        return this;
    }

    public Map<String, String> getMap(){
        return fieldList.stream().collect(Collectors.toMap(FlatValue::getName, FlatValue::getValue));
    }

    public String getValueFromRow(String name){
        for (FlatValue value : fieldList) {
            if(value.getName().equals(name))
                return value.getValue();
        }
        return "";
    }

    public void updateValueByName(String name, String value) {
        for (FlatValue flatValue : fieldList) {
            if (flatValue.getName().equals(name)) {
                flatValue.setValue(value);
                return;
            }
        }
    }

    public void addValueByName(String name, String value) {
        for (FlatValue flatValue : fieldList) {
            if (flatValue.getName().equals(name)) {
                flatValue.setValue(value);
                return;
            }
        }
        fieldList.add( new FlatValue().setName(name).setValue(value));
    }

    public void deleteByName(String name) {
        fieldList.removeIf(next -> next.getName().equals(name));
    }

    public static List<FlatValue> getListFromMap(Map<String, Object> map) {
        List<FlatValue> list = new ArrayList<>();
        for (Map.Entry<String, Object> object : map.entrySet()) {
            list.add(new FlatValue()
                    .setName(object.getKey())
                    .setValue(object.getValue() == null ? "" : StringConverter.toString(object.getValue())));

        }
        return list;
    }

}
