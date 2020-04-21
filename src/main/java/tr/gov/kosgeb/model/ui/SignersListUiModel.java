package tr.gov.kosgeb.model.ui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class SignersListUiModel {
    private final SimpleStringProperty colCitizenShipNumber = new SimpleStringProperty("");
    private final SimpleStringProperty colFirstNameLastName = new SimpleStringProperty("");
    private final SimpleIntegerProperty colSignOrder = new SimpleIntegerProperty(0);

    public SignersListUiModel(String citizenshipNumber,String firstNameLastName,Integer signOrder) {
        setColCitizenShipNumber(citizenshipNumber);
        setColFirstNameLastName(firstNameLastName);
        setColSignOrder(signOrder);
    }

    public SignersListUiModel() {
        this("","",0);
    }

    public String getColCitizenShipNumber() {
        return colCitizenShipNumber.get();
    }

    public SimpleStringProperty colCitizenShipNumberProperty() {
        return colCitizenShipNumber;
    }

    public void setColCitizenShipNumber(String colCitizenShipNumber) {
        this.colCitizenShipNumber.set(colCitizenShipNumber);
    }

    public String getColFirstNameLastName() {
        return colFirstNameLastName.get();
    }

    public SimpleStringProperty colFirstNameLastNameProperty() {
        return colFirstNameLastName;
    }

    public void setColFirstNameLastName(String colFirstNameLastName) {
        this.colFirstNameLastName.set(colFirstNameLastName);
    }

    public int getColSignOrder() {
        return colSignOrder.get();
    }

    public SimpleIntegerProperty colSignOrderProperty() {
        return colSignOrder;
    }

    public void setColSignOrder(int colSignOrder) {
        this.colSignOrder.set(colSignOrder);
    }
}
