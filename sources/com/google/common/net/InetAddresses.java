package com.google.common.net;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.hash.Hashing;
import com.google.common.p039io.ByteStreams;
import com.google.common.primitives.Ints;
import com.google.common.primitives.UnsignedBytes;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import org.apache.commons.lang3.ClassUtils;

@Beta
@GwtIncompatible
/* loaded from: classes2.dex */
public final class InetAddresses {
    private static final int IPV4_PART_COUNT = 4;
    private static final int IPV6_PART_COUNT = 8;
    private static final Splitter IPV4_SPLITTER = Splitter.m17556on(ClassUtils.PACKAGE_SEPARATOR_CHAR).limit(4);
    private static final Inet4Address LOOPBACK4 = (Inet4Address) forString("127.0.0.1");
    private static final Inet4Address ANY4 = (Inet4Address) forString("0.0.0.0");

    @Beta
    public static final class TeredoInfo {
        private final Inet4Address client;
        private final int flags;
        private final int port;
        private final Inet4Address server;

        public TeredoInfo(Inet4Address inet4Address, Inet4Address inet4Address2, int i9, int i10) {
            Preconditions.checkArgument(i9 >= 0 && i9 <= 65535, "port '%s' is out of range (0 <= port <= 0xffff)", i9);
            Preconditions.checkArgument(i10 >= 0 && i10 <= 65535, "flags '%s' is out of range (0 <= flags <= 0xffff)", i10);
            this.server = (Inet4Address) MoreObjects.firstNonNull(inet4Address, InetAddresses.ANY4);
            this.client = (Inet4Address) MoreObjects.firstNonNull(inet4Address2, InetAddresses.ANY4);
            this.port = i9;
            this.flags = i10;
        }

        public Inet4Address getClient() {
            return this.client;
        }

        public int getFlags() {
            return this.flags;
        }

        public int getPort() {
            return this.port;
        }

        public Inet4Address getServer() {
            return this.server;
        }
    }

    private InetAddresses() {
    }

    private static InetAddress bytesToInetAddress(byte[] bArr) {
        try {
            return InetAddress.getByAddress(bArr);
        } catch (UnknownHostException e9) {
            throw new AssertionError(e9);
        }
    }

    public static int coerceToInteger(InetAddress inetAddress) {
        return ByteStreams.newDataInput(getCoercedIPv4Address(inetAddress).getAddress()).readInt();
    }

    private static void compressLongestRunOfZeroes(int[] iArr) {
        int i9 = -1;
        int i10 = -1;
        int i11 = -1;
        for (int i12 = 0; i12 < iArr.length + 1; i12++) {
            if (i12 >= iArr.length || iArr[i12] != 0) {
                if (i11 >= 0) {
                    int i13 = i12 - i11;
                    if (i13 > i9) {
                        i10 = i11;
                        i9 = i13;
                    }
                    i11 = -1;
                }
            } else if (i11 < 0) {
                i11 = i12;
            }
        }
        if (i9 >= 2) {
            Arrays.fill(iArr, i10, i9 + i10, -1);
        }
    }

    private static String convertDottedQuadToHex(String str) {
        int iLastIndexOf = str.lastIndexOf(58) + 1;
        String strSubstring = str.substring(0, iLastIndexOf);
        byte[] bArrTextToNumericFormatV4 = textToNumericFormatV4(str.substring(iLastIndexOf));
        if (bArrTextToNumericFormatV4 == null) {
            return null;
        }
        return strSubstring + Integer.toHexString(((bArrTextToNumericFormatV4[0] & UnsignedBytes.MAX_VALUE) << 8) | (bArrTextToNumericFormatV4[1] & UnsignedBytes.MAX_VALUE)) + ":" + Integer.toHexString((bArrTextToNumericFormatV4[3] & UnsignedBytes.MAX_VALUE) | ((bArrTextToNumericFormatV4[2] & UnsignedBytes.MAX_VALUE) << 8));
    }

    public static InetAddress decrement(InetAddress inetAddress) {
        byte[] address = inetAddress.getAddress();
        int length = address.length - 1;
        while (length >= 0 && address[length] == 0) {
            address[length] = -1;
            length--;
        }
        Preconditions.checkArgument(length >= 0, "Decrementing %s would wrap.", inetAddress);
        address[length] = (byte) (address[length] - 1);
        return bytesToInetAddress(address);
    }

