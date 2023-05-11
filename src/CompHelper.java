import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CompHelper implements ActionListener {
    JFrame frame;
    JPanel dPanel;
    JPanel buttonPanel;
    JPanel colorPanel;
    JButton pencilButton, sprayButton, eraserButton, clearButton, fillButton;
    JButton blackButton, redButton, orangeButton, yellowButton, greenButton, blueButton, purpleButton, bigButton, mediumButton, smallButton;
    JLabel sizeLabel;

    // This is the PaintProgram constructor which sets up the JFrame
    // and all other components and containers
    // ** Code to be edited in Part C **
    public CompHelper() {
        // Set up JFrame using BorderLayout
        frame = new JFrame("Paint Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and add DrawingPanel to CENTER
        dPanel = new JPanel();
        frame.add(dPanel);
        dPanel.setBackground(Color.WHITE);

        // Create buttonPanel and buttons
        buttonPanel = new JPanel();
        frame.add(buttonPanel, BorderLayout.SOUTH);

        pencilButton = new JButton("Pencil");
        pencilButton.addActionListener(this);
        buttonPanel.add(pencilButton);

        sprayButton = new JButton("Spray Paint");
        sprayButton.addActionListener(this);
        buttonPanel.add(sprayButton);

        eraserButton = new JButton("Eraser");
        eraserButton.addActionListener(this);
        buttonPanel.add(eraserButton);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        buttonPanel.add(clearButton);

        fillButton = new JButton("Fill");
        fillButton.addActionListener(this);
        buttonPanel.add(fillButton);


        colorPanel = new JPanel();
        frame.add(colorPanel, BorderLayout.EAST);
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.Y_AXIS));

        blackButton = new JButton("Black");
        blackButton.addActionListener(this);
        colorPanel.add(blackButton);

        redButton = new JButton("Red");
        redButton.addActionListener(this);
        colorPanel.add(redButton);

        orangeButton = new JButton("Orange");
        orangeButton.addActionListener(this);
        colorPanel.add(orangeButton);

        yellowButton = new JButton("Yellow");
        yellowButton.addActionListener(this);
        colorPanel.add(yellowButton);

        greenButton = new JButton("Green");
        greenButton.addActionListener(this);
        colorPanel.add(greenButton);

        blueButton = new JButton("Blue");
        blueButton.addActionListener(this);
        colorPanel.add(blueButton);

        purpleButton = new JButton("Purple");
        purpleButton.addActionListener(this);
        colorPanel.add(purpleButton);

        sizeLabel= new JLabel("   Size:");
        colorPanel.add(sizeLabel);

        bigButton = new JButton("Big");
        bigButton.addActionListener(this);
        colorPanel.add(bigButton);

        mediumButton = new JButton("Medium");
        mediumButton.addActionListener(this);
        colorPanel.add(mediumButton);

        smallButton = new JButton("Small");
        smallButton.addActionListener(this);
        colorPanel.add(smallButton);
        // Set the size and set the visibility
        frame.pack();
        frame.setVisible(true);
    }

    // This the code that is called when any button is pressed
    // We should have a separate case for each button
    // ** Code to be edited in Part B **
    public void actionPerformed(ActionEvent ae) {
        // If pencilButton is pressed, set drawingPanel mode to "Pencil"
        if (ae.getActionCommand().equals("Pencil")) {

        }
    }

    // Main method just creates a PaintProgram object
    public static void main(String[] args) {
        CompHelper x = new CompHelper();
    }
}
