


public class Main {
/*
Notes: Need to start using "is second to last"

*/
    public static void main(String[] args) {
        CompHelper cH = new CompHelper();

        Note[] notes = {new Note("E", 5),
                new Note("E", 4),
                new Note("G", 3),
                new Note("C", 3)
        };

        Chord c = new Chord(notes, "I");
        Measure m1 = new Measure(9);
        Note[] melody = {new Note("E", 6), new Note("D", 5), new Note("E", 5), new Note("F", 5), new Note("G", 5), new Note("E", 5), new Note("D", 5), new Note("B", 4), new Note("C", 5)};
        Chord prevChord = c;
        Chord chosenChord = new Chord();
        m1.addChord(prevChord, 1);
        boolean cadenceTime = false;
        for (int i = 1; i < m1.meter; i++) {
            if (i != m1.meter - 2) {
                chosenChord = Chord.generateNextChord(prevChord, melody[i], false, false, false, false, cadenceTime);
            } else {
                System.out.println("YEAH1");
                chosenChord = Chord.generateNextChord(prevChord, melody[i], false, false, false, true, cadenceTime);
                if(chosenChord.getScaleDegree().equals("IV") || chosenChord.getScaleDegree().equals("V")) {
                    cadenceTime = true;
                    System.out.println("YEAH");
                }
            }
            m1.addChord(chosenChord, i+1);
            System.out.println(i + " chord " + chosenChord);
            prevChord = chosenChord;
        }
        System.out.println(m1);
    }
}
