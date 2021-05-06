import java.util.BitSet;

public class GeffeGenerator {
    private LFSR firstRNG;
    private LFSR secondRNG;
    private LFSR thirdRNG;

    GeffeGenerator(byte[] firstPolynomial, byte[] secondPolynomial, byte[] thirdPolynomial, byte[] firstStartPos, byte[] secondStartPos, byte[] thirdStartPos) {
        firstRNG = new LFSR(BitSet.valueOf(reverseBytes(firstPolynomial)), BitSet.valueOf(reverseBytes(firstStartPos)), firstPolynomial.length);
        secondRNG = new LFSR(BitSet.valueOf(reverseBytes(secondPolynomial)), BitSet.valueOf(reverseBytes(secondStartPos)), firstPolynomial.length);
        thirdRNG = new LFSR(BitSet.valueOf(reverseBytes(thirdPolynomial)), BitSet.valueOf(reverseBytes(thirdStartPos)), firstPolynomial.length);
    }

    public static byte[] reverseBytes(byte[] arr) {
        int length = arr.length;

        byte[] reversed = new byte[length];
        for(int i = 0; i < length; i++) {
            reversed[i] = arr[length - 1 - i];
        }

        return reversed;
    }

    public boolean getNext() {
        boolean f, s, t;

        f = firstRNG.getNext();
        s = secondRNG.getNext();
        t = thirdRNG.getNext();

        return Multiplexer.getNext(f, s, t);
    }

    public byte getNextByte() {
        byte nextByte = 0;

        boolean current;

        for(int i = 0; i < 8; i++) {
            current = this.getNext();
            nextByte += current ? Math.pow(2, i) : 0;
        }
        return nextByte;
    }
}
