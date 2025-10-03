package com.google.i18n.phonenumbers.repackaged.com.google.protobuf;

import java.io.IOException;

/* loaded from: classes2.dex */
public class LazyFieldLite {
    private ByteString bytes;
    private ExtensionRegistryLite extensionRegistry;
    private volatile boolean isDirty = false;
    protected volatile MessageLite value;

    public LazyFieldLite(ExtensionRegistryLite extensionRegistryLite, ByteString byteString) {
        this.extensionRegistry = extensionRegistryLite;
        this.bytes = byteString;
    }

    public static LazyFieldLite fromValue(MessageLite messageLite) {
        LazyFieldLite lazyFieldLite = new LazyFieldLite();
        lazyFieldLite.setValue(messageLite);
        return lazyFieldLite;
    }

    public void clear() {
        this.bytes = null;
        this.value = null;
        this.extensionRegistry = null;
        this.isDirty = true;
    }

    public boolean containsDefaultInstance() {
        return this.value == null && this.bytes == null;
    }

    public void ensureInitialized(MessageLite messageLite) {
        if (this.value != null) {
            return;
        }
        synchronized (this) {
            if (this.value != null) {
                return;
            }
            try {
                if (this.bytes != null) {
                    this.value = messageLite.getParserForType().parseFrom(this.bytes, this.extensionRegistry);
                } else {
                    this.value = messageLite;
                }
            } catch (IOException unused) {
            }
        }
    }

    public ExtensionRegistryLite getExtensionRegistry() {
        return this.extensionRegistry;
    }

    public int getSerializedSize() {
        return this.isDirty ? this.value.getSerializedSize() : this.bytes.size();
    }

    public MessageLite getValue(MessageLite messageLite) {
        ensureInitialized(messageLite);
        return this.value;
    }

    public void merge(LazyFieldLite lazyFieldLite) {
        if (lazyFieldLite.containsDefaultInstance()) {
            return;
        }
        ByteString byteString = this.bytes;
        if (byteString == null) {
            this.bytes = lazyFieldLite.bytes;
        } else {
            byteString.concat(lazyFieldLite.toByteString());
        }
        this.isDirty = false;
    }

    public void setByteString(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        this.bytes = byteString;
        this.extensionRegistry = extensionRegistryLite;
        this.isDirty = false;
    }

    public MessageLite setValue(MessageLite messageLite) {
        MessageLite messageLite2 = this.value;
        this.value = messageLite;
        this.bytes = null;
        this.isDirty = true;
        return messageLite2;
    }

    public ByteString toByteString() {
        if (!this.isDirty) {
            return this.bytes;
        }
        synchronized (this) {
            if (!this.isDirty) {
                return this.bytes;
            }
            if (this.value == null) {
                this.bytes = ByteString.EMPTY;
            } else {
                this.bytes = this.value.toByteString();
            }
            this.isDirty = false;
            return this.bytes;
        }
    }

    public LazyFieldLite() {
    }
}
