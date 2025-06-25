package com.duckycryptography.controller;

import com.duckycryptography.service.DecryptService;
import com.duckycryptography.service.EncryptService;
import com.duckycryptography.service.KeyPairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

//Call the frontend local server
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private KeyPairService keyPairService;

    private final EncryptService encryptService;
    private final DecryptService decryptService;

    @Autowired
    public UserController(EncryptService encryptService, DecryptService decryptService) {
        this.encryptService = encryptService;
        this.decryptService = decryptService;
    }

    @GetMapping("/generate-key-pair")
    public String generateKeyPair() {
        try {
            keyPairService.generateKeyPair();
            return "Successfully generated key pair";
        } catch (Exception e) {
            return "Failed to generate key pair: " + e.getMessage();
        }
    }

    @GetMapping("/download")
    public ResponseEntity<?> downloadFile() {
        try {
            File file = new File(System.getProperty("java.io.tmpdir") + File.separator + "encryptedData.zip");

            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }

            File tempDir = file.getParentFile();

            StreamingResponseBody responseBody = outputStream -> {
                try (InputStream inputStream = new FileInputStream(file)) {
                    inputStream.transferTo(outputStream);
                } finally {
                    FileSystemUtils.deleteRecursively(tempDir);
                }
            };

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(file.length())
                    .body(responseBody);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error:" + e.getMessage());
        }
    }

    @PostMapping("/encrypt/password")
    public ResponseEntity<String> encryptPassword(@RequestParam("file") MultipartFile file,@RequestParam("password") String pass) {
        try {
            encryptService.encryptDataWithPassword(file, pass);

            return ResponseEntity.ok("Encryption complete! Please check the temp folder.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error:" + e.getMessage());
        }
    }

    @PostMapping("/encrypt/keyPair")
    public ResponseEntity<String> encryptKeyPair(@RequestParam("file") MultipartFile file,@RequestParam("publicKey") MultipartFile keyPair) {
        try {
            encryptService.encryptDataWithKeyPair(file, keyPair);

            return ResponseEntity.ok("Encryption complete! Please check the temp folder.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error:" + e.getMessage());
        }
    }

    @PostMapping("/decrypt/password")
    public ResponseEntity<String> decryptPassword(@RequestParam("encryptedFile") MultipartFile encryptedFile,@RequestParam("IV") MultipartFile IV,@RequestParam("salt") MultipartFile salt, @RequestParam("password") String pass) {
        try {
            decryptService.decryptWithPassword(encryptedFile, IV, salt, pass);

            return ResponseEntity.ok("Decryption complete! Please check the temp folder.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error:" + e.getMessage());
        }
    }

    @PostMapping("/decrypt/keyPair")
    public ResponseEntity<String> decrypt(@RequestParam("file") MultipartFile encryptedFile, @RequestParam("keyIV") MultipartFile encryptedKeyIV, @RequestParam("privateKey") MultipartFile privateKeyFile) {
        try {
            decryptService.decryptWithKeyPair(encryptedFile, encryptedKeyIV, privateKeyFile);

            return ResponseEntity.ok("Decryption complete! Please check the temp folder.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
