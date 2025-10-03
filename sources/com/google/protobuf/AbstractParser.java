package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.MessageLite;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public abstract class AbstractParser<MessageType extends MessageLite> implements Parser<MessageType> {
    private static final ExtensionRegistryLite EMPTY_REGISTRY = ExtensionRegistryLite.getEmptyRegistry();

    private MessageType checkMessageInitialized(MessageType messagetype) throws InvalidProtocolBufferException {
        if (messagetype == null || messagetype.isInitialized()) {
            return messagetype;
        }
        throw newUninitializedMessageException(messagetype).asInvalidProtocolBufferException().setUnfinishedMessage(messagetype);
    }

    private UninitializedMessageException newUninitializedMessageException(MessageType messagetype) {
        return messagetype instanceof AbstractMessageLite ? ((AbstractMessageLite) messagetype).newUninitializedMessageException() : new UninitializedMessageException(messagetype);
    }

    @Override // com.google.protobuf.Parser
    public MessageType parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (MessageType) checkMessageInitialized(parsePartialDelimitedFrom(inputStream, extensionRegistryLite));
    }

    @Override // com.google.protobuf.Parser
    public MessageType parsePartialDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        try {
            int i9 = inputStream.read();
            if (i9 == -1) {
                return null;
            }
            return (MessageType) parsePartialFrom((InputStream) new AbstractMessageLite.Builder.LimitedInputStream(inputStream, CodedInputStream.readRawVarint32(i9, inputStream)), extensionRegistryLite);
        } catch (IOException e9) {
            throw new InvalidProtocolBufferException(e9);
        }
    }

    @Override // com.google.protobuf.Parser
    public MessageType parseDelimitedFrom(InputStream inputStream) {
        return (MessageType) parseDelimitedFrom(inputStream, EMPTY_REGISTRY);
    }

    @Override // com.google.protobuf.Parser
    public MessageType parsePartialDelimitedFrom(InputStream inputStream) {
        return (MessageType) parsePartialDelimitedFrom(inputStream, EMPTY_REGISTRY);
    }

    @Override // com.google.protobuf.Parser
    public MessageType parsePartialFrom(CodedInputStream codedInputStream) {
        return parsePartialFrom(codedInputStream, EMPTY_REGISTRY);
    }

    @Override // com.google.protobuf.Parser
    public MessageType parsePartialFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        try {
            CodedInputStream codedInputStreamNewCodedInput = byteString.newCodedInput();
            MessageType partialFrom = parsePartialFrom(codedInputStreamNewCodedInput, extensionRegistryLite);
            try {
                codedInputStreamNewCodedInput.checkLastTagWas(0);
                return partialFrom;
            } catch (InvalidProtocolBufferException e9) {
                throw e9.setUnfinishedMessage(partialFrom);
            }
        } catch (InvalidProtocolBufferException e10) {
            throw e10;
        }
    }

    @Override // com.google.protobuf.Parser
    public MessageType parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (MessageType) checkMessageInitialized(parsePartialFrom(codedInputStream, extensionRegistryLite));
    }

    @Override // com.google.protobuf.Parser
    public MessageType parseFrom(CodedInputStream codedInputStream) {
        return (MessageType) parseFrom(codedInputStream, EMPTY_REGISTRY);
    }

    @Override // com.google.protobuf.Parser
    public MessageType parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (MessageType) checkMessageInitialized(parsePartialFrom(byteString, extensionRegistryLite));
    }

    @Override // com.google.protobuf.Parser
    public MessageType parseFrom(ByteString byteString) {
        return (MessageType) parseFrom(byteString, EMPTY_REGISTRY);
    }

    @Override // com.google.protobuf.Parser
    public MessageType parsePartialFrom(ByteString byteString) {
        return (MessageType) parsePartialFrom(byteString, EMPTY_REGISTRY);
    }

    @Override // com.google.protobuf.Parser
    public MessageType parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        try {
            CodedInputStream codedInputStreamNewInstance = CodedInputStream.newInstance(byteBuffer);
            MessageType partialFrom = parsePartialFrom(codedInputStreamNewInstance, extensionRegistryLite);
            try {
                codedInputStreamNewInstance.checkLastTagWas(0);
                return (MessageType) checkMessageInitialized(partialFrom);
            } catch (InvalidProtocolBufferException e9) {
                throw e9.setUnfinishedMessage(partialFrom);
            }
        } catch (InvalidProtocolBufferException e10) {
            throw e10;
        }
    }

    @Override // com.google.protobuf.Parser
    public MessageType parsePartialFrom(byte[] bArr, int i9, int i10, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        try {
            CodedInputStream codedInputStreamNewInstance = CodedInputStream.newInstance(bArr, i9, i10);
            MessageType partialFrom = parsePartialFrom(codedInputStreamNewInstance, extensionRegistryLite);
            try {
                codedInputStreamNewInstance.checkLastTagWas(0);
                return partialFrom;
            } catch (InvalidProtocolBufferException e9) {
                throw e9.setUnfinishedMessage(partialFrom);
            }
        } catch (InvalidProtocolBufferException e10) {
            throw e10;
        }
    }

    @Override // com.google.protobuf.Parser
    public MessageType parsePartialFrom(byte[] bArr, int i9, int i10) {
        return (MessageType) parsePartialFrom(bArr, i9, i10, EMPTY_REGISTRY);
    }

    @Override // com.google.protobuf.Parser
    public MessageType parseFrom(ByteBuffer byteBuffer) {
        return (MessageType) parseFrom(byteBuffer, EMPTY_REGISTRY);
    }

    @Override // com.google.protobuf.Parser
    public MessageType parsePartialFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (MessageType) parsePartialFrom(bArr, 0, bArr.length, extensionRegistryLite);
    }

    @Override // com.google.protobuf.Parser
    public MessageType parseFrom(byte[] bArr, int i9, int i10, ExtensionRegistryLite extensionRegistryLite) {
        return (MessageType) checkMessageInitialized(parsePartialFrom(bArr, i9, i10, extensionRegistryLite));
    }

    @Override // com.google.protobuf.Parser
    public MessageType parsePartialFrom(byte[] bArr) {
        return (MessageType) parsePartialFrom(bArr, 0, bArr.length, EMPTY_REGISTRY);
    }

    @Override // com.google.protobuf.Parser
    public MessageType parseFrom(byte[] bArr, int i9, int i10) {
        return (MessageType) parseFrom(bArr, i9, i10, EMPTY_REGISTRY);
    }

    @Override // com.google.protobuf.Parser
    public MessageType parsePartialFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        CodedInputStream codedInputStreamNewInstance = CodedInputStream.newInstance(inputStream);
        MessageType partialFrom = parsePartialFrom(codedInputStreamNewInstance, extensionRegistryLite);
        try {
            codedInputStreamNewInstance.checkLastTagWas(0);
            return partialFrom;
        } catch (InvalidProtocolBufferException e9) {
            throw e9.setUnfinishedMessage(partialFrom);
        }
    }

    @Override // com.google.protobuf.Parser
    public MessageType parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (MessageType) parseFrom(bArr, 0, bArr.length, extensionRegistryLite);
    }

    @Override // com.google.protobuf.Parser
    public MessageType parseFrom(byte[] bArr) {
        return (MessageType) parseFrom(bArr, EMPTY_REGISTRY);
    }

    @Override // com.google.protobuf.Parser
    public MessageType parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (MessageType) checkMessageInitialized(parsePartialFrom(inputStream, extensionRegistryLite));
    }

    @Override // com.google.protobuf.Parser
    public MessageType parseFrom(InputStream inputStream) {
        return (MessageType) parseFrom(inputStream, EMPTY_REGISTRY);
    }

    @Override // com.google.protobuf.Parser
    public MessageType parsePartialFrom(InputStream inputStream) {
        return (MessageType) parsePartialFrom(inputStream, EMPTY_REGISTRY);
    }
}
