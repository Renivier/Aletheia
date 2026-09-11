package test;
import java.io.FileInputStream;
import java.io.IOException;
import decompiler.io.BinaryReader;

public class testing {

public static void main(String[] args) throws IOException {


    try(
        FileInputStream in = new FileInputStream("test/testfile.class");
        BinaryReader reader = new BinaryReader(in);
    )
    {

        System.out.println(reader.readU4() == 0xCAFEBABEL);


    }




}







    
}
