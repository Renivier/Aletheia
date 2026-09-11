package decompiler.classFile.ConstantPool;
import decompiler.io.BinaryReader;
import java.io.IOException;

    public class DoubleEntry extends constantPoolEntry {
        private final long highBytes;
        private final long lowBytes;

        public DoubleEntry(BinaryReader reader) throws IOException {
            highBytes = reader.readU4();
            lowBytes = reader.readU4();
        }

        public double getDouble() {
            long bits = (highBytes << 32) | (lowBytes & 0xFFFFFFFFL);
            return Double.longBitsToDouble(bits);
        }




    }
