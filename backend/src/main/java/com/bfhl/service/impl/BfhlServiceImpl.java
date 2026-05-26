package com.bfhl.service.impl;

import com.bfhl.dto.BfhlRequest;
import com.bfhl.dto.BfhlResponse;
import com.bfhl.service.BfhlService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    private static final String USER_ID = "aditya_yadav_23022005";
    private static final String EMAIL = "adityayadav230093@acropolis.in";
    private static final String ROLL_NUMBER = "0827IT231015";

    @Override
    public BfhlResponse processData(BfhlRequest request) {
        List<String> data = request.getData();

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        long sum = 0;

        for (String item : data) {
            if (isNumber(item)) {
                long num = Long.parseLong(item);
                sum += num;
                if (num % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            } else if (isAlphabet(item)) {
                alphabets.add(item.toUpperCase());
            } else {
                specialCharacters.add(item);
            }
        }

        String concatString = buildConcatString(data);

        BfhlResponse response = new BfhlResponse();
        response.setSuccess(true);
        response.setUserId(USER_ID);
        response.setEmail(EMAIL);
        response.setRollNumber(ROLL_NUMBER);
        response.setOddNumbers(oddNumbers);
        response.setEvenNumbers(evenNumbers);
        response.setAlphabets(alphabets);
        response.setSpecialCharacters(specialCharacters);
        response.setSum(String.valueOf(sum));
        response.setConcatString(concatString);

        return response;
    }

    /**
     * Checks if a string represents a valid integer number.
     */
    private boolean isNumber(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if a string consists entirely of alphabetical characters.
     */
    private boolean isAlphabet(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        for (char c : s.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Builds the concat_string:
     * 1. Extract all individual alphabetical characters from alphabet items in order.
     * 2. Reverse the collected characters.
     * 3. Apply alternating caps (index 0 = uppercase, index 1 = lowercase, ...).
     */
    private String buildConcatString(List<String> data) {
        // Collect all individual alpha characters from items that are purely alphabetical
        List<Character> allChars = new ArrayList<>();
        for (String item : data) {
            if (isAlphabet(item)) {
                for (char c : item.toCharArray()) {
                    allChars.add(c);
                }
            }
        }

        if (allChars.isEmpty()) {
            return "";
        }

        // Reverse
        List<Character> reversed = new ArrayList<>();
        for (int i = allChars.size() - 1; i >= 0; i--) {
            reversed.add(allChars.get(i));
        }

        // Alternating caps: even index → uppercase, odd index → lowercase
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < reversed.size(); i++) {
            char c = reversed.get(i);
            if (i % 2 == 0) {
                sb.append(Character.toUpperCase(c));
            } else {
                sb.append(Character.toLowerCase(c));
            }
        }

        return sb.toString();
    }
}
