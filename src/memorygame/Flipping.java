package memorygame;

import javax.swing.*;

public class Flipping extends JButton
{
    private final ClassLoader loader = getClass().getClassLoader();

    private Icon front;
    private final Icon back = new ImageIcon(loader.getResource("res/Back.jpg"));

    private int id;
    private String customName;

    public Flipping() { super(); }

    public Flipping(ImageIcon frontImg)
    {
        super();
        front = frontImg;
        super.setIcon(front);
        super.setDisabledIcon(front);
    }

    public void setFrontImage(ImageIcon frontImg) { front = frontImg; }

    public void showFront() {
        super.setIcon(front);
    }
    public void hideFront() {
        super.setIcon(back);
    }

    public void lock(boolean Locked){
        if (Locked){
            super.setDisabledIcon(back);
        }
        else{
            super.setDisabledIcon(front);
        }
    }

    public int id() { return id; }
    public void setID(int i) { id = i; }

    public String customName() { return customName; }
    public void setCustomName(String s) { customName = s; }
}
