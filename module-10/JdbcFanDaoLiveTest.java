// Justin Marucci 
// Assignment 10 
// 10-1-2025

package com.meco.fans.dao;

import com.meco.fans.model.Fan;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcFanDaoLiveTest {

    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_MYSQL_TESTS", matches = "true")
    void findAndUpdate_liveDb_smoke() throws Exception {
        JdbcFanDao dao = new JdbcFanDao();
        Optional<Fan> before = dao.findById(1);
        assertTrue(before.isPresent());

        Fan f = before.get();
        String orig = f.getFavoriteTeam();
        f.setFavoriteTeam("TestTeam");
        int rows = dao.update(f);
        assertEquals(1, rows);

        Optional<Fan> after = dao.findById(1);
        assertEquals("TestTeam", after.get().getFavoriteTeam());

        f.setFavoriteTeam(orig);
        dao.update(f);
    }
}
