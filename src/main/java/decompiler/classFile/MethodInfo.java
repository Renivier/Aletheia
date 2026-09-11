package decompiler.classFile;
import java.io.IOException;
import decompiler.classFile.ConstantPool.ConstantPool;
import decompiler.classFile.Attributes.AttributeInfo;
import decompiler.io.BinaryReader;

public class MethodInfo {
    private final int accessFlags;
    private final int nameIndex;
    private final int descriptorIndex;
    private final int attributesCount;
    private final AttributeInfo[] attributes;

    public MethodInfo(BinaryReader reader, ConstantPool constantPool) throws IOException {
        this.accessFlags = reader.readU2();
        this.nameIndex = reader.readU2();
        this.descriptorIndex = reader.readU2();
        this.attributesCount = reader.readU2();
        this.attributes = new AttributeInfo[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            attributes[i] = AttributeInfo.parse(reader, constantPool);
        }
    }
    public int getAccessFlags() { return accessFlags; }
    public int getNameIndex() { return nameIndex; }
    public int descriptorIndex() { return descriptorIndex; }
    public int getAttributesCount() { return attributesCount; }
    public AttributeInfo[] getAttributes() { return attributes; }
}
