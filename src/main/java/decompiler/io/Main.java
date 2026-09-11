package decompiler.io;
import java.io.IOException;
import java.io.FileInputStream;

public class Main {


    public static void main(String[] args) throws IOException{

        try (
        FileInputStream in = new FileInputStream("../../../test/test.bin")
        )
        {

            BinaryReader myReader = new BinaryReader(in);
            System.out.println(myReader.readU4());
            myReader.close();


        }
        
    }
    
}
