package tr.gov.kosgeb.model.enums;

public enum EnumActionType {

    REQUIRED("Gereği"),
    INFO("Bilgi");

    private String actionType;

    EnumActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getActionType() {
        return actionType;
    }
}
