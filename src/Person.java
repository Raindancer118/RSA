import java.security.PublicKey;

public class Person {
    private long[] privateKey = new long[2];
    private long[] publicKey = new long[2];
    private String name;
    private long prime1;
    private long prime2;
    private PrimeKit pk;
    private long d;

    public Person(String name) {
        this.name = name;
        prepareLaunch();
    }

    public void encrypt(Message m, Person recipient) {
        System.out.println("Starting encryption of Message "+m.getM());
        long[] publicKey = recipient.publicKey;
        long encrypt = m.getM();
        long encrypted = RSAToolkit.squareMultiply(encrypt, publicKey[1], publicKey[0]);
        m.setM(encrypted);
        m.setC(encrypted);
        m.setEncrypted(true);
        System.out.println("Encrypted Message as: "+encrypted);
    }

    public void decrypt(Message m) {
        System.out.println("Starting decryption of encrypted Message "+m.getC());
        long[] privateKey = this.privateKey;
        long decrypt = m.getC();
        long decrypted = RSAToolkit.squareMultiply(decrypt, privateKey[1], privateKey[0]);
        m.setM(decrypted);
        m.setEncrypted(false);
        System.out.println("Decrypted Message as: "+decrypted);
    }

    private void prepareLaunch(){
        System.out.println("Started Preparation for Launch");
        long startTime = System.currentTimeMillis();

        // Primzahlen 1 und 2 werden generiert
        System.out.println("Now generating prime1.");
        prime1 = RSAToolkit.getSomeRandomPrimes(Main.settings);
        System.out.println("Now generating prime2.");
        prime2 = RSAToolkit.getSomeRandomPrimes(Main.settings);
        while (prime1 == prime2) {
            System.out.println("prime1 was equal to prime2. Generating new prime2.");
            prime1 = RSAToolkit.getSomeRandomPrimes(Main.settings);
        }
        System.out.println("Generated primes. Starting Generation of PrimeKit.");

        // Berechne die anderen Zahlen für RSA
        pk = new PrimeKit(prime1,prime2);
        System.out.println("Finishing up ... please hang tight!");

        // Berechnung des Public-Keys
        this.publicKey[0] = pk.getN();
        this.publicKey[1] = pk.getE();
        System.out.println(name+"'s Public Key: (" + publicKey[0]+","+publicKey[1]+")");

        // Berechnung des Private-Keys
        this.privateKey[0] = pk.getN();
        this.privateKey[1] = pk.getD();
        System.out.println(name+"'s Private Key: (" + privateKey[0]+","+privateKey[1]+")");
        System.out.println("Finished preparing Launch. Took "+(System.currentTimeMillis()-startTime)+" ms");
    }

    public void printKeys(){
        System.out.println(name+"'s Private Key: (" + privateKey[0]+","+privateKey[1]+")");
        System.out.println(name+"'s Public Key: (" + publicKey[0]+","+publicKey[1]+")");
    }
}
