package com.google.common.p039io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.hash.Funnels;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;

@GwtIncompatible
/* loaded from: classes2.dex */
public abstract class ByteSource {

    public class AsCharSource extends CharSource {
        final Charset charset;

        public AsCharSource(Charset charset) {
            this.charset = (Charset) Preconditions.checkNotNull(charset);
        }

        @Override // com.google.common.p039io.CharSource
        public ByteSource asByteSource(Charset charset) {
            return charset.equals(this.charset) ? ByteSource.this : super.asByteSource(charset);
        }

        @Override // com.google.common.p039io.CharSource
        public Reader openStream() {
            return new InputStreamReader(ByteSource.this.openStream(), this.charset);
        }

        @Override // com.google.common.p039io.CharSource
        public String read() {
            return new String(ByteSource.this.read(), this.charset);
        }

        public String toString() {
            return ByteSource.this.toString() + ".asCharSource(" + this.charset + ")";
        }
    }

    public static class ByteArrayByteSource extends ByteSource {
        final byte[] bytes;
        final int length;
        final int offset;

        public ByteArrayByteSource(byte[] bArr) {
            this(bArr, 0, bArr.length);
        }

        @Override // com.google.common.p039io.ByteSource
        public long copyTo(OutputStream outputStream) throws IOException {
            outputStream.write(this.bytes, this.offset, this.length);
            return this.length;
        }

        @Override // com.google.common.p039io.ByteSource
        public HashCode hash(HashFunction hashFunction) {
            return hashFunction.hashBytes(this.bytes, this.offset, this.length);
        }

        @Override // com.google.common.p039io.ByteSource
        public boolean isEmpty() {
            return this.length == 0;
        }

        @Override // com.google.common.p039io.ByteSource
        public InputStream openBufferedStream() {
            return openStream();
        }

        @Override // com.google.common.p039io.ByteSource
        public InputStream openStream() {
            return new ByteArrayInputStream(this.bytes, this.offset, this.length);
        }

        @Override // com.google.common.p039io.ByteSource
        public byte[] read() {
            byte[] bArr = this.bytes;
            int i9 = this.offset;
            return Arrays.copyOfRange(bArr, i9, this.length + i9);
        }

        @Override // com.google.common.p039io.ByteSource
        public long size() {
            return this.length;
        }

        @Override // com.google.common.p039io.ByteSource
        public Optional<Long> sizeIfKnown() {
            return Optional.m17551of(Long.valueOf(this.length));
        }

        @Override // com.google.common.p039io.ByteSource
        public ByteSource slice(long j9, long j10) {
            Preconditions.checkArgument(j9 >= 0, "offset (%s) may not be negative", j9);
            Preconditions.checkArgument(j10 >= 0, "length (%s) may not be negative", j10);
            long jMin = Math.min(j9, this.length);
            return new ByteArrayByteSource(this.bytes, this.offset + ((int) jMin), (int) Math.min(j10, this.length - jMin));
        }

        public String toString() {
            return "ByteSource.wrap(" + Ascii.truncate(BaseEncoding.base16().encode(this.bytes, this.offset, this.length), 30, "...") + ")";
        }

        public ByteArrayByteSource(byte[] bArr, int i9, int i10) {
            this.bytes = bArr;
            this.offset = i9;
            this.length = i10;
        }

        @Override // com.google.common.p039io.ByteSource
        public <T> T read(ByteProcessor<T> byteProcessor) {
            byteProcessor.processBytes(this.bytes, this.offset, this.length);
            return byteProcessor.getResult();
        }
    }

    public static final class ConcatenatedByteSource extends ByteSource {
        final Iterable<? extends ByteSource> sources;

        public ConcatenatedByteSource(Iterable<? extends ByteSource> iterable) {
            this.sources = (Iterable) Preconditions.checkNotNull(iterable);
        }

