public class Message {
    private long m;
    private boolean encrypted;

    public Message(long m) {
        this.m = m;
        this.encrypted = false;
    }

    public long getM() {
        return m;
    }

    public void setM(long m) {
        this.m = m;
    }

    public void prlongMessage() {
        System.out.println(m);
    }

    public boolean isEncrypted() {
        return encrypted;
    }

    public void prlongEncrypted() {
        System.out.println("Message is currently: "+encrypted);
    }
}
