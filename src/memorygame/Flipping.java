/**
 * Memory Game, a minigame for Datos Party, first project of CE1103, IS-2020
 */
package memorygame;

import javax.swing.*;

/**
 * Card class, it creates the flippable cards for the game.
 * @author Hansel Hampton
 * @version 1.3
 */
public class Flipping extends JButton {
    
    private final ClassLoader loader = getClass().getClassLoader();
    private Icon front;
    private final Icon back = new ImageIcon(loader.getResource("res/Back.jpg"));
    private int id;
    private String customName;

    /**
     * default constructor, only call the JButton constructor.
     */
    public Flipping() {
        super();
    }
    
    /**
     *Constructor that sets the card's front.
     * @param frontImg
     */
    public Flipping(ImageIcon frontImg) {
        super();
        front = frontImg;
        super.setIcon(front);
        super.setDisabledIcon(front);
    }

    /**
     *Set the card's front image.
     * @param frontImg
     */
    public void setFrontImage(ImageIcon frontImg) { 
        front = frontImg;
    }

    /**
     *Flip-to-show function of the card.
     */
    public void showFront() {
        super.setIcon(front);
    }

    /**
     *Flip-to-hide function of the card.
     */
    public void hideFront() {
        super.setIcon(back);
    }

    /**
     *front or back image assignment.
     * @param Locked
     */
    public void lock(boolean Locked){
        if (Locked){
            super.setDisabledIcon(back);
        }
        else{
            super.setDisabledIcon(front);
        }
    }

    /**
     * Get the ID of the card
     * @return id
     */
    public int id() {
        return id;
    }

    /**
     *Set the ID of the card.
     * @param i
     */
    public void setID(int i) {
        id = i;
    }

    /**
     *Get the custom name of the card.
     * @return customName
     */
    public String customName() {
        return customName; 
    }

    /**
     *Set a custom name for the card.
     * @param s
     */
    public void setCustomName(String s) {
        customName = s;
    }
}