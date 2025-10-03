package com.google.android.exoplayer2.extractor.mkv;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.Assertions;
import com.google.common.primitives.UnsignedBytes;
import java.util.Stack;

/* loaded from: classes.dex */
final class DefaultEbmlReader implements EbmlReader {
    private static final int ELEMENT_STATE_READ_CONTENT = 2;
    private static final int ELEMENT_STATE_READ_CONTENT_SIZE = 1;
    private static final int ELEMENT_STATE_READ_ID = 0;
    private static final int MAX_ID_BYTES = 4;
    private static final int MAX_INTEGER_ELEMENT_SIZE_BYTES = 8;
    private static final int MAX_LENGTH_BYTES = 8;
    private static final int VALID_FLOAT32_ELEMENT_SIZE_BYTES = 4;
    private static final int VALID_FLOAT64_ELEMENT_SIZE_BYTES = 8;
    private long elementContentSize;
    private int elementId;
    private int elementState;
    private EbmlReaderOutput output;
    private final byte[] scratch = new byte[8];
    private final Stack<MasterElement> masterElementsStack = new Stack<>();
    private final VarintReader varintReader = new VarintReader();

    public static final class MasterElement {
        private final long elementEndPosition;
        private final int elementId;

        private MasterElement(int i9, long j9) {
            this.elementId = i9;
            this.elementEndPosition = j9;
        }
    }

    private long maybeResyncToNextLevel1Element(ExtractorInput extractorInput) {
        extractorInput.resetPeekPosition();
        while (true) {
            extractorInput.peekFully(this.scratch, 0, 4);
            int unsignedVarintLength = VarintReader.parseUnsignedVarintLength(this.scratch[0]);
            if (unsignedVarintLength != -1 && unsignedVarintLength <= 4) {
                int iAssembleVarint = (int) VarintReader.assembleVarint(this.scratch, unsignedVarintLength, false);
                if (this.output.isLevel1Element(iAssembleVarint)) {
                    extractorInput.skipFully(unsignedVarintLength);
                    return iAssembleVarint;
                }
            }
            extractorInput.skipFully(1);
        }
    }

    private double readFloat(ExtractorInput extractorInput, int i9) {
        return i9 == 4 ? Float.intBitsToFloat((int) r0) : Double.longBitsToDouble(readInteger(extractorInput, i9));
    }

    private long readInteger(ExtractorInput extractorInput, int i9) {
        extractorInput.readFully(this.scratch, 0, i9);
        long j9 = 0;
        for (int i10 = 0; i10 < i9; i10++) {
            j9 = (j9 << 8) | (this.scratch[i10] & UnsignedBytes.MAX_VALUE);
        }
        return j9;
    }

    private String readString(ExtractorInput extractorInput, int i9) {
        if (i9 == 0) {
            return "";
        }
        byte[] bArr = new byte[i9];
        extractorInput.readFully(bArr, 0, i9);
        while (i9 > 0 && bArr[i9 - 1] == 0) {
            i9--;
        }
        return new String(bArr, 0, i9);
    }

    @Override // com.google.android.exoplayer2.extractor.mkv.EbmlReader
    public void init(EbmlReaderOutput ebmlReaderOutput) {
        this.output = ebmlReaderOutput;
    }

    @Override // com.google.android.exoplayer2.extractor.mkv.EbmlReader
    public boolean read(ExtractorInput extractorInput) throws ParserException {
        Assertions.checkState(this.output != null);
        while (true) {
            if (!this.masterElementsStack.isEmpty() && extractorInput.getPosition() >= this.masterElementsStack.peek().elementEndPosition) {
                this.output.endMasterElement(this.masterElementsStack.pop().elementId);
                return true;
            }
            if (this.elementState == 0) {
                long unsignedVarint = this.varintReader.readUnsignedVarint(extractorInput, true, false, 4);
                if (unsignedVarint == -2) {
                    unsignedVarint = maybeResyncToNextLevel1Element(extractorInput);
                }
                if (unsignedVarint == -1) {
                    return false;
                }
                this.elementId = (int) unsignedVarint;
                this.elementState = 1;
            }
            if (this.elementState == 1) {
                this.elementContentSize = this.varintReader.readUnsignedVarint(extractorInput, false, true, 8);
                this.elementState = 2;
            }
            int elementType = this.output.getElementType(this.elementId);
            if (elementType != 0) {
                if (elementType == 1) {
                    long position = extractorInput.getPosition();
                    this.masterElementsStack.add(new MasterElement(this.elementId, this.elementContentSize + position));
                    this.output.startMasterElement(this.elementId, position, this.elementContentSize);
                    this.elementState = 0;
                    return true;
                }
                if (elementType == 2) {
                    long j9 = this.elementContentSize;
                    if (j9 <= 8) {
                        this.output.integerElement(this.elementId, readInteger(extractorInput, (int) j9));
                        this.elementState = 0;
                        return true;
                    }
                    throw new ParserException("Invalid integer size: " + this.elementContentSize);
                }
                if (elementType == 3) {
                    long j10 = this.elementContentSize;
                    if (j10 <= 2147483647L) {
                        this.output.stringElement(this.elementId, readString(extractorInput, (int) j10));
                        this.elementState = 0;
                        return true;
                    }
                    throw new ParserException("String element size: " + this.elementContentSize);
                }
                if (elementType == 4) {
                    this.output.binaryElement(this.elementId, (int) this.elementContentSize, extractorInput);
                    this.elementState = 0;
                    return true;
                }
                if (elementType != 5) {
                    throw new ParserException("Invalid element type " + elementType);
                }
                long j11 = this.elementContentSize;
                if (j11 == 4 || j11 == 8) {
                    this.output.floatElement(this.elementId, readFloat(extractorInput, (int) j11));
                    this.elementState = 0;
                    return true;
                }
                throw new ParserException("Invalid float size: " + this.elementContentSize);
            }
            extractorInput.skipFully((int) this.elementContentSize);
            this.elementState = 0;
        }
    }

    @Override // com.google.android.exoplayer2.extractor.mkv.EbmlReader
    public void reset() {
        this.elementState = 0;
        this.masterElementsStack.clear();
        this.varintReader.reset();
    }
}
