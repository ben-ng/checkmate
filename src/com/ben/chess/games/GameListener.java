package com.ben.chess.games;

import com.ben.chess.gui.GameGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ben on 2/21/14.
 */
public class GameListener implements ActionListener {

    private GameGUI gameGUI;

    public GameListener(GameGUI game) {
        this.gameGUI = game;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand().toString();

        if(action.equals("Surrender"))
            this.gameGUI.onSurrender();
        else if (action.equals("Undo"))
            this.gameGUI.onUndo();
    }
}
