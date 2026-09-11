package decompiler.classFile.ConstantPool;
import decompiler.io.BinaryReader;
import java.io.IOException;

public class MethodTypeEntry extends constantPoolEntry {
    private final int descriptorIndex;

    public MethodTypeEntry(BinaryReader reader) throws IOException {
        descriptorIndex = reader.readU2();
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }
    
}
