package br.com.destack360.br.com.destack360.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FirebaseInitialization {

    FileInputStream serviceAccount;

    @PostConstruct
    public void initialization(){

            this.serviceAccount = null;

        try {

            this.serviceAccount = new FileInputStream("../br.com.destack360/src/main/resources/serviceAccountKey.json");
          FirebaseOptions  options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://destack360-default-rtdb.firebaseio.com")
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
