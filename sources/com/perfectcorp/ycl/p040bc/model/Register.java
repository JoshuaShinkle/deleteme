package com.perfectcorp.ycl.p040bc.model;

import com.perfectcorp.model.Model;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class Register {

    public static class listUserRegisterLiveResult extends Model {
        public ArrayList<Live> results;
        public int totalSize;
    }

    public static class queryRegistrationFormResult extends Model {
        public String approvedAccounts;
        public String approvedDomains;
        public Boolean autoApproved;
        public JSONObject preSurveyConfig;
        public Boolean preSurveyConfigEnabled;
        public Boolean sendRegMail;

        @Override // com.perfectcorp.model.Model
        public String toString() {
            return "queryRegistrationFormResult:\nautoApproved = " + this.autoApproved + "\nsendRegMail = " + this.sendRegMail + "\napprovedDomains = " + this.approvedDomains + "\napprovedAccounts = " + this.approvedAccounts + "\npreSurveyConfig = " + this.preSurveyConfig + "\npreSurveyConfigEnabled = " + this.preSurveyConfigEnabled;
        }
    }

    public static class registerLiveResult extends Model {
        public String contactEmail;
        public Date endDate;
        public String host;
        public Date registeredTime;
        public String registrationStatus;
        public Date startDate;
        public String title;
    }
}
