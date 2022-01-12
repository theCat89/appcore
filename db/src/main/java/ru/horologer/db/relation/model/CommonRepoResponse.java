package ru.horologer.db.relation.model;

import java.util.List;

public class CommonRepoResponse {
    private List<FlatValueRecord> records;
    private int count;

    public List<FlatValueRecord> getRecords() {
        return records;
    }

    public CommonRepoResponse setRecords(List<FlatValueRecord> records) {
        this.records = records;
        return this;
    }

    public int getCount() {
        return count;
    }

    public CommonRepoResponse setCount(int count) {
        this.count = count;
        return this;
    }
}
