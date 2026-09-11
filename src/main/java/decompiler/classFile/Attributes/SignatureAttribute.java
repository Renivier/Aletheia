package decompiler.classFile.Attributes;

import java.io.IOException;

import decompiler.classFile.ConstantPool.ConstantPool;
import decompiler.io.BinaryReader;

public class SignatureAttribute extends AttributeInfo {
    private final int signatureIndex;

    public SignatureAttribute(
            BinaryReader reader,
            ConstantPool constantPool,
            int attributeNameIndex,
            long attributeLength
    ) throws IOException {
        super(attributeNameIndex, attributeLength, "Signature");
        this.signatureIndex = reader.readU2();
    }

    public int getSignatureIndex() {
        return signatureIndex;
    }
}
