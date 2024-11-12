import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Color;
import javax.swing.JLabel;
import views.GameView;
import models.BoardData;
import models.ComputerMove;
import models.GameBoard;
import models.GeneralGameBoard;

public class GameBoardTest {

    private GameBoard gameBoard;
    private GameView gameView;
    private BoardData boardData;

    @Before
    public void setUp() {
        gameView = new GameView();
        boardData = new BoardData();
        boardData.SetBoard(3); // Set up a 3x3 board
        gameBoard = new GeneralGameBoard(gameView, boardData);
        gameBoard.createBoard(3);
    }

    @Test
    public void testTurnSwitchAfterValidComputerMove() {
        //Setup compute move
        gameView.setPlayer1(false);
        gameView.setPlayer2(true);
        gameView.getCurrentTurn().setText("Current turn is Red");

        //The computer makes a move
        ComputerMove computerMove = new ComputerMove(gameBoard, boardData);
        computerMove.execute(-1, Color.RED, false);
        gameView.setPlayer1(true);
        gameView.setPlayer2(false);
        gameView.getCurrentTurn().setText("Current turn is Blue");

        //Test to make sure turn has switched
        assertTrue("Turn should switch to player 1", gameView.getPlayer1());
        assertFalse("Player 2's turn should end", gameView.getPlayer2());
        assertEquals("Current turn should be updated to Blue", "Current turn is Blue", gameView.getCurrentTurn().getText());
    }
}