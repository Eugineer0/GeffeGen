import java.util.BitSet;

public class LFSR {
    private BitSet characteristicPolynomialCoefficients;
    private BitSet currentPosition;
    private int length;

    LFSR(BitSet characteristicPolynomialCoefficients, BitSet startPosition, int length) {
        this.characteristicPolynomialCoefficients = characteristicPolynomialCoefficients;
        this.currentPosition = startPosition;
        this.length = length * 8;
    }

    public boolean getNext() {
        byte[] arr = GeffeGenerator.reverseBytes(currentPosition.toByteArray());
        //System.out.println(currentPosition);
        //print(arr);

        boolean current;
        boolean sum = false;
        boolean prev = currentPosition.get(0);

        for(int i = 0; i < length; i++) {
            current = currentPosition.get(i);
            currentPosition.set(i, prev);
            prev = current;

            if(characteristicPolynomialCoefficients.get(i)) {
                sum = Boolean.logicalXor(current, sum);
            }
        }

        currentPosition.set(0, sum);
        return currentPosition.get(length - 1);
    }

    private void print(byte[] arr) {
        for(byte element : arr) {
            System.out.printf("%d", element);
        }
        System.out.printf("\n");
    }
}
