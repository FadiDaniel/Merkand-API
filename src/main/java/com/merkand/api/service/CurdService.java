package com.merkand.api.service;

import java.util.ArrayList;

public interface CurdService<T> {
    public T get(Long Id);
    public ArrayList<T> getAll();
    public void save(T entity);
    public void delete(Long Id);
}
