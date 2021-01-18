package com.string;

import java.util.HashSet;
import java.util.Set;

public class PalindromeSubStrings {
    //Brute force method : O(n^3).
    private static boolean isPalindrome(String input) {
        StringBuilder plain = new StringBuilder(input);
        StringBuilder reverse = plain.reverse();
        return (reverse.toString()).equals(input);
    }

    public static Set<String> findAllPalindromesUsingBruteForceApproach(String input) {
        Set<String> palindromes = new HashSet<>();
        for (int i = 0; i < input.length(); i++) {
            for (int j = i + 1; j <= input.length(); j++) {
                if (isPalindrome(input.substring(i, j))) {
                    palindromes.add(input.substring(i, j));
                }
            }
        }
        return palindromes;
    }

    // Consider each character as the pivot and expand in both directions to find palindromes. O(n^2)
    // We'll only expand if the characters on the left and right side match, qualifying the string to be a palindrome.
    // Otherwise, we continue to the next character.
    public static Set<String> findAllPalindromesUsingCenter(String input) {
        Set<String> palindromes = new HashSet<>();
        for (int i = 0; i < input.length(); i++) {
            palindromes.addAll(findPalindromes(input, i, i + 1));
            palindromes.addAll(findPalindromes(input, i, i));
        }
        return palindromes;
    }

    private static Set<String> findPalindromes(String input, int low, int high) {
        Set<String> result = new HashSet<>();
        while (low >= 0 && high < input.length() && input.charAt(low) == input.charAt(high)) {
            result.add(input.substring(low, high + 1));
            low--;
            high++;
        }
        return result;
    }

    public static void main(String[] args) {
        //Brute Force
        String str = "aabbbaa";
        Set<String> stringSet = findAllPalindromesUsingBruteForceApproach(str);
        System.out.println("Total palindrome substrings: " + stringSet.size());

        //Centrailized method
        str = "aabbbaa";
        stringSet = findAllPalindromesUsingCenter(str);
        System.out.println("Total palindrome substrings: " + stringSet.size());
    }
}
