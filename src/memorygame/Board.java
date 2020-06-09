package memorygame;

import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Board
{
    // Array to hold board cards
    private Flipping cards[];

    // Resource loader
    private ClassLoader loader = getClass().getClassLoader();

    public Board(int size, ActionListener AL)
    {
        // Allocate and configure the game board: an array of cards
        cards = new Flipping[size];

        // Fill the Cards array
        int imageIdx = 1;
        for (int i = 0; i < size; i += 2) {

            // Load the front image from the resources folder
            String imgPath = "res/card" + imageIdx + ".jpg";
            ImageIcon img = new ImageIcon(loader.getResource(imgPath));
            imageIdx++;  // get ready for the next pair of cards

            // Setup two cards at a time
            Flipping c1 = new Flipping(img);
            c1.setCustomName(imgPath);
            Flipping c2 = new Flipping(img);
            c2.setCustomName(imgPath);

            // Add them to the array
            cards[i] = c1;
            cards[i + 1] = c2;
        }

        //Randomize the card positions
        int index;
        Random random = new Random();
        Flipping temp = new Flipping();
        for (int i = cards.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = cards[index];
            cards[index] = cards[i];
            cards[i] = temp;
        }
        //Set cards so backs are facing forwards
        for(int i = 0; i < cards.length; i++)
        {
            cards[i].hideFront();
        }
    }

    public void fillBoardView(JPanel view)
    {
        for (Flipping c : cards) {
            view.add(c);
        }
    }

    public void resetBoard()
    {
        //Display the back of all cards and reset clickability
        for(int i = 0; i < cards.length; i++)
        {
            cards[i].setEnabled(true);
            cards[i].hideFront();
        }
        //Randomize the card positions
        int index;
        Random random = new Random();
        Flipping temp = new Flipping();
        for (int i = cards.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = cards[index];
            cards[index] = cards[i];
            cards[i] = temp;
        }
    }

    public int getSize() {return cards.length;}
    public Flipping[] getCards(){return cards;}
}
