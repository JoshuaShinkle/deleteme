package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.NoSuchElementException;

@GwtCompatible
/* loaded from: classes2.dex */
public abstract class AbstractIterator<T> extends UnmodifiableIterator<T> {
    private T next;
    private State state = State.NOT_READY;

    /* renamed from: com.google.common.collect.AbstractIterator$1 */
    public static /* synthetic */ class C36211 {
        static final /* synthetic */ int[] $SwitchMap$com$google$common$collect$AbstractIterator$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$com$google$common$collect$AbstractIterator$State = iArr;
            try {
                iArr[State.DONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$common$collect$AbstractIterator$State[State.READY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public enum State {
        READY,
        NOT_READY,
        DONE,
        FAILED
    }

    private boolean tryToComputeNext() {
        this.state = State.FAILED;
        this.next = computeNext();
        if (this.state == State.DONE) {
            return false;
        }
        this.state = State.READY;
        return true;
    }

    public abstract T computeNext();

    @CanIgnoreReturnValue
    public final T endOfData() {
        this.state = State.DONE;
        return null;
    }

    @Override // java.util.Iterator
    @CanIgnoreReturnValue
    public final boolean hasNext() {
        Preconditions.checkState(this.state != State.FAILED);
        int i9 = C36211.$SwitchMap$com$google$common$collect$AbstractIterator$State[this.state.ordinal()];
        if (i9 == 1) {
            return false;
        }
        if (i9 != 2) {
            return tryToComputeNext();
        }
        return true;
    }

    @Override // java.util.Iterator
    @CanIgnoreReturnValue
    public final T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.state = State.NOT_READY;
        T t8 = this.next;
        this.next = null;
        return t8;
    }

    public final T peek() {
        if (hasNext()) {
            return this.next;
        }
        throw new NoSuchElementException();
    }
}
