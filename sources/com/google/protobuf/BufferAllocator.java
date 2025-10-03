package com.google.protobuf;

import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
abstract class BufferAllocator {
    private static final BufferAllocator UNPOOLED = new BufferAllocator() { // from class: com.google.protobuf.BufferAllocator.1
        @Override // com.google.protobuf.BufferAllocator
        public AllocatedBuffer allocateDirectBuffer(int i9) {
            return AllocatedBuffer.wrap(ByteBuffer.allocateDirect(i9));
        }

        @Override // com.google.protobuf.BufferAllocator
        public AllocatedBuffer allocateHeapBuffer(int i9) {
            return AllocatedBuffer.wrap(new byte[i9]);
        }
    };

    public static BufferAllocator unpooled() {
        return UNPOOLED;
    }

    public abstract AllocatedBuffer allocateDirectBuffer(int i9);

    public abstract AllocatedBuffer allocateHeapBuffer(int i9);
}
