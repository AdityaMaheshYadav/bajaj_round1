package com.bfhl.service.impl;

import com.bfhl.dto.BfhlRequest;
import com.bfhl.dto.BfhlResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BfhlServiceImplTest {

    private BfhlServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new BfhlServiceImpl();
    }

    @Test
    @DisplayName("Example A: Mixed data with numbers, alphabets, and special characters")
    void testExampleA() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("a", "1", "334", "4", "R", "$"));
        BfhlResponse response = service.processData(request);

        assertTrue(response.isSuccess());
        assertEquals("aditya_yadav_23022005", response.getUserId());
        assertEquals(List.of("1"), response.getOddNumbers());
        assertEquals(List.of("334", "4"), response.getEvenNumbers());
        assertEquals(List.of("A", "R"), response.getAlphabets());
        assertEquals(List.of("$"), response.getSpecialCharacters());
        assertEquals("339", response.getSum());
        assertEquals("Ra", response.getConcatString());
    }

    @Test
    @DisplayName("Example B: Multiple numbers, alphabets, and special characters")
    void testExampleB() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("2", "a", "y", "4", "&", "-", "*", "5", "92", "b"));
        BfhlResponse response = service.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(List.of("5"), response.getOddNumbers());
        assertEquals(List.of("2", "4", "92"), response.getEvenNumbers());
        assertEquals(List.of("A", "Y", "B"), response.getAlphabets());
        assertEquals(List.of("&", "-", "*"), response.getSpecialCharacters());
        assertEquals("103", response.getSum());
        assertEquals("ByA", response.getConcatString());
    }

    @Test
    @DisplayName("Example C: Multi-character alphabetical strings")
    void testExampleC() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("A", "ABCD", "DOE"));
        BfhlResponse response = service.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(Collections.emptyList(), response.getOddNumbers());
        assertEquals(Collections.emptyList(), response.getEvenNumbers());
        assertEquals(List.of("A", "ABCD", "DOE"), response.getAlphabets());
        assertEquals(Collections.emptyList(), response.getSpecialCharacters());
        assertEquals("0", response.getSum());
        assertEquals("EoDdCbAa", response.getConcatString());
    }

    @Test
    @DisplayName("Empty data array should return empty lists and zero sum")
    void testEmptyData() {
        BfhlRequest request = new BfhlRequest(Collections.emptyList());
        BfhlResponse response = service.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    @DisplayName("Only numbers should return correct odd/even categorization and sum")
    void testOnlyNumbers() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("1", "2", "3", "4", "5"));
        BfhlResponse response = service.processData(request);

        assertEquals(List.of("1", "3", "5"), response.getOddNumbers());
        assertEquals(List.of("2", "4"), response.getEvenNumbers());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("15", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    @DisplayName("Only special characters should be categorized correctly")
    void testOnlySpecialCharacters() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("@", "#", "$", "%"));
        BfhlResponse response = service.processData(request);

        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertEquals(List.of("@", "#", "$", "%"), response.getSpecialCharacters());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    @DisplayName("Single alphabet character should have correct concat_string")
    void testSingleAlphabet() {
        BfhlRequest request = new BfhlRequest(List.of("z"));
        BfhlResponse response = service.processData(request);

        assertEquals(List.of("Z"), response.getAlphabets());
        assertEquals("Z", response.getConcatString());
    }

    @Test
    @DisplayName("Negative numbers should be categorized as odd correctly")
    void testNegativeNumbers() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("-3", "-2", "0"));
        BfhlResponse response = service.processData(request);

        assertEquals(List.of("-3"), response.getOddNumbers());
        assertEquals(List.of("-2", "0"), response.getEvenNumbers());
        assertEquals("-5", response.getSum());
    }

    @Test
    @DisplayName("User identity fields should be populated correctly")
    void testUserIdentity() {
        BfhlRequest request = new BfhlRequest(List.of("1"));
        BfhlResponse response = service.processData(request);

        assertEquals("aditya_yadav_23022005", response.getUserId());
        assertEquals("adityayadav230093@acropolis.in", response.getEmail());
        assertEquals("0827IT231015", response.getRollNumber());
    }
}
