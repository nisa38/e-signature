package tr.gov.kosgeb.model.ui;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import tr.gov.kosgeb.model.enums.EnumActionType;

public class PublishListUiModel {
    private final SimpleStringProperty colNameSurname = new SimpleStringProperty("");
    private final ObjectProperty<EnumActionType> colAction = new SimpleObjectProperty<>();
    private final SimpleStringProperty colUnit = new SimpleStringProperty("");

    public PublishListUiModel(String nameSurname, EnumActionType actionType, String unit) {
        setColNameSurname(nameSurname);
        setColAction(actionType);
        setColUnit(unit);
    }

    public PublishListUiModel() {
        this("",null,"");
    }

    public String getColNameSurname() {
        return colNameSurname.get();
    }

    public SimpleStringProperty colNameSurnameProperty() {
        return colNameSurname;
    }

    public void setColNameSurname(String colNameSurname) {
        this.colNameSurname.set(colNameSurname);
    }

    public EnumActionType getColAction() {
        return colAction.get();
    }

    public ObjectProperty<EnumActionType> colActionProperty() {
        return colAction;
    }

    public void setColAction(EnumActionType colAction) {
        this.colAction.set(colAction);
    }

    public String getColUnit() {
        return colUnit.get();
    }

    public SimpleStringProperty colUnitProperty() {
        return colUnit;
    }

    public void setColUnit(String colUnit) {
        this.colUnit.set(colUnit);
    }
}
