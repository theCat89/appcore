package ru.horologer.db.relation.model;

import java.util.function.Function;

public class TMRequest {
    private Function<InnerRepoRequest,CommonRepoResponse> function;
    private CommonRepoRequest request;
    private Function<InnerRepoRequest,CommonRepoResponse> compensatingFunction;

    public Function<InnerRepoRequest, CommonRepoResponse> getFunction() {
        return function;
    }

    public TMRequest setFunction(Function<InnerRepoRequest, CommonRepoResponse> function) {
        this.function = function;
        return this;
    }

    public CommonRepoRequest getRequest() {
        return request;
    }

    public TMRequest setRequest(CommonRepoRequest request) {
        this.request = request;
        return this;
    }

    public Function<InnerRepoRequest, CommonRepoResponse> getCompensatingFunction() {
        return compensatingFunction;
    }

    public TMRequest setCompensatingFunction(Function<InnerRepoRequest, CommonRepoResponse> compensatingFunction) {
        this.compensatingFunction = compensatingFunction;
        return this;
    }
}
