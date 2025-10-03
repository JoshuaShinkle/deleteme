package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;

@GwtCompatible
/* loaded from: classes2.dex */
public final class MoreObjects {

    public static final class ToStringHelper {
        private final String className;
        private final ValueHolder holderHead;
        private ValueHolder holderTail;
        private boolean omitNullValues;

        public static final class ValueHolder {
            String name;
            ValueHolder next;
            Object value;

            private ValueHolder() {
            }
        }

        private ValueHolder addHolder() {
            ValueHolder valueHolder = new ValueHolder();
            this.holderTail.next = valueHolder;
            this.holderTail = valueHolder;
            return valueHolder;
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, Object obj) {
            return addHolder(str, obj);
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(Object obj) {
            return addHolder(obj);
        }

        @CanIgnoreReturnValue
        public ToStringHelper omitNullValues() {
            this.omitNullValues = true;
            return this;
        }

        public String toString() {
            boolean z8 = this.omitNullValues;
            StringBuilder sb = new StringBuilder(32);
            sb.append(this.className);
            sb.append('{');
            String str = "";
            for (ValueHolder valueHolder = this.holderHead.next; valueHolder != null; valueHolder = valueHolder.next) {
                Object obj = valueHolder.value;
                if (!z8 || obj != null) {
                    sb.append(str);
                    String str2 = valueHolder.name;
                    if (str2 != null) {
                        sb.append(str2);
                        sb.append('=');
                    }
                    if (obj == null || !obj.getClass().isArray()) {
                        sb.append(obj);
                    } else {
                        String strDeepToString = Arrays.deepToString(new Object[]{obj});
                        sb.append((CharSequence) strDeepToString, 1, strDeepToString.length() - 1);
                    }
                    str = ", ";
                }
            }
            sb.append('}');
            return sb.toString();
        }

        private ToStringHelper(String str) {
            ValueHolder valueHolder = new ValueHolder();
            this.holderHead = valueHolder;
            this.holderTail = valueHolder;
            this.omitNullValues = false;
            this.className = (String) Preconditions.checkNotNull(str);
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, boolean z8) {
            return addHolder(str, String.valueOf(z8));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(boolean z8) {
            return addHolder(String.valueOf(z8));
        }

        private ToStringHelper addHolder(Object obj) {
            addHolder().value = obj;
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, char c9) {
            return addHolder(str, String.valueOf(c9));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(char c9) {
            return addHolder(String.valueOf(c9));
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, double d9) {
            return addHolder(str, String.valueOf(d9));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(double d9) {
            return addHolder(String.valueOf(d9));
        }

        private ToStringHelper addHolder(String str, Object obj) {
            ValueHolder valueHolderAddHolder = addHolder();
            valueHolderAddHolder.value = obj;
            valueHolderAddHolder.name = (String) Preconditions.checkNotNull(str);
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, float f9) {
            return addHolder(str, String.valueOf(f9));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(float f9) {
            return addHolder(String.valueOf(f9));
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, int i9) {
            return addHolder(str, String.valueOf(i9));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(int i9) {
            return addHolder(String.valueOf(i9));
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, long j9) {
            return addHolder(str, String.valueOf(j9));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(long j9) {
            return addHolder(String.valueOf(j9));
        }
    }

    private MoreObjects() {
    }

    public static <T> T firstNonNull(T t8, T t9) {
        return t8 != null ? t8 : (T) Preconditions.checkNotNull(t9);
    }

    public static ToStringHelper toStringHelper(Object obj) {
        return new ToStringHelper(obj.getClass().getSimpleName());
    }

    public static ToStringHelper toStringHelper(Class<?> cls) {
        return new ToStringHelper(cls.getSimpleName());
    }

    public static ToStringHelper toStringHelper(String str) {
        return new ToStringHelper(str);
    }
}
