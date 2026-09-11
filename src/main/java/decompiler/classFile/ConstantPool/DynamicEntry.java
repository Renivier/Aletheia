package decompiler.classFile.ConstantPool;

import java.io.IOException;

import decompiler.io.BinaryReader;

public class DynamicEntry extends constantPoolEntry {
    private final int bootstrapMethodAttrIndex;
    private final int nameAndTypeIndex;

    public DynamicEntry(BinaryReader reader) throws IOException{
        bootstrapMethodAttrIndex = reader.readU2();
        nameAndTypeIndex = reader.readU2();
    }

    public int getBootstrapMethodAttrIndex() {
        return bootstrapMethodAttrIndex;
    }
    
    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }
}
