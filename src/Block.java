
import com.sun.org.apache.xml.internal.serializer.utils.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Block {

    private int index;
    private long timestamp;
    private String hash;
    private String previousHash;
    private String data;
    private int nonce;

    public Block(int index, long timestamp, String previousHash, String data) {
        this.index = index;
        this.timestamp = timestamp;
        this.hash = Block.calculateHash(this);
        this.previousHash = previousHash;
        this.data = data;
        this.nonce = 0;
    }

    public static String calculateHash(Block block) {
        if (block != null) {
            MessageDigest digest = null;


            try {
                digest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            }
            String txt = block.str();
            final byte bytes[] = digest.digest(txt.getBytes());
            final StringBuilder builder = new StringBuilder();

            for (final byte b : bytes) {
                String hex = Integer.toHexString(0xff & b);
//                System.out.println("Hex is " + hex);
                if (hex.length() == 1) {
                    builder.append('0');
                }
                builder.append(hex);
            }
            return builder.toString();
        }
        return null;
    }

    public void mineBlock(int difficulty) {
        nonce = 0;

        while (!getHash().substring(0, difficulty).equals(MyUtility.zeros(difficulty))) {
            nonce++;
            hash = Block.calculateHash(this);
//            System.out.println("Nonce => " + nonce);
//            System.out.println("hash => " + hash);
        }

    }

    public String str() {
        return index + timestamp + previousHash + data + nonce;
    }

    public int getIndex() {
        return index;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getData() {
        return data;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Block #").append(index).
                append(" [previousHash : ").append(previousHash).
                append(", ").
                append("timestamp : ").append(new Date(timestamp)).
                append(", ").append("data : ").append(data).
                append(", ").
                append("hash : ").append(hash).
                append(", ").
                append("nonce : ").append(nonce).append("]");
        return builder.toString();
    }


}
