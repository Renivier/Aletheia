package decompiler.classFile.ConstantPool;
import decompiler.io.BinaryReader;
import java.io.IOException;


public class Utf8Entry extends constantPoolEntry {
        private final int length;
        private final byte[] bytes;

        public Utf8Entry(BinaryReader reader) throws IOException{
            this.length = reader.readU2();
            this.bytes = new byte[length];
            for (int i = 0; i < this.length; i++) {
                bytes[i] = (byte)(reader.readU1());

            }
        }
        public int getUtf8Length() {
            return length;
        }

        public byte[] getUtf8Bytes() {
            return bytes;
        }

    }
