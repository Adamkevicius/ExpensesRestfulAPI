package lt.viko.eif.madamkevicius.expansesapi.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class SecretKeyService {

    public String getSecretKey() {
        String fileName = "secret-key.txt";
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Error while reading a file" + e);
        }
    }
}
