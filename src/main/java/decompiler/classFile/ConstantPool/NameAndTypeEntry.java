package decompiler.classFile.ConstantPool;
import java.io.IOException;
import decompiler.io.BinaryReader;

public class NameAndTypeEntry extends constantPoolEntry {
    private final int nameIndex;
    private final int descriptorIndex;

    public NameAndTypeEntry(BinaryReader reader) throws IOException {
        nameIndex = reader.readU2();
        descriptorIndex = reader.readU2();
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }
    
}
