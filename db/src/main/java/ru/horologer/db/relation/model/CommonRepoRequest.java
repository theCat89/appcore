package ru.horologer.db.relation.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ru.horologer.base.StringConverter.toObject;

public class CommonRepoRequest {

    private List<String> fields = new ArrayList<>();
    private Map<String, Object> filters;
    private FlatValueRecord record;
    int limit;
    int offset;


    public Map<String, Object> getFilters() {
        return filters;
    }

    public CommonRepoRequest setFilters(Map<String, Object> filters) {
        this.filters = filters;
        return this;
    }

    public CommonRepoRequest setStringFilters(Map<String, String> filters) {
        for (Map.Entry<String, String> entry : filters.entrySet()) {
            this.filters.put(entry.getKey(), toObject(entry.getValue()));
        }

        return this;
    }

    public FlatValueRecord getRecord() {
        return record;
    }

    public CommonRepoRequest setRecord(FlatValueRecord record) {
        this.record = record;
        return this;
    }

    public int getLimit() {
        return limit;
    }

    public CommonRepoRequest setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public int getOffset() {
        return offset;
    }

    public CommonRepoRequest setOffset(int offset) {
        this.offset = offset;
        return this;
    }

    public List<String> getFields() {
        return fields;
    }

    public CommonRepoRequest setFields(List<String> fields) {
        this.fields = fields;
        return this;
    }

    public static CommonRepoRequest clone(CommonRepoRequest from) {
        return new CommonRepoRequest()
                .setFilters(from.getFilters())
                .setRecord(from.getRecord())
                .setLimit(from.getLimit())
                .setOffset(from.getOffset());
    }

}
