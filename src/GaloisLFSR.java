import java.util.BitSet;

public class GaloisLFSR {
    private BitSet characteristicPolynomialCoefficients;
    private BitSet currentPosition;
    private int length;

    GaloisLFSR(BitSet characteristicPolynomialCoefficients, BitSet startPosition, int length) {
        this.length = length;
        this.characteristicPolynomialCoefficients = reverseBits(characteristicPolynomialCoefficients);
        this.currentPosition = startPosition;
    }

    private BitSet reverseBits(BitSet bits) {
        BitSet reversed = new BitSet(length);
        for(int i = 0; i < length; i++) {
            reversed.set(i, bits.get(length - 1 - i));
        }

        return reversed;
    }

    public boolean getNext() {
        boolean firstBit = currentPosition.get(0);

        if(firstBit) {
            currentPosition.xor(characteristicPolynomialCoefficients);
        }

        for(int i = 0; i < length - 1; i++) {
            currentPosition.set(i, currentPosition.get(i + 1));
        }

        currentPosition.set(length - 1, firstBit);

        return firstBit;
    }
}
