package decompiler.classFile.Attributes;

import java.io.IOException;

import decompiler.classFile.ConstantPool.ConstantPool;
import decompiler.io.BinaryReader;

public class DeprecatedAttribute extends AttributeInfo {
    public DeprecatedAttribute(
            BinaryReader reader,
            ConstantPool constantPool,
            int attributeNameIndex,
            long attributeLength
    ) throws IOException {
        super(attributeNameIndex, attributeLength, "Deprecated");
    }
}
