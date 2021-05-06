import java.io.*;

public class FileOps {
    public static byte[][] readFile(String filename, int expectedNumberLines) {
        return readFile(filename, expectedNumberLines, (int)(new File(filename)).length() / expectedNumberLines);
    }
    public static byte[][] readFile(String filename, int expectedNumberLines, int expectedLength) {
        byte[][] input = new byte[expectedNumberLines][];


        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(filename));
            if(inputStream.available() != expectedLength * expectedNumberLines) {
                throw new IOException();
            }

            for(int i = 0; i < expectedNumberLines; i++) {
                input[i] = new byte[expectedLength];

                inputStream.read(input[i], 0, expectedLength);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return input;
    }

    public static void writeFile(String filename, byte[] output) {
        try {
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filename));
            outputStream.write(output, 0, output.length);

            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

