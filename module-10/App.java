// Justin Marucci 
// Assignment 10
// 10-1-2025

package com.meco.fans;

import com.meco.fans.dao.JdbcFanDao;
import com.meco.fans.service.FanService;
import com.meco.fans.ui.FanManagerFrame;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FanService service = new FanService(new JdbcFanDao());
            new FanManagerFrame(service).setVisible(true);
        });
    }
}
