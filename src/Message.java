import java.security.PublicKey;

public class Message {
    private long m;
    private long c;
    private boolean encrypted;
    private Person from;
    private Person to;

    public Message(long m,Person from,Person to) {
        this.m = m;
        this.encrypted = false;
    }

    public long getM() {
        return m;
    }

    public void setM(long m) {
        this.m = m;
    }

    public void setEncrypted(boolean encrypted) {
        this.encrypted = encrypted;
    }

    public void toggleEncrypted() {
        this.encrypted = !this.encrypted;
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

    public long getC() {
        return c;
    }

    public void setC(long c) {
        this.c = c;
    }
}
