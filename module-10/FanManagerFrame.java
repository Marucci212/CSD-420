// Justin Marucci 
// Assignment 10
// 10-1-2025


package com.meco.fans.ui;

import com.meco.fans.model.Fan;
import com.meco.fans.service.FanService;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class FanManagerFrame extends JFrame {
    private final FanService service;

    private final JTextField idField = new JTextField(10);
    private final JTextField firstField = new JTextField(25);
    private final JTextField lastField = new JTextField(25);
    private final JTextField teamField = new JTextField(25);
    private final JButton displayBtn = new JButton("Display");
    private final JButton updateBtn = new JButton("Update");

    public FanManagerFrame(FanService service) {
        super("Fans | View & Update");
        this.service = service;
        buildUi();
        bindActions();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(420, 240);
        setLocationRelativeTo(null);
    }

    private void buildUi() {
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6,6,6,6);
        c.anchor = GridBagConstraints.WEST;

        c.gridx=0; c.gridy=0; form.add(new JLabel("ID:"), c);
        c.gridx=1; c.gridy=0; form.add(idField, c);

        c.gridx=0; c.gridy=1; form.add(new JLabel("First name:"), c);
        c.gridx=1; c.gridy=1; form.add(firstField, c);

        c.gridx=0; c.gridy=2; form.add(new JLabel("Last name:"), c);
        c.gridx=1; c.gridy=2; form.add(lastField, c);

        c.gridx=0; c.gridy=3; form.add(new JLabel("Favorite team:"), c);
        c.gridx=1; c.gridy=3; form.add(teamField, c);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.add(displayBtn);
        buttons.add(updateBtn);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(form, BorderLayout.CENTER);
        getContentPane().add(buttons, BorderLayout.SOUTH);
    }

    private void bindActions() {
        displayBtn.addActionListener(e -> {
            try {
                Optional<Fan> maybe = service.displayById(idField.getText());
                if (maybe.isPresent()) {
                    Fan f = maybe.get();
                    firstField.setText(f.getFirstName());
                    lastField.setText(f.getLastName());
                    teamField.setText(f.getFavoriteTeam());
                    JOptionPane.showMessageDialog(this, "Record loaded.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    clearNames();
                    JOptionPane.showMessageDialog(this, "No record found for that ID.", "Not found", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                showError(ex);
            }
        });

        updateBtn.addActionListener(e -> {
            try {
                int rows = service.update(idField.getText(), firstField.getText(), lastField.getText(), teamField.getText());
                if (rows == 1) {
                    JOptionPane.showMessageDialog(this, "Record updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No record updated (check ID exists).", "No change", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                showError(ex);
            }
        });
    }

    private void clearNames() {
        firstField.setText("");
        lastField.setText("");
        teamField.setText("");
    }

    private void showError(Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
