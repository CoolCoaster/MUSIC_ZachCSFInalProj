import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CompHelper implements ActionListener {
    JFrame frame;
    DrawingPanel dPanel;
    JPanel buttonPanel;
    JPanel sidePanel;
    JPanel topPanel;

    JCheckBox second, fourth, seventh;
    JButton executeButton;
    JLabel sizeLabel;

    //Bottom Panel
    JTextField beat, voiceOne, voiceTwo, voiceThree, wholeMelody, startingScaleDegree;

    JLabel beatLabel, secondLabel, fourthLabel, seventhLabel, startingScaleDegreeLabel, voiceOneLabel, voiceTwoLabel, voiceThreeLabel, wholeMelodyLabel;


    // This is the PaintProgram constructor which sets up the JFrame
    // and all other components and containers
    // ** Code to be edited in Part C **
    public CompHelper() {
        // Set up JFrame using BorderLayout
        frame = new JFrame("Composition Helper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and add DrawingPanel to CENTER
        dPanel = new DrawingPanel();
        frame.add(dPanel);
        dPanel.setBackground(Color.WHITE);

        sidePanel = new JPanel();
        frame.add(sidePanel, BorderLayout.EAST);
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));

        topPanel = new JPanel();
        frame.add(topPanel, BorderLayout.NORTH);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));



        // Create buttonPanel and buttons
        buttonPanel = new JPanel();
        frame.add(buttonPanel, BorderLayout.SOUTH);

        voiceOneLabel = new JLabel("V1:");
        buttonPanel.add(voiceOneLabel);

        voiceOne = new JTextField(3);
        voiceOne.addActionListener(this);
        buttonPanel.add(voiceOne);

        voiceTwoLabel = new JLabel("V2:");
        buttonPanel.add(voiceTwoLabel);

        voiceTwo = new JTextField(3);
        voiceTwo.addActionListener(this);
        buttonPanel.add(voiceTwo);

        voiceThreeLabel = new JLabel("V3:");
        buttonPanel.add(voiceThreeLabel);

        voiceThree = new JTextField(3);
        voiceThree.addActionListener(this);
        buttonPanel.add(voiceThree);


        beatLabel = new JLabel("Beat:");
        sidePanel.add(beatLabel);

        beat = new JTextField(3);
        beat.addActionListener(this);
        sidePanel.add(beat);

        secondLabel = new JLabel("Second");
        sidePanel.add(secondLabel);

        second = new JCheckBox();
        second.addActionListener(this);
        sidePanel.add(second);

        fourthLabel = new JLabel("Fourth");
        sidePanel.add(fourthLabel);

        fourth = new JCheckBox();
        fourth.addActionListener(this);
        sidePanel.add(fourth);

        seventhLabel = new JLabel("Seventh");
        sidePanel.add(seventhLabel);

        seventh = new JCheckBox();
        seventh.addActionListener(this);
        sidePanel.add(seventh);




        startingScaleDegreeLabel = new JLabel("First Scale Degree:");
        buttonPanel.add(startingScaleDegreeLabel);

        startingScaleDegree = new JTextField(3);
        startingScaleDegree.addActionListener(this);
        buttonPanel.add(startingScaleDegree);

        wholeMelodyLabel = new JLabel("Melody (C4, E4, etc):");
        topPanel.add(wholeMelodyLabel);

        wholeMelody = new JTextField(3);
        wholeMelody.addActionListener(this);
        topPanel.add(wholeMelody);

        executeButton = new JButton("Execute");
        executeButton.addActionListener(this);
        buttonPanel.add(executeButton);

        // Set the size and set the visibility
        frame.pack();
        frame.setVisible(true);
    }

    // This the code that is called when any button is pressed
    // We should have a separate case for each button
    // ** Code to be edited in Part B **

    public void addChord(Chord chord, int beat, int measureLength) {
        for (int i = 1; i < 5; i ++) {
            dPanel.addNote(chord.getVoice(i), beat, measureLength);
        }
    }
    public void actionPerformed(ActionEvent ae) {
        // If execute is pressed, generates the music.

        if (ae.getActionCommand().equals("Execute")) {
            dPanel.clearArrays();
            String melodyText = wholeMelody.getText().toUpperCase();
            ArrayList<Note> melody = new ArrayList<>();
            int measureLength = 0;

            //Going through user inputted melody and creating array of notes
            for (int i = 0; i < melodyText.length(); i++) {
                if (melodyText.charAt(i)<= 'Z' && melodyText.charAt(i)>= 'A') {
                    if (!(melodyText.charAt(i+1) == '#' || melodyText.toUpperCase().charAt(i+1) == 'B')) {
                        melody.add(new Note(melodyText.substring(i, i + 1), Integer.parseInt(melodyText.substring(i + 1, i + 2))));
                        measureLength++;
                    } else {
                        melody.add(new Note(melodyText.substring(i, i + 2), Integer.parseInt(melodyText.substring(i + 2, i + 3))));
                        measureLength++;
                    }
                }
            }
            Note[] melodyNotes = new Note[measureLength];
            for (int i = 0; i < measureLength; i++) {
                melodyNotes[i] = melody.get(i);
            }

            //Creating notes from V1 V2 and V3
            Note melodicNote1 = melodyNotes[0];
            Note harmonicNote1, harmonicNote2, harmonicNote3;
            if (voiceOne.getText().contains("#")) {
                harmonicNote1 = new Note(voiceOne.getText().substring(0,2), Integer.parseInt(voiceOne.getText().substring(2,3)));
            } else {
                harmonicNote1 = new Note(voiceOne.getText().substring(0, 1), Integer.parseInt(voiceOne.getText().substring(1, 2)));
            }

            if (voiceTwo.getText().contains("#")) {
                harmonicNote2 = new Note(voiceTwo.getText().substring(0,2), Integer.parseInt(voiceTwo.getText().substring(2,3)));
            } else {
                harmonicNote2 = new Note(voiceTwo.getText().substring(0, 1), Integer.parseInt(voiceTwo.getText().substring(1, 2)));
            }

            if (voiceThree.getText().contains("#")) {
                harmonicNote3 = new Note(voiceThree.getText().substring(0,2), Integer.parseInt(voiceThree.getText().substring(2,3)));
            } else {
                harmonicNote3 = new Note(voiceThree.getText().substring(0, 1), Integer.parseInt(voiceThree.getText().substring(1, 2)));
            }

            //First Chord
            Note[] chordOne = {melodicNote1, harmonicNote1, harmonicNote2, harmonicNote3};

            boolean[] secondArray = new boolean[melody.size()];
            boolean[] fourthArray = new boolean[melody.size()];
            boolean[] seventhArray = new boolean[melody.size()];
            if (!(beat.getText().equals(""))) {
                if (beat.getText().equalsIgnoreCase("all")) {
                    for (int i = 0; i < melody.size(); i++) {
                        secondArray[i] = second.isSelected();
                        fourthArray[i] = fourth.isSelected();
                        seventhArray[i] = seventh.isSelected();
                    }
                } else {
                    for (int i = 0; i < beat.getText().length(); i++) {
                        if (beat.getText().charAt(i) >= '1' && beat.getText().charAt(i) < '1' + melody.size()) {
                            secondArray[Integer.parseInt(beat.getText().charAt(i) + "") - 1] = second.isSelected();
                            fourthArray[Integer.parseInt(beat.getText().charAt(i) + "") - 1] = fourth.isSelected();
                            seventhArray[Integer.parseInt(beat.getText().charAt(i) + "") - 1] = seventh.isSelected();
                        }
                    }
                }
                for (int i = 0; i < melody.size(); i++) {
                    System.out.println("seventh at i:" + seventhArray[i]);
                }
            }


            Chord c = new Chord(chordOne, startingScaleDegree.getText());
            Measure m1 = new Measure(measureLength);

            Chord prevChord = c;
            Chord chosenChord;
            m1.addChord(prevChord, 1);
            addChord(prevChord, 1, measureLength);
            dPanel.repaint();
            boolean cadenceTime = false;
            for (int i = 1; i < m1.meter; i++) {
                System.out.println("Pos in Meter:" + i);
                System.out.println("Pos in Array:" + seventhArray[i]);

                if (i != m1.meter - 2) {
                    chosenChord = Chord.generateNextChord(prevChord, melodyNotes[i], secondArray[i], fourthArray[i], seventhArray[i], false, cadenceTime);

                } else {
                    chosenChord = Chord.generateNextChord(prevChord, melodyNotes[i], secondArray[i], fourthArray[i], seventhArray[i], true, cadenceTime);
                    if(chosenChord.getScaleDegree().equals("IV") || chosenChord.getScaleDegree().equals("V")) {
                        cadenceTime = true;
                    }
                }
                m1.addChord(chosenChord, i+1);

                addChord(chosenChord, i+1, measureLength);
                dPanel.repaint();

                prevChord = chosenChord;

            }
            System.out.println(m1);
        }
    }

    // Main method just creates a PaintProgram object
    public static void main(String[] args) {
        CompHelper x = new CompHelper();
    }
    class DrawingPanel extends JPanel {


        // These are constant values
        private static final int WIDTH = 500;
        private static final int HEIGHT = 500;

        private boolean[][] hasNote;

        private boolean[][] isSharp;
        private boolean[][] isFlat;



        // Constructor sets up DrawingPanel
        // ** You should never need to edit this code **
        public DrawingPanel() {
            // Set background color
            setBackground(Color.WHITE);
            hasNote = new boolean[HEIGHT][WIDTH];
            isSharp = new boolean[HEIGHT][WIDTH];
            isFlat = new boolean[HEIGHT][WIDTH];

        }
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(WIDTH, HEIGHT);
        }

        public void addNoteHelper(int row, int col, boolean sharp) {
            hasNote[row][col] = true;
            isSharp[row][col] = sharp;
        }
        public void clearArrays() {
            for (int row = 0; row < HEIGHT; row++) {
                for (int col = 0; col < WIDTH; col++) {
                    hasNote[row][col] = false;
                    isSharp[row][col] = false;

                }
            }
        }
        public void addNote(Note note, int beat, int measureLength) {
            Note D = new Note("D", 4);
            int row = ((D.getWhiteNotePos()-note.getWhiteNotePos()) * 10) + 225;
            int col = (int)((450.0/(measureLength + 1))*((double)beat));
            addNoteHelper(row, col, note.getIsSharp());
        }
        protected void paintComponent(Graphics g) {
            g.fillRect(0, 0, 500, 500);
            super.paintComponent(g);

            g.setColor(Color.white);
            g.fillRect(0, 0, 500, 500);
            g.setColor(Color.black);

            int lineLength = WIDTH - 50;


            g.fillRect(25, 145, lineLength, 2);
            g.fillRect(25, 165, lineLength, 2);
            g.fillRect(25, 185, lineLength, 2);
            g.fillRect(25, 205, lineLength, 2);
            g.fillRect(25, 225, lineLength, 2);

            g.fillRect(25, 265, lineLength, 2);
            g.fillRect(25, 285, lineLength, 2);
            g.fillRect(25, 305, lineLength, 2);
            g.fillRect(25, 325, lineLength, 2);
            g.fillRect(25, 345, lineLength, 2);


            for (int col = 0; col< WIDTH; col++) {
                for (int row = 0; row< HEIGHT; row++) {
                    int offSet = 0;
                    if(hasNote[row][col]) {
                        if(hasNote[row-10][col] && !hasNote[row-20][col]) {
                            offSet = -28;
                        }
                        g.fillOval(col + offSet, row, 30, 20);
                        if (isSharp[row][col]) {
                            g.fillRect(col - 10, row-7, 3, 36);
                            g.fillRect(col - 20, row-7, 3, 36);
                            g.fillRect(col-27, row+5, 25, 3);
                            g.fillRect(col-27, row+15, 25, 3);
                        }
                        if (offSet == 0 && row > 235) {
                            if (!(row >= 245)) {
                                g.fillRect(col + 26 + offSet, row - 45, 4, 55);
                            } else {
                                g.fillRect(col + offSet, row + 10, 4, 55);
                            }
                        } else if (row <= 235 &&  !hasNote[row + 10][col]){
                                g.fillRect(col + 26 + offSet, row - 45, 4, 55);
                        }
                        if(row == 235) { // Middle C ledger lines
                            g.fillRect(col - 8, row+10, 46, 3);
                        }
                        if (row >= 355) { //ledger lines below staff
                            for (int i = 355; i <=row; i+=20) {
                                g.fillRect(col - 8, i+10, 46, 3);
                            }
                        }
                        if (row <= 115) { //ledger lines above staff
                            for (int i = 115; i >=row; i-=20) {
                                g.fillRect(col - 8, i+10, 46, 3);
                            }
                        }
                    }
                }
            }
        }
    }
}
