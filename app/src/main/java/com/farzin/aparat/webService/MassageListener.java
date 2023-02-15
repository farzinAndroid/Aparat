package com.farzin.aparat.webService;

public interface MassageListener<T> {

    public void onSuccess(T successMassage);
    public void onFailure(String errorMassage);
}
