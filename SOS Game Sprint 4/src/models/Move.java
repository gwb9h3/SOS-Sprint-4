package models;

import java.awt.Color;

import views.GameView;

public abstract class Move {
    protected GameBoard gameBoard;
    protected BoardData virtualBoard;

    //Initializes the move and sets the current game board and virtual board that will be used in the move logic
    public Move(GameBoard gameBoard, BoardData virtualBoard) {
        this.gameBoard = gameBoard;
        this.virtualBoard = virtualBoard;
    }

    //Empty function to be used in the child classes
    public abstract boolean execute(int buttonIndex, Color playerColor, boolean isS);
}
