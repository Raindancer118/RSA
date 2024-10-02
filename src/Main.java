public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Sender Bob = new Sender("Bob");
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Program Runtime: "+totalTime+" ms");
    }

}