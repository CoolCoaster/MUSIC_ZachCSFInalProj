public class Note{
    private int octave;
    private String noteName;
    private int pianoPos;
    private boolean isRest;
    private boolean isSharp = false;

    public Note(){ // if it's a rest
        this.isRest = true;
        this.noteName = "Rest";
    }

    public Note(String noteName, int octave){
        this.isRest = false;
        this.octave = octave;
        this.noteName = noteName;
        int pos = 0;
        if (noteName.toUpperCase().equals("C")) {
            pos = 1;
        }
        if (noteName.toUpperCase().equals("C#") || noteName.toUpperCase().equals("DB")) {
            pos = 2;
            isSharp = true;
        }
        if (noteName.toUpperCase().equals("D")) {
            pos = 3;

        }
        if (noteName.toUpperCase().equals("D#") || noteName.toUpperCase().equals("EB")) {
            pos = 4;
            isSharp = true;

        }
        if (noteName.toUpperCase().equals("E")) {
            pos = 5;
        }
        if (noteName.toUpperCase().equals("F")) {
            pos = 6;
        }
        if (noteName.toUpperCase().equals("F#") || noteName.toUpperCase().equals("GB")) {
            pos = 7;
            isSharp = true;

        }
        if (noteName.toUpperCase().equals("G")) {
            pos = 8;
        }
        if (noteName.toUpperCase().equals("G#") || noteName.toUpperCase().equals("AB")) {
            pos = 9;
            isSharp = true;

        }
        if (noteName.toUpperCase().equals("A")) {
            pos = 10;
        }
        if (noteName.toUpperCase().equals("A#") || noteName.toUpperCase().equals("BB")) {
            pos = 11;
            isSharp = true;

        }
        if (noteName.toUpperCase().equals("B")) {
            pos = 12;
        }
        this.pianoPos = (octave - 1)*12 + 3 + pos;
    }

    public Note(int pianoPos){
        this.isRest = false;
        this.octave = (pianoPos+8)/12;
        int noteName = (pianoPos) % 12;
        if (noteName == 0) {
            this.noteName = "G#";
            isSharp = true;

        }
        if (noteName == 1) {
            this.noteName = "A";
        }
        if (noteName == 2) {
            this.noteName = "A#";
            isSharp = true;

        }
        if (noteName == 3) {
            this.noteName = "B";
        }
        if (noteName == 4) {
            this.noteName = "C";
        }
        if (noteName == 5) {
            this.noteName = "C#";
            isSharp = true;

        }
        if (noteName == 6) {
            this.noteName = "D";
        }
        if (noteName == 7) {
            this.noteName = "D#";
            isSharp = true;

        }
        if (noteName == 8) {
            this.noteName = "E";
        }
        if (noteName == 9) {
            this.noteName = "F";
        }
        if (noteName == 10) {
            this.noteName = "F#";
            isSharp = true;

        }
        if (noteName == 11) {
            this.noteName = "G";
        }
        this.pianoPos = pianoPos;
    }

    public String getNoteName() {
        return noteName.toUpperCase();
    }

    public int getOctave() {
        return octave;
    }

    public int getPianoPos() {
        return pianoPos;
    }

    public int getWhiteNotePos() {
        if (noteName.charAt(0) > 'B') {
            return 3 + (octave - 1) * 7 + noteName.charAt(0) - 'C';
        }
        if (noteName.charAt(0) == 'A' || noteName.charAt(0) == 'B') {
            return 1 + (octave) * 7 + noteName.charAt(0) - 'A';

        }
        return -1;
    }
    public double getPitch() {
        double pos = -49 + pianoPos;
        return Math.pow(2.0, (pos/12.0))*440.0;
    }

    public boolean getIsSharp() {
        return isSharp;
    }
    public String toString() {
        if (isRest) {
            return noteName;
        }
        return noteName + "" + octave;
    }

}
