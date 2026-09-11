package decompiler.classFile.Attributes;

import java.io.IOException;

import decompiler.io.BinaryReader;

public class ExceptionTableEntry {
    private final int startPc;
    private final int endPc;
    private final int handlerPc;
    private final int catchType;


    public ExceptionTableEntry(BinaryReader reader) throws IOException {
        this.startPc = reader.readU2();
        this.endPc = reader.readU2();
        this.handlerPc = reader.readU2();
        this.catchType = reader.readU2();

    }


    public int getStartPc() {
        return startPc;
    }
    
    public int getEndPc() {
        return endPc;
    }

    public int getHandlerPc() {
        return handlerPc;
    }

    public int getCatchType() {
        return catchType;
    }
}
