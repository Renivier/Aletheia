package decompiler.classFile.ConstantPool;
import java.io.IOException;
import decompiler.io.BinaryReader;

public class ConstantPool {
    private final BinaryReader reader;
    private final constantPoolEntry[] entries;



    public ConstantPool(BinaryReader reader, int constantPoolCount) throws IOException{
        this.reader = reader;
        this.entries = new constantPoolEntry[constantPoolCount]; 

        for (int i = 1; i < constantPoolCount; i++) {
            int tag = this.reader.readU1();
            switch (tag) {
                default -> throw new IOException("Invalid constant pool tag: " + tag);
                case 1 ->  entries[i] = new Utf8Entry(reader);
                case 3 -> entries[i] = new IntegerEntry(reader);
                case 4 -> entries[i] = new FloatEntry(reader);
                case 5 -> entries[i] = new LongEntry(reader);
                case 6 -> entries[i] = new DoubleEntry(reader);
                case 7 -> entries[i] = new ClassEntry(reader);
                case 8 -> entries[i] = new StringEntry(reader);
                case 9 -> entries[i] = new FieldrefEntry(reader);
                case 10 -> entries[i] = new MethodrefEntry(reader);
                case 11 -> entries[i] = new InterfaceMethodrefEntry(reader);
                case 12 -> entries[i] = new NameAndTypeEntry(reader);
                case 15 -> entries[i] = new MethodHandleEntry(reader);
                case 16 -> entries[i] = new MethodTypeEntry(reader);
                case 17 -> entries[i] = new DynamicEntry(reader);
                case 18 -> entries[i] = new InvokeDynamicEntry(reader);
                case 19 -> entries[i] = new ModuleEntry(reader);
                case 20 -> entries[i] = new PackageEntry(reader);
    }
    if (tag == 5 || tag == 6) {
    i++;
}
        }
}

    public constantPoolEntry getEntry(int index) {
        return entries[index];
    }
}




