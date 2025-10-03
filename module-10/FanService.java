// Justin Marucci 
// Assignment 10
// 10-1-2025


package com.meco.fans.service;

import com.meco.fans.dao.FanDao;
import com.meco.fans.model.Fan;
import java.util.Optional;

public class FanService {
    private final FanDao dao;

    public FanService(FanDao dao) {
        this.dao = dao;
    }

    public Optional<Fan> displayById(String idText) throws Exception {
        int id = parseId(idText);
        return dao.findById(id);
    }

    public int update(String idText, String first, String last, String team) throws Exception {
        int id = parseId(idText);
        validateName("First name", first);
        validateName("Last name",  last);
        validateName("Favorite team", team);
        Fan f = new Fan(id, first.trim(), last.trim(), team.trim());
        return dao.update(f);
    }

    private int parseId(String idText) {
        if (idText == null || idText.isBlank()) throw new IllegalArgumentException("ID is required.");
        try {
            int id = Integer.parseInt(idText.trim());
            if (id <= 0) throw new IllegalArgumentException("ID must be positive.");
            return id;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("ID must be an integer.");
        }
    }

    private void validateName(String field, String value) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(field + " is required.");
        if (value.length() > 25) throw new IllegalArgumentException(field + " must be ≤ 25 characters.");
    }
}
