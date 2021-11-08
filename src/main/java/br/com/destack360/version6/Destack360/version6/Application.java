package br.com.destack360.version6.Destack360.version6;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

@SpringBootApplication
@RequestMapping("/")
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);


		FileInputStream serviceAccount =
				null;
		try {
			serviceAccount = new FileInputStream("../SpringBoot-Firebase-Firestore-c0f56580f400ff07245d8ae220ebc3cf1fc600eb/src/main/resources/serviceAccountKey.json");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		FirebaseOptions options = null;
		try {
			options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://destack360-default-rtdb.firebaseio.com")
					.build();
		} catch (IOException e) {
			e.printStackTrace();
		}

		FirebaseApp.initializeApp(options);

	}

}
