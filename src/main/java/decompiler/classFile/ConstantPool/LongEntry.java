package decompiler.classFile.ConstantPool;
import decompiler.io.BinaryReader;
import java.io.IOException;

    public class LongEntry extends constantPoolEntry {
        private final long lowBytes;
        private final long highBytes;


        public LongEntry(BinaryReader reader) throws IOException {
            highBytes = reader.readU4();
            lowBytes = reader.readU4();

        }

        public long getLong() {
            long reconstructedLong = ((long) highBytes << 32) | (lowBytes & 0xFFFFFFFFL);
            return (reconstructedLong);
        }

    }