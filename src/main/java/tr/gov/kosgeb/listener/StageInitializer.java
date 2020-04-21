package tr.gov.kosgeb.listener;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import tr.gov.kosgeb.events.StageReadyEvent;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

    @Value("classpath:/fxml/Login.fxml")
    private Resource loginUiResource;
    public ApplicationContext context;

    public StageInitializer(ApplicationContext context) {
        this.context=context;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(loginUiResource.getURL());
            fxmlLoader.setControllerFactory(context::getBean); //Spring now FXML Controller Factory
//            fxmlLoader.setResources(resourceBundle);

            Parent parent = fxmlLoader.load();

            Stage stage = event.getStage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("can not be loaded login.fxml\n" + e.getMessage());
        }

    }

}
