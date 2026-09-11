package decompiler.classFile.Attributes;

import java.io.IOException;

import decompiler.classFile.ConstantPool.ConstantPool;
import decompiler.io.BinaryReader;

public class NestHostAttribute extends AttributeInfo {
    private final int hostClassIndex;

    public NestHostAttribute(
            BinaryReader reader,
            ConstantPool constantPool,
            int attributeNameIndex,
            long attributeLength
    ) throws IOException {
        super(attributeNameIndex, attributeLength, "NestHost");
        this.hostClassIndex = reader.readU2();
    }

    public int getHostClassIndex() {
        return hostClassIndex;
    }
}
