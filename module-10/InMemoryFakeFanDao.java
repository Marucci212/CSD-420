// Justin Marucci 
// Assignment 10
// 10-1-2025
package com.meco.fans.testdoubles;

import com.meco.fans.dao.FanDao;
import com.meco.fans.model.Fan;

import java.util.*;

public class InMemoryFakeFanDao implements FanDao {
    private final Map<Integer, Fan> store = new HashMap<>();

    public InMemoryFakeFanDao() {
        store.put(1, new Fan(1, "Alex", "Morgan", "Steelers"));
        store.put(2, new Fan(2, "Taylor", "Hall", "Penguins"));
    }

    @Override
    public Optional<Fan> findById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public int update(Fan fan) {
        if (!store.containsKey(fan.getId())) return 0;
        store.put(fan.getId(), fan);
        return 1;
    }
}
