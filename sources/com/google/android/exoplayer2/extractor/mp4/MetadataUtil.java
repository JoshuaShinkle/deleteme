package com.google.android.exoplayer2.extractor.mp4;

import android.util.Log;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import com.google.android.exoplayer2.metadata.id3.CommentFrame;
import com.google.android.exoplayer2.metadata.id3.Id3Frame;
import com.google.android.exoplayer2.metadata.id3.TextInformationFrame;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.common.net.HttpHeaders;
import com.perfectcorp.ycl.p040bc.model.network.NetworkManager;

/* loaded from: classes.dex */
final class MetadataUtil {
    private static final String LANGUAGE_UNDEFINED = "und";
    private static final String TAG = "MetadataUtil";
    private static final int SHORT_TYPE_NAME_1 = Util.getIntegerCodeForString("nam");
    private static final int SHORT_TYPE_NAME_2 = Util.getIntegerCodeForString("trk");
    private static final int SHORT_TYPE_COMMENT = Util.getIntegerCodeForString("cmt");
    private static final int SHORT_TYPE_YEAR = Util.getIntegerCodeForString("day");
    private static final int SHORT_TYPE_ARTIST = Util.getIntegerCodeForString("ART");
    private static final int SHORT_TYPE_ENCODER = Util.getIntegerCodeForString("too");
    private static final int SHORT_TYPE_ALBUM = Util.getIntegerCodeForString("alb");
    private static final int SHORT_TYPE_COMPOSER_1 = Util.getIntegerCodeForString("com");
    private static final int SHORT_TYPE_COMPOSER_2 = Util.getIntegerCodeForString("wrt");
    private static final int SHORT_TYPE_LYRICS = Util.getIntegerCodeForString("lyr");
    private static final int SHORT_TYPE_GENRE = Util.getIntegerCodeForString("gen");
    private static final int TYPE_COVER_ART = Util.getIntegerCodeForString("covr");
    private static final int TYPE_GENRE = Util.getIntegerCodeForString("gnre");
    private static final int TYPE_GROUPING = Util.getIntegerCodeForString("grp");
    private static final int TYPE_DISK_NUMBER = Util.getIntegerCodeForString("disk");
    private static final int TYPE_TRACK_NUMBER = Util.getIntegerCodeForString("trkn");
    private static final int TYPE_TEMPO = Util.getIntegerCodeForString("tmpo");
    private static final int TYPE_COMPILATION = Util.getIntegerCodeForString("cpil");
    private static final int TYPE_ALBUM_ARTIST = Util.getIntegerCodeForString("aART");
    private static final int TYPE_SORT_TRACK_NAME = Util.getIntegerCodeForString("sonm");
    private static final int TYPE_SORT_ALBUM = Util.getIntegerCodeForString("soal");
    private static final int TYPE_SORT_ARTIST = Util.getIntegerCodeForString("soar");
    private static final int TYPE_SORT_ALBUM_ARTIST = Util.getIntegerCodeForString("soaa");
    private static final int TYPE_SORT_COMPOSER = Util.getIntegerCodeForString("soco");
    private static final int TYPE_RATING = Util.getIntegerCodeForString("rtng");
    private static final int TYPE_GAPLESS_ALBUM = Util.getIntegerCodeForString("pgap");
    private static final int TYPE_TV_SORT_SHOW = Util.getIntegerCodeForString("sosn");
    private static final int TYPE_TV_SHOW = Util.getIntegerCodeForString("tvsh");
    private static final int TYPE_INTERNAL = Util.getIntegerCodeForString("----");
    private static final String[] STANDARD_GENRES = {"Blues", "Classic Rock", "Country", "Dance", "Disco", "Funk", "Grunge", "Hip-Hop", "Jazz", "Metal", "New Age", "Oldies", NetworkManager.REPORT_TARGET_RESON.OTHER, "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", "Alternative", "Ska", "Death Metal", "Pranks", "Soundtrack", "Euro-Techno", "Ambient", "Trip-Hop", "Vocal", "Jazz+Funk", "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "Sound Clip", "Gospel", "Noise", "AlternRock", "Bass", "Soul", "Punk", "Space", "Meditative", "Instrumental Pop", "Instrumental Rock", "Ethnic", "Gothic", "Darkwave", "Techno-Industrial", "Electronic", "Pop-Folk", "Eurodance", "Dream", "Southern Rock", "Comedy", "Cult", "Gangsta", "Top 40", "Christian Rap", "Pop/Funk", "Jungle", "Native American", "Cabaret", "New Wave", "Psychadelic", "Rave", "Showtunes", HttpHeaders.TRAILER, "Lo-Fi", "Tribal", "Acid Punk", "Acid Jazz", "Polka", "Retro", "Musical", "Rock & Roll", "Hard Rock", "Folk", "Folk-Rock", "National Folk", "Swing", "Fast Fusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", "Avantgarde", "Gothic Rock", "Progressive Rock", "Psychedelic Rock", "Symphonic Rock", "Slow Rock", "Big Band", "Chorus", "Easy Listening", "Acoustic", "Humour", "Speech", "Chanson", "Opera", "Chamber Music", "Sonata", "Symphony", "Booty Bass", "Primus", "Porn Groove", "Satire", "Slow Jam", "Club", "Tango", "Samba", "Folklore", "Ballad", "Power Ballad", "Rhythmic Soul", "Freestyle", "Duet", "Punk Rock", "Drum Solo", "A capella", "Euro-House", "Dance Hall", "Goa", "Drum & Bass", "Club-House", "Hardcore", "Terror", "Indie", "BritPop", "Negerpunk", "Polsk Punk", "Beat", "Christian Gangsta Rap", "Heavy Metal", "Black Metal", "Crossover", "Contemporary Christian", "Christian Rock", "Merengue", "Salsa", "Thrash Metal", "Anime", "Jpop", "Synthpop"};

    private MetadataUtil() {
    }

    private static CommentFrame parseCommentAttribute(int i9, ParsableByteArray parsableByteArray) {
        int i10 = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == Atom.TYPE_data) {
            parsableByteArray.skipBytes(8);
            String nullTerminatedString = parsableByteArray.readNullTerminatedString(i10 - 16);
            return new CommentFrame("und", nullTerminatedString, nullTerminatedString);
        }
        Log.w(TAG, "Failed to parse comment attribute: " + Atom.getAtomTypeString(i9));
        return null;
    }

