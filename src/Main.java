import javax.swing.*;

public class Main {
    public static Settings settings;


    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        // Neue Settings werden angelegt, wobei die Key-Size vom User eingegeben wird.
        settings = new Settings();
        long keySize = Long.parseLong(JOptionPane.showInputDialog(null,"Please enter the desired maximum Key-Size.","Key-Size",JOptionPane.PLAIN_MESSAGE));
        settings.setKeySize(keySize);

        // Generieren neuer Personen:
        Person Bob = new Person("Bob");
        Person Alice = new Person("Alice");

        Message firstMessage = new Message(32,Bob,Alice);
        Bob.encrypt(firstMessage);
        Alice.decrypt(firstMessage);

        // Checkups:
        Bob.printKeys();
        Alice.printKeys();
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Program Runtime: "+totalTime+" ms");
    }

}