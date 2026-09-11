package decompiler.io;

import java.io.IOException;
import java.io.InputStream;

public class BinaryReader implements AutoCloseable {
    private final InputStream in;
    private long bytesRead;

    public BinaryReader(InputStream in) {
        this.in = in;
    }

    @Override
    public void close() throws IOException {
        in.close();
    }

    private int readByte() throws IOException {
        int val = in.read();
        if (val == -1) {
            throw new IOException("Unexpected end of input");
        }
        bytesRead++;
        return val;
    }

    public int readU1() throws IOException {
        return readByte();
    }

    public int readU2() throws IOException {
        int val1 = readByte();
        int val2 = readByte();
        return (val1 << 8) | val2;
    }

    public long readU4() throws IOException {
        return ((long) readByte() << 24)
                | ((long) readByte() << 16)
                | ((long) readByte() << 8)
                | readByte();
    }

    public long getBytesRead() {
        return bytesRead;
    }
}
