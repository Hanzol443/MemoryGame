package memorygame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Hansel
 */
public class Game extends JFrame implements ActionListener {
    
    private final Board board;
    private Flipping card1, card2;
    private final JLabel errorLabel;
    private JLabel timerLabel;
    private final int delay = 1000;
    ActionListener taskPerformer;
    private Timer gameTimer = new Timer(delay, taskPerformer);
    private JPanel boardView, labelView;
    private int clickCount = 0;
    private int gameTime = 0;
    private int errorCount = 0;
    private int pairsFound = 0;
    private int maxPairs = 0;

    /**
     *
     */
    public Game() {

        super("Hubble Memory Game");
        this.taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                gameTime += 1;
                timerLabel.setText("Timer: " + gameTime);
            }
        };

        JButton restart = new JButton("Restart");
        JButton quit = new JButton("Quit");
        timerLabel = new JLabel("Timer: 0");
        errorLabel = new JLabel("Errors: 0");

        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        gameTimer.start();

        labelView = new JPanel();
        boardView = new JPanel();

        Container c = getContentPane();

        board = new Board(24, this);
        maxPairs = board.getSize() / 2;

        Flipping[] cards = board.getCards();
        for(int i = 0; i < cards.length; i++){
            cards[i].addActionListener(this::actionPerformed);
        }

        boardView.setLayout(new GridLayout(4, 6, 10, 10));
        board.viewFill(boardView);

        labelView.setLayout(new GridLayout(1, 4, 2, 2));
        labelView.add(quit);
        labelView.add(restart);
        labelView.add(timerLabel);
        labelView.add(errorLabel);

        c.add(labelView, BorderLayout.NORTH);
        c.add(boardView, BorderLayout.SOUTH);

        setSize(745, 470);
        setVisible(true);
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e){
        if(clickCount == 0) {
            clickCount++;
            card1 = (Flipping)e.getSource();
            card1.showFront();
            card1.setEnabled(false);
        }
        else {
            card2 = (Flipping)e.getSource();
            card2.showFront();
            card2.setEnabled(false);

            if(card1.customName().equals(card2.customName())) {
                pairsFound++;
                if(pairsFound == maxPairs) {
                    gameTimer.stop();
                }
            }
            if(!card1.customName().equals(card2.customName())) {
                errorCount++;
                errorLabel.setText("Errors: " + errorCount);
                Flipping[] cards = board.getCards();
                for(int i = 0; i < cards.length; i++){
                    cards[i].lock(true);
                    cards[i].setEnabled(false);
                }
                card1.lock(false);
                card2.lock(false);
                Timer pause = new Timer(500, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        card1.hideFront();
                        card1.setEnabled(true);
                        card2.hideFront();
                        card2.setEnabled(true);
                        for(int i = 0; i < cards.length; i++){
                            cards[i].lock(false);
                            cards[i].setEnabled(true);
                        }
                    }
                });
                
                pause.setRepeats(false);
                pause.start();
            }
            clickCount = 0;
        }
    }

    private void restartGame()
    {
        pairsFound = 0;
        gameTimer.restart();
        gameTime = 0;
        clickCount = 0;
        errorCount = 0;
        timerLabel.setText("Timer: 0");
        errorLabel.setText("Errors: 0");

        boardView.removeAll();
        board.resetBoard();
        board.viewFill(boardView);
    }
    
    /**
     *
     * @param args
     */
    public static void main(String args[]){
        Game game = new Game();
        game.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) { 
                System.exit(0); 
            }
        });
    }
}
