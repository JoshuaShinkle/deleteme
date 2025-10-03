package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import com.google.common.primitives.UnsignedBytes;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

/* loaded from: classes2.dex */
class RopeByteString extends ByteString {
    private static final int[] minLengthByDepth;
    private int hash;
    private final ByteString left;
    private final int leftLength;
    private final ByteString right;
    private final int totalLength;
    private final int treeDepth;

    public static class Balancer {
        private final Stack<ByteString> prefixesStack;

        private Balancer() {
            this.prefixesStack = new Stack<>();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ByteString balance(ByteString byteString, ByteString byteString2) {
            doBalance(byteString);
            doBalance(byteString2);
            ByteString byteStringPop = this.prefixesStack.pop();
            while (!this.prefixesStack.isEmpty()) {
                byteStringPop = new RopeByteString(this.prefixesStack.pop(), byteStringPop);
            }
            return byteStringPop;
        }

        private void doBalance(ByteString byteString) {
            if (byteString.isBalanced()) {
                insert(byteString);
                return;
            }
            if (byteString instanceof RopeByteString) {
                RopeByteString ropeByteString = (RopeByteString) byteString;
                doBalance(ropeByteString.left);
                doBalance(ropeByteString.right);
            } else {
                String strValueOf = String.valueOf(byteString.getClass());
                StringBuilder sb = new StringBuilder(strValueOf.length() + 49);
                sb.append("Has a new type of ByteString been created? Found ");
                sb.append(strValueOf);
                throw new IllegalArgumentException(sb.toString());
            }
        }

        private int getDepthBinForLength(int i9) {
            int iBinarySearch = Arrays.binarySearch(RopeByteString.minLengthByDepth, i9);
            return iBinarySearch < 0 ? (-(iBinarySearch + 1)) - 1 : iBinarySearch;
        }

        private void insert(ByteString byteString) {
            int depthBinForLength = getDepthBinForLength(byteString.size());
            int i9 = RopeByteString.minLengthByDepth[depthBinForLength + 1];
            if (this.prefixesStack.isEmpty() || this.prefixesStack.peek().size() >= i9) {
                this.prefixesStack.push(byteString);
                return;
            }
            int i10 = RopeByteString.minLengthByDepth[depthBinForLength];
            ByteString byteStringPop = this.prefixesStack.pop();
            while (true) {
                if (this.prefixesStack.isEmpty() || this.prefixesStack.peek().size() >= i10) {
                    break;
                } else {
                    byteStringPop = new RopeByteString(this.prefixesStack.pop(), byteStringPop);
                }
            }
            RopeByteString ropeByteString = new RopeByteString(byteStringPop, byteString);
            while (!this.prefixesStack.isEmpty()) {
                if (this.prefixesStack.peek().size() >= RopeByteString.minLengthByDepth[getDepthBinForLength(ropeByteString.size()) + 1]) {
                    break;
                } else {
                    ropeByteString = new RopeByteString(this.prefixesStack.pop(), ropeByteString);
                }
            }
            this.prefixesStack.push(ropeByteString);
        }
    }

    public static class PieceIterator implements Iterator<LiteralByteString> {
        private final Stack<RopeByteString> breadCrumbs;
        private LiteralByteString next;

        private LiteralByteString getLeafByLeft(ByteString byteString) {
            while (byteString instanceof RopeByteString) {
                RopeByteString ropeByteString = (RopeByteString) byteString;
                this.breadCrumbs.push(ropeByteString);
                byteString = ropeByteString.left;
            }
            return (LiteralByteString) byteString;
        }

