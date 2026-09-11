package decompiler.classFile.Attributes;

import decompiler.exception.InvalidClassFileException;

import java.io.IOException;

import decompiler.classFile.ConstantPool.ConstantPool;
import decompiler.io.BinaryReader;

public class CodeAttribute extends AttributeInfo {
    private final int maxStack;
    private final int maxLocals;
    private final long codeLength;
    private final byte[] code;
    private final int exceptionTableLength;
    private final ExceptionTableEntry[] exceptionsTable;
    private final int attributesCount;
    private final AttributeInfo[] attributes;

    public CodeAttribute(
            BinaryReader reader,
            ConstantPool constantPool,
            int attributeNameIndex,
            long attributeLength
    ) throws IOException {
        super(attributeNameIndex, attributeLength, "Code");

        this.maxStack = reader.readU2();
        this.maxLocals = reader.readU2();
        this.codeLength = reader.readU4();
        if (codeLength > Integer.MAX_VALUE) {
            throw new InvalidClassFileException(
                    "Code attribute is too large: " + codeLength
            );
        }
        this.code = new byte[(int) codeLength];

        for (int i = 0; i < code.length; i++) {
            this.code[i] = (byte) reader.readU1();
        }

        this.exceptionTableLength = reader.readU2();
        this.exceptionsTable = new ExceptionTableEntry[exceptionTableLength];

        for (int i = 0; i < exceptionTableLength; i++) {
            this.exceptionsTable[i] = new ExceptionTableEntry(reader);
        }

        this.attributesCount = reader.readU2();
        this.attributes = new AttributeInfo[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            this.attributes[i] = AttributeInfo.parse(reader, constantPool);
        }
    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public long getCodeLength() {
        return codeLength;
    }

    public byte[] getCode() {
        return code;
    }

    public int getExceptionTableLength() {
        return exceptionTableLength;
    }

    public ExceptionTableEntry[] getExceptions() {
        return exceptionsTable;
    }

    public int getAttributesCount() {
        return attributesCount;
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }
}
