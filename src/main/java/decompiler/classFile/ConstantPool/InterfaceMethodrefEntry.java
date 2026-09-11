package decompiler.classFile.ConstantPool;
import java.io.IOException;
import decompiler.io.BinaryReader;

public class InterfaceMethodrefEntry extends constantPoolEntry {
    private final int classIndex;
    private final int nameAndTypeIndex;

    public InterfaceMethodrefEntry(BinaryReader reader) throws IOException {
        classIndex = reader.readU2();
        nameAndTypeIndex = reader.readU2();
    }

    public int getClassIndex() {
        return classIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }
    
}
