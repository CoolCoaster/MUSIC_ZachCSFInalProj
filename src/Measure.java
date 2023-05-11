public class Measure {
    Chord[] measure;
    String[] chordProgression;

    int meter;

    public Measure (int meter) {
        this.meter = meter;
        measure = new Chord[meter];
        for (int i = 0; i < meter; i++) {
            measure[i] = new Chord();
        }
        chordProgression = new String[meter];
    }

    public Measure (int meter, Note[] melody) {
        this.meter = meter;
        measure = new Chord[meter];
        chordProgression = new String[meter];
    }

    public void addChord(Chord chord, int beat) {
        measure[beat-1] = chord;
    }

    /*public Note getMelodyNote(int beat) {
        return melody
    }*/
    public String toString() {
        String str = "";
        for (int row = 1; row < 5; row++) {
            str += "Voice" + row + ": ";
            for (int col = 0; col < meter; col++) {
                str += measure[col].getVoice(row).toString() + " ";
            }
        }
        return str;

    }


}

