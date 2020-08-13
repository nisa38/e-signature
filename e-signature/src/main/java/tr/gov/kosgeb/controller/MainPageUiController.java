package tr.gov.kosgeb.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.converter.IntegerStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tr.gov.kosgeb.config.Constants;
import tr.gov.kosgeb.model.ESignerUserInfo;
import tr.gov.kosgeb.model.enums.EnumActionType;
import tr.gov.kosgeb.model.ui.*;
import tr.gov.kosgeb.service.SmartCardService;

import java.io.File;
import java.net.URL;
import java.util.*;

@Controller
public class MainPageUiController implements Initializable {

    @Autowired
    private SmartCardService cardService;

    @FXML
    private TableView<PublishListUiModel> tblPublishList;
    @FXML private TableColumn<PublishListUiModel, String> colNameSurname;
    @FXML private TableColumn<PublishListUiModel, EnumActionType> colAction;
    @FXML private TableColumn<PublishListUiModel, String> colUnit;

    ObservableList<PublishListUiModel> dataPublishList;

    @FXML
    private TableView<FileListUiModel> tblFileList;
    @FXML private TableColumn<FileListUiModel, String> colFileName;

    ObservableList<FileListUiModel> dataFileList;

    @FXML
    private TableView<SignersListUiModel> tblSignersList;
    @FXML private TableColumn<SignersListUiModel, String> colCitizenShipNumber;
    @FXML private TableColumn<SignersListUiModel, String> colFirstNameLastName;
    @FXML private TableColumn<SignersListUiModel, Integer> colSignOrder;

    ObservableList<SignersListUiModel> dataSignersList;


    @FXML
    private TableView<DocumentList> tblDocumenList;
    @FXML private TableColumn<DocumentList, String> colQrCode;
    @FXML private TableColumn<DocumentList, String> colCreateDate;
    @FXML private TableColumn<DocumentList, String> colDocumentDateNo;
    @FXML private TableColumn<DocumentList, String> colState;

    ObservableList<DocumentList> dataDocumentList;

    @FXML
    public Button btnAddFile;
    @FXML
    private ComboBox<PersonnelComboBoxModel> cbSelectPersonnel;
    @FXML
    private ComboBox cbSelectSigner,cbSelectAction,cbSelectSearchType;

