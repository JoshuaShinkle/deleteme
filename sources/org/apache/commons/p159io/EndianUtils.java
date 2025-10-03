package org.apache.commons.p159io;

import com.google.common.primitives.UnsignedBytes;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes.dex */
public class EndianUtils {
    private static int read(InputStream inputStream) throws IOException {
        int i9 = inputStream.read();
        if (-1 != i9) {
            return i9;
        }
        throw new EOFException("Unexpected EOF reached");
    }

    public static double readSwappedDouble(byte[] bArr, int i9) {
        return Double.longBitsToDouble(readSwappedLong(bArr, i9));
    }

    public static float readSwappedFloat(byte[] bArr, int i9) {
        return Float.intBitsToFloat(readSwappedInteger(bArr, i9));
    }

    public static int readSwappedInteger(byte[] bArr, int i9) {
        return ((bArr[i9 + 0] & UnsignedBytes.MAX_VALUE) << 0) + ((bArr[i9 + 1] & UnsignedBytes.MAX_VALUE) << 8) + ((bArr[i9 + 2] & UnsignedBytes.MAX_VALUE) << 16) + ((bArr[i9 + 3] & UnsignedBytes.MAX_VALUE) << 24);
    }

    public static long readSwappedLong(byte[] bArr, int i9) {
        return ((((((bArr[i9 + 4] & UnsignedBytes.MAX_VALUE) << 0) + ((bArr[i9 + 5] & UnsignedBytes.MAX_VALUE) << 8)) + ((bArr[i9 + 6] & UnsignedBytes.MAX_VALUE) << 16)) + ((bArr[i9 + 7] & UnsignedBytes.MAX_VALUE) << 24)) << 32) + ((((bArr[i9 + 0] & UnsignedBytes.MAX_VALUE) << 0) + ((bArr[i9 + 1] & UnsignedBytes.MAX_VALUE) << 8) + ((bArr[i9 + 2] & UnsignedBytes.MAX_VALUE) << 16) + ((bArr[i9 + 3] & UnsignedBytes.MAX_VALUE) << 24)) & 4294967295L);
    }

    public static short readSwappedShort(byte[] bArr, int i9) {
        return (short) (((bArr[i9 + 0] & UnsignedBytes.MAX_VALUE) << 0) + ((bArr[i9 + 1] & UnsignedBytes.MAX_VALUE) << 8));
    }

    public static long readSwappedUnsignedInteger(byte[] bArr, int i9) {
        return ((bArr[i9 + 3] & UnsignedBytes.MAX_VALUE) << 24) + ((((bArr[i9 + 0] & UnsignedBytes.MAX_VALUE) << 0) + ((bArr[i9 + 1] & UnsignedBytes.MAX_VALUE) << 8) + ((bArr[i9 + 2] & UnsignedBytes.MAX_VALUE) << 16)) & 4294967295L);
    }

    public static int readSwappedUnsignedShort(byte[] bArr, int i9) {
        return ((bArr[i9 + 0] & UnsignedBytes.MAX_VALUE) << 0) + ((bArr[i9 + 1] & UnsignedBytes.MAX_VALUE) << 8);
    }

    public static double swapDouble(double d9) {
        return Double.longBitsToDouble(swapLong(Double.doubleToLongBits(d9)));
    }

    public static float swapFloat(float f9) {
        return Float.intBitsToFloat(swapInteger(Float.floatToIntBits(f9)));
    }

    public static int swapInteger(int i9) {
        return (((i9 >> 0) & 255) << 24) + (((i9 >> 8) & 255) << 16) + (((i9 >> 16) & 255) << 8) + (((i9 >> 24) & 255) << 0);
    }

    public static long swapLong(long j9) {
        return (((j9 >> 0) & 255) << 56) + (((j9 >> 8) & 255) << 48) + (((j9 >> 16) & 255) << 40) + (((j9 >> 24) & 255) << 32) + (((j9 >> 32) & 255) << 24) + (((j9 >> 40) & 255) << 16) + (((j9 >> 48) & 255) << 8) + (((j9 >> 56) & 255) << 0);
    }

    public static short swapShort(short s8) {
        return (short) ((((s8 >> 0) & 255) << 8) + (((s8 >> 8) & 255) << 0));
    }

    public static void writeSwappedDouble(byte[] bArr, int i9, double d9) {
        writeSwappedLong(bArr, i9, Double.doubleToLongBits(d9));
    }

