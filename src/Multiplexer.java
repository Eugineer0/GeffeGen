public class Multiplexer {
    static boolean getNext(boolean firstChannel, boolean secondChannel, boolean controller) {
        boolean result = Boolean.logicalXor(firstChannel && secondChannel, !firstChannel && controller);

        return result;
    }
}