    private static ApicFrame parseCoverArt(ParsableByteArray parsableByteArray) {
        int i9 = parsableByteArray.readInt();
        if (parsableByteArray.readInt() != Atom.TYPE_data) {
            Log.w(TAG, "Failed to parse cover art attribute");
            return null;
        }
        int fullAtomFlags = Atom.parseFullAtomFlags(parsableByteArray.readInt());
        String str = fullAtomFlags == 13 ? "image/jpeg" : fullAtomFlags == 14 ? "image/png" : null;
        if (str == null) {
            Log.w(TAG, "Unrecognized cover art flags: " + fullAtomFlags);
            return null;
        }
        parsableByteArray.skipBytes(4);
        int i10 = i9 - 16;
        byte[] bArr = new byte[i10];
        parsableByteArray.readBytes(bArr, 0, i10);
        return new ApicFrame(str, null, 3, bArr);
    }

    public static Metadata.Entry parseIlstElement(ParsableByteArray parsableByteArray) {
        int position = parsableByteArray.getPosition() + parsableByteArray.readInt();
        int i9 = parsableByteArray.readInt();
        int i10 = (i9 >> 24) & 255;
        try {
            if (i10 == 169 || i10 == 65533) {
                int i11 = 16777215 & i9;
                if (i11 == SHORT_TYPE_COMMENT) {
                    return parseCommentAttribute(i9, parsableByteArray);
                }
                if (i11 != SHORT_TYPE_NAME_1 && i11 != SHORT_TYPE_NAME_2) {
                    if (i11 != SHORT_TYPE_COMPOSER_1 && i11 != SHORT_TYPE_COMPOSER_2) {
                        if (i11 == SHORT_TYPE_YEAR) {
                            return parseTextAttribute(i9, "TDRC", parsableByteArray);
                        }
                        if (i11 == SHORT_TYPE_ARTIST) {
                            return parseTextAttribute(i9, "TPE1", parsableByteArray);
                        }
                        if (i11 == SHORT_TYPE_ENCODER) {
                            return parseTextAttribute(i9, "TSSE", parsableByteArray);
                        }
                        if (i11 == SHORT_TYPE_ALBUM) {
                            return parseTextAttribute(i9, "TALB", parsableByteArray);
                        }
                        if (i11 == SHORT_TYPE_LYRICS) {
                            return parseTextAttribute(i9, "USLT", parsableByteArray);
                        }
                        if (i11 == SHORT_TYPE_GENRE) {
                            return parseTextAttribute(i9, "TCON", parsableByteArray);
                        }
                        if (i11 == TYPE_GROUPING) {
                            return parseTextAttribute(i9, "TIT1", parsableByteArray);
                        }
                    }
                    return parseTextAttribute(i9, "TCOM", parsableByteArray);
                }
                return parseTextAttribute(i9, "TIT2", parsableByteArray);
            }
            if (i9 == TYPE_GENRE) {
                return parseStandardGenreAttribute(parsableByteArray);
            }
            if (i9 == TYPE_DISK_NUMBER) {
                return parseIndexAndCountAttribute(i9, "TPOS", parsableByteArray);
            }
            if (i9 == TYPE_TRACK_NUMBER) {
                return parseIndexAndCountAttribute(i9, "TRCK", parsableByteArray);
            }
            if (i9 == TYPE_TEMPO) {
                return parseUint8Attribute(i9, "TBPM", parsableByteArray, true, false);
            }
            if (i9 == TYPE_COMPILATION) {
                return parseUint8Attribute(i9, "TCMP", parsableByteArray, true, true);
            }
            if (i9 == TYPE_COVER_ART) {
                return parseCoverArt(parsableByteArray);
            }
            if (i9 == TYPE_ALBUM_ARTIST) {
                return parseTextAttribute(i9, "TPE2", parsableByteArray);
            }
            if (i9 == TYPE_SORT_TRACK_NAME) {
                return parseTextAttribute(i9, "TSOT", parsableByteArray);
            }
            if (i9 == TYPE_SORT_ALBUM) {
                return parseTextAttribute(i9, "TSO2", parsableByteArray);
            }
            if (i9 == TYPE_SORT_ARTIST) {
                return parseTextAttribute(i9, "TSOA", parsableByteArray);
            }
            if (i9 == TYPE_SORT_ALBUM_ARTIST) {
                return parseTextAttribute(i9, "TSOP", parsableByteArray);
            }
            if (i9 == TYPE_SORT_COMPOSER) {
                return parseTextAttribute(i9, "TSOC", parsableByteArray);
            }
            if (i9 == TYPE_RATING) {
                return parseUint8Attribute(i9, "ITUNESADVISORY", parsableByteArray, false, false);
            }
            if (i9 == TYPE_GAPLESS_ALBUM) {
                return parseUint8Attribute(i9, "ITUNESGAPLESS", parsableByteArray, false, true);
            }
            if (i9 == TYPE_TV_SORT_SHOW) {
                return parseTextAttribute(i9, "TVSHOWSORT", parsableByteArray);
            }
            if (i9 == TYPE_TV_SHOW) {
                return parseTextAttribute(i9, "TVSHOW", parsableByteArray);
            }
            if (i9 == TYPE_INTERNAL) {
                return parseInternalAttribute(parsableByteArray, position);
            }
            Log.d(TAG, "Skipped unknown metadata entry: " + Atom.getAtomTypeString(i9));
            parsableByteArray.setPosition(position);
            return null;
        } finally {
            parsableByteArray.setPosition(position);
        }
    }

