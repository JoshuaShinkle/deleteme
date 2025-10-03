package com.google.common.p039io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

@Beta
@GwtIncompatible
/* loaded from: classes2.dex */
public final class CharStreams {
    private static final int DEFAULT_BUF_SIZE = 2048;

    public static final class NullWriter extends Writer {
        private static final NullWriter INSTANCE = new NullWriter();

        private NullWriter() {
        }

        @Override // java.io.Writer, java.lang.Appendable
        public Writer append(char c9) {
            return this;
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }

        public String toString() {
            return "CharStreams.nullWriter()";
        }

        @Override // java.io.Writer
        public void write(int i9) {
        }

        @Override // java.io.Writer
        public void write(char[] cArr) {
            Preconditions.checkNotNull(cArr);
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i9, int i10) {
            Preconditions.checkPositionIndexes(i9, i10 + i9, cArr.length);
        }

        @Override // java.io.Writer
        public void write(String str) {
            Preconditions.checkNotNull(str);
        }

        @Override // java.io.Writer, java.lang.Appendable
        public Writer append(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            return this;
        }

        @Override // java.io.Writer
        public void write(String str, int i9, int i10) {
            Preconditions.checkPositionIndexes(i9, i10 + i9, str.length());
        }

        @Override // java.io.Writer, java.lang.Appendable
        public Writer append(CharSequence charSequence, int i9, int i10) {
            Preconditions.checkPositionIndexes(i9, i10, charSequence.length());
            return this;
        }
    }

    private CharStreams() {
    }

    public static Writer asWriter(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new AppendableWriter(appendable);
    }

    @CanIgnoreReturnValue
    public static long copy(Readable readable, Appendable appendable) throws IOException {
        if (readable instanceof Reader) {
            return appendable instanceof StringBuilder ? copyReaderToBuilder((Reader) readable, (StringBuilder) appendable) : copyReaderToWriter((Reader) readable, asWriter(appendable));
        }
        Preconditions.checkNotNull(readable);
        Preconditions.checkNotNull(appendable);
        CharBuffer charBufferCreateBuffer = createBuffer();
        long jRemaining = 0;
        while (readable.read(charBufferCreateBuffer) != -1) {
            charBufferCreateBuffer.flip();
            appendable.append(charBufferCreateBuffer);
            jRemaining += charBufferCreateBuffer.remaining();
            charBufferCreateBuffer.clear();
        }
        return jRemaining;
    }

    @CanIgnoreReturnValue
    public static long copyReaderToBuilder(Reader reader, StringBuilder sb) throws IOException {
        Preconditions.checkNotNull(reader);
        Preconditions.checkNotNull(sb);
        char[] cArr = new char[DEFAULT_BUF_SIZE];
        long j9 = 0;
        while (true) {
            int i9 = reader.read(cArr);
            if (i9 == -1) {
                return j9;
            }
            sb.append(cArr, 0, i9);
            j9 += i9;
        }
    }

    @CanIgnoreReturnValue
    public static long copyReaderToWriter(Reader reader, Writer writer) throws IOException {
        Preconditions.checkNotNull(reader);
        Preconditions.checkNotNull(writer);
        char[] cArr = new char[DEFAULT_BUF_SIZE];
        long j9 = 0;
        while (true) {
            int i9 = reader.read(cArr);
            if (i9 == -1) {
                return j9;
            }
            writer.write(cArr, 0, i9);
            j9 += i9;
        }
    }

    public static CharBuffer createBuffer() {
        return CharBuffer.allocate(DEFAULT_BUF_SIZE);
    }

    @CanIgnoreReturnValue
    public static long exhaust(Readable readable) {
        CharBuffer charBufferCreateBuffer = createBuffer();
        long j9 = 0;
        while (true) {
            long j10 = readable.read(charBufferCreateBuffer);
            if (j10 == -1) {
                return j9;
            }
            j9 += j10;
            charBufferCreateBuffer.clear();
        }
    }

    public static Writer nullWriter() {
        return NullWriter.INSTANCE;
    }

    public static List<String> readLines(Readable readable) throws IOException {
        ArrayList arrayList = new ArrayList();
        LineReader lineReader = new LineReader(readable);
        while (true) {
            String line = lineReader.readLine();
            if (line == null) {
                return arrayList;
            }
            arrayList.add(line);
        }
    }

    public static void skipFully(Reader reader, long j9) throws IOException {
        Preconditions.checkNotNull(reader);
        while (j9 > 0) {
            long jSkip = reader.skip(j9);
            if (jSkip == 0) {
                throw new EOFException();
            }
            j9 -= jSkip;
        }
    }

    public static String toString(Readable readable) {
        return toStringBuilder(readable).toString();
    }

    private static StringBuilder toStringBuilder(Readable readable) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (readable instanceof Reader) {
            copyReaderToBuilder((Reader) readable, sb);
        } else {
            copy(readable, sb);
        }
        return sb;
    }

    @CanIgnoreReturnValue
    public static <T> T readLines(Readable readable, LineProcessor<T> lineProcessor) throws IOException {
        String line;
        Preconditions.checkNotNull(readable);
        Preconditions.checkNotNull(lineProcessor);
        LineReader lineReader = new LineReader(readable);
        do {
            line = lineReader.readLine();
            if (line == null) {
                break;
            }
        } while (lineProcessor.processLine(line));
        return lineProcessor.getResult();
    }
}
