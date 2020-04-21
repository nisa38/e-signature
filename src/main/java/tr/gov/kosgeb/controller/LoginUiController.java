package tr.gov.kosgeb.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import tr.gov.kosgeb.config.ESignatureAppContext;
import tr.gov.kosgeb.model.ESignerUserInfo;
import tr.gov.kosgeb.service.SmartCardService;
import tr.gov.tubitak.uekae.esya.api.common.ESYAException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class LoginUiController  implements Initializable {

    @Autowired
    private SmartCardService cardService;

    @FXML
    private Button btnSignin;
    @FXML
    private Button btnCheckSmarCardInfo;

    @FXML
    private PasswordField txtPinCode;

    @FXML
    private Label lblErrors;
    @FXML
    private Label lblCitizenshipNumber;
    @FXML
    private Label lblNameSurname;
    @FXML
    private Label lblIssuingAuthority;
    @FXML
    private Label lblStartDAte;
    @FXML
    private Label lblEndDate;

    public ApplicationContext context;

    @Value("classpath:/fxml/MainPage.fxml")
    private Resource mainPageUiResource;

    public LoginUiController(ApplicationContext context) {
        this.context = context;
    }

    @FXML
    private void checkSmartCard(MouseEvent event) {
        try {
//            cardService = new SmartCardService();
            ESignerUserInfo userInfo = cardService.getESignerUserInfo();
            if (userInfo==null){
                lblErrors.setText("Check your smart card. Sure to be slotted. then press the check button");
                return;
            }
            setLabels(userInfo);
        }catch (ESYAException e){
            lblErrors.setText(e.getLocalizedMessage());
        }
        catch (Exception e){
            lblErrors.setText(e.getLocalizedMessage());
        }
    }

    @FXML
    public void signInAction(MouseEvent event) throws IOException {

        if (event.getSource() == btnSignin) {
            //login here
            try {
                ESignatureAppContext.eSignerUserInfo = cardService.loginUser(txtPinCode.getText().toString());

                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(mainPageUiResource.getURL());
                fxmlLoader.setControllerFactory(context::getBean);

                Parent parent = fxmlLoader.load();

                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.show();
            }catch (Exception ex){
                lblErrors.setText(ex.getLocalizedMessage());
            }

        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ESignerUserInfo userInfo = cardService.getESignerUserInfo();
            if (userInfo==null){
                lblErrors.setText("Check your smart card. Sure to be slotted. then press the check button");
                return;
            }
            setLabels(userInfo);
        }catch (ESYAException e){
            lblErrors.setText(e.getLocalizedMessage());
        }
        catch (Exception e){
            lblErrors.setText(e.getLocalizedMessage());
        }

    }

    private void setLabels(ESignerUserInfo userInfo){
        lblNameSurname.setText(userInfo.getNameSurname());
        lblCitizenshipNumber.setText(userInfo.getCitizenshipNumber());
        lblIssuingAuthority.setText(userInfo.getIssuingAuthority());
        lblEndDate.setText(userInfo.StartDate());
        lblStartDAte.setText(userInfo.EndDate());
    }
}
