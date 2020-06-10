package memorygame;

import javax.swing.*;

/**
 *
 * @author Hansel
 */
public class Flipping extends JButton {
    
    private final ClassLoader loader = getClass().getClassLoader();
    private Icon front;
    private final Icon back = new ImageIcon(loader.getResource("res/Back.jpg"));
    private int id;
    private String customName;

    /**
     *
     */
    public Flipping() { super(); }
    
    /**
     *
     * @param frontImg
     */
    public Flipping(ImageIcon frontImg) {
        super();
        front = frontImg;
        super.setIcon(front);
        super.setDisabledIcon(front);
    }

    /**
     *
     * @param frontImg
     */
    public void setFrontImage(ImageIcon frontImg) { 
        front = frontImg;
    }

    /**
     *
     */
    public void showFront() {
        super.setIcon(front);
    }

    /**
     *
     */
    public void hideFront() {
        super.setIcon(back);
    }

    /**
     *
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
     *
     * @return
     */
    public int id() {
        return id;
    }

    /**
     *
     * @param i
     */
    public void setID(int i) {
        id = i;
    }

    /**
     *
     * @return
     */
    public String customName() {
        return customName; 
    }

    /**
     *
     * @param s
     */
    public void setCustomName(String s) {
        customName = s;
    }
}