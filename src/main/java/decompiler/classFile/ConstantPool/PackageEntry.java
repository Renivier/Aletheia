package decompiler.classFile.ConstantPool;
import java.io.IOException;
import decompiler.io.BinaryReader;

public class PackageEntry extends constantPoolEntry {
    private final int nameIndex;

    public PackageEntry(BinaryReader reader) throws IOException{
        nameIndex = reader.readU2();
    }

    public int getNameIndex() {
        return nameIndex;
    }
    
}
