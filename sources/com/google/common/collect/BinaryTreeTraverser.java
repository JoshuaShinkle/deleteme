package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.Deque;
import java.util.Iterator;

@Beta
@GwtCompatible
/* loaded from: classes2.dex */
public abstract class BinaryTreeTraverser<T> extends TreeTraverser<T> {

    public final class InOrderIterator extends AbstractIterator<T> {
        private final BitSet hasExpandedLeft;
        private final Deque<T> stack;

        public InOrderIterator(T t8) {
            ArrayDeque arrayDeque = new ArrayDeque(8);
            this.stack = arrayDeque;
            this.hasExpandedLeft = new BitSet();
            arrayDeque.addLast(t8);
        }

        @Override // com.google.common.collect.AbstractIterator
        public T computeNext() {
            while (!this.stack.isEmpty()) {
                T last = this.stack.getLast();
                if (this.hasExpandedLeft.get(this.stack.size() - 1)) {
                    this.stack.removeLast();
                    this.hasExpandedLeft.clear(this.stack.size());
                    BinaryTreeTraverser.pushIfPresent(this.stack, BinaryTreeTraverser.this.rightChild(last));
                    return last;
                }
                this.hasExpandedLeft.set(this.stack.size() - 1);
                BinaryTreeTraverser.pushIfPresent(this.stack, BinaryTreeTraverser.this.leftChild(last));
            }
            return endOfData();
        }
    }

    public final class PostOrderIterator extends UnmodifiableIterator<T> {
        private final BitSet hasExpanded;
        private final Deque<T> stack;

        public PostOrderIterator(T t8) {
            ArrayDeque arrayDeque = new ArrayDeque(8);
            this.stack = arrayDeque;
            arrayDeque.addLast(t8);
            this.hasExpanded = new BitSet();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        @Override // java.util.Iterator
        public T next() {
            while (true) {
                T last = this.stack.getLast();
                if (this.hasExpanded.get(this.stack.size() - 1)) {
                    this.stack.removeLast();
                    this.hasExpanded.clear(this.stack.size());
                    return last;
                }
                this.hasExpanded.set(this.stack.size() - 1);
                BinaryTreeTraverser.pushIfPresent(this.stack, BinaryTreeTraverser.this.rightChild(last));
                BinaryTreeTraverser.pushIfPresent(this.stack, BinaryTreeTraverser.this.leftChild(last));
            }
        }
    }

    public final class PreOrderIterator extends UnmodifiableIterator<T> implements PeekingIterator<T> {
        private final Deque<T> stack;

        public PreOrderIterator(T t8) {
            ArrayDeque arrayDeque = new ArrayDeque(8);
            this.stack = arrayDeque;
            arrayDeque.addLast(t8);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        @Override // java.util.Iterator, com.google.common.collect.PeekingIterator
        public T next() {
            T tRemoveLast = this.stack.removeLast();
            BinaryTreeTraverser.pushIfPresent(this.stack, BinaryTreeTraverser.this.rightChild(tRemoveLast));
            BinaryTreeTraverser.pushIfPresent(this.stack, BinaryTreeTraverser.this.leftChild(tRemoveLast));
            return tRemoveLast;
        }

        @Override // com.google.common.collect.PeekingIterator
        public T peek() {
            return this.stack.getLast();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> void pushIfPresent(Deque<T> deque, Optional<T> optional) {
        if (optional.isPresent()) {
            deque.addLast(optional.get());
        }
    }

    @Override // com.google.common.collect.TreeTraverser
    public final Iterable<T> children(final T t8) {
        Preconditions.checkNotNull(t8);
        return new FluentIterable<T>() { // from class: com.google.common.collect.BinaryTreeTraverser.1
            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return new AbstractIterator<T>() { // from class: com.google.common.collect.BinaryTreeTraverser.1.1
                    boolean doneLeft;
                    boolean doneRight;

                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // com.google.common.collect.AbstractIterator
                    public T computeNext() {
                        if (!this.doneLeft) {
                            this.doneLeft = true;
                            C36351 c36351 = C36351.this;
                            Optional optionalLeftChild = BinaryTreeTraverser.this.leftChild(t8);
                            if (optionalLeftChild.isPresent()) {
                                return (T) optionalLeftChild.get();
                            }
                        }
                        if (!this.doneRight) {
                            this.doneRight = true;
                            C36351 c363512 = C36351.this;
                            Optional optionalRightChild = BinaryTreeTraverser.this.rightChild(t8);
                            if (optionalRightChild.isPresent()) {
                                return (T) optionalRightChild.get();
                            }
                        }
                        return endOfData();
                    }
                };
            }
        };
    }

    public final FluentIterable<T> inOrderTraversal(final T t8) {
        Preconditions.checkNotNull(t8);
        return new FluentIterable<T>() { // from class: com.google.common.collect.BinaryTreeTraverser.2
            @Override // java.lang.Iterable
            public UnmodifiableIterator<T> iterator() {
                return new InOrderIterator(t8);
            }
        };
    }

    public abstract Optional<T> leftChild(T t8);

    @Override // com.google.common.collect.TreeTraverser
    public UnmodifiableIterator<T> postOrderIterator(T t8) {
        return new PostOrderIterator(t8);
    }

    @Override // com.google.common.collect.TreeTraverser
    public UnmodifiableIterator<T> preOrderIterator(T t8) {
        return new PreOrderIterator(t8);
    }

    public abstract Optional<T> rightChild(T t8);
}
