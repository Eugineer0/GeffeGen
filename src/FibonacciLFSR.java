import java.util.BitSet;

public class FibonacciLFSR {
    private BitSet characteristicPolynomialCoefficients;
    private BitSet currentPosition;
    private int length;

    FibonacciLFSR(BitSet characteristicPolynomialCoefficients, BitSet startPosition, int length) {
        this.characteristicPolynomialCoefficients = characteristicPolynomialCoefficients;
        this.currentPosition = startPosition;
        this.length = length;
    }

    public boolean getNext() {
        boolean currentBit;
        boolean sum = false;
        boolean prevBit = currentPosition.get(0);

        for(int i = 0; i < length; i++) {
            currentBit = currentPosition.get(i);
            currentPosition.set(i, prevBit);
            prevBit = currentBit;

            if(characteristicPolynomialCoefficients.get(i)) {
                sum = Boolean.logicalXor(currentBit, sum);
            }
        }

        currentPosition.set(0, sum);
        return currentPosition.get(length - 1);
    }
}
