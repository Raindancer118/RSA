import java.util.ArrayList;
import java.util.Random;

public class RSAToolkit {
//    public static Message encrypt(Message m) {
//
//    }
//
//    public static Message decrypt(Message m) {
//
//    }

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

    public static long squareMultiply(long a, long b, long n) {
        long rest = 0;
        ArrayList<Long> zweierPotenzen = new ArrayList<>();
        ArrayList<Long> toAdd = new ArrayList<>();
        long result = a;
        long potenz = 20;
        long c = (long) Math.pow(2,potenz);
        while (potenz >= 0) {
            long d = b-c;
            if (d >= 0) {
                zweierPotenzen.add(potenz);
                b = d;
            }
            potenz--;
            c = (long) Math.pow(2,potenz);
        }
        potenz = 20;
        long letztesErgebnis = a;
        for (int e = 1; e <= 20; e++) {
            letztesErgebnis = ((long) Math.pow(2,potenz) * a)%n;
            if (zweierPotenzen.contains(e)) {
                toAdd.add(letztesErgebnis);
            }
        }
        for (int i = 0; i < toAdd.size(); i++) {
            result = result * toAdd.get(i);
        }
        result = result % n;
        return result;
    }

    public static long getSomeRandomPrimes(Settings settings) {
        Random random = new Random();
        long tryNum = random.nextLong(settings.getKeySize());
        while (tryNum < 1000) tryNum = random.nextLong(settings.getKeySize());
        while (!fermat(tryNum)){
            System.out.println("Picking another number bc "+tryNum+" was not a prime."
            );
            tryNum = random.nextLong(settings.getKeySize());
        }
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
