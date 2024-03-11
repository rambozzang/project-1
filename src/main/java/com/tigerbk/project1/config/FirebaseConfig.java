package com.tigerbk.project1.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

	@PostConstruct
	public void init() {
		try {			
			FirebaseOptions options = FirebaseOptions.builder().setCredentials(
					GoogleCredentials.fromStream(new ClassPathResource("./serviceAccountKey.json").getInputStream()))
					.build();
			FirebaseApp.initializeApp(options);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
