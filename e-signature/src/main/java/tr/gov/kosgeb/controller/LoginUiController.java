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
import tr.gov.kosgeb.config.Constants;
import tr.gov.kosgeb.config.ESignatureAppContext;
import tr.gov.kosgeb.model.ESignerUserInfo;
import tr.gov.kosgeb.service.SmartCardService;
import tr.gov.tubitak.uekae.esya.api.common.ESYAException;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

/*

implement  => interface diğerinterface veya class implement

1.eğer class implement
 */
@Controller
public class LoginUiController  implements Initializable {

    @Autowired
    private SmartCardService cardService;
    @FXML  //
    private Button btnSignin;
    @FXML
    private Button btnTr;
    @FXML
    private Button btnEn;
    @FXML
    private Button btnCheckSmarCardInfo;
    @FXML
    private PasswordField txtPinCode;
    @FXML
    private Label lblErrors;
    @FXML
    private Label lblCitizenshipNumber;
    @FXML
    private Label lblCitizenshipNumberTitle;
    @FXML
    private Label lblNameSurname;
    @FXML
    private Label lblNameSurnameTitle;
    @FXML
    private Label lblIssuingAuthority;
    @FXML
    private Label lblIssuingAuthorityTitle;
    @FXML
    private Label lblStartDAte;
    @FXML
    private Label lblStartDAteTitle;
    @FXML
    private Label lblEndDate;
    @FXML
    private Label lblEndDateTitle;

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
    public void changeLanguage(MouseEvent event) throws IOException {
        Locale locale =new Locale("en","en");

        ResourceBundle bundle = ResourceBundle.getBundle("messages",locale);
        if(event.getSource() == btnTr)
        {

             locale =new Locale("tr","tr");

             bundle = ResourceBundle.getBundle("messages",locale);

            Constants.LANGUAGE ="tr";

        }


        lblNameSurnameTitle.setText(bundle.getString("lblNameSurnameTitle"));
        lblCitizenshipNumberTitle.setText(bundle.getString("lblCitizenshipNumberTitle"));
        lblIssuingAuthorityTitle.setText(bundle.getString("lblIssuingAuthorityTitle"));
        btnSignin.setText(bundle.getString("btnSignin"));
        btnCheckSmarCardInfo.setText(bundle.getString("btnCheckSmarCardInfo"));
        lblStartDAteTitle.setText(bundle.getString("lblStartDAteTitle"));
        lblEndDateTitle.setText(bundle.getString("lblEndDateTitle"));

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

            if(Constants.LANGUAGE == "tr")
                lblNameSurnameTitle.setText("Ad Soyad");
            else
                lblNameSurnameTitle.setText("Name-Surname");

            ESignerUserInfo userInfo = cardService.getESignerUserInfo();
            if (userInfo==null){
                lblErrors.setText("Check your smart card. Sure to be slotted. then press the check button");
                return;
            }
            setLabels(userInfo);
        }catch (ESYAException e){
            lblErrors.setText(e.getLocalizedMessage());
        }
        catch (Exception e) {
            lblErrors.setText(e.getLocalizedMessage());
        }
    }
    private void setLabels(ESignerUserInfo userInfo) {
        lblNameSurname.setText(userInfo.getNameSurname());
        lblCitizenshipNumber.setText(userInfo.getCitizenshipNumber());
        lblIssuingAuthority.setText(userInfo.getIssuingAuthority());
        lblEndDate.setText(userInfo.StartDate());
        lblStartDAte.setText(userInfo.EndDate());
    }
}
