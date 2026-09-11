package decompiler.classFile.Attributes;

import java.io.IOException;

import decompiler.classFile.ConstantPool.ConstantPool;
import decompiler.io.BinaryReader;

public class SyntheticAttribute extends AttributeInfo {
    public SyntheticAttribute(
            BinaryReader reader,
            ConstantPool constantPool,
            int attributeNameIndex,
            long attributeLength
    ) throws IOException {
        super(attributeNameIndex, attributeLength, "Synthetic");
    }
}
