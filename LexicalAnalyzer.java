/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lexicalanalyzer;

import java.util.Scanner;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalAnalyzer {
    private static final HashSet<String> datatype = new HashSet<>();
    private static final HashSet<String> keywords = new HashSet<>();
    private static final HashSet<String> operators = new HashSet<>();
    private static final HashSet<String> punctuators = new HashSet<>();
    private static final Pattern identifierPattern = Pattern.compile("[a-zA-Z_]\\w*");
    private static final Pattern constantPattern = Pattern.compile("\\d+(\\.\\d+)?");

    static {
        //Data type Words
        datatype.add("int");
        datatype.add("double");
        datatype.add("float");
        datatype.add("boolean");
        datatype.add("char");

        // Java reserved keywords
        keywords.add("abstract");
        keywords.add("assert");
        keywords.add("break");
        keywords.add("byte");
        keywords.add("case");
        keywords.add("catch");
        keywords.add("class");
        keywords.add("const");
        keywords.add("continue");
        keywords.add("default");
        keywords.add("do");
        keywords.add("else");
        keywords.add("enum");
        keywords.add("extends");
        keywords.add("final");
        keywords.add("finally");
        keywords.add("for");
        keywords.add("if");
        keywords.add("implements");
        keywords.add("import");
        keywords.add("instanceof");
        keywords.add("interface");
        keywords.add("long");
        keywords.add("native");
        keywords.add("new");
        keywords.add("package");
        keywords.add("private");
        keywords.add("protected");
        keywords.add("public");
        keywords.add("return");
        keywords.add("short");
        keywords.add("static");
        keywords.add("strictfp");
        keywords.add("super");
        keywords.add("switch");
        keywords.add("synchronized");
        keywords.add("this");
        keywords.add("throw");
        keywords.add("throws");
        keywords.add("transient");
        keywords.add("try");
        keywords.add("void");
        keywords.add("volatile");
        keywords.add("while");

        // Example operators
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
        operators.add("=");
        operators.add("==");
        operators.add("!=");
        operators.add("<");
        operators.add(">");
        operators.add("<=");
        operators.add(">=");

        // Example punctuators
        punctuators.add(",");
        punctuators.add(";");
        punctuators.add("(");
        punctuators.add(")");
        punctuators.add("{");
        punctuators.add("}");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your code (type 'end' to finish):");
        int lineNum = 0;
        while (scanner.hasNextLine()) {
            lineNum++;
            String line = scanner.nextLine().trim();
            if (line.equals("end")) break;
            tokenizeLine(line, lineNum);
        }
        scanner.close();
    }

    private static void tokenizeLine(String line, int lineNum) {
        line = line.trim();
        while (!line.isEmpty()) {
            Matcher identifierMatcher = identifierPattern.matcher(line);
            if (identifierMatcher.find()) {
                String token = identifierMatcher.group();
                if (keywords.contains(token)) {
                    System.out.println("KEYWORD: " + token + " at line " + lineNum);
                }
                else if (datatype.contains(token)) {
                    System.out.println("DATA TYPE: " + token + " at line " + lineNum);
                }else {
                    System.out.println("IDENTIFIER: " + token + " at line " + lineNum);
                }
                line = line.substring(identifierMatcher.end()).trim();
            } else {
                Matcher constantMatcher = constantPattern.matcher(line);
                if (constantMatcher.find()) {
                    String token = constantMatcher.group();
                    System.out.println("CONSTANT: " + token + " at line " + lineNum);
                    line = line.substring(constantMatcher.end()).trim();
                } else {
                    String firstChar = line.substring(0, 1);
                    if (operators.contains(firstChar) || punctuators.contains(firstChar)) {
                        System.out.println("OPERATOR/PUNCTUATOR: " + firstChar + " at line " + lineNum);
                        line = line.substring(1).trim();
                    } else {
                        System.out.println("Error: Invalid token '" + line.split("\\s+")[0] + "' at line " + lineNum);
                        return;
                    }
                }
            }
        }
    }
}
