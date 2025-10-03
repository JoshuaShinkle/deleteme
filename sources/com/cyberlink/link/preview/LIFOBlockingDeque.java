package com.cyberlink.link.preview;

import java.util.concurrent.LinkedBlockingDeque;

/* loaded from: classes.dex */
public class LIFOBlockingDeque<T> extends LinkedBlockingDeque<T> {
    @Override // java.util.concurrent.LinkedBlockingDeque, java.util.Queue, java.util.concurrent.BlockingDeque, java.util.concurrent.BlockingQueue, java.util.Deque
    public boolean offer(T t8) {
        return super.offerFirst(t8);
    }

    @Override // java.util.concurrent.LinkedBlockingDeque, java.util.AbstractQueue, java.util.Queue, java.util.concurrent.BlockingDeque, java.util.Deque
    public T remove() {
        return (T) super.removeFirst();
    }
}
