import java.util.ArrayList;

/**
In dieser Klasse werden Tests für die Methoden in anderen Klassen durchgeführt.
 */
public class Testing {
    public static void main(String[] args) {
        PrimeKit primeKit = new PrimeKit(7,11);
        RSAToolkit.squareMultiply(42,13,77);
    }

    public static void printEuklidLists(ArrayList<Long> a,ArrayList<Long> b,ArrayList<Long> r,ArrayList<Long> s,ArrayList<Long> t) {
        for (int i = 0; i < r.size(); i++) {
            System.out.println(a.get(i) + "  "  + b.get(i) + "  "  + r.get(i) + "  "  + s.get(s.size()-i-1) + "  "  + t.get(t.size()-i-1));
        }
        System.out.println(a.getLast()+"  "+b.getLast()+"   "+"   "+s.getFirst()+"  "+t.getFirst());
    }
}
