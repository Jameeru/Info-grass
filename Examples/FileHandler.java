package Examples;

import java.io.*;

class FileHandler {
    public static void findFile() throws IOException {
        throw new IOException("File not found");  //  throw exception
    }

    public static void main(String[] args) {
        try {
            findFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

