package com.google.common.net;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.io.Serializable;

@Beta
@GwtCompatible
/* loaded from: classes2.dex */
public final class HostAndPort implements Serializable {
    private static final int NO_PORT = -1;
    private static final long serialVersionUID = 0;
    private final boolean hasBracketlessColons;
    private final String host;
    private final int port;

    private HostAndPort(String str, int i9, boolean z8) {
        this.host = str;
        this.port = i9;
        this.hasBracketlessColons = z8;
    }

    public static HostAndPort fromHost(String str) throws NumberFormatException {
        HostAndPort hostAndPortFromString = fromString(str);
        Preconditions.checkArgument(!hostAndPortFromString.hasPort(), "Host has a port: %s", str);
        return hostAndPortFromString;
    }

    public static HostAndPort fromParts(String str, int i9) throws NumberFormatException {
        Preconditions.checkArgument(isValidPort(i9), "Port out of range: %s", i9);
        HostAndPort hostAndPortFromString = fromString(str);
        Preconditions.checkArgument(!hostAndPortFromString.hasPort(), "Host has a port: %s", str);
        return new HostAndPort(hostAndPortFromString.host, i9, hostAndPortFromString.hasBracketlessColons);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static HostAndPort fromString(String str) throws NumberFormatException {
        String strSubstring;
        String strSubstring2;
        Preconditions.checkNotNull(str);
        int i9 = -1;
        if (str.startsWith("[")) {
            String[] hostAndPortFromBracketedHost = getHostAndPortFromBracketedHost(str);
            strSubstring2 = hostAndPortFromBracketedHost[0];
            strSubstring = hostAndPortFromBracketedHost[1];
        } else {
            int iIndexOf = str.indexOf(58);
            if (iIndexOf >= 0) {
                int i10 = iIndexOf + 1;
                if (str.indexOf(58, i10) == -1) {
                    strSubstring2 = str.substring(0, iIndexOf);
                    strSubstring = str.substring(i10);
                } else {
                    z = iIndexOf >= 0;
                    strSubstring = null;
                    strSubstring2 = str;
                }
            }
        }
        if (!Strings.isNullOrEmpty(strSubstring)) {
            Preconditions.checkArgument(!strSubstring.startsWith("+"), "Unparseable port number: %s", str);
            try {
                i9 = Integer.parseInt(strSubstring);
                Preconditions.checkArgument(isValidPort(i9), "Port number out of range: %s", str);
            } catch (NumberFormatException unused) {
                throw new IllegalArgumentException("Unparseable port number: " + str);
            }
        }
        return new HostAndPort(strSubstring2, i9, z);
    }

    private static String[] getHostAndPortFromBracketedHost(String str) {
        Preconditions.checkArgument(str.charAt(0) == '[', "Bracketed host-port string must start with a bracket: %s", str);
        int iIndexOf = str.indexOf(58);
        int iLastIndexOf = str.lastIndexOf(93);
        Preconditions.checkArgument(iIndexOf > -1 && iLastIndexOf > iIndexOf, "Invalid bracketed host/port: %s", str);
        String strSubstring = str.substring(1, iLastIndexOf);
        int i9 = iLastIndexOf + 1;
        if (i9 == str.length()) {
            return new String[]{strSubstring, ""};
        }
        Preconditions.checkArgument(str.charAt(i9) == ':', "Only a colon may follow a close bracket: %s", str);
        int i10 = iLastIndexOf + 2;
        for (int i11 = i10; i11 < str.length(); i11++) {
            Preconditions.checkArgument(Character.isDigit(str.charAt(i11)), "Port must be numeric: %s", str);
        }
        return new String[]{strSubstring, str.substring(i10)};
    }

    private static boolean isValidPort(int i9) {
        return i9 >= 0 && i9 <= 65535;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HostAndPort)) {
            return false;
        }
        HostAndPort hostAndPort = (HostAndPort) obj;
        return Objects.equal(this.host, hostAndPort.host) && this.port == hostAndPort.port && this.hasBracketlessColons == hostAndPort.hasBracketlessColons;
    }

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        Preconditions.checkState(hasPort());
        return this.port;
    }

    public int getPortOrDefault(int i9) {
        return hasPort() ? this.port : i9;
    }

    public boolean hasPort() {
        return this.port >= 0;
    }

    public int hashCode() {
        return Objects.hashCode(this.host, Integer.valueOf(this.port), Boolean.valueOf(this.hasBracketlessColons));
    }

    public HostAndPort requireBracketsForIPv6() {
        Preconditions.checkArgument(!this.hasBracketlessColons, "Possible bracketless IPv6 literal: %s", this.host);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.host.length() + 8);
        if (this.host.indexOf(58) >= 0) {
            sb.append('[');
            sb.append(this.host);
            sb.append(']');
        } else {
            sb.append(this.host);
        }
        if (hasPort()) {
            sb.append(':');
            sb.append(this.port);
        }
        return sb.toString();
    }

    public HostAndPort withDefaultPort(int i9) {
        Preconditions.checkArgument(isValidPort(i9));
        return (hasPort() || this.port == i9) ? this : new HostAndPort(this.host, i9, this.hasBracketlessColons);
    }
}
