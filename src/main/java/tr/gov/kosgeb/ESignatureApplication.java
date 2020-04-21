package tr.gov.kosgeb;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ESignatureApplication {


	public static void main(String[] args) {
		Application.launch(LoginApplication.class,args);
	}

}
