package org.apache.commons.p159io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes.dex */
public class LineIterator implements Iterator {
    private final BufferedReader bufferedReader;
    private String cachedLine;
    private boolean finished = false;

    public LineIterator(Reader reader) {
        if (reader == null) {
            throw new IllegalArgumentException("Reader must not be null");
        }
        if (reader instanceof BufferedReader) {
            this.bufferedReader = (BufferedReader) reader;
        } else {
            this.bufferedReader = new BufferedReader(reader);
        }
    }

    public static void closeQuietly(LineIterator lineIterator) throws IOException {
        if (lineIterator != null) {
            lineIterator.close();
        }
    }

    public void close() throws IOException {
        this.finished = true;
        IOUtils.closeQuietly(this.bufferedReader);
        this.cachedLine = null;
    }

    @Override // java.util.Iterator
    public boolean hasNext() throws IOException {
        String line;
        if (this.cachedLine != null) {
            return true;
        }
        if (this.finished) {
            return false;
        }
        do {
            try {
                line = this.bufferedReader.readLine();
                if (line == null) {
                    this.finished = true;
                    return false;
                }
            } catch (IOException e9) {
                close();
                throw new IllegalStateException(e9.toString());
            }
        } while (!isValidLine(line));
        this.cachedLine = line;
        return true;
    }

    public boolean isValidLine(String str) {
        return true;
    }

    @Override // java.util.Iterator
    public Object next() {
        return nextLine();
    }

    public String nextLine() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more lines");
        }
        String str = this.cachedLine;
        this.cachedLine = null;
        return str;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Remove unsupported on LineIterator");
    }
}
