import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import views.GameView;
import models.BoardData;
import models.ComputerMove;
import models.GameBoard;
import models.GeneralGameBoard;

public class GameBoardTest2 {

    private GameBoard gameBoard;
    private GameView gameView;
    private BoardData boardData;
    private ComputerMove computerMove;

    @Before
    public void setUp() {
        gameView = new GameView();
        boardData = new BoardData();
        boardData.SetBoard(3); // Set up a 3x3 board
        gameBoard = new GeneralGameBoard(gameView, boardData);
        gameBoard.createBoard(3);
        computerMove = new ComputerMove(gameBoard, boardData);
    }


    @Test
    public void testValidComputerMovePlacement() {
        int initialBlueScore = gameView.getBlueTotal();
        
        // The system places and S or O 
        computerMove.execute(-1, Color.BLUE, false);
        
        //The letter should pop up in the chosen cell previously
        boolean moveFound = false;
        int finalBlueScore = gameView.getBlueTotal();
        
        for (JButton button : gameBoard.getButtons()) {
            if (!button.isEnabled()) {
                moveFound = true;
                String buttonText = button.getText();
                assertTrue("Button should contain 'S' or 'O'", buttonText.equals("S") || buttonText.equals("O"));
                break;
            }
        }
        
        assertTrue("A move should have been made", moveFound);
        assertTrue("Score should be the same or higher", finalBlueScore >= initialBlueScore);
        
        // Check if the virtual board has been updated
        boolean cellUpdated = false;
        for (ArrayList<String> row : boardData.getGameBoard()) {
            for (String cell : row) {
                if (cell.equals("S") || cell.equals("O")) {
                    cellUpdated = true;
                    break;
                }
            }
            if (cellUpdated) break;
        }
        assertTrue("Virtual board should be updated", cellUpdated);
    }
}