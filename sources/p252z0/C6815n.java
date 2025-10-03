package p252z0;

import android.content.ContentResolver;
import android.content.UriMatcher;
import android.net.Uri;
import android.provider.ContactsContract;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: z0.n */
/* loaded from: classes.dex */
public class C6815n extends AbstractC6813l<InputStream> {

    /* renamed from: e */
    public static final UriMatcher f22569e;

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        f22569e = uriMatcher;
        uriMatcher.addURI("com.android.contacts", "contacts/lookup/*/#", 1);
        uriMatcher.addURI("com.android.contacts", "contacts/lookup/*", 1);
        uriMatcher.addURI("com.android.contacts", "contacts/#/photo", 2);
        uriMatcher.addURI("com.android.contacts", "contacts/#", 3);
        uriMatcher.addURI("com.android.contacts", "contacts/#/display_photo", 4);
        uriMatcher.addURI("com.android.contacts", "phone_lookup/*", 5);
    }

    public C6815n(ContentResolver contentResolver, Uri uri) {
        super(contentResolver, uri);
    }

    @Override // p252z0.InterfaceC6805d
    /* renamed from: a */
    public Class<InputStream> mo56a() {
        return InputStream.class;
    }

    @Override // p252z0.AbstractC6813l
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public void mo25358c(InputStream inputStream) throws IOException {
        inputStream.close();
    }

    @Override // p252z0.AbstractC6813l
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public InputStream mo25359d(Uri uri, ContentResolver contentResolver) throws FileNotFoundException {
        InputStream inputStreamM25383i = m25383i(uri, contentResolver);
        if (inputStreamM25383i != null) {
            return inputStreamM25383i;
        }
        throw new FileNotFoundException("InputStream is null for " + uri);
    }

    /* renamed from: i */
    public final InputStream m25383i(Uri uri, ContentResolver contentResolver) throws FileNotFoundException {
        int iMatch = f22569e.match(uri);
        if (iMatch != 1) {
            if (iMatch == 3) {
                return m25384j(contentResolver, uri);
            }
            if (iMatch != 5) {
                return contentResolver.openInputStream(uri);
            }
        }
        Uri uriLookupContact = ContactsContract.Contacts.lookupContact(contentResolver, uri);
        if (uriLookupContact != null) {
            return m25384j(contentResolver, uriLookupContact);
        }
        throw new FileNotFoundException("Contact cannot be found");
    }

    /* renamed from: j */
    public final InputStream m25384j(ContentResolver contentResolver, Uri uri) {
        return ContactsContract.Contacts.openContactPhotoInputStream(contentResolver, uri, true);
    }
}