    private static TextInformationFrame parseIndexAndCountAttribute(int i9, String str, ParsableByteArray parsableByteArray) {
        int i10 = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == Atom.TYPE_data && i10 >= 22) {
            parsableByteArray.skipBytes(10);
            int unsignedShort = parsableByteArray.readUnsignedShort();
            if (unsignedShort > 0) {
                String str2 = "" + unsignedShort;
                int unsignedShort2 = parsableByteArray.readUnsignedShort();
                if (unsignedShort2 > 0) {
                    str2 = str2 + "/" + unsignedShort2;
                }
                return new TextInformationFrame(str, null, str2);
            }
        }
        Log.w(TAG, "Failed to parse index/count attribute: " + Atom.getAtomTypeString(i9));
        return null;
    }

    private static Id3Frame parseInternalAttribute(ParsableByteArray parsableByteArray, int i9) {
        String nullTerminatedString = null;
        String nullTerminatedString2 = null;
        int i10 = -1;
        int i11 = -1;
        while (parsableByteArray.getPosition() < i9) {
            int position = parsableByteArray.getPosition();
            int i12 = parsableByteArray.readInt();
            int i13 = parsableByteArray.readInt();
            parsableByteArray.skipBytes(4);
            if (i13 == Atom.TYPE_mean) {
                nullTerminatedString = parsableByteArray.readNullTerminatedString(i12 - 12);
            } else if (i13 == Atom.TYPE_name) {
                nullTerminatedString2 = parsableByteArray.readNullTerminatedString(i12 - 12);
            } else {
                if (i13 == Atom.TYPE_data) {
                    i10 = position;
                    i11 = i12;
                }
                parsableByteArray.skipBytes(i12 - 12);
            }
        }
        if (!"com.apple.iTunes".equals(nullTerminatedString) || !"iTunSMPB".equals(nullTerminatedString2) || i10 == -1) {
            return null;
        }
        parsableByteArray.setPosition(i10);
        parsableByteArray.skipBytes(16);
        return new CommentFrame("und", nullTerminatedString2, parsableByteArray.readNullTerminatedString(i11 - 16));
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0011  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static TextInformationFrame parseStandardGenreAttribute(ParsableByteArray parsableByteArray) {
        String str;
        int uint8AttributeValue = parseUint8AttributeValue(parsableByteArray);
        if (uint8AttributeValue > 0) {
            String[] strArr = STANDARD_GENRES;
            str = uint8AttributeValue <= strArr.length ? strArr[uint8AttributeValue - 1] : null;
        }
        if (str != null) {
            return new TextInformationFrame("TCON", null, str);
        }
        Log.w(TAG, "Failed to parse standard genre code");
        return null;
    }

    private static TextInformationFrame parseTextAttribute(int i9, String str, ParsableByteArray parsableByteArray) {
        int i10 = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == Atom.TYPE_data) {
            parsableByteArray.skipBytes(8);
            return new TextInformationFrame(str, null, parsableByteArray.readNullTerminatedString(i10 - 16));
        }
        Log.w(TAG, "Failed to parse text attribute: " + Atom.getAtomTypeString(i9));
        return null;
    }

    private static Id3Frame parseUint8Attribute(int i9, String str, ParsableByteArray parsableByteArray, boolean z8, boolean z9) {
        int uint8AttributeValue = parseUint8AttributeValue(parsableByteArray);
        if (z9) {
            uint8AttributeValue = Math.min(1, uint8AttributeValue);
        }
        if (uint8AttributeValue >= 0) {
            return z8 ? new TextInformationFrame(str, null, Integer.toString(uint8AttributeValue)) : new CommentFrame("und", str, Integer.toString(uint8AttributeValue));
        }
        Log.w(TAG, "Failed to parse uint8 attribute: " + Atom.getAtomTypeString(i9));
        return null;
    }

    private static int parseUint8AttributeValue(ParsableByteArray parsableByteArray) {
        parsableByteArray.skipBytes(4);
        if (parsableByteArray.readInt() == Atom.TYPE_data) {
            parsableByteArray.skipBytes(8);
            return parsableByteArray.readUnsignedByte();
        }
        Log.w(TAG, "Failed to parse uint8 attribute value");
        return -1;
    }
}
