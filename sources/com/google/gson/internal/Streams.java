package com.google.gson.internal;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;

/* loaded from: classes2.dex */
public final class Streams {
    private Streams() {
        throw new UnsupportedOperationException();
    }

    public static JsonElement parse(JsonReader jsonReader) {
        boolean z8;
        try {
            try {
                jsonReader.peek();
                z8 = false;
            } catch (EOFException e9) {
                e = e9;
                z8 = true;
            }
            try {
                return TypeAdapters.JSON_ELEMENT.read2(jsonReader);
            } catch (EOFException e10) {
                e = e10;
                if (z8) {
                    return JsonNull.INSTANCE;
                }
                throw new JsonSyntaxException(e);
            }
        } catch (MalformedJsonException e11) {
            throw new JsonSyntaxException(e11);
        } catch (IOException e12) {
            throw new JsonIOException(e12);
        } catch (NumberFormatException e13) {
            throw new JsonSyntaxException(e13);
        }
    }

    public static void write(JsonElement jsonElement, JsonWriter jsonWriter) {
        TypeAdapters.JSON_ELEMENT.write(jsonWriter, jsonElement);
    }

    public static Writer writerForAppendable(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new AppendableWriter(appendable);
    }

    public static final class AppendableWriter extends Writer {
        private final Appendable appendable;
        private final CurrentWrite currentWrite = new CurrentWrite();

        public static class CurrentWrite implements CharSequence {
            char[] chars;

            @Override // java.lang.CharSequence
            public char charAt(int i9) {
                return this.chars[i9];
            }

            @Override // java.lang.CharSequence
            public int length() {
                return this.chars.length;
            }

            @Override // java.lang.CharSequence
            public CharSequence subSequence(int i9, int i10) {
                return new String(this.chars, i9, i10 - i9);
            }
        }

        public AppendableWriter(Appendable appendable) {
            this.appendable = appendable;
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i9, int i10) throws IOException {
            CurrentWrite currentWrite = this.currentWrite;
            currentWrite.chars = cArr;
            this.appendable.append(currentWrite, i9, i10 + i9);
        }

        @Override // java.io.Writer
        public void write(int i9) throws IOException {
            this.appendable.append((char) i9);
        }
    }
}
