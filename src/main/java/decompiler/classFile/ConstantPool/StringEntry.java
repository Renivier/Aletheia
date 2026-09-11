package decompiler.classFile.ConstantPool;
import java.io.IOException;
import decompiler.io.BinaryReader;

public class StringEntry extends constantPoolEntry {
    private final int stringIndex;

    public StringEntry(BinaryReader reader) throws IOException{
        stringIndex = reader.readU2();
    }

    public int getStringIndex() {
        return stringIndex;
    }
    
}
