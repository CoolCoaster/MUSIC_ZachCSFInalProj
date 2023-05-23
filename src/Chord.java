public class Chord {
    //Root, Second, Third, Fourth, Fifth, Seventh
    private static String[][] allChords = {
            {"I", "C", "D", "E", "F", "G", "B"}, //List of chords in C Major
            {"II", "D", "E", "F#", "G", "A", "C#"},
            {"ii", "D", "E", "F", "G", "A", "C"},
            {"III", "E", "F#", "G#", "A", "B", "D#"},
            {"iii", "E", "F#", "G", "A", "B", "D"},
            {"IV", "F", "G", "A", "A#", "C", "E"},
            {"iv", "F", "G", "G#", "A#", "C", "D#"},
            {"V", "G", "A", "B", "C", "D", "F"},
            {"v", "G", "A", "A#", "C", "D", "F"},
            {"VI", "A", "B", "C#", "D", "E", "G#"},
            {"vi", "A", "B", "C", "D", "E", "G"},
            {"VII", "B", "C#", "D#", "E", "F#", "A#"},
            {"vii", "B", "C#", "D", "E", "F#", "A"}
    };

    private static String[] listOfScaleDegrees = {"I", "II", "ii", "III", "iii", "IV", "iv", "V", "v", "VI", "vi", "VII", "vii"};

    private static Double[][] chanceOfChord = {
            {50.0, 1.0, 2.0, 2.0, 2.0, 15.0, 4.0, 10.0, 2.0, 1.0, 9.0, 1.0, 1.0}, //I
            {1.0, 50.0, 5.0, 5.0, 2.0, 2.0, 1.0, 9.0, 9.0, 9.0, 1.0, 5.0, 1.0}, //II
            {2.0, 5.0, 50.0, 3.0, 3.0, 8.0, 3.0, 12.0, 8.0, 1.0, 3.0, 1.0, 1.0}, //ii
            {2.0, 5.0, 3.0, 50.0, 5.0, 17.5, 0.5, 0.5, 0.5, 6.0, 9.0, 0.5, 0.5}, //III
            {2.0, 2.0, 3.0, 5.0, 50.0, 3.0, 5.0, 11.0, 2.0, 4.0, 5.0, 4.0, 4.0}, //iii
            {5.0, 2.0, 8.0, 1.0, 2.0, 50.0, 11.0, 10.0, 2.0, 2.0, 5.0, 1.0, 1.0}, //IV
            {4.0, 1.0, 2.0, 2.0, 2.0, 15.0, 50.0, 14.5, 4.0, 2.0, 2.0, 0.5, 1.0}, //iv
            {10.0, 0.0, 0.0, 1.0, 3.0, 11.0, 3.0, 50.0, 3.0, 3.0, 11.0, 1.0, 4.0}, //V
            {2.0, 9.0, 4.0, 1.0, 1.0, 10.0, 4.0, 10.0, 50.0, 5.0, 2.0, 1.0, 10.0}, //v
            {1.0, 10.0, 1.0, 6.0, 7.0, 1.0, 2.0, 7.0, 3.0, 50.0, 3.0, 4.0, 5.0}, //VI
            {11.0, 1.0, 3.0, 1.0, 2.0, 12.0, 1.0, 12.0, 1.0, 3.0, 50.0, 1.0, 2.0}, //vi
            {1.0, 7.0, 1.0, 8.0, 1.0, 7.0, 1.0, 7.0, 1.0, 7.0, 8.0, 50.0, 1.0}, //VII
            {26.0, 1.0, 1.0, 1.0, 1.0, 3.0, 1.0, 1.0, 1.0, 2.0, 10.0, 2.0, 50.0}, //vii
    };
    private Note[] notes = {new Note(), new Note(), new Note(), new Note()};

    private String scaleDegree;
    public Chord(Note[] notes, String scaleDegree) {
        this.notes = notes;
        this.scaleDegree = scaleDegree;
    }
    public Chord() {
    }
    public void setScaleDegree (String scaleDegree) {
        this.scaleDegree = scaleDegree;
    }
    public String getScaleDegree () {
        return scaleDegree;
    }
    public Chord(String scaleDegree) {
        int degree = convertScaleStringToInt(scaleDegree);
        Note one = new Note(allChords[degree][1], 4);
        Note two = new Note(allChords[degree][3], 4);
        Note three = new Note(allChords[degree][5], 4);
        Note four = new Note(allChords[degree][6], 4);
        notes[0] = four;
        notes[1] = three;
        notes[2] = two;
        notes[3] = one;
        this.scaleDegree = allChords[degree][0];
    }

    public static boolean isNoteInChord(Note note, String scaleDegree, boolean second, boolean fourth, boolean seventh) {
        int posInArray = -1;
        for (int row = 0; row < 13; row++) { //figuring out the row of the scale degree for the next for loop, or returning false if it was put in incorrectly
            if (allChords[row][0].equals(scaleDegree)) {
                posInArray = row;
            }
        }
        if (posInArray == -1) {
            return false;
        }

        for (int col = 1; col < 7; col++) { //going through the loop and returning true if given note is in the chord
            Note tempWithSharp = new Note(note.getPianoPos());
            if (!((col == 2 && second == false) || (col == 4 && fourth == false) || (col == 6 && seventh == false))) {
                if (allChords[posInArray][col].equals(tempWithSharp.getNoteName())) {
                    return true;
                }
            }
        }
        return false;
    }


    public Note getVoice(int voice) {
        return notes[voice-1];
    }

    public void setVoice(Note noteToAdd, int voice) {
        notes[voice-1] = noteToAdd;
    }
    public static String nextScaleDegree(Note melodyNote, Chord currentChord, boolean second, boolean fourth, boolean seventh) {
        if (melodyNote.getNoteName().toUpperCase().equals("REST")) {
            return currentChord.getScaleDegree();
        }
        int scaleDegreePos = convertScaleStringToInt(currentChord.getScaleDegree());
        while (true) { //I'm sorry Sawyer
            double randomNum = ((Math.random() * 100) + 1);
            double current = 0;
            for (int i = 0; i < 13; i++) {
                current += chanceOfChord[scaleDegreePos][i];
                if (randomNum <= current && isNoteInChord(melodyNote, listOfScaleDegrees[i], second, fourth, seventh)) {
                    return listOfScaleDegrees[i];
                }
            }
        }
        //add up the chords to 100, if is more than one before, less than one after, choose it
    }

    //Only checks if it follows counterpoint rules in respect of the most immediately previous one
    public static boolean followsCounterpointRules(Chord chordPrev,Chord chordCurrent){
        int[] differences = {-100,-100,-100,-100};
        for (int i = 1; i <= 4; i++) { // returns the difference between notes
            if (!(chordCurrent.getVoice(i).getNoteName().toUpperCase().equals("REST"))) {
                differences[i - 1] = chordCurrent.getVoice(i).getPianoPos() - chordPrev.getVoice(i).getPianoPos();
            }
        }
        // NO PARALLEL FIFTHS! or OCTAVES
        for(int i = 1; i <= 3; i++) {
            for(int j = i+1; j <= 4; j++) {
                if (differences[i-1] == differences[j-1]) {
                    if (Math.abs(chordCurrent.getVoice(i).getPianoPos() - chordCurrent.getVoice(j).getPianoPos()) == 7) {
                        return false;
                    }
                    if (Math.abs(chordCurrent.getVoice(i).getPianoPos() - chordCurrent.getVoice(j).getPianoPos()) == 12) {
                        return false;
                    }
                    if (Math.abs(chordCurrent.getVoice(i).getPianoPos() - chordCurrent.getVoice(j).getPianoPos()) == 19) {
                        return false;
                    }
                    if (Math.abs(chordCurrent.getVoice(i).getPianoPos() - chordCurrent.getVoice(j).getPianoPos()) == 24) {
                        return false;
                    }
                }
            }
        }
        //Chord must be in order!
        if(!(isAlignedVertically(chordCurrent))) {
            return false;  //Keep working here<<< Add more rules
        }
        //No big leaps!
        for (int i = 0; i < 4; i++) {
            if (Math.abs(differences[i]) > 9 && differences[i] != -100) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAlignedVertically(Chord chordCurrent) {
        if (chordCurrent.getVoice(4).getNoteName().toUpperCase().equals("REST")) {
            return chordCurrent.getVoice(1).getPianoPos() > chordCurrent.getVoice(2).getPianoPos() && chordCurrent.getVoice(2).getPianoPos() > chordCurrent.getVoice(3).getPianoPos();
        }
        return chordCurrent.getVoice(1).getPianoPos() > chordCurrent.getVoice(2).getPianoPos() && chordCurrent.getVoice(2).getPianoPos() > chordCurrent.getVoice(3).getPianoPos() && chordCurrent.getVoice(3).getPianoPos() > chordCurrent.getVoice(4).getPianoPos();
    }

    public static Chord generateNextChord(Chord chordPrev, Note melodyNote, boolean second, boolean fourth, boolean seventh, boolean isSecondToLast, boolean cadenceTime) {
        String scaleDegree;
        for (int k = 0; k < 100; k++) {
            if (!isSecondToLast || Math.random() < 0.5) {// IF it is second to last there is a 50% chance it will try to have a V chord.
                scaleDegree = nextScaleDegree(melodyNote, chordPrev, second, fourth, seventh);
            }
            else {
                scaleDegree = "V";
            }
            if (cadenceTime && Math.random() < 0.8) {
                scaleDegree = "I";
            }
            Note[] chordNotes = {melodyNote, new Note(), new Note(), new Note()};
            Chord nextChord = new Chord(chordNotes, scaleDegree);
            int notesAdded = 0;
            for (int i = 2; i <= 4; i++) {
                int j = 0;
                boolean noteFound = false;
                while (Math.abs(j) <= 10 && !noteFound) {
                    int pianoPos = chordPrev.getVoice(i).getPianoPos() + j;
                    if (isNoteInChord(new Note(pianoPos), scaleDegree, second, fourth, seventh)) {
                        nextChord.setVoice(new Note(pianoPos), i);
                        if(followsCounterpointRules(chordPrev, nextChord)) {
                            noteFound = true;
                            notesAdded++;
                        }
                        else {
                            nextChord.setVoice(new Note(), i);
                        }
                    }
                    if (j == 0) {
                        j++;
                    } else if (j > 0) {
                        j *= -1;
                    } else {
                        j *= -1;
                        j++;
                    }
                }
            }
            if (notesAdded == 3 && followsCounterpointRules(chordPrev, nextChord)) {
                return nextChord;
            }
        }
        return null;
    }

    public static int convertScaleStringToInt(String scaleString) {
        for (int i = 0; i < 13; i++) {
            if (listOfScaleDegrees[i].equals(scaleString)) {
                return i;
            }
        }
        return -1;
    }

    public String toString() {
        return notes[0].toString() + "/" + notes[1].toString() + "/" + notes[2].toString() + "/" + notes[3].toString();
    }

    public static int numberOfNotes(String str) {
        int tot = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.toUpperCase().charAt(i) >= 'A'|| str.toUpperCase().charAt(i) <= 'G') {
                tot++;
            }
        }
        return tot;
    }
}