        private LiteralByteString getNextNonEmptyLeaf() {
            while (!this.breadCrumbs.isEmpty()) {
                LiteralByteString leafByLeft = getLeafByLeft(this.breadCrumbs.pop().right);
                if (!leafByLeft.isEmpty()) {
                    return leafByLeft;
                }
            }
            return null;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.next != null;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        private PieceIterator(ByteString byteString) {
            this.breadCrumbs = new Stack<>();
            this.next = getLeafByLeft(byteString);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public LiteralByteString next() {
            LiteralByteString literalByteString = this.next;
            if (literalByteString == null) {
                throw new NoSuchElementException();
            }
            this.next = getNextNonEmptyLeaf();
            return literalByteString;
        }
    }

    public class RopeByteIterator implements ByteString.ByteIterator {
        private ByteString.ByteIterator bytes;
        int bytesRemaining;
        private final PieceIterator pieces;

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.bytesRemaining > 0;
        }

        /* JADX WARN: Type inference failed for: r0v8, types: [com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString$ByteIterator] */
        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString.ByteIterator
        public byte nextByte() {
            if (!this.bytes.hasNext()) {
                this.bytes = this.pieces.next().iterator();
            }
            this.bytesRemaining--;
            return this.bytes.nextByte();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString$ByteIterator] */
        private RopeByteIterator() {
            PieceIterator pieceIterator = new PieceIterator(RopeByteString.this);
            this.pieces = pieceIterator;
            this.bytes = pieceIterator.next().iterator();
            this.bytesRemaining = RopeByteString.this.size();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Byte next() {
            return Byte.valueOf(nextByte());
        }
    }

    static {
        ArrayList arrayList = new ArrayList();
        int i9 = 1;
        int i10 = 1;
        while (i9 > 0) {
            arrayList.add(Integer.valueOf(i9));
            int i11 = i10 + i9;
            i10 = i9;
            i9 = i11;
        }
        arrayList.add(Integer.MAX_VALUE);
        minLengthByDepth = new int[arrayList.size()];
        int i12 = 0;
        while (true) {
            int[] iArr = minLengthByDepth;
            if (i12 >= iArr.length) {
                return;
            }
            iArr[i12] = ((Integer) arrayList.get(i12)).intValue();
            i12++;
        }
    }

    public static ByteString concatenate(ByteString byteString, ByteString byteString2) {
        RopeByteString ropeByteString = byteString instanceof RopeByteString ? (RopeByteString) byteString : null;
        if (byteString2.size() == 0) {
            return byteString;
        }
        if (byteString.size() != 0) {
            int size = byteString.size() + byteString2.size();
            if (size < 128) {
                return concatenateBytes(byteString, byteString2);
            }
            if (ropeByteString != null && ropeByteString.right.size() + byteString2.size() < 128) {
                byteString2 = new RopeByteString(ropeByteString.left, concatenateBytes(ropeByteString.right, byteString2));
            } else {
                if (ropeByteString == null || ropeByteString.left.getTreeDepth() <= ropeByteString.right.getTreeDepth() || ropeByteString.getTreeDepth() <= byteString2.getTreeDepth()) {
                    return size >= minLengthByDepth[Math.max(byteString.getTreeDepth(), byteString2.getTreeDepth()) + 1] ? new RopeByteString(byteString, byteString2) : new Balancer().balance(byteString, byteString2);
                }
                byteString2 = new RopeByteString(ropeByteString.left, new RopeByteString(ropeByteString.right, byteString2));
            }
        }
        return byteString2;
    }

    private static LiteralByteString concatenateBytes(ByteString byteString, ByteString byteString2) {
        int size = byteString.size();
        int size2 = byteString2.size();
        byte[] bArr = new byte[size + size2];
        byteString.copyTo(bArr, 0, 0, size);
        byteString2.copyTo(bArr, 0, size, size2);
        return new LiteralByteString(bArr);
    }

    private boolean equalsFragments(ByteString byteString) {
        PieceIterator pieceIterator = new PieceIterator(this);
        LiteralByteString next = pieceIterator.next();
        PieceIterator pieceIterator2 = new PieceIterator(byteString);
        LiteralByteString next2 = pieceIterator2.next();
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        while (true) {
            int size = next.size() - i9;
            int size2 = next2.size() - i10;
            int iMin = Math.min(size, size2);
            if (!(i9 == 0 ? next.equalsRange(next2, i10, iMin) : next2.equalsRange(next, i9, iMin))) {
                return false;
            }
            i11 += iMin;
            int i12 = this.totalLength;
            if (i11 >= i12) {
                if (i11 == i12) {
                    return true;
                }
                throw new IllegalStateException();
            }
            if (iMin == size) {
                next = pieceIterator.next();
                i9 = 0;
            } else {
                i9 += iMin;
            }
            if (iMin == size2) {
                next2 = pieceIterator2.next();
                i10 = 0;
            } else {
                i10 += iMin;
            }
        }
    }

    public static RopeByteString newInstanceForTest(ByteString byteString, ByteString byteString2) {
        return new RopeByteString(byteString, byteString2);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public ByteBuffer asReadOnlyByteBuffer() {
        return ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public List<ByteBuffer> asReadOnlyByteBufferList() {
        ArrayList arrayList = new ArrayList();
        PieceIterator pieceIterator = new PieceIterator(this);
        while (pieceIterator.hasNext()) {
            arrayList.add(pieceIterator.next().asReadOnlyByteBuffer());
        }
        return arrayList;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public byte byteAt(int i9) {
        if (i9 < 0) {
            StringBuilder sb = new StringBuilder(22);
            sb.append("Index < 0: ");
            sb.append(i9);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        if (i9 <= this.totalLength) {
            int i10 = this.leftLength;
            return i9 < i10 ? this.left.byteAt(i9) : this.right.byteAt(i9 - i10);
        }
        int i11 = this.totalLength;
        StringBuilder sb2 = new StringBuilder(40);
        sb2.append("Index > length: ");
        sb2.append(i9);
        sb2.append(", ");
        sb2.append(i11);
        throw new ArrayIndexOutOfBoundsException(sb2.toString());
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public void copyTo(ByteBuffer byteBuffer) {
        this.left.copyTo(byteBuffer);
        this.right.copyTo(byteBuffer);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public void copyToInternal(byte[] bArr, int i9, int i10, int i11) {
        int i12 = i9 + i11;
        int i13 = this.leftLength;
        if (i12 <= i13) {
            this.left.copyToInternal(bArr, i9, i10, i11);
        } else {
            if (i9 >= i13) {
                this.right.copyToInternal(bArr, i9 - i13, i10, i11);
                return;
            }
            int i14 = i13 - i9;
            this.left.copyToInternal(bArr, i9, i10, i14);
            this.right.copyToInternal(bArr, 0, i10 + i14, i11 - i14);
        }
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public boolean equals(Object obj) {
        int iPeekCachedHashCode;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ByteString)) {
            return false;
        }
        ByteString byteString = (ByteString) obj;
        if (this.totalLength != byteString.size()) {
            return false;
        }
        if (this.totalLength == 0) {
            return true;
        }
        if (this.hash == 0 || (iPeekCachedHashCode = byteString.peekCachedHashCode()) == 0 || this.hash == iPeekCachedHashCode) {
            return equalsFragments(byteString);
        }
        return false;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public int getTreeDepth() {
        return this.treeDepth;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public int hashCode() {
        int iPartialHash = this.hash;
        if (iPartialHash == 0) {
            int i9 = this.totalLength;
            iPartialHash = partialHash(i9, 0, i9);
            if (iPartialHash == 0) {
                iPartialHash = 1;
            }
            this.hash = iPartialHash;
        }
        return iPartialHash;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public boolean isBalanced() {
        return this.totalLength >= minLengthByDepth[this.treeDepth];
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public boolean isValidUtf8() {
        int iPartialIsValidUtf8 = this.left.partialIsValidUtf8(0, 0, this.leftLength);
        ByteString byteString = this.right;
        return byteString.partialIsValidUtf8(iPartialIsValidUtf8, 0, byteString.size()) == 0;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public CodedInputStream newCodedInput() {
        return CodedInputStream.newInstance(new RopeInputStream());
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public InputStream newInput() {
        return new RopeInputStream();
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public int partialHash(int i9, int i10, int i11) {
        int i12 = i10 + i11;
        int i13 = this.leftLength;
        if (i12 <= i13) {
            return this.left.partialHash(i9, i10, i11);
        }
        if (i10 >= i13) {
            return this.right.partialHash(i9, i10 - i13, i11);
        }
        int i14 = i13 - i10;
        return this.right.partialHash(this.left.partialHash(i9, i10, i14), 0, i11 - i14);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public int partialIsValidUtf8(int i9, int i10, int i11) {
        int i12 = i10 + i11;
        int i13 = this.leftLength;
        if (i12 <= i13) {
            return this.left.partialIsValidUtf8(i9, i10, i11);
        }
        if (i10 >= i13) {
            return this.right.partialIsValidUtf8(i9, i10 - i13, i11);
        }
        int i14 = i13 - i10;
        return this.right.partialIsValidUtf8(this.left.partialIsValidUtf8(i9, i10, i14), 0, i11 - i14);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public int peekCachedHashCode() {
        return this.hash;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public int size() {
        return this.totalLength;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public ByteString substring(int i9, int i10) {
        if (i9 < 0) {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Beginning index: ");
            sb.append(i9);
            sb.append(" < 0");
            throw new IndexOutOfBoundsException(sb.toString());
        }
        int i11 = this.totalLength;
        if (i10 > i11) {
            int i12 = this.totalLength;
            StringBuilder sb2 = new StringBuilder(36);
            sb2.append("End index: ");
            sb2.append(i10);
            sb2.append(" > ");
            sb2.append(i12);
            throw new IndexOutOfBoundsException(sb2.toString());
        }
        int i13 = i10 - i9;
        if (i13 >= 0) {
            if (i13 == 0) {
                return ByteString.EMPTY;
            }
            if (i13 == i11) {
                return this;
            }
            int i14 = this.leftLength;
            return i10 <= i14 ? this.left.substring(i9, i10) : i9 >= i14 ? this.right.substring(i9 - i14, i10 - i14) : new RopeByteString(this.left.substring(i9), this.right.substring(0, i10 - this.leftLength));
        }
        StringBuilder sb3 = new StringBuilder(66);
        sb3.append("Beginning index larger than ending index: ");
        sb3.append(i9);
        sb3.append(", ");
        sb3.append(i10);
        throw new IndexOutOfBoundsException(sb3.toString());
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public String toString(String str) {
        return new String(toByteArray(), str);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public void writeTo(OutputStream outputStream) {
        this.left.writeTo(outputStream);
        this.right.writeTo(outputStream);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString
    public void writeToInternal(OutputStream outputStream, int i9, int i10) {
        int i11 = i9 + i10;
        int i12 = this.leftLength;
        if (i11 <= i12) {
            this.left.writeToInternal(outputStream, i9, i10);
        } else {
            if (i9 >= i12) {
                this.right.writeToInternal(outputStream, i9 - i12, i10);
                return;
            }
            int i13 = i12 - i9;
            this.left.writeToInternal(outputStream, i9, i13);
            this.right.writeToInternal(outputStream, 0, i10 - i13);
        }
    }

    private RopeByteString(ByteString byteString, ByteString byteString2) {
        this.hash = 0;
        this.left = byteString;
        this.right = byteString2;
        int size = byteString.size();
        this.leftLength = size;
        this.totalLength = size + byteString2.size();
        this.treeDepth = Math.max(byteString.getTreeDepth(), byteString2.getTreeDepth()) + 1;
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString, java.lang.Iterable
    public Iterator<Byte> iterator() {
        return new RopeByteIterator();
    }

    public class RopeInputStream extends InputStream {
        private LiteralByteString currentPiece;
        private int currentPieceIndex;
        private int currentPieceOffsetInRope;
        private int currentPieceSize;
        private int mark;
        private PieceIterator pieceIterator;

        public RopeInputStream() {
            initialize();
        }

        private void advanceIfCurrentPieceFullyRead() {
            if (this.currentPiece != null) {
                int i9 = this.currentPieceIndex;
                int i10 = this.currentPieceSize;
                if (i9 == i10) {
                    this.currentPieceOffsetInRope += i10;
                    this.currentPieceIndex = 0;
                    if (!this.pieceIterator.hasNext()) {
                        this.currentPiece = null;
                        this.currentPieceSize = 0;
                    } else {
                        LiteralByteString next = this.pieceIterator.next();
                        this.currentPiece = next;
                        this.currentPieceSize = next.size();
                    }
                }
            }
        }

        private void initialize() {
            PieceIterator pieceIterator = new PieceIterator(RopeByteString.this);
            this.pieceIterator = pieceIterator;
            LiteralByteString next = pieceIterator.next();
            this.currentPiece = next;
            this.currentPieceSize = next.size();
            this.currentPieceIndex = 0;
            this.currentPieceOffsetInRope = 0;
        }

        private int readSkipInternal(byte[] bArr, int i9, int i10) {
            int i11 = i10;
            while (true) {
                if (i11 <= 0) {
                    break;
                }
                advanceIfCurrentPieceFullyRead();
                if (this.currentPiece != null) {
                    int iMin = Math.min(this.currentPieceSize - this.currentPieceIndex, i11);
                    if (bArr != null) {
                        this.currentPiece.copyTo(bArr, this.currentPieceIndex, i9, iMin);
                        i9 += iMin;
                    }
                    this.currentPieceIndex += iMin;
                    i11 -= iMin;
                } else if (i11 == i10) {
                    return -1;
                }
            }
            return i10 - i11;
        }

        @Override // java.io.InputStream
        public int available() {
            return RopeByteString.this.size() - (this.currentPieceOffsetInRope + this.currentPieceIndex);
        }

        @Override // java.io.InputStream
        public void mark(int i9) {
            this.mark = this.currentPieceOffsetInRope + this.currentPieceIndex;
        }

        @Override // java.io.InputStream
        public boolean markSupported() {
            return true;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i9, int i10) {
            bArr.getClass();
            if (i9 < 0 || i10 < 0 || i10 > bArr.length - i9) {
                throw new IndexOutOfBoundsException();
            }
            return readSkipInternal(bArr, i9, i10);
        }

        @Override // java.io.InputStream
        public synchronized void reset() {
            initialize();
            readSkipInternal(null, 0, this.mark);
        }

        @Override // java.io.InputStream
        public long skip(long j9) {
            if (j9 < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (j9 > 2147483647L) {
                j9 = 2147483647L;
            }
            return readSkipInternal(null, 0, (int) j9);
        }

        @Override // java.io.InputStream
        public int read() {
            advanceIfCurrentPieceFullyRead();
            LiteralByteString literalByteString = this.currentPiece;
            if (literalByteString == null) {
                return -1;
            }
            int i9 = this.currentPieceIndex;
            this.currentPieceIndex = i9 + 1;
            return literalByteString.byteAt(i9) & UnsignedBytes.MAX_VALUE;
        }
    }
}
