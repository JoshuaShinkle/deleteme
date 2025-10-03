package com.google.common.p039io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.BufferedWriter;
import java.io.Writer;
import java.util.Iterator;

@GwtIncompatible
/* loaded from: classes2.dex */
public abstract class CharSink {
    public Writer openBufferedStream() {
        Writer writerOpenStream = openStream();
        return writerOpenStream instanceof BufferedWriter ? (BufferedWriter) writerOpenStream : new BufferedWriter(writerOpenStream);
    }

    public abstract Writer openStream();

    public void write(CharSequence charSequence) throws X {
        Preconditions.checkNotNull(charSequence);
        try {
            Writer writer = (Writer) Closer.create().register(openStream());
            writer.append(charSequence);
            writer.flush();
        } finally {
        }
    }

    @CanIgnoreReturnValue
    public long writeFrom(Readable readable) throws X {
        Preconditions.checkNotNull(readable);
        try {
            Writer writer = (Writer) Closer.create().register(openStream());
            long jCopy = CharStreams.copy(readable, writer);
            writer.flush();
            return jCopy;
        } finally {
        }
    }

    public void writeLines(Iterable<? extends CharSequence> iterable) throws X {
        writeLines(iterable, System.getProperty("line.separator"));
    }

    public void writeLines(Iterable<? extends CharSequence> iterable, String str) throws X {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkNotNull(str);
        try {
            Writer writer = (Writer) Closer.create().register(openBufferedStream());
            Iterator<? extends CharSequence> it = iterable.iterator();
            while (it.hasNext()) {
                writer.append(it.next()).append((CharSequence) str);
            }
            writer.flush();
        } finally {
        }
    }
}
