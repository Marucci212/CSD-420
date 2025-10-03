// Justin Marucci 
// Assignment 10
// 10-1-2025

package com.meco.fans.dao;

import com.meco.fans.model.Fan;
import java.util.Optional;

public interface FanDao {
    Optional<Fan> findById(int id) throws Exception;
    int update(Fan fan) throws Exception;
}
