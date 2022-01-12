package ru.horologer.db.relation.model;

import java.sql.Connection;

public class InnerRepoRequest {
    CommonRepoRequest request;
    Connection conn;

    public CommonRepoRequest getPublicRequest() {
        return request;
    }

    public InnerRepoRequest setRequest(CommonRepoRequest request) {
        this.request = request;
        return this;
    }

    public Connection getConn() {
        return conn;
    }

    public InnerRepoRequest setConn(Connection conn) {
        this.conn = conn;
        return this;
    }
}
