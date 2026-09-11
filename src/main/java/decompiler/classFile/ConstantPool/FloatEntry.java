package decompiler.classFile.ConstantPool;
import decompiler.io.BinaryReader;
import java.io.IOException;

    public class FloatEntry extends constantPoolEntry {
        private final long bits;


        public FloatEntry(BinaryReader reader) throws IOException {
            bits = reader.readU4();
        }

        public float getFloat() {
            return (Float.intBitsToFloat((int) bits));
        }
    }