    /**
     * Select multiple files
     */
    public void actionChooseFiles(ActionEvent event) {
        FileChooser fc = new FileChooser();
//        // if we want to open fixed location
//        //fc.setInitialDirectory(new File("D:\\\\Books"));
//
//        // if we want to fixed file extension
//        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        List<File> selectedFiles = fc.showOpenMultipleDialog(null);

        if(selectedFiles != null) {
            for(int i=0; i<selectedFiles.size(); i++) {
                tblFileList.getItems().add(new FileListUiModel(selectedFiles.get(i).getName()));
            }
        }else {
            System.out.println("File is not valid!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(Constants.LANGUAGE == "tr"){

           Locale locale =new Locale("tr","tr");

            ResourceBundle bundle = ResourceBundle.getBundle("messages",locale);
        }
        /*





         */

        colNameSurname.setCellValueFactory(new PropertyValueFactory("colNameSurname"));
        colAction.setCellValueFactory(new PropertyValueFactory("colAction"));
        colUnit.setCellValueFactory(new PropertyValueFactory("colUnit"));
        dataPublishList = FXCollections.observableArrayList();
        tblPublishList.setItems(dataPublishList);
        tblPublishList.getColumns().setAll(colNameSurname,colAction,colUnit);
        tblPublishList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

//        tblPublishList.setEditable(true);
//        colNameSurname.setCellFactory(TextFieldTableCell.forTableColumn());
//        colUnit.setCellFactory(TextFieldTableCell.forTableColumn());
//        colAction.setCellFactory(TextFieldTableCell.forTableColumn());
//        colAction.setCellFactory(tc -> {
//            ComboBox<EnumActionType> combo = new ComboBox<>();
//            combo.getItems().addAll(EnumActionType.values());
//            TableCell<PublishListUiModel, EnumActionType> cell = new TableCell<PublishListUiModel, EnumActionType>() {
//                @Override
//                protected void updateItem(EnumActionType reason, boolean empty) {
//                    super.updateItem(reason, empty);
//                    if (empty) {
//                        setGraphic(null);
//                    } else {
//                        combo.setValue(reason);
//                        setGraphic(combo);
//                    }
//                }
//            };
//            combo.setOnAction(e ->
//                    tblPublishList.getItems().get(cell.getIndex()).setColAction(combo.getValue()));
//            return cell ;
//        });


        colFileName.setCellValueFactory(new PropertyValueFactory("colFileName"));
        dataFileList = FXCollections.observableArrayList();
        tblFileList.setItems(dataFileList);
        tblFileList.getColumns().setAll(colFileName);
        //This will allow the table to select multiple rows at once
        tblFileList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//
//
        colCitizenShipNumber.setCellValueFactory(new PropertyValueFactory("colCitizenShipNumber"));
        colFirstNameLastName.setCellValueFactory(new PropertyValueFactory("colFirstNameLastName"));
        colSignOrder.setCellValueFactory(new PropertyValueFactory("colSignOrder"));
        dataSignersList= FXCollections.observableArrayList();
        tblSignersList.setItems(dataSignersList);
        tblSignersList.getColumns().setAll(colCitizenShipNumber,colFirstNameLastName,colSignOrder);
        tblSignersList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tblSignersList.setEditable(true);
//        colFirstNameLastName.setCellFactory(TextFieldTableCell.forTableColumn());
//        colCitizenShipNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        colSignOrder.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));


        cbSelectPersonnel.getItems().setAll(allPersonnels);
//        cbSelectPersonnel.setItems(FXCollections.observableArrayList(getAllPersonnels()));


        cbSelectSigner.getItems().setAll(allPersonnels);
        cbSelectAction.getItems().addAll(EnumActionType.values());
        cbSelectSearchType.getItems().setAll(new ArrayList(Arrays.asList("1- Created Documents","2- Pending to be signed","3- Signed documents")));
    }

    private List<PersonnelComboBoxModel> allPersonnels = getAllPersonnels();

    private List<PersonnelComboBoxModel> getAllPersonnels(){
        return new ArrayList<>(Arrays.asList(new PersonnelComboBoxModel(1,"sc dsc sd","dv dvdsv"),
                new PersonnelComboBoxModel(2,"egvferavgr","löıyhögk")
        ));
    }

    public void changeCellValueEvent(TableColumn.CellEditEvent edittedCell){
        String editedColumnId = ((TableColumn) edittedCell.getSource()).getId();
        String tableId = edittedCell.getTableView().getId();
        String editValue = edittedCell.getNewValue().toString();

        switch (tableId){
            case "tblPublishList":
                PublishListUiModel rowSelected =  tblPublishList.getSelectionModel().getSelectedItem();
                switch (editedColumnId){
                    case "colNameSurname":
                        rowSelected.setColNameSurname(editValue);
                        break;
                    case "colAction":
                        rowSelected.setColAction(EnumActionType.valueOf(editValue));
                        break;
                    case "colUnit":
                        rowSelected.setColUnit(editValue);
                        break;
                }
            break;
            case "tblSignersList":
                SignersListUiModel rowSelected2 =  tblSignersList.getSelectionModel().getSelectedItem();
                switch (editedColumnId){
                    case "colCitizenShipNumber":
                        rowSelected2.setColCitizenShipNumber(editValue);
                        break;
                    case "colFirstNameLastName":
                        rowSelected2.setColFirstNameLastName(editValue);
                        break;
                    case "colSignOrder":
                        rowSelected2.setColSignOrder(Integer.parseInt(editValue));
                        break;
                }
                break;
        }
    }

    @FXML
    public void addEmptyTableRow(MouseEvent event) {
        String clickedButtonId = ((Button)(event.getSource())).getId();
        switch (clickedButtonId){
            case "btnAddBroadCast":
                PublishListUiModel emptyPublish = new PublishListUiModel();
                tblPublishList.getSelectionModel().clearSelection();
                Object selectedValue = cbSelectPersonnel.getValue();
                String selectedActionType = cbSelectAction.getValue().toString();
                dataPublishList.add(new PublishListUiModel(selectedValue.toString().split("-")[0],EnumActionType.valueOf(selectedActionType),selectedValue.toString().split("-")[1]));
//                int lastRow = tblPublishList.getItems().size() - 1;
//                tblPublishList.scrollTo(lastRow);
//                tblPublishList.getSelectionModel().select( lastRow);
//                tblPublishList.edit(lastRow,colNameSurname);
                break;
            case "btnAddFile":
                dataFileList.add(new FileListUiModel());
                break;
            case "btnAddSigner":
                tblSignersList.getSelectionModel().clearSelection();
                Object selectedSigner = cbSelectSigner.getValue();
                dataSignersList.add(new SignersListUiModel(selectedSigner.toString().split("-")[0],selectedSigner.toString().split("-")[1],0));
//                int lastRow_tblSignersList = tblSignersList.getItems().size() - 1;
//                tblSignersList.scrollTo(lastRow_tblSignersList);
//                tblSignersList.getSelectionModel().select( lastRow_tblSignersList);
//                tblSignersList.edit(lastRow_tblSignersList,colCitizenShipNumber);
                break;
            case "btnAddCustomSigner":
                tblSignersList.getSelectionModel().clearSelection();
                dataSignersList.add(new SignersListUiModel("","",0));
                break;
        }
    }

    @FXML
    public void actionDeleteButton(MouseEvent event){
        String clickedButtonId = ((Button)(event.getSource())).getId();
        ObservableList<?> selectedRows=null,allItems=null;
        switch (clickedButtonId){
            case "btnDeletePublish":
                allItems = tblPublishList.getItems();
                selectedRows = tblPublishList.getSelectionModel().getSelectedItems();

                break;
            case "btnDeleteFile":
                allItems = tblFileList.getItems();
                selectedRows = tblFileList.getSelectionModel().getSelectedItems();
                break;
            case "btnDeleteSigner":
                allItems = tblSignersList.getItems();
                selectedRows = tblSignersList.getSelectionModel().getSelectedItems();
                break;
        }
        for (Object item: selectedRows)
            allItems.remove(item);
    }

    @FXML
    public void searchDocuments(MouseEvent event) {
        //TODO
        //dataDocumentList.setAll(get)
    }

    @FXML
    public void signDocuments(MouseEvent event) {
        //TODO
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Enter PIN code :");
        inputDialog.setContentText("PIN Code : ");
        Optional<String> enteredPinCode = inputDialog.showAndWait();
        TextField txtPinCode = inputDialog.getEditor();

        try {
            ESignerUserInfo signer = cardService.loginUser(txtPinCode.getText());
            cardService.signDocument(txtPinCode.getText());
            new Alert(Alert.AlertType.INFORMATION, "Succesfully signed").showAndWait();
        }catch (Exception ex){
            new Alert(Alert.AlertType.ERROR, ex.getLocalizedMessage()).showAndWait();
        }

    }
}
