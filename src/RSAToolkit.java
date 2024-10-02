import java.util.ArrayList;
import java.util.Random;

public class RSAToolkit {

    public static boolean fermat(long n) {
        for (long i = 1; i < n-1/2*n; i++) {
            double currentProgress = ((double) i / (n-1/2*n))*100;
            System.out.println("fermat: Current Progress: "+currentProgress+" %");
            ArrayList<Long> had = new ArrayList<>();
            Random random = new Random();
            long a = random.nextLong(n);
            if (n != 1) {
                while (a == 0 || had.contains(a)) a = random.nextLong(n);
                if (euklid(n, a) != 1) return false;
                had.add(a);
            }
            else return false;
        }
        return true;
    }

    public static long euklid(long n1, long n2) {
        long a;
        long b;
        a = Math.max(n1, n2);
        b = Math.min(n1, n2);

        // Actual Logik des Euklid
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static long getBiggest(ArrayList<Long> a) {
        long biggest = a.get(0);
        for (int i = 1; i < a.size(); i++) {
            if (a.get(i) > biggest) {
                biggest = a.get(i);
            }
        }
        return biggest;
    }

    public static long squareMultiply(long basis, long potenz, long modulo) {
        ArrayList<Long> zweierPotenzen = new ArrayList<>();
        ArrayList<Long> einmalAlle = new ArrayList<>();
        ArrayList<Long> Calculations = new ArrayList<>();
        ArrayList<Long> selectedCalculations = new ArrayList<>();
        long addUp = 1;

        // Zerlegung in Zweierpotenzen:
        long rest = potenz;
        while (rest != 0) {
            for (long i = potenz; i >= 0; i--) {
                if (potenz - Math.pow(2, i) >= 0) {
                    einmalAlle.add(i);
                }
            }
            for (long num = potenz; num >= 0; num--){
                if (rest - Math.pow(2, num) >= 0) {
                    zweierPotenzen.add(num);
                    rest = (long) (rest - Math.pow(2L,num));
                }
            }
        }
        Calculations.add(basis%modulo);
        for (int i = 1; i < einmalAlle.size(); i++) {
            long last = Calculations.getLast();
            long result = (long)Math.pow(last,2)%modulo;
            Calculations.add(result);
        }
        for (int i = 0; i < einmalAlle.size(); i++) {
            if (zweierPotenzen.contains(einmalAlle.get(i))){
                selectedCalculations.add(Calculations.get(Calculations.size()-i-1));
            }
        }
        for (int i = 0; i < selectedCalculations.size(); i++) {
            addUp = selectedCalculations.get(i) * addUp;
        }
        addUp = addUp % modulo;
        return addUp;
    }

    public static long getSomeRandomPrimes(Settings settings) {
        Random random = new Random();
        long tryNum = random.nextLong(settings.getKeySize());
        while (tryNum < 10) tryNum = random.nextLong(settings.getKeySize());
        while (!fermat(tryNum)){
            System.out.println("Picking another number bc "+tryNum+" was not a prime."
            );
            tryNum = random.nextLong(settings.getKeySize());
        }
        System.out.println("Picked "+tryNum+" as prime.");
        return tryNum;
    }

    public static long extendedEuklid(long n1, long n2) {
        ArrayList<Long> aSpalte = new ArrayList<>();
        ArrayList<Long> bSpalte = new ArrayList<>();
        ArrayList<Long> qSpalte = new ArrayList<>();
        // basic Euklid
        long a;
        long b;
        long q;
        System.out.println("extended euklid: recieved "+n1+" and "+n2+". Starting extended euklid.");
        a = Math.max(n1, n2);
        b = Math.min(n1, n2);
        aSpalte.add(a);
        bSpalte.add(b);

        // Actual Logik des Euklid
        while (b != 0) {
            if (b != 0) {
                q = Math.floorDiv(a,b);
                qSpalte.add(q);
            }
            long temp = b;
            b = a % b;
            bSpalte.add(b);
            a = temp;
            aSpalte.add(a);
        }

        // Logik des erweiterten Euklid:
        // Variablen:
        long sAlt;
        long tNeu;
        long tAlt;
        long qNeu;
        long sNeu;
        // ArrayList für S & T:
        ArrayList<Long> sSpalte = new ArrayList<>();
        ArrayList<Long> tSpalte = new ArrayList<>();
        // Vorbereitung für Durchlauf:
        sSpalte.add(1L);
        tSpalte.add(0L);
        sAlt = 1;
        tAlt = 0;

        for (int i = qSpalte.size(); i > 0; i--) {
            sNeu = tAlt;
            sSpalte.add(sNeu);
            qNeu = qSpalte.get(i-1);
            tNeu = sAlt - qNeu * tAlt;
            tSpalte.add(tNeu);
            sAlt = sNeu;
            tAlt = tNeu;
        }
        return tAlt;
    }
}
