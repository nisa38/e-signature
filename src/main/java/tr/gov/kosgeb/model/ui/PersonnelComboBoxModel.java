package tr.gov.kosgeb.model.ui;

public class PersonnelComboBoxModel {
    private int id;
    private String nameSurname;
    private String unitOfPersonnel;

    public PersonnelComboBoxModel(int id, String nameSurname, String unitOfPersonnel) {
        this.id = id;
        this.nameSurname = nameSurname;
        this.unitOfPersonnel = unitOfPersonnel;
    }

    public PersonnelComboBoxModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getUnitOfPersonnel() {
        return unitOfPersonnel;
    }

    public void setUnitOfPersonnel(String unitOfPersonnel) {
        this.unitOfPersonnel = unitOfPersonnel;
    }

    public String getComboText() {
        return this.nameSurname + " ("+this.unitOfPersonnel+")";
    }

    @Override
    public String toString() {
        return this.nameSurname + " -"+this.unitOfPersonnel;
    }
}
