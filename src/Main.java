import java.util.BitSet;

public class Main {
    private static byte[][] firstLFSR;
    private static byte[][] secondLFSR;
    private static byte[][] thirdLFSR;

    private static byte[] output;

    public static void main(String[] args) {
        int rounds = 64;

        firstLFSR = FileOps.readFile("res/firstLFSR.bin", 2);
        secondLFSR = FileOps.readFile("res/secondLFSR.bin", 2);
        thirdLFSR = FileOps.readFile("res/thirdLFSR.bin", 2);

        BitSet pol = BitSet.valueOf(GeffeGenerator.reverseBytes(firstLFSR[0]));
        BitSet start = BitSet.valueOf(GeffeGenerator.reverseBytes(firstLFSR[1]));

        LFSR one = new LFSR(pol, start, firstLFSR[0].length);

        GeffeGenerator generator = new GeffeGenerator(firstLFSR[0], secondLFSR[0], thirdLFSR[0], firstLFSR[1], secondLFSR[1], thirdLFSR[1]);

        output = new byte[rounds];
        for(int i = 0; i < rounds; i++) {
            output[i] = generator.getNextByte();
            //output[i] = one.getNext() ? (byte)1 : 0;
        }

        FileOps.writeFile("out.bin", output);
    }
}