    public static InetAddress forString(String str) {
        byte[] bArrIpStringToBytes = ipStringToBytes(str);
        if (bArrIpStringToBytes != null) {
            return bytesToInetAddress(bArrIpStringToBytes);
        }
        throw formatIllegalArgumentException("'%s' is not an IP string literal.", str);
    }

    public static InetAddress forUriString(String str) {
        InetAddress inetAddressForUriStringNoThrow = forUriStringNoThrow(str);
        if (inetAddressForUriStringNoThrow != null) {
            return inetAddressForUriStringNoThrow;
        }
        throw formatIllegalArgumentException("Not a valid URI IP literal: '%s'", str);
    }

    private static InetAddress forUriStringNoThrow(String str) {
        int i9;
        Preconditions.checkNotNull(str);
        if (str.startsWith("[") && str.endsWith("]")) {
            str = str.substring(1, str.length() - 1);
            i9 = 16;
        } else {
            i9 = 4;
        }
        byte[] bArrIpStringToBytes = ipStringToBytes(str);
        if (bArrIpStringToBytes == null || bArrIpStringToBytes.length != i9) {
            return null;
        }
        return bytesToInetAddress(bArrIpStringToBytes);
    }

    private static IllegalArgumentException formatIllegalArgumentException(String str, Object... objArr) {
        return new IllegalArgumentException(String.format(Locale.ROOT, str, objArr));
    }

    public static Inet4Address fromInteger(int i9) {
        return getInet4Address(Ints.toByteArray(i9));
    }

