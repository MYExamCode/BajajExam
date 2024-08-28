package com.exam.controller;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@RestController
public class MyController {

    private static final int RANDOM_STRING_LENGTH = 8;

    @GetMapping("/create/user")
    public String createUser(@RequestParam String prnNumber, @RequestParam String filePath) {
        prnNumber = prnNumber.toLowerCase();
        String destinationValue = extractDestinationValue(filePath);
        if (destinationValue == null) {
            return "Key 'destination' not found in JSON file.";
        }

        String randomString = generateRandomString(RANDOM_STRING_LENGTH);
        String concatenatedString = prnNumber + destinationValue + randomString;

        String md5Hash = generateMD5Hash(concatenatedString);
        return md5Hash + ";" + randomString;
    }

    private String extractDestinationValue(String jsonFilePath) {
        try (FileReader reader = new FileReader(jsonFilePath)) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject jsonObject = new JSONObject(tokener);

            return findDestinationValue(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String findDestinationValue(JSONObject jsonObject) {
        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);
            if (key.equalsIgnoreCase("destination")) {
                return value.toString();
            } else if (value instanceof JSONObject) {
                String result = findDestinationValue((JSONObject) value);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    private String generateMD5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}

