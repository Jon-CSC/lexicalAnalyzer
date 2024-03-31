package com.espinal.lexicalanalyzer;

import java.util.Scanner;

/**
 * A lexical analyzer
 *
 *
 * @author Jonathan Espinal
 */
public class LexicalAnalyzer {

    /**
     * Check if supplied character is a delimiter.
     *
     * @param c The character to check
     * @return True if delimiter, false if not.
     */
    public static boolean isDelimiter(char c) {
        char[] delimiter = {'(', ')', '{', '}'};
        for (int i = 0; i < delimiter.length; i++) {
            if (delimiter[i] == c) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if supplied string is a keyword.
     *
     * @param s The string to check
     * @return True if keyword, false if not.
     */
    public static boolean isKeyword(String s) {
        String[] keyword = {"return", "int"};
        for (int i = 0; i < keyword.length; i++) {
            if (keyword[i].equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if supplied string is an operator.
     *
     * @param s The string to check
     * @return True if keyword, false if not.
     */
    public static boolean isOperator(String s) {
        String[] operator = {"<<", "::"};
        for (int i = 0; i < operator.length; i++) {
            if (operator[i].equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if supplied string is a number.
     *
     * @param s The string to check
     * @return True if number, false if not.
     */
    public static boolean isNumeric(String s) {
        // Try to parse an integer value, if not possible, then it is not a number
        try {
            int num = Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        char seperator = ';';
        String testString = " int main() { std::cout << \"Hello world\";return 0;}";
        String token = "";
        Scanner scan = new Scanner(testString);

        char nextChar;
        while (scan.hasNext()) {
            nextChar = scan.findInLine(".").charAt(0);
            if (nextChar == ':') {
                if (!token.equals("") && !token.equals(":")) {
                    System.out.println(token + " is an IDENTIFIER");
                    token = "";
                }
            }
            if (nextChar == '<') {
                if (!token.equals("") && !token.equals("<")) {
                    System.out.println(token + " is an IDENTIFIER");
                    token = "";
                }
            }

            if (isDelimiter(nextChar)) {
                if (!token.equals("")) {
                    System.out.println(token + " is an IDENTIFIER");
                }
                System.out.println(nextChar + " is a DELIMITER");
                token = "";
            } else if (nextChar == seperator) {
                System.out.println(nextChar + " is a SEPERATOR");
                token = "";
            } else if (nextChar != '\n' && nextChar != ' ') {
                token += nextChar;
            }

            if (isOperator(token)) {
                System.out.println(token + " is an OPERATOR");
                token = "";
            } else if (isKeyword(token)) {
                System.out.println(token + " is a KEYWORD");
                token = "";
            } else if (isNumeric(token)) {
                System.out.println(token + " is a INT_LIT");
                token = "";
            } else if( token.startsWith("\"") && token.endsWith("\"") && token.length() > 1){
                System.out.println(token + " is a STRING_LIT");
                token = "";
            }
        }
    }
}
