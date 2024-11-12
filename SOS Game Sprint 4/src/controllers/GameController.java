package controllers;


import java.awt.event.*;

import javax.swing.*;

import views.*;
import models.*;

public class GameController {
    private GameView gameView;
    private BoardData virtualBoard;
    private GameBoard gameBoard;

    public GameController(GameView gv) {
        this.gameView = gv;
        this.virtualBoard = new BoardData();

        gameView.getBtnEnter().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createGame();
            }
        });
    }

    private void createGame() {
        String input = gameView.getNumSize().getText();
        int boardSize;

        try {
            boardSize = Integer.parseInt(input);
        } 
        catch (Exception f) {
            JOptionPane.showMessageDialog(gameView, "Please input a valid boardsize", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (3 <= boardSize && boardSize <= 10 && (gameView.getGameMode1().isSelected() || gameView.getGameMode2().isSelected())) {
            virtualBoard.SetBoard(boardSize);

            if (gameView.getGameMode1().isSelected()) {
                gameBoard = new SimpleGameBoard(gameView, virtualBoard);
            } 
            else {
                gameBoard = new GeneralGameBoard(gameView, virtualBoard);
            }

            gameBoard.createBoard(boardSize);
            
            //Check to see if it is a fully CPU game
            if(gameView.getCheck1().isSelected() && gameView.getCheck2().isSelected()) {
            	//Start a fully CPU game
            	gameBoard.cpuGame();
            }
            //Check to see if it is a Blue CPU game
            else if(gameView.getCheck1().isSelected()) {
            	//Set the red player to start first
            	gameView.getCurrentTurn().setText("Current Turn is Red");
                gameView.setPlayer1(false);
                gameView.setPlayer2(true);
            }
            
            
        } 
        else {
            JOptionPane.showMessageDialog(gameView, "Please confirm gamemode is selected and valid boardsize is entered", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}