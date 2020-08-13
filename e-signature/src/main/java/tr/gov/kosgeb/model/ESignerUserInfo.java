package tr.gov.kosgeb.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ESignerUserInfo {
    private String citizenshipNumber;
    private String nameSurname;
    private Calendar startDate;
    private Calendar endDate;
    private String issuingAuthority;
    private long Session;

    public ESignerUserInfo() {
    }

    public ESignerUserInfo(String citizenshipNumber, String nameSurname, Calendar startDate, Calendar endDate, String issuingAuthority, long session) {
        this.citizenshipNumber = citizenshipNumber;
        this.nameSurname = nameSurname;
        this.startDate = startDate;
        this.endDate = endDate;
        this.issuingAuthority = issuingAuthority;
        Session = session;
    }

    public String getCitizenshipNumber() {
        return citizenshipNumber;
    }

    public void setCitizenshipNumber(String citizenshipNumber) {
        this.citizenshipNumber = citizenshipNumber;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public String getIssuingAuthority() {
        return issuingAuthority;
    }

    public void setIssuingAuthority(String issuingAuthority) {
        this.issuingAuthority = issuingAuthority;
    }

    public long getSession() {
        return Session;
    }

    public void setSession(long session) {
        Session = session;
    }

    public String StartDate(){
        if (this.startDate != null && this.startDate.getTime()!=null)
            return new SimpleDateFormat("yyyy-MM-dd").format(this.startDate.getTime()).toString();
            return "";
    }

    public String EndDate(){
        if (this.startDate != null && this.endDate.getTime()!=null)
            return new SimpleDateFormat("yyyy-MM-dd").format(this.endDate.getTime()).toString();
        return "";
    }

}