        @Override // com.google.common.p039io.ByteSource
        public boolean isEmpty() {
            Iterator<? extends ByteSource> it = this.sources.iterator();
            while (it.hasNext()) {
                if (!it.next().isEmpty()) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.common.p039io.ByteSource
        public InputStream openStream() {
            return new MultiInputStream(this.sources.iterator());
        }

        @Override // com.google.common.p039io.ByteSource
        public long size() {
            Iterator<? extends ByteSource> it = this.sources.iterator();
            long size = 0;
            while (it.hasNext()) {
                size += it.next().size();
            }
            return size;
        }

        @Override // com.google.common.p039io.ByteSource
        public Optional<Long> sizeIfKnown() {
            Iterator<? extends ByteSource> it = this.sources.iterator();
            long jLongValue = 0;
            while (it.hasNext()) {
                Optional<Long> optionalSizeIfKnown = it.next().sizeIfKnown();
                if (!optionalSizeIfKnown.isPresent()) {
                    return Optional.absent();
                }
                jLongValue += optionalSizeIfKnown.get().longValue();
            }
            return Optional.m17551of(Long.valueOf(jLongValue));
        }

        public String toString() {
            return "ByteSource.concat(" + this.sources + ")";
        }
    }

    public static final class EmptyByteSource extends ByteArrayByteSource {
        static final EmptyByteSource INSTANCE = new EmptyByteSource();

        public EmptyByteSource() {
            super(new byte[0]);
        }

        @Override // com.google.common.p039io.ByteSource
        public CharSource asCharSource(Charset charset) {
            Preconditions.checkNotNull(charset);
            return CharSource.empty();
        }

        @Override // com.google.common.io.ByteSource.ByteArrayByteSource, com.google.common.p039io.ByteSource
        public byte[] read() {
            return this.bytes;
        }

        @Override // com.google.common.io.ByteSource.ByteArrayByteSource
        public String toString() {
            return "ByteSource.empty()";
        }
    }

    public final class SlicedByteSource extends ByteSource {
        final long length;
        final long offset;

        public SlicedByteSource(long j9, long j10) {
            Preconditions.checkArgument(j9 >= 0, "offset (%s) may not be negative", j9);
            Preconditions.checkArgument(j10 >= 0, "length (%s) may not be negative", j10);
            this.offset = j9;
            this.length = j10;
        }

        private InputStream sliceStream(InputStream inputStream) throws X, IOException {
            long j9 = this.offset;
            if (j9 > 0) {
                try {
                    if (ByteStreams.skipUpTo(inputStream, j9) < this.offset) {
                        inputStream.close();
                        return new ByteArrayInputStream(new byte[0]);
                    }
                } finally {
                }
            }
            return ByteStreams.limit(inputStream, this.length);
        }

        @Override // com.google.common.p039io.ByteSource
        public boolean isEmpty() {
            return this.length == 0 || super.isEmpty();
        }

        @Override // com.google.common.p039io.ByteSource
        public InputStream openBufferedStream() {
            return sliceStream(ByteSource.this.openBufferedStream());
        }

        @Override // com.google.common.p039io.ByteSource
        public InputStream openStream() {
            return sliceStream(ByteSource.this.openStream());
        }

        @Override // com.google.common.p039io.ByteSource
        public Optional<Long> sizeIfKnown() {
            Optional<Long> optionalSizeIfKnown = ByteSource.this.sizeIfKnown();
            if (!optionalSizeIfKnown.isPresent()) {
                return Optional.absent();
            }
            long jLongValue = optionalSizeIfKnown.get().longValue();
            return Optional.m17551of(Long.valueOf(Math.min(this.length, jLongValue - Math.min(this.offset, jLongValue))));
        }

        @Override // com.google.common.p039io.ByteSource
        public ByteSource slice(long j9, long j10) {
            Preconditions.checkArgument(j9 >= 0, "offset (%s) may not be negative", j9);
            Preconditions.checkArgument(j10 >= 0, "length (%s) may not be negative", j10);
            return ByteSource.this.slice(this.offset + j9, Math.min(j10, this.length - j9));
        }

        public String toString() {
            return ByteSource.this.toString() + ".slice(" + this.offset + ", " + this.length + ")";
        }
    }

    public static ByteSource concat(Iterable<? extends ByteSource> iterable) {
        return new ConcatenatedByteSource(iterable);
    }

    private long countBySkipping(InputStream inputStream) throws IOException {
        long j9 = 0;
        while (true) {
            long jSkipUpTo = ByteStreams.skipUpTo(inputStream, 2147483647L);
            if (jSkipUpTo <= 0) {
                return j9;
            }
            j9 += jSkipUpTo;
        }
    }

    public static ByteSource empty() {
        return EmptyByteSource.INSTANCE;
    }

    public static ByteSource wrap(byte[] bArr) {
        return new ByteArrayByteSource(bArr);
    }

    public CharSource asCharSource(Charset charset) {
        return new AsCharSource(charset);
    }

    public boolean contentEquals(ByteSource byteSource) throws X {
        int i9;
        Preconditions.checkNotNull(byteSource);
        byte[] bArrCreateBuffer = ByteStreams.createBuffer();
        byte[] bArrCreateBuffer2 = ByteStreams.createBuffer();
        Closer closerCreate = Closer.create();
        try {
            InputStream inputStream = (InputStream) closerCreate.register(openStream());
            InputStream inputStream2 = (InputStream) closerCreate.register(byteSource.openStream());
            do {
                i9 = ByteStreams.read(inputStream, bArrCreateBuffer, 0, bArrCreateBuffer.length);
                if (i9 == ByteStreams.read(inputStream2, bArrCreateBuffer2, 0, bArrCreateBuffer2.length) && Arrays.equals(bArrCreateBuffer, bArrCreateBuffer2)) {
                }
                return false;
            } while (i9 == bArrCreateBuffer.length);
            closerCreate.close();
            return true;
        } finally {
        }
    }

    @CanIgnoreReturnValue
    public long copyTo(OutputStream outputStream) throws X {
        Preconditions.checkNotNull(outputStream);
        try {
            return ByteStreams.copy((InputStream) Closer.create().register(openStream()), outputStream);
        } finally {
        }
    }

    public HashCode hash(HashFunction hashFunction) throws X {
        Hasher hasherNewHasher = hashFunction.newHasher();
        copyTo(Funnels.asOutputStream(hasherNewHasher));
        return hasherNewHasher.hash();
    }

    public boolean isEmpty() throws X {
        Optional<Long> optionalSizeIfKnown = sizeIfKnown();
        if (optionalSizeIfKnown.isPresent()) {
            return optionalSizeIfKnown.get().longValue() == 0;
        }
        Closer closerCreate = Closer.create();
        try {
            return ((InputStream) closerCreate.register(openStream())).read() == -1;
        } catch (Throwable th) {
            try {
                throw closerCreate.rethrow(th);
            } finally {
                closerCreate.close();
            }
        }
    }

    public InputStream openBufferedStream() {
        InputStream inputStreamOpenStream = openStream();
        return inputStreamOpenStream instanceof BufferedInputStream ? (BufferedInputStream) inputStreamOpenStream : new BufferedInputStream(inputStreamOpenStream);
    }

    public abstract InputStream openStream();

    public byte[] read() throws X {
        try {
            return ByteStreams.toByteArray((InputStream) Closer.create().register(openStream()));
        } finally {
        }
    }

    public long size() throws X {
        Optional<Long> optionalSizeIfKnown = sizeIfKnown();
        if (optionalSizeIfKnown.isPresent()) {
            return optionalSizeIfKnown.get().longValue();
        }
        Closer closerCreate = Closer.create();
        try {
            return countBySkipping((InputStream) closerCreate.register(openStream()));
        } catch (IOException unused) {
            closerCreate.close();
            try {
                return ByteStreams.exhaust((InputStream) Closer.create().register(openStream()));
            } finally {
            }
        } finally {
        }
    }

    @Beta
    public Optional<Long> sizeIfKnown() {
        return Optional.absent();
    }

    public ByteSource slice(long j9, long j10) {
        return new SlicedByteSource(j9, j10);
    }

    public static ByteSource concat(Iterator<? extends ByteSource> it) {
        return concat(ImmutableList.copyOf(it));
    }

    public static ByteSource concat(ByteSource... byteSourceArr) {
        return concat(ImmutableList.copyOf(byteSourceArr));
    }

    @CanIgnoreReturnValue
    @Beta
    public <T> T read(ByteProcessor<T> byteProcessor) throws X {
        Preconditions.checkNotNull(byteProcessor);
        try {
            return (T) ByteStreams.readBytes((InputStream) Closer.create().register(openStream()), byteProcessor);
        } finally {
        }
    }

    @CanIgnoreReturnValue
    public long copyTo(ByteSink byteSink) throws X {
        Preconditions.checkNotNull(byteSink);
        Closer closerCreate = Closer.create();
        try {
            return ByteStreams.copy((InputStream) closerCreate.register(openStream()), (OutputStream) closerCreate.register(byteSink.openStream()));
        } finally {
        }
    }
}
