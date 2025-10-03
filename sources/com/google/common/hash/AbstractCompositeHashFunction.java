package com.google.common.hash;

import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* loaded from: classes2.dex */
abstract class AbstractCompositeHashFunction extends AbstractHashFunction {
    private static final long serialVersionUID = 0;
    final HashFunction[] functions;

    public AbstractCompositeHashFunction(HashFunction... hashFunctionArr) {
        for (HashFunction hashFunction : hashFunctionArr) {
            Preconditions.checkNotNull(hashFunction);
        }
        this.functions = hashFunctionArr;
    }

    private Hasher fromHashers(final Hasher[] hasherArr) {
        return new Hasher() { // from class: com.google.common.hash.AbstractCompositeHashFunction.1
            @Override // com.google.common.hash.Hasher
            public HashCode hash() {
                return AbstractCompositeHashFunction.this.makeHash(hasherArr);
            }

            @Override // com.google.common.hash.Hasher
            public <T> Hasher putObject(T t8, Funnel<? super T> funnel) {
                for (Hasher hasher : hasherArr) {
                    hasher.putObject(t8, funnel);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink
            public Hasher putBoolean(boolean z8) {
                for (Hasher hasher : hasherArr) {
                    hasher.putBoolean(z8);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink
            public Hasher putByte(byte b9) {
                for (Hasher hasher : hasherArr) {
                    hasher.putByte(b9);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink
            public Hasher putChar(char c9) {
                for (Hasher hasher : hasherArr) {
                    hasher.putChar(c9);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink
            public Hasher putDouble(double d9) {
                for (Hasher hasher : hasherArr) {
                    hasher.putDouble(d9);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink
            public Hasher putFloat(float f9) {
                for (Hasher hasher : hasherArr) {
                    hasher.putFloat(f9);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink
            public Hasher putInt(int i9) {
                for (Hasher hasher : hasherArr) {
                    hasher.putInt(i9);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink
            public Hasher putLong(long j9) {
                for (Hasher hasher : hasherArr) {
                    hasher.putLong(j9);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink
            public Hasher putShort(short s8) {
                for (Hasher hasher : hasherArr) {
                    hasher.putShort(s8);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink
            public Hasher putString(CharSequence charSequence, Charset charset) {
                for (Hasher hasher : hasherArr) {
                    hasher.putString(charSequence, charset);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink
            public Hasher putUnencodedChars(CharSequence charSequence) {
                for (Hasher hasher : hasherArr) {
                    hasher.putUnencodedChars(charSequence);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink
            public Hasher putBytes(byte[] bArr) {
                for (Hasher hasher : hasherArr) {
                    hasher.putBytes(bArr);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink
            public Hasher putBytes(byte[] bArr, int i9, int i10) {
                for (Hasher hasher : hasherArr) {
                    hasher.putBytes(bArr, i9, i10);
                }
                return this;
            }

            @Override // com.google.common.hash.PrimitiveSink
            public Hasher putBytes(ByteBuffer byteBuffer) {
                int iPosition = byteBuffer.position();
                for (Hasher hasher : hasherArr) {
                    byteBuffer.position(iPosition);
                    hasher.putBytes(byteBuffer);
                }
                return this;
            }
        };
    }

    public abstract HashCode makeHash(Hasher[] hasherArr);

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        int length = this.functions.length;
        Hasher[] hasherArr = new Hasher[length];
        for (int i9 = 0; i9 < length; i9++) {
            hasherArr[i9] = this.functions[i9].newHasher();
        }
        return fromHashers(hasherArr);
    }

    @Override // com.google.common.hash.AbstractHashFunction, com.google.common.hash.HashFunction
    public Hasher newHasher(int i9) {
        Preconditions.checkArgument(i9 >= 0);
        int length = this.functions.length;
        Hasher[] hasherArr = new Hasher[length];
        for (int i10 = 0; i10 < length; i10++) {
            hasherArr[i10] = this.functions[i10].newHasher(i9);
        }
        return fromHashers(hasherArr);
    }
}
