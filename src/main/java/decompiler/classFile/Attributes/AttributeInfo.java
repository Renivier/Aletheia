package decompiler.classFile.Attributes;

import decompiler.exception.InvalidClassFileException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import decompiler.classFile.ConstantPool.ConstantPool;
import decompiler.classFile.ConstantPool.Utf8Entry;
import decompiler.classFile.ConstantPool.constantPoolEntry;
import decompiler.io.BinaryReader;

public class AttributeInfo {
    private final int attributeNameIndex;
    private final long attributeLength;
    private final String attributeName;

    protected AttributeInfo(
            int attributeNameIndex,
            long attributeLength,
            String attributeName
    ) {
        this.attributeNameIndex = attributeNameIndex;
        this.attributeLength = attributeLength;
        this.attributeName = attributeName;
    }

    public static AttributeInfo parse(
            BinaryReader reader,
            ConstantPool constantPool
    ) throws IOException {
        int attributeNameIndex = reader.readU2();
        long attributeLength = reader.readU4();

        constantPoolEntry entry = constantPool.getEntry(attributeNameIndex);
        if (!(entry instanceof Utf8Entry utf8Entry)) {
            throw new InvalidClassFileException(
                    "Invalid attribute name index: " + attributeNameIndex
            );
        }

        String attributeName = new String(
                utf8Entry.getUtf8Bytes(),
                StandardCharsets.UTF_8
        );

        long payloadStart = reader.getBytesRead();

        AttributeInfo attribute = switch (attributeName) {
            case "ConstantValue" ->
                    new ConstantValueAttribute(
                            reader,
                            constantPool,
                            attributeNameIndex,
                            attributeLength
                    );
            case "Synthetic" ->
                    new SyntheticAttribute(
                            reader,
                            constantPool,
                            attributeNameIndex,
                            attributeLength
                    );
            case "Deprecated" ->
                    new DeprecatedAttribute(
                            reader,
                            constantPool,
                            attributeNameIndex,
                            attributeLength
                    );
            case "SourceFile" ->
                    new SourceFileAttribute(
                            reader,
                            constantPool,
                            attributeNameIndex,
                            attributeLength
                    );
            case "Signature" ->
                    new SignatureAttribute(
                            reader,
                            constantPool,
                            attributeNameIndex,
                            attributeLength
                    );
            case "NestHost" ->
                    new NestHostAttribute(
                            reader,
                            constantPool,
                            attributeNameIndex,
                            attributeLength
                    );
            default ->
                    new RawAttribute(
                            reader,
                            attributeNameIndex,
                            attributeLength,
                            attributeName
                    );
        };

        long bytesConsumed = reader.getBytesRead() - payloadStart;
        if (bytesConsumed != attributeLength) {
            throw new InvalidClassFileException(
                    "Invalid attribute length for '" + attributeName
                            + "': consumed " + bytesConsumed
                            + " bytes, expected " + attributeLength
            );
        }

        return attribute;
    }

    public long getAttributeLength() {
        return attributeLength;
    }

    public int getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public String getAttributeName() {
        return attributeName;
    }
}
