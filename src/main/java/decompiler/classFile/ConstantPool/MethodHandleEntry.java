package decompiler.classFile.ConstantPool;

import java.io.IOException;

import decompiler.io.BinaryReader;

public class MethodHandleEntry extends constantPoolEntry {
    private final int referenceKind;
    private final int referenceIndex;

    public MethodHandleEntry(BinaryReader reader) throws IOException{
        referenceKind = reader.readU1();
        referenceIndex = reader.readU2();
    }

    public int getReferenceKind() {
        return referenceKind;
    }

    public int getReferenceIndex() {
        return referenceIndex;
    }
    
}
