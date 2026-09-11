package decompiler.classFile.Attributes;

import decompiler.exception.InvalidClassFileException;

import java.io.IOException;

import decompiler.io.BinaryReader;

public class RawAttribute extends AttributeInfo {
    private final byte[] info;

    public RawAttribute(
            BinaryReader reader,
            int attributeNameIndex,
            long attributeLength,
            String attributeName
    ) throws IOException {
        super(attributeNameIndex, attributeLength, attributeName);

        if (attributeLength > Integer.MAX_VALUE) {
            throw new InvalidClassFileException(
                    "Attribute is too large: " + attributeLength
            );
        }

        this.info = new byte[(int) attributeLength];
        for (int i = 0; i < info.length; i++) {
            info[i] = (byte) reader.readU1();
        }
    }

    public byte[] getInfo() {
        return info;
    }
}
