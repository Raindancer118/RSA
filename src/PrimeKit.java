import java.util.ArrayList;
import java.util.Random;

public class PrimeKit {
    private long p;
    private long q;
    private long n;
    private long e;
    private long phiN;
    private long d;

    public PrimeKit(long p, long q) {
        System.out.println("PrimeKit: Started Generation ... ");
        long startTime = System.currentTimeMillis();
        this.p = p;
        this.q = q;
        this.n = p*q;
        long P = p-1;
        long Q = q-1;
        this.phiN = P*Q;
        System.out.println("PrimeKit: Now generating 'E'...");
        this.e = teilerfremdeZahl(phiN);
        System.out.println("PrimeKit: Now generating 'D'...");
        this.d = multiplikativInverse();
        long endtime = System.currentTimeMillis();
        System.out.println("PrimeKit: Finished generating. Took "+(endtime-startTime)+" ms.");
    }

    public long multiplikativInverse(){
        System.out.println("MultiplikativInverse: Started search ... ");
        d = RSAToolkit.extendedEuklid(phiN,e);
        if (d <= 0) d += phiN;
        System.out.println("MultiplikativInverse: Found "+d+".");
        return d;
    }

    private long teilerfremdeZahl(long zuZahl){
        System.out.println("TeilerfremdeZahl: Starting Search.");
        Random rand = new Random();
        long try1;
        do {
            try1 = rand.nextLong(zuZahl);
            while (try1 == 0) try1 = rand.nextLong(zuZahl);
        } while (RSAToolkit.euklid(zuZahl,try1) != 1);
        return try1;
    }

    private long spedUpTeilerfremdeZahl(long zuZahl){
        System.out.println("SpedUpTeilerfremdeZahl: Using sped up algorithm. Might not be safe.");
        Random rand = new Random();
        long try1 = rand.nextLong(zuZahl/2);
        while (try1 == 0) try1 = rand.nextLong(zuZahl);
        long take = zuZahl - try1;
        if (RSAToolkit.euklid(zuZahl,take) == 1) return take;
        return 2;
    }

    private static ArrayList<Long> primfaktorZerlegung(long number){
        ArrayList<Long> primfaktoren = new ArrayList<>();
        ArrayList<Long> possiblePrimfaktoren = possiblePrimfaktoren(number, Teiler(number));
        for (int i = 0; i < possiblePrimfaktoren.size(); i++) {
            if(RSAToolkit.fermat(possiblePrimfaktoren.get(i))) {
                if (primfaktoren.contains(possiblePrimfaktoren.get(i)) == false) {
                    primfaktoren.add(possiblePrimfaktoren.get(i));
                }
            }
        }
        return primfaktoren;
    }

    private static ArrayList<Long> possiblePrimfaktoren(long number, ArrayList<Long> teiler) {
        ArrayList<Long> possiblePrimfaktoren = new ArrayList<>();
        for (long zahl1: teiler){
            for (long zahl2: teiler){
                if (zahl1*zahl2 == number){
                    possiblePrimfaktoren.add(zahl1);
                    possiblePrimfaktoren.add(zahl2);
                }
            }
        }
        return possiblePrimfaktoren;
    }

    public static ArrayList<Long> Teiler(long number){
        ArrayList<Long> teiler = new ArrayList<>();
        for (long i = 1; i <= number; i++) {
            if (number % i == 0) {
                teiler.add(i);
            }
        }
        return teiler;
    }

    public long getP() {
        return p;
    }

    public long getQ() {
        return q;
    }

    public long getN() {
        return n;
    }

    public long getE() {
        return e;
    }

    public long getD() {
        return d;
    }

    public void deletePQPhiN(){
        this.phiN = 0;
        this.p = 0;
        this.q = 0;
    }
}
