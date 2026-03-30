package com.example.soccerapp.repository;

import java.util.*;
import com.example.soccerapp.model.SoccerEntity;

public class Repository<T extends SoccerEntity> {

    protected List<T> items = new ArrayList<>();

    public List<T> getAll() {
        return items;
    }

    public void add(T item) {
        items.add(item);
    }

    // 移除使用 Predicate 的 filter 方法，改用 Filter 接口
    public List<T> filter(Filter<T> filter) {
        List<T> result = new ArrayList<>();
        for (T item : items) {
            if (filter.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    // 自定义过滤接口
    public interface Filter<T> {
        boolean test(T item);
    }
}