package com.ben.chess.gui;

import com.ben.chess.games.ChessGame;
import com.ben.chess.games.StandardChessGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class ChessGUI {

    public ChessGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            //silently ignore
        }

        JFrame window = new JFrame("Standard Chess Game");
        GameGUI gameGui;

        try {
            ChessGame game = new StandardChessGame();

            gameGui = new GameGUI(game);

            window.setSize(gameGui.getWidth(), gameGui.getHeight());
            window.setResizable(false);

            window.setContentPane(gameGui);

            window.setVisible(true);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        catch(Exception e) {
            System.err.println(e.getStackTrace());
        }
    }

    public static void main(String[] args) {
        new ChessGUI();
    }
}
