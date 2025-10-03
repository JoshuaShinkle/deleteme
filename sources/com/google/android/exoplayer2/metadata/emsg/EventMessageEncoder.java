package com.google.android.exoplayer2.metadata.emsg;

import com.google.android.exoplayer2.C3322C;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public final class EventMessageEncoder {
    private final ByteArrayOutputStream byteArrayOutputStream;
    private final DataOutputStream dataOutputStream;

    public EventMessageEncoder() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
        this.byteArrayOutputStream = byteArrayOutputStream;
        this.dataOutputStream = new DataOutputStream(byteArrayOutputStream);
    }

    private static void writeNullTerminatedString(DataOutputStream dataOutputStream, String str) throws IOException {
        dataOutputStream.writeBytes(str);
        dataOutputStream.writeByte(0);
    }

    private static void writeUnsignedInt(DataOutputStream dataOutputStream, long j9) throws IOException {
        dataOutputStream.writeByte(((int) (j9 >>> 24)) & 255);
        dataOutputStream.writeByte(((int) (j9 >>> 16)) & 255);
        dataOutputStream.writeByte(((int) (j9 >>> 8)) & 255);
        dataOutputStream.writeByte(((int) j9) & 255);
    }

    public byte[] encode(EventMessage eventMessage, long j9) throws IOException {
        Assertions.checkArgument(j9 >= 0);
        this.byteArrayOutputStream.reset();
        try {
            writeNullTerminatedString(this.dataOutputStream, eventMessage.schemeIdUri);
            String str = eventMessage.value;
            if (str == null) {
                str = "";
            }
            writeNullTerminatedString(this.dataOutputStream, str);
            writeUnsignedInt(this.dataOutputStream, j9);
            writeUnsignedInt(this.dataOutputStream, Util.scaleLargeTimestamp(eventMessage.presentationTimeUs, j9, C3322C.MICROS_PER_SECOND));
            writeUnsignedInt(this.dataOutputStream, Util.scaleLargeTimestamp(eventMessage.durationMs, j9, 1000L));
            writeUnsignedInt(this.dataOutputStream, eventMessage.f15306id);
            this.dataOutputStream.write(eventMessage.messageData);
            this.dataOutputStream.flush();
            return this.byteArrayOutputStream.toByteArray();
        } catch (IOException e9) {
            throw new RuntimeException(e9);
        }
    }
}
