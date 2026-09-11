package decompiler.classFile.Attributes;

import java.io.IOException;

import decompiler.classFile.ConstantPool.ConstantPool;
import decompiler.io.BinaryReader;

public class SourceFileAttribute extends AttributeInfo {
    private final int sourceFileIndex;

    public SourceFileAttribute(
            BinaryReader reader,
            ConstantPool constantPool,
            int attributeNameIndex,
            long attributeLength
    ) throws IOException {
        super(attributeNameIndex, attributeLength, "SourceFile");
        this.sourceFileIndex = reader.readU2();
    }

    public int getSourceFileIndex() {
        return sourceFileIndex;
    }
}
