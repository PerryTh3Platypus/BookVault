package com.github.perryth3platypus.gui;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    private JMenu settingsMenu;
    private JMenu helpMenu;

    public MenuBar(){
        init();
        addMenusToMenuBar();
    }

    public void init(){
        settingsMenu = new JMenu("Settings");
        helpMenu = new JMenu("Help");
    }

    public void addMenusToMenuBar(){
        this.add(settingsMenu);
        this.add(helpMenu);
    }
}
