package coding;

import java.io.*;
import java.util.Map;

import static coding.HuffmanCoding.*;

public class CompressFileTest {
    public static void main(String[] args) {
        compressFile("a.jpg", "olg.zip");
        System.out.println("压缩ok");
        uncompressFile("olg.zip","b.jpg");
        System.out.println("解压ok");
    }

    public static void compressFile(String src, String dest){
        try (FileInputStream fis = new FileInputStream(src);
             FileOutputStream fos = new FileOutputStream(dest);
             ObjectOutputStream oos = new ObjectOutputStream(fos) ) {

            byte[] b = new byte[fis.available()];
            fis.read(b);
            byte[] huffmanCodeBytes = toHuffmanCodeZip(b);

            oos.writeObject(huffmanCodeBytes);
            oos.writeObject(huffmanCodes);
        }catch (IOException e){
            e.getStackTrace();
        }
    }

    public static void uncompressFile(String src, String dest){
        try (FileInputStream fis = new FileInputStream(src);
             ObjectInputStream ois = new ObjectInputStream(fis);
             FileOutputStream fos = new FileOutputStream(dest)) {

            byte[] huffmanCodeBytes = (byte[])ois.readObject();
            Map<Byte, String> huffmanCodes = (Map<Byte, String>)ois.readObject();
            byte[] bytes = decode(huffmanCodes, huffmanCodeBytes);
            fos.write(bytes);
        }catch (Exception e){
            e.getStackTrace();
        }

    }
}
