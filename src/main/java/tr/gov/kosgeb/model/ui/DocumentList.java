package tr.gov.kosgeb.model.ui;

public class DocumentList {
    private String qrCodeId;
    private String documentName;
    private String creationDate;
    private String documentDateNumber;
    private String dcoumentState;

    public DocumentList(String qrCodeId, String documentName, String creationDate, String documentDateNumber, String dcoumentState) {
        this.qrCodeId = qrCodeId;
        this.documentName = documentName;
        this.creationDate = creationDate;
        this.documentDateNumber = documentDateNumber;
        this.dcoumentState = dcoumentState;
    }

    public DocumentList() {
    }

    public String getQrCodeId() {
        return qrCodeId;
    }

    public void setQrCodeId(String qrCodeId) {
        this.qrCodeId = qrCodeId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDocumentDateNumber() {
        return documentDateNumber;
    }

    public void setDocumentDateNumber(String documentDateNumber) {
        this.documentDateNumber = documentDateNumber;
    }

    public String getDcoumentState() {
        return dcoumentState;
    }

    public void setDcoumentState(String dcoumentState) {
        this.dcoumentState = dcoumentState;
    }
}