    public static void writeSwappedFloat(byte[] bArr, int i9, float f9) {
        writeSwappedInteger(bArr, i9, Float.floatToIntBits(f9));
    }

    public static void writeSwappedInteger(byte[] bArr, int i9, int i10) {
        bArr[i9 + 0] = (byte) ((i10 >> 0) & 255);
        bArr[i9 + 1] = (byte) ((i10 >> 8) & 255);
        bArr[i9 + 2] = (byte) ((i10 >> 16) & 255);
        bArr[i9 + 3] = (byte) ((i10 >> 24) & 255);
    }

    public static void writeSwappedLong(byte[] bArr, int i9, long j9) {
        bArr[i9 + 0] = (byte) ((j9 >> 0) & 255);
        bArr[i9 + 1] = (byte) ((j9 >> 8) & 255);
        bArr[i9 + 2] = (byte) ((j9 >> 16) & 255);
        bArr[i9 + 3] = (byte) ((j9 >> 24) & 255);
        bArr[i9 + 4] = (byte) ((j9 >> 32) & 255);
        bArr[i9 + 5] = (byte) ((j9 >> 40) & 255);
        bArr[i9 + 6] = (byte) ((j9 >> 48) & 255);
        bArr[i9 + 7] = (byte) ((j9 >> 56) & 255);
    }

    public static void writeSwappedShort(byte[] bArr, int i9, short s8) {
        bArr[i9 + 0] = (byte) ((s8 >> 0) & 255);
        bArr[i9 + 1] = (byte) ((s8 >> 8) & 255);
    }

    public static double readSwappedDouble(InputStream inputStream) {
        return Double.longBitsToDouble(readSwappedLong(inputStream));
    }

    public static float readSwappedFloat(InputStream inputStream) {
        return Float.intBitsToFloat(readSwappedInteger(inputStream));
    }

    public static int readSwappedInteger(InputStream inputStream) throws IOException {
        return ((read(inputStream) & 255) << 0) + ((read(inputStream) & 255) << 8) + ((read(inputStream) & 255) << 16) + ((read(inputStream) & 255) << 24);
    }

    public static short readSwappedShort(InputStream inputStream) {
        return (short) (((read(inputStream) & 255) << 0) + ((read(inputStream) & 255) << 8));
    }

    public static int readSwappedUnsignedShort(InputStream inputStream) throws IOException {
        return ((read(inputStream) & 255) << 0) + ((read(inputStream) & 255) << 8);
    }

    public static void writeSwappedDouble(OutputStream outputStream, double d9) throws IOException {
        writeSwappedLong(outputStream, Double.doubleToLongBits(d9));
    }

    public static void writeSwappedFloat(OutputStream outputStream, float f9) throws IOException {
        writeSwappedInteger(outputStream, Float.floatToIntBits(f9));
    }

    public static long readSwappedLong(InputStream inputStream) {
        byte[] bArr = new byte[8];
        for (int i9 = 0; i9 < 8; i9++) {
            bArr[i9] = (byte) read(inputStream);
        }
        return readSwappedLong(bArr, 0);
    }

    public static long readSwappedUnsignedInteger(InputStream inputStream) throws IOException {
        return ((read(inputStream) & 255) << 24) + ((((read(inputStream) & 255) << 0) + ((read(inputStream) & 255) << 8) + ((read(inputStream) & 255) << 16)) & 4294967295L);
    }

    public static void writeSwappedShort(OutputStream outputStream, short s8) throws IOException {
        outputStream.write((byte) ((s8 >> 0) & 255));
        outputStream.write((byte) ((s8 >> 8) & 255));
    }

    public static void writeSwappedInteger(OutputStream outputStream, int i9) throws IOException {
        outputStream.write((byte) ((i9 >> 0) & 255));
        outputStream.write((byte) ((i9 >> 8) & 255));
        outputStream.write((byte) ((i9 >> 16) & 255));
        outputStream.write((byte) ((i9 >> 24) & 255));
    }

    public static void writeSwappedLong(OutputStream outputStream, long j9) throws IOException {
        outputStream.write((byte) ((j9 >> 0) & 255));
        outputStream.write((byte) ((j9 >> 8) & 255));
        outputStream.write((byte) ((j9 >> 16) & 255));
        outputStream.write((byte) ((j9 >> 24) & 255));
        outputStream.write((byte) ((j9 >> 32) & 255));
        outputStream.write((byte) ((j9 >> 40) & 255));
        outputStream.write((byte) ((j9 >> 48) & 255));
        outputStream.write((byte) ((j9 >> 56) & 255));
    }
}
