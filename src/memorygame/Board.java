/**
 * Memory Game, a minigame for Datos Party, first project of CE1103, IS-2020
 */
package memorygame;

import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

/**
 * Canvas class, this class place and flips the cards oportunatelly.
 * @author Hansel Hampton
 * @version 1.3
 */
public class Board {
    private final Flipping cards[];
    private final ClassLoader loader = getClass().getClassLoader();

    /**
     *Board method that sets the flippable cards in position and ready to flip.
     * @param size nteger dimentions of the board.
     * @param AL action realized when the board is generated.
     */
    public Board(int size, ActionListener AL) {

        cards = new Flipping[size];

        int idx = 1;
        for (int i = 0; i < size; i += 2) {

            String imgPath = "/res/card" + idx + ".jpg";
            ImageIcon img = new ImageIcon(loader.getResource(imgPath));
            idx++;

            Flipping c1 = new Flipping(img);
            c1.setCustomName(imgPath);
            Flipping c2 = new Flipping(img);
            c2.setCustomName(imgPath);

            cards[i] = c1;
            cards[i + 1] = c2;
        }

        int index;
        Random random = new Random();
        Flipping temp = new Flipping();
        for (int i = cards.length - 1; i > 0; i--) {
            
            index = random.nextInt(i + 1);
            temp = cards[index];
            cards[index] = cards[i];
            cards[i] = temp;
        }

        for(int i = 0; i < cards.length; i++) { 
       
            cards[i].hideFront();
        }
    }
  
    /**
     *Add things to show on the board.
     * @param view
     */
    public void viewFill(JPanel view){
        for (Flipping c : cards) {
            view.add(c);
        }
    }

    /**
     * Return the game to it initial state.
     */
    public void resetBoard() {
        for(int i = 0; i < cards.length; i++){
        
            cards[i].setEnabled(true);
            cards[i].hideFront();
        }

        int index;
        Random random = new Random();
        Flipping temp = new Flipping();
        for (int i = cards.length - 1; i > 0; i--) {
    
            index = random.nextInt(i + 1);
            cards[index] = cards[i];
            cards[i] = temp;
        }
    }

    /**
     *Get the total of cards in the game.
     * @return cards.lenght
     */
    public int getSize() {
        return cards.length;
    }

    /**
     * Get the array of cards of the game.
     * @return cards
     */
    public Flipping[] getCards(){
        return cards;
    }
}