    public static InetAddress fromLittleEndianByteArray(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i9 = 0; i9 < bArr.length; i9++) {
            bArr2[i9] = bArr[(bArr.length - i9) - 1];
        }
        return InetAddress.getByAddress(bArr2);
    }

    public static Inet4Address get6to4IPv4Address(Inet6Address inet6Address) {
        Preconditions.checkArgument(is6to4Address(inet6Address), "Address '%s' is not a 6to4 address.", toAddrString(inet6Address));
        return getInet4Address(Arrays.copyOfRange(inet6Address.getAddress(), 2, 6));
    }

    public static Inet4Address getCoercedIPv4Address(InetAddress inetAddress) {
        boolean z8;
        if (inetAddress instanceof Inet4Address) {
            return (Inet4Address) inetAddress;
        }
        byte[] address = inetAddress.getAddress();
        int i9 = 0;
        while (true) {
            if (i9 >= 15) {
                z8 = true;
                break;
            }
            if (address[i9] != 0) {
                z8 = false;
                break;
            }
            i9++;
        }
        if (z8 && address[15] == 1) {
            return LOOPBACK4;
        }
        if (z8 && address[15] == 0) {
            return ANY4;
        }
        Inet6Address inet6Address = (Inet6Address) inetAddress;
        int iAsInt = Hashing.murmur3_32().hashLong(hasEmbeddedIPv4ClientAddress(inet6Address) ? getEmbeddedIPv4ClientAddress(inet6Address).hashCode() : ByteBuffer.wrap(inet6Address.getAddress(), 0, 8).getLong()).asInt() | (-536870912);
        if (iAsInt == -1) {
            iAsInt = -2;
        }
        return getInet4Address(Ints.toByteArray(iAsInt));
    }

    public static Inet4Address getCompatIPv4Address(Inet6Address inet6Address) {
        Preconditions.checkArgument(isCompatIPv4Address(inet6Address), "Address '%s' is not IPv4-compatible.", toAddrString(inet6Address));
        return getInet4Address(Arrays.copyOfRange(inet6Address.getAddress(), 12, 16));
    }

    public static Inet4Address getEmbeddedIPv4ClientAddress(Inet6Address inet6Address) {
        if (isCompatIPv4Address(inet6Address)) {
            return getCompatIPv4Address(inet6Address);
        }
        if (is6to4Address(inet6Address)) {
            return get6to4IPv4Address(inet6Address);
        }
        if (isTeredoAddress(inet6Address)) {
            return getTeredoInfo(inet6Address).getClient();
        }
        throw formatIllegalArgumentException("'%s' has no embedded IPv4 address.", toAddrString(inet6Address));
    }

    private static Inet4Address getInet4Address(byte[] bArr) {
        Preconditions.checkArgument(bArr.length == 4, "Byte array has invalid length for an IPv4 address: %s != 4.", bArr.length);
        return (Inet4Address) bytesToInetAddress(bArr);
    }

    public static Inet4Address getIsatapIPv4Address(Inet6Address inet6Address) {
        Preconditions.checkArgument(isIsatapAddress(inet6Address), "Address '%s' is not an ISATAP address.", toAddrString(inet6Address));
        return getInet4Address(Arrays.copyOfRange(inet6Address.getAddress(), 12, 16));
    }

    public static TeredoInfo getTeredoInfo(Inet6Address inet6Address) {
        Preconditions.checkArgument(isTeredoAddress(inet6Address), "Address '%s' is not a Teredo address.", toAddrString(inet6Address));
        byte[] address = inet6Address.getAddress();
        Inet4Address inet4Address = getInet4Address(Arrays.copyOfRange(address, 4, 8));
        int i9 = ByteStreams.newDataInput(address, 8).readShort() & 65535;
        int i10 = 65535 & (~ByteStreams.newDataInput(address, 10).readShort());
        byte[] bArrCopyOfRange = Arrays.copyOfRange(address, 12, 16);
        for (int i11 = 0; i11 < bArrCopyOfRange.length; i11++) {
            bArrCopyOfRange[i11] = (byte) (~bArrCopyOfRange[i11]);
        }
        return new TeredoInfo(inet4Address, getInet4Address(bArrCopyOfRange), i10, i9);
    }

    public static boolean hasEmbeddedIPv4ClientAddress(Inet6Address inet6Address) {
        return isCompatIPv4Address(inet6Address) || is6to4Address(inet6Address) || isTeredoAddress(inet6Address);
    }

    private static String hextetsToIPv6String(int[] iArr) {
        StringBuilder sb = new StringBuilder(39);
        int i9 = 0;
        boolean z8 = false;
        while (i9 < iArr.length) {
            boolean z9 = iArr[i9] >= 0;
            if (z9) {
                if (z8) {
                    sb.append(':');
                }
                sb.append(Integer.toHexString(iArr[i9]));
            } else if (i9 == 0 || z8) {
                sb.append("::");
            }
            i9++;
            z8 = z9;
        }
        return sb.toString();
    }

    public static InetAddress increment(InetAddress inetAddress) {
        byte[] address = inetAddress.getAddress();
        int length = address.length - 1;
        while (true) {
            if (length < 0 || address[length] != -1) {
                break;
            }
            address[length] = 0;
            length--;
        }
        Preconditions.checkArgument(length >= 0, "Incrementing %s would wrap.", inetAddress);
        address[length] = (byte) (address[length] + 1);
        return bytesToInetAddress(address);
    }

    private static byte[] ipStringToBytes(String str) {
        boolean z8 = false;
        boolean z9 = false;
        for (int i9 = 0; i9 < str.length(); i9++) {
            char cCharAt = str.charAt(i9);
            if (cCharAt == '.') {
                z9 = true;
            } else if (cCharAt == ':') {
                if (z9) {
                    return null;
                }
                z8 = true;
            } else if (Character.digit(cCharAt, 16) == -1) {
                return null;
            }
        }
        if (!z8) {
            if (z9) {
                return textToNumericFormatV4(str);
            }
            return null;
        }
        if (z9 && (str = convertDottedQuadToHex(str)) == null) {
            return null;
        }
        return textToNumericFormatV6(str);
    }

    public static boolean is6to4Address(Inet6Address inet6Address) {
        byte[] address = inet6Address.getAddress();
        return address[0] == 32 && address[1] == 2;
    }

    public static boolean isCompatIPv4Address(Inet6Address inet6Address) {
        byte b9;
        if (!inet6Address.isIPv4CompatibleAddress()) {
            return false;
        }
        byte[] address = inet6Address.getAddress();
        return (address[12] == 0 && address[13] == 0 && address[14] == 0 && ((b9 = address[15]) == 0 || b9 == 1)) ? false : true;
    }

    public static boolean isInetAddress(String str) {
        return ipStringToBytes(str) != null;
    }

    public static boolean isIsatapAddress(Inet6Address inet6Address) {
        if (isTeredoAddress(inet6Address)) {
            return false;
        }
        byte[] address = inet6Address.getAddress();
        return (address[8] | 3) == 3 && address[9] == 0 && address[10] == 94 && address[11] == -2;
    }

    public static boolean isMappedIPv4Address(String str) {
        byte[] bArrIpStringToBytes = ipStringToBytes(str);
        if (bArrIpStringToBytes == null || bArrIpStringToBytes.length != 16) {
            return false;
        }
        int i9 = 0;
        while (true) {
            if (i9 >= 10) {
                for (int i10 = 10; i10 < 12; i10++) {
                    if (bArrIpStringToBytes[i10] != -1) {
                        return false;
                    }
                }
                return true;
            }
            if (bArrIpStringToBytes[i9] != 0) {
                return false;
            }
            i9++;
        }
    }

    public static boolean isMaximum(InetAddress inetAddress) {
        for (byte b9 : inetAddress.getAddress()) {
            if (b9 != -1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isTeredoAddress(Inet6Address inet6Address) {
        byte[] address = inet6Address.getAddress();
        return address[0] == 32 && address[1] == 1 && address[2] == 0 && address[3] == 0;
    }

    public static boolean isUriInetAddress(String str) {
        return forUriStringNoThrow(str) != null;
    }

    private static short parseHextet(String str) throws NumberFormatException {
        int i9 = Integer.parseInt(str, 16);
        if (i9 <= 65535) {
            return (short) i9;
        }
        throw new NumberFormatException();
    }

    private static byte parseOctet(String str) throws NumberFormatException {
        int i9 = Integer.parseInt(str);
        if (i9 > 255 || (str.startsWith("0") && str.length() > 1)) {
            throw new NumberFormatException();
        }
        return (byte) i9;
    }

    private static byte[] textToNumericFormatV4(String str) {
        byte[] bArr = new byte[4];
        try {
            Iterator<String> it = IPV4_SPLITTER.split(str).iterator();
            int i9 = 0;
            while (it.hasNext()) {
                int i10 = i9 + 1;
                bArr[i9] = parseOctet(it.next());
                i9 = i10;
            }
            if (i9 == 4) {
                return bArr;
            }
            return null;
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private static byte[] textToNumericFormatV6(String str) {
        int length;
        int length2;
        String[] strArrSplit = str.split(":", 10);
        if (strArrSplit.length < 3 || strArrSplit.length > 9) {
            return null;
        }
        int i9 = -1;
        for (int i10 = 1; i10 < strArrSplit.length - 1; i10++) {
            if (strArrSplit[i10].length() == 0) {
                if (i9 >= 0) {
                    return null;
                }
                i9 = i10;
            }
        }
        if (i9 >= 0) {
            length2 = (strArrSplit.length - i9) - 1;
            if (strArrSplit[0].length() == 0) {
                length = i9 - 1;
                if (length != 0) {
                    return null;
                }
            } else {
                length = i9;
            }
            if (strArrSplit[strArrSplit.length - 1].length() == 0 && length2 - 1 != 0) {
                return null;
            }
        } else {
            length = strArrSplit.length;
            length2 = 0;
        }
        int i11 = 8 - (length + length2);
        if (i9 < 0 ? i11 != 0 : i11 < 1) {
            return null;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(16);
        for (int i12 = 0; i12 < length; i12++) {
            try {
                byteBufferAllocate.putShort(parseHextet(strArrSplit[i12]));
            } catch (NumberFormatException unused) {
                return null;
            }
        }
        for (int i13 = 0; i13 < i11; i13++) {
            byteBufferAllocate.putShort((short) 0);
        }
        while (length2 > 0) {
            byteBufferAllocate.putShort(parseHextet(strArrSplit[strArrSplit.length - length2]));
            length2--;
        }
        return byteBufferAllocate.array();
    }

    public static String toAddrString(InetAddress inetAddress) {
        Preconditions.checkNotNull(inetAddress);
        if (inetAddress instanceof Inet4Address) {
            return inetAddress.getHostAddress();
        }
        Preconditions.checkArgument(inetAddress instanceof Inet6Address);
        byte[] address = inetAddress.getAddress();
        int[] iArr = new int[8];
        for (int i9 = 0; i9 < 8; i9++) {
            int i10 = i9 * 2;
            iArr[i9] = Ints.fromBytes((byte) 0, (byte) 0, address[i10], address[i10 + 1]);
        }
        compressLongestRunOfZeroes(iArr);
        return hextetsToIPv6String(iArr);
    }

    public static String toUriString(InetAddress inetAddress) {
        if (!(inetAddress instanceof Inet6Address)) {
            return toAddrString(inetAddress);
        }
        return "[" + toAddrString(inetAddress) + "]";
    }
}
