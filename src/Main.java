public class Main {

    public static void main(String[] args) {
        System.out.println("Initializing");
        BlockChain blockchain = new BlockChain(4);
        System.out.println("Initialized");
        blockchain.addBlock(blockchain.newBlock("Tout sur le Bitcoin"));
        System.out.println("First Block added");
        blockchain.addBlock(blockchain.newBlock("Sylvain Saurel"));
        System.out.println("Second Block added");
        blockchain.addBlock(blockchain.newBlock("https://www.toutsurlebitcoin.fr"));
        System.out.println("Third Block added");
        System.out.println("BlockChain valid ? " + blockchain.isBlockChainValid());
        System.out.println(blockchain);
    }
}
