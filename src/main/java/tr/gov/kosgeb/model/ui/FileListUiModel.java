package tr.gov.kosgeb.model.ui;

import javafx.beans.property.SimpleStringProperty;

public class FileListUiModel {
    private final SimpleStringProperty colFileName = new SimpleStringProperty("");

    public FileListUiModel(String fielName) {
        setColFileName(fielName);
    }

    public FileListUiModel() {
    }

    public String getColFileName() {
        return colFileName.get();
    }

    public SimpleStringProperty colFileNameProperty() {
        return colFileName;
    }

    public void setColFileName(String colFileName) {
        this.colFileName.set(colFileName);
    }
}
