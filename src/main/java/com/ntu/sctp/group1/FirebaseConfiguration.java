package com.ntu.sctp.group1;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.ntu.sctp.group1.DataTransferObject.FirebaseCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
public class FirebaseConfiguration {

    @Value("classpath:service-account.json")
    Resource serviceAccount;

    @Bean
    public FirebaseAuth firebaseAuth() throws IOException {

        FirebaseCredentials firebaseCredentials = new FirebaseCredentials();


       var options = FirebaseOptions.builder()
               .setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
               .build();

       var firebaseApp = FirebaseApp.initializeApp(options);

       return FirebaseAuth.getInstance(firebaseApp);
    }
}
