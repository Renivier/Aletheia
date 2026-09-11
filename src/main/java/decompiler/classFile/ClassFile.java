package decompiler.classFile;
import java.io.IOException;

import decompiler.classFile.Attributes.AttributeInfo;
import decompiler.classFile.ConstantPool.ConstantPool;
import decompiler.io.BinaryReader;
public class ClassFile {
    private final long magic;
    private final int minorVersion;
    private final int majorVersion;
    private final BinaryReader reader;
    private final int constantPoolCount;
    private final ConstantPool constantPool;
    private final int accessFlags;
    private final int thisClass;
    private final int superClass;
    private final int interfacesCount;
    private final int[] interfaces;
    private final int fieldCount;
    private final FieldInfo[] fields;
    private final int methodsCount;
    private final MethodInfo[] methods;
    private final int attributesCount;
    private final AttributeInfo[] attributes;

    public ClassFile(BinaryReader reader) throws IOException {
        this.reader = reader;
        this.magic = reader.readU4();
        if (!magicNumberCheck()) {
            throw new IOException("Invalid class file");
        }
        this.minorVersion = reader.readU2();
        this.majorVersion = reader.readU2();
        this.constantPoolCount = reader.readU2();
        this.constantPool = new ConstantPool(reader, constantPoolCount);
        this.accessFlags = reader.readU2();
        this.thisClass = reader.readU2();
        this.superClass = reader.readU2();
        this.interfacesCount = reader.readU2();
        this.interfaces = new int[interfacesCount];
        for (int i = 0; i < interfacesCount; i++) {
            interfaces[i] = reader.readU2();
        }
        this.fieldCount = reader.readU2();
        this.fields = new FieldInfo[fieldCount];
        for (int i = 0; i < fieldCount; i++) {
            fields[i] = new FieldInfo(reader, this.constantPool);
        }

        this.methodsCount = reader.readU2();
        this.methods = new MethodInfo[methodsCount];
        for (int i = 0; i < methodsCount; i++) {
            methods[i] = new MethodInfo(reader, this.constantPool);
        }
        this.attributesCount = reader.readU2();
        this.attributes = new AttributeInfo[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            attributes[i] = AttributeInfo.parse(reader, this.constantPool);
        }
    }

    public long getMagic() { return magic; }

    private boolean magicNumberCheck() {
        return (magic == 0xCAFEBABEL);
    }

    public int getMinorVersion() { return minorVersion; }
    public int getMajorVersion() { return majorVersion; }
    public int getConstantPoolCount() { return constantPoolCount; }
    public ConstantPool getConstantPool() { return constantPool; }
    public int getAccessFlags() { return accessFlags; }
    public int getThisClass() { return thisClass; }
    public int getSuperClass() { return superClass; }
    public int[] getInterfaces() { return interfaces; }
    public int getFieldCount() { return fieldCount; }
    public AttributeInfo[] getAttributes() { return attributes; }
}
