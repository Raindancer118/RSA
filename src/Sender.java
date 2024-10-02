import javax.swing.JOptionPane;

public class Sender {
    private long[] privateKey = new long[2];
    private long[] publicKey = new long[2];
    private String name;
    private long prime1;
    private long prime2;
    private PrimeKit pk;
    public Settings settings;
    private long d;

    public Sender(String name) {
        this.name = name;
        prepareLaunch();
    }

    private void prepareLaunch(){
        System.out.println("Started Preparation for Launch");
        long startTime = System.currentTimeMillis();

        // Neue Settings werden angelegt, wobei die Key-Size vom User eingegeben wird.
        settings = new Settings();
        long keySize = Long.parseLong(JOptionPane.showInputDialog(null,"Please enter the desired maximum Key-Size.","Key-Size",JOptionPane.PLAIN_MESSAGE));
        settings.setKeySize(keySize);

        // Primzahlen 1 und 2 werden generiert
        System.out.println("Now generating prime1.");
        prime1 = RSAToolkit.getSomeRandomPrimes(settings);
        System.out.println("Now generating prime2.");
        prime2 = RSAToolkit.getSomeRandomPrimes(settings);
        while (prime1 == prime2) {
            System.out.println("prime1 was equal to prime2. Generating new prime2.");
            prime1 = RSAToolkit.getSomeRandomPrimes(settings);
        }
        System.out.println("Generated primes. Starting Generation of PrimeKit.");

        // Berechne die anderen Zahlen für RSA
        pk = new PrimeKit(prime1,prime2);
        System.out.println("Finishing up ... please hang tight!");

        // Berechnung des Public-Keys
        this.publicKey[0] = pk.getN();
        this.publicKey[1] = pk.getE();
        System.out.println(name+"'s Public Key: (" + publicKey[0]+","+publicKey[1]+")");


        System.out.println("Finished preparing Launch. Took "+(System.currentTimeMillis()-startTime)+" ms");
    }
}
