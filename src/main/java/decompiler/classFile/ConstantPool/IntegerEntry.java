package decompiler.classFile.ConstantPool;
import decompiler.io.BinaryReader;
import java.io.IOException;





public class IntegerEntry extends constantPoolEntry {
        private final long val;
        
        public IntegerEntry(BinaryReader reader) throws IOException {
            val = reader.readU4();
        }

        public int getInteger() {
            return (int)(val);
        }

}