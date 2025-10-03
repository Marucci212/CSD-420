// Justin Marucci 
// Assignment 10 
// 10-1-2025

// File: src/test/java/com/meco/fans/service/FanServiceTest.java
package com.meco.fans.service;

import com.meco.fans.model.Fan;
import com.meco.fans.testdoubles.InMemoryFakeFanDao;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class FanServiceTest {

    @Test
    void display_validId_returnsFan() throws Exception {
        FanService svc = new FanService(new InMemoryFakeFanDao());
        Optional<Fan> fan = svc.displayById("1");
        assertTrue(fan.isPresent());
        assertEquals("Alex", fan.get().getFirstName());
    }

    @Test
    void display_invalidId_throws() {
        FanService svc = new FanService(new InMemoryFakeFanDao());
        assertThrows(IllegalArgumentException.class, () -> svc.displayById("abc"));
    }

    @Test
    void update_valid_updatesAndReturns1() throws Exception {
        FanService svc = new FanService(new InMemoryFakeFanDao());
        int rows = svc.update("2", "Jamie", "Lee", "Pirates");
        assertEquals(1, rows);
        Optional<Fan> fan = svc.displayById("2");
        assertEquals("Jamie", fan.get().getFirstName());
        assertEquals("Pirates", fan.get().getFavoriteTeam());
    }

    @Test
    void update_missingFirst_throws() {
        FanService svc = new FanService(new InMemoryFakeFanDao());
        assertThrows(IllegalArgumentException.class, () -> svc.update("1", "", "Smith", "Steelers"));
    }
}

