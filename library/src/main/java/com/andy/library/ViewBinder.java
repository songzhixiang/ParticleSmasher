package com.andy.library;

/**
 * @author andysong
 * @data 2019-06-11
 * @discription 新创建的MainActivity&ViewBind 会 implements ViewBinder<MainActivity>
 */
public interface ViewBinder<T> {

    void bind(T target);
}
