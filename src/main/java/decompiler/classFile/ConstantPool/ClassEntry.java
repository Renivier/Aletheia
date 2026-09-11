package decompiler.classFile.ConstantPool;
import java.io.IOException;

import decompiler.io.BinaryReader;

public class ClassEntry extends constantPoolEntry {
    private final int nameIndex;


    public ClassEntry(BinaryReader reader) throws IOException{
        nameIndex = reader.readU2();
    }

    public int getClassNameIndex() {
        return nameIndex;
    }
    
}
