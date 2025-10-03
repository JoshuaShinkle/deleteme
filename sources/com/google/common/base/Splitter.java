package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public final class Splitter {
    private final int limit;
    private final boolean omitEmptyStrings;
    private final Strategy strategy;
    private final CharMatcher trimmer;

    @Beta
    public static final class MapSplitter {
        private static final String INVALID_ENTRY_MESSAGE = "Chunk [%s] is not a valid entry";
        private final Splitter entrySplitter;
        private final Splitter outerSplitter;

        public Map<String, String> split(CharSequence charSequence) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (String str : this.outerSplitter.split(charSequence)) {
                Iterator itSplittingIterator = this.entrySplitter.splittingIterator(str);
                Preconditions.checkArgument(itSplittingIterator.hasNext(), INVALID_ENTRY_MESSAGE, str);
                String str2 = (String) itSplittingIterator.next();
                Preconditions.checkArgument(!linkedHashMap.containsKey(str2), "Duplicate key [%s] found.", str2);
                Preconditions.checkArgument(itSplittingIterator.hasNext(), INVALID_ENTRY_MESSAGE, str);
                linkedHashMap.put(str2, (String) itSplittingIterator.next());
                Preconditions.checkArgument(!itSplittingIterator.hasNext(), INVALID_ENTRY_MESSAGE, str);
            }
            return Collections.unmodifiableMap(linkedHashMap);
        }

        private MapSplitter(Splitter splitter, Splitter splitter2) {
            this.outerSplitter = splitter;
            this.entrySplitter = (Splitter) Preconditions.checkNotNull(splitter2);
        }
    }

    public static abstract class SplittingIterator extends AbstractIterator<String> {
        int limit;
        int offset = 0;
        final boolean omitEmptyStrings;
        final CharSequence toSplit;
        final CharMatcher trimmer;

        public SplittingIterator(Splitter splitter, CharSequence charSequence) {
            this.trimmer = splitter.trimmer;
            this.omitEmptyStrings = splitter.omitEmptyStrings;
            this.limit = splitter.limit;
            this.toSplit = charSequence;
        }

        public abstract int separatorEnd(int i9);

        public abstract int separatorStart(int i9);

        @Override // com.google.common.base.AbstractIterator
        public String computeNext() {
            int iSeparatorStart;
            int i9 = this.offset;
            while (true) {
                int i10 = this.offset;
                if (i10 == -1) {
                    return endOfData();
                }
                iSeparatorStart = separatorStart(i10);
                if (iSeparatorStart == -1) {
                    iSeparatorStart = this.toSplit.length();
                    this.offset = -1;
                } else {
                    this.offset = separatorEnd(iSeparatorStart);
                }
                int i11 = this.offset;
                if (i11 == i9) {
                    int i12 = i11 + 1;
                    this.offset = i12;
                    if (i12 > this.toSplit.length()) {
                        this.offset = -1;
                    }
                } else {
                    while (i9 < iSeparatorStart && this.trimmer.matches(this.toSplit.charAt(i9))) {
                        i9++;
                    }
                    while (iSeparatorStart > i9 && this.trimmer.matches(this.toSplit.charAt(iSeparatorStart - 1))) {
                        iSeparatorStart--;
                    }
                    if (!this.omitEmptyStrings || i9 != iSeparatorStart) {
                        break;
                    }
                    i9 = this.offset;
                }
            }
            int i13 = this.limit;
            if (i13 == 1) {
                iSeparatorStart = this.toSplit.length();
                this.offset = -1;
                while (iSeparatorStart > i9 && this.trimmer.matches(this.toSplit.charAt(iSeparatorStart - 1))) {
                    iSeparatorStart--;
                }
            } else {
                this.limit = i13 - 1;
            }
            return this.toSplit.subSequence(i9, iSeparatorStart).toString();
        }
    }

    public interface Strategy {
        Iterator<String> iterator(Splitter splitter, CharSequence charSequence);
    }

    private Splitter(Strategy strategy) {
        this(strategy, false, CharMatcher.none(), Integer.MAX_VALUE);
    }

    public static Splitter fixedLength(final int i9) {
        Preconditions.checkArgument(i9 > 0, "The length may not be less than 1");
        return new Splitter(new Strategy() { // from class: com.google.common.base.Splitter.4
            @Override // com.google.common.base.Splitter.Strategy
            public SplittingIterator iterator(Splitter splitter, CharSequence charSequence) {
                return new SplittingIterator(splitter, charSequence) { // from class: com.google.common.base.Splitter.4.1
                    @Override // com.google.common.base.Splitter.SplittingIterator
                    public int separatorEnd(int i10) {
                        return i10;
                    }

                    @Override // com.google.common.base.Splitter.SplittingIterator
                    public int separatorStart(int i10) {
                        int i11 = i10 + i9;
                        if (i11 < this.toSplit.length()) {
                            return i11;
                        }
                        return -1;
                    }
                };
            }
        });
    }

    /* renamed from: on */
    public static Splitter m17556on(char c9) {
        return m17557on(CharMatcher.m17547is(c9));
    }

    @GwtIncompatible
    public static Splitter onPattern(String str) {
        return m17558on(Platform.compilePattern(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Iterator<String> splittingIterator(CharSequence charSequence) {
        return this.strategy.iterator(this, charSequence);
    }

    public Splitter limit(int i9) {
        Preconditions.checkArgument(i9 > 0, "must be greater than zero: %s", i9);
        return new Splitter(this.strategy, this.omitEmptyStrings, this.trimmer, i9);
    }

    public Splitter omitEmptyStrings() {
        return new Splitter(this.strategy, true, this.trimmer, this.limit);
    }

    public Iterable<String> split(final CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        return new Iterable<String>() { // from class: com.google.common.base.Splitter.5
            @Override // java.lang.Iterable
            public Iterator<String> iterator() {
                return Splitter.this.splittingIterator(charSequence);
            }

            public String toString() {
                Joiner joinerM17550on = Joiner.m17550on(", ");
                StringBuilder sb = new StringBuilder();
                sb.append('[');
                StringBuilder sbAppendTo = joinerM17550on.appendTo(sb, (Iterable<?>) this);
                sbAppendTo.append(']');
                return sbAppendTo.toString();
            }
        };
    }

    @Beta
    public List<String> splitToList(CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        Iterator<String> itSplittingIterator = splittingIterator(charSequence);
        ArrayList arrayList = new ArrayList();
        while (itSplittingIterator.hasNext()) {
            arrayList.add(itSplittingIterator.next());
        }
        return Collections.unmodifiableList(arrayList);
    }

    public Splitter trimResults() {
        return trimResults(CharMatcher.whitespace());
    }

    @Beta
    public MapSplitter withKeyValueSeparator(String str) {
        return withKeyValueSeparator(m17559on(str));
    }

    private Splitter(Strategy strategy, boolean z8, CharMatcher charMatcher, int i9) {
        this.strategy = strategy;
        this.omitEmptyStrings = z8;
        this.trimmer = charMatcher;
        this.limit = i9;
    }

    /* renamed from: on */
    public static Splitter m17557on(final CharMatcher charMatcher) {
        Preconditions.checkNotNull(charMatcher);
        return new Splitter(new Strategy() { // from class: com.google.common.base.Splitter.1
            @Override // com.google.common.base.Splitter.Strategy
            public SplittingIterator iterator(Splitter splitter, CharSequence charSequence) {
                return new SplittingIterator(splitter, charSequence) { // from class: com.google.common.base.Splitter.1.1
                    @Override // com.google.common.base.Splitter.SplittingIterator
                    public int separatorEnd(int i9) {
                        return i9 + 1;
                    }

                    @Override // com.google.common.base.Splitter.SplittingIterator
                    public int separatorStart(int i9) {
                        return charMatcher.indexIn(this.toSplit, i9);
                    }
                };
            }
        });
    }

    public Splitter trimResults(CharMatcher charMatcher) {
        Preconditions.checkNotNull(charMatcher);
        return new Splitter(this.strategy, this.omitEmptyStrings, charMatcher, this.limit);
    }

    @Beta
    public MapSplitter withKeyValueSeparator(char c9) {
        return withKeyValueSeparator(m17556on(c9));
    }

    @Beta
    public MapSplitter withKeyValueSeparator(Splitter splitter) {
        return new MapSplitter(splitter);
    }

    /* renamed from: on */
    public static Splitter m17559on(final String str) {
        Preconditions.checkArgument(str.length() != 0, "The separator may not be the empty string.");
        if (str.length() == 1) {
            return m17556on(str.charAt(0));
        }
        return new Splitter(new Strategy() { // from class: com.google.common.base.Splitter.2
            @Override // com.google.common.base.Splitter.Strategy
            public SplittingIterator iterator(Splitter splitter, CharSequence charSequence) {
                return new SplittingIterator(splitter, charSequence) { // from class: com.google.common.base.Splitter.2.1
                    @Override // com.google.common.base.Splitter.SplittingIterator
                    public int separatorEnd(int i9) {
                        return i9 + str.length();
                    }

                    /* JADX WARN: Code restructure failed: missing block: B:8:0x0026, code lost:
                    
                        r6 = r6 + 1;
                     */
                    @Override // com.google.common.base.Splitter.SplittingIterator
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                    */
                    public int separatorStart(int i9) {
                        int length = str.length();
                        int length2 = this.toSplit.length() - length;
                        while (i9 <= length2) {
                            for (int i10 = 0; i10 < length; i10++) {
                                if (this.toSplit.charAt(i10 + i9) != str.charAt(i10)) {
                                    break;
                                }
                            }
                            return i9;
                        }
                        return -1;
                    }
                };
            }
        });
    }

    @GwtIncompatible
    /* renamed from: on */
    public static Splitter m17560on(Pattern pattern) {
        return m17558on(new JdkPattern(pattern));
    }

    /* renamed from: on */
    private static Splitter m17558on(final CommonPattern commonPattern) {
        Preconditions.checkArgument(!commonPattern.matcher("").matches(), "The pattern may not match the empty string: %s", commonPattern);
        return new Splitter(new Strategy() { // from class: com.google.common.base.Splitter.3
            @Override // com.google.common.base.Splitter.Strategy
            public SplittingIterator iterator(Splitter splitter, CharSequence charSequence) {
                final CommonMatcher commonMatcherMatcher = commonPattern.matcher(charSequence);
                return new SplittingIterator(splitter, charSequence) { // from class: com.google.common.base.Splitter.3.1
                    @Override // com.google.common.base.Splitter.SplittingIterator
                    public int separatorEnd(int i9) {
                        return commonMatcherMatcher.end();
                    }

                    @Override // com.google.common.base.Splitter.SplittingIterator
                    public int separatorStart(int i9) {
                        if (commonMatcherMatcher.find(i9)) {
                            return commonMatcherMatcher.start();
                        }
                        return -1;
                    }
                };
            }
        });
    }
}
