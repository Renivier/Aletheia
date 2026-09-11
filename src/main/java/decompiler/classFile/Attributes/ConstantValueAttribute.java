package decompiler.classFile.Attributes;

import java.io.IOException;

import decompiler.classFile.ConstantPool.ConstantPool;
import decompiler.io.BinaryReader;

public class ConstantValueAttribute extends AttributeInfo {
    private final int constantValueIndex;

    public ConstantValueAttribute(
            BinaryReader reader,
            ConstantPool constantPool,
            int attributeNameIndex,
            long attributeLength
    ) throws IOException {
        super(attributeNameIndex, attributeLength, "ConstantValue");
        this.constantValueIndex = reader.readU2();
    }

    public int getConstantValueIndex() {
        return constantValueIndex;
    }
}
