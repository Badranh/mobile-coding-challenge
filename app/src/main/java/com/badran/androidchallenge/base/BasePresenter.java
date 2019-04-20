package com.badran.androidchallenge.base;

public abstract class BasePresenter<T> {
    public abstract void AssignView(T view);
    public abstract void RemoveView();
}
