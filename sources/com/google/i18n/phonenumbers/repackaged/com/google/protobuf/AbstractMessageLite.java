package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.ByteString;
import com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes2.dex */
public abstract class AbstractMessageLite implements MessageLite {
    protected int memoizedHashCode = 0;

    public static void checkByteStringIsUtf8(ByteString byteString) {
        if (!byteString.isValidUtf8()) {
            throw new IllegalArgumentException("Byte string is not UTF-8.");
        }
    }

    public UninitializedMessageException newUninitializedMessageException() {
        return new UninitializedMessageException(this);
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite
    public byte[] toByteArray() {
        try {
            byte[] bArr = new byte[getSerializedSize()];
            CodedOutputStream codedOutputStreamNewInstance = CodedOutputStream.newInstance(bArr);
            writeTo(codedOutputStreamNewInstance);
            codedOutputStreamNewInstance.checkNoSpaceLeft();
            return bArr;
        } catch (IOException e9) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e9);
        }
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite
    public ByteString toByteString() {
        try {
            ByteString.CodedBuilder codedBuilderNewCodedBuilder = ByteString.newCodedBuilder(getSerializedSize());
            writeTo(codedBuilderNewCodedBuilder.getCodedOutput());
            return codedBuilderNewCodedBuilder.build();
        } catch (IOException e9) {
            throw new RuntimeException("Serializing to a ByteString threw an IOException (should never happen).", e9);
        }
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite
    public void writeDelimitedTo(OutputStream outputStream) throws IOException {
        int serializedSize = getSerializedSize();
        CodedOutputStream codedOutputStreamNewInstance = CodedOutputStream.newInstance(outputStream, CodedOutputStream.computePreferredBufferSize(CodedOutputStream.computeRawVarint32Size(serializedSize) + serializedSize));
        codedOutputStreamNewInstance.writeRawVarint32(serializedSize);
        writeTo(codedOutputStreamNewInstance);
        codedOutputStreamNewInstance.flush();
    }

    @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite
    public void writeTo(OutputStream outputStream) throws IOException {
        CodedOutputStream codedOutputStreamNewInstance = CodedOutputStream.newInstance(outputStream, CodedOutputStream.computePreferredBufferSize(getSerializedSize()));
        writeTo(codedOutputStreamNewInstance);
        codedOutputStreamNewInstance.flush();
    }

    public static abstract class Builder<BuilderType extends Builder> implements MessageLite.Builder {
        public static <T> void addAll(Iterable<T> iterable, Collection<? super T> collection) {
            if (iterable instanceof LazyStringList) {
                checkForNullValues(((LazyStringList) iterable).getUnderlyingElements());
                collection.addAll((Collection) iterable);
            } else {
                if (iterable instanceof Collection) {
                    checkForNullValues(iterable);
                    collection.addAll((Collection) iterable);
                    return;
                }
                for (T t8 : iterable) {
                    t8.getClass();
                    collection.add(t8);
                }
            }
        }

        private static void checkForNullValues(Iterable<?> iterable) {
            Iterator<?> it = iterable.iterator();
            while (it.hasNext()) {
                it.next().getClass();
            }
        }

        public static UninitializedMessageException newUninitializedMessageException(MessageLite messageLite) {
            return new UninitializedMessageException(messageLite);
        }

        @Override // 
        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        public abstract BuilderType mo25567clone();

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public boolean mergeDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int i9 = inputStream.read();
            if (i9 == -1) {
                return false;
            }
            mergeFrom((InputStream) new LimitedInputStream(inputStream, CodedInputStream.readRawVarint32(i9, inputStream)), extensionRegistryLite);
            return true;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public abstract BuilderType mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite);

        public static final class LimitedInputStream extends FilterInputStream {
            private int limit;

            public LimitedInputStream(InputStream inputStream, int i9) {
                super(inputStream);
                this.limit = i9;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int available() {
                return Math.min(super.available(), this.limit);
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read() throws IOException {
                if (this.limit <= 0) {
                    return -1;
                }
                int i9 = super.read();
                if (i9 >= 0) {
                    this.limit--;
                }
                return i9;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public long skip(long j9) throws IOException {
                long jSkip = super.skip(Math.min(j9, this.limit));
                if (jSkip >= 0) {
                    this.limit = (int) (this.limit - jSkip);
                }
                return jSkip;
            }

            @Override // java.io.FilterInputStream, java.io.InputStream
            public int read(byte[] bArr, int i9, int i10) throws IOException {
                int i11 = this.limit;
                if (i11 <= 0) {
                    return -1;
                }
                int i12 = super.read(bArr, i9, Math.min(i10, i11));
                if (i12 >= 0) {
                    this.limit -= i12;
                }
                return i12;
            }
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public boolean mergeDelimitedFrom(InputStream inputStream) {
            return mergeDelimitedFrom(inputStream, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(CodedInputStream codedInputStream) {
            return (BuilderType) mergeFrom(codedInputStream, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(ByteString byteString) throws InvalidProtocolBufferException {
            try {
                CodedInputStream codedInputStreamNewCodedInput = byteString.newCodedInput();
                mergeFrom(codedInputStreamNewCodedInput);
                codedInputStreamNewCodedInput.checkLastTagWas(0);
                return this;
            } catch (InvalidProtocolBufferException e9) {
                throw e9;
            } catch (IOException e10) {
                throw new RuntimeException("Reading from a ByteString threw an IOException (should never happen).", e10);
            }
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            try {
                CodedInputStream codedInputStreamNewCodedInput = byteString.newCodedInput();
                mergeFrom(codedInputStreamNewCodedInput, extensionRegistryLite);
                codedInputStreamNewCodedInput.checkLastTagWas(0);
                return this;
            } catch (InvalidProtocolBufferException e9) {
                throw e9;
            } catch (IOException e10) {
                throw new RuntimeException("Reading from a ByteString threw an IOException (should never happen).", e10);
            }
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(byte[] bArr) {
            return (BuilderType) mergeFrom(bArr, 0, bArr.length);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(byte[] bArr, int i9, int i10) throws InvalidProtocolBufferException {
            try {
                CodedInputStream codedInputStreamNewInstance = CodedInputStream.newInstance(bArr, i9, i10);
                mergeFrom(codedInputStreamNewInstance);
                codedInputStreamNewInstance.checkLastTagWas(0);
                return this;
            } catch (InvalidProtocolBufferException e9) {
                throw e9;
            } catch (IOException e10) {
                throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e10);
            }
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (BuilderType) mergeFrom(bArr, 0, bArr.length, extensionRegistryLite);
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(byte[] bArr, int i9, int i10, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            try {
                CodedInputStream codedInputStreamNewInstance = CodedInputStream.newInstance(bArr, i9, i10);
                mergeFrom(codedInputStreamNewInstance, extensionRegistryLite);
                codedInputStreamNewInstance.checkLastTagWas(0);
                return this;
            } catch (InvalidProtocolBufferException e9) {
                throw e9;
            } catch (IOException e10) {
                throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e10);
            }
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(InputStream inputStream) throws InvalidProtocolBufferException {
            CodedInputStream codedInputStreamNewInstance = CodedInputStream.newInstance(inputStream);
            mergeFrom(codedInputStreamNewInstance);
            codedInputStreamNewInstance.checkLastTagWas(0);
            return this;
        }

        @Override // com.google.i18n.phonenumbers.repackaged.com.google.protobuf.MessageLite.Builder, com.google.i18n.phonenumbers.repackaged.com.google.protobuf.Message.Builder
        public BuilderType mergeFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            CodedInputStream codedInputStreamNewInstance = CodedInputStream.newInstance(inputStream);
            mergeFrom(codedInputStreamNewInstance, extensionRegistryLite);
            codedInputStreamNewInstance.checkLastTagWas(0);
            return this;
        }
    }
}
