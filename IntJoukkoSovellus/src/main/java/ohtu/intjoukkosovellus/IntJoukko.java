
package ohtu.intjoukkosovellus;

public class IntJoukko {
    private final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko = OLETUSKASVATUS;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm = 0;    // Tyhjässä joukossa alkioiden_määrä on nolla.

    // Main constructor, all other constructors call this one
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 1 || kasvatuskoko < 1) {
            throw new IndexOutOfBoundsException("One or more invalid values given to constructor: " + kapasiteetti +
                                                ", " + kasvatuskoko);
        }

        // Integer arrays are by default initialized to zero
        ljono = new int[kapasiteetti];
        this.kasvatuskoko = kasvatuskoko;
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public boolean lisaa(int luku) {
        // in case of the array being empty, just add the number
        if (isEmpty()) {
            addEntry(luku);
            return true;
        }

        // otherwise check if the value exists in the array;
        // if it exists, return false
        if (kuuluu(luku)) {
            return false;
        // if it doesn't exist, we will try to add it
        } else {
            // if the array is full, create a new, bigger one
            if (isFull()) {
                rescaleArray(ljono.length + kasvatuskoko);
            }
            addEntry(luku);

            return true;
        }
    }

    private void addEntry(int num) {
        ljono[alkioidenLkm] = num;
        alkioidenLkm++;
    }

    private void zeroSpot(int pos) {
        ljono[pos] = 0;
    }

    private void rescaleArray(int new_size) {
        int[] new_array = new int[ljono.length + kasvatuskoko];
        kopioiTaulukko(ljono, new_array);
        ljono = new_array;
    }

    private boolean isFull() {
        return ljono.length == alkioidenLkm;
    }

    private boolean isEmpty() {
        return alkioidenLkm == 0;
    }

    public boolean kuuluu(int luku) {
        return givePosition(luku) != -1;
    }

    private int givePosition(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (ljono[i] == luku) {
                return i;
            }
        }
        return -1;
    }

    public boolean poista(int luku) {
        int position = givePosition(luku);
        if (position == -1) {
            return false;
        }

        moveValuesOneBackwards(position);
        return true;
    }

    private void moveValuesOneBackwards(int start_point) {
        for (int i = start_point; i < alkioidenLkm - 1; i++) {
            ljono[i] = ljono[i+1];
        }

        zeroSpot(alkioidenLkm - 1);
        alkioidenLkm--;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < alkioidenLkm; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + ljono[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += ljono[i];
                tuotos += ", ";
            }
            tuotos += ljono[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }
   

    public IntJoukko yhdiste(IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public IntJoukko leikkaus(IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }
    
    public IntJoukko erotus(IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(i);
        }
 
        return z;
    }
        
}