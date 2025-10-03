package org.apache.commons.p159io;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.lang3.ClassUtils;

/* loaded from: classes.dex */
public class HexDump {
    public static final String EOL = System.getProperty("line.separator");
    private static final StringBuffer _lbuffer = new StringBuffer(8);
    private static final StringBuffer _cbuffer = new StringBuffer(2);
    private static final char[] _hexcodes = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final int[] _shifts = {28, 24, 20, 16, 12, 8, 4, 0};

    public static void dump(byte[] bArr, long j9, OutputStream outputStream, int i9) throws IOException {
        if (i9 < 0 || i9 >= bArr.length) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("illegal index: ");
            stringBuffer.append(i9);
            stringBuffer.append(" into array of length ");
            stringBuffer.append(bArr.length);
            throw new ArrayIndexOutOfBoundsException(stringBuffer.toString());
        }
        if (outputStream == null) {
            throw new IllegalArgumentException("cannot write to nullstream");
        }
        long j10 = j9 + i9;
        StringBuffer stringBuffer2 = new StringBuffer(74);
        while (i9 < bArr.length) {
            int length = bArr.length - i9;
            if (length > 16) {
                length = 16;
            }
            stringBuffer2.append(dump(j10));
            stringBuffer2.append(' ');
            for (int i10 = 0; i10 < 16; i10++) {
                if (i10 < length) {
                    stringBuffer2.append(dump(bArr[i10 + i9]));
                } else {
                    stringBuffer2.append("  ");
                }
                stringBuffer2.append(' ');
            }
            for (int i11 = 0; i11 < length; i11++) {
                byte b9 = bArr[i11 + i9];
                if (b9 < 32 || b9 >= 127) {
                    stringBuffer2.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
                } else {
                    stringBuffer2.append((char) b9);
                }
            }
            stringBuffer2.append(EOL);
            outputStream.write(stringBuffer2.toString().getBytes());
            outputStream.flush();
            stringBuffer2.setLength(0);
            j10 += length;
            i9 += 16;
        }
    }

    private static StringBuffer dump(long j9) {
        _lbuffer.setLength(0);
        for (int i9 = 0; i9 < 8; i9++) {
            _lbuffer.append(_hexcodes[((int) (j9 >> _shifts[i9])) & 15]);
        }
        return _lbuffer;
    }

    private static StringBuffer dump(byte b9) {
        _cbuffer.setLength(0);
        for (int i9 = 0; i9 < 2; i9++) {
            _cbuffer.append(_hexcodes[(b9 >> _shifts[i9 + 6]) & 15]);
        }
        return _cbuffer;
    }
}
