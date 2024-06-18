/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Arrays;
import java.util.List;

/**
 *
 *  @author T490 U88
 */
public class LookForLexema {
    static String DIGITOS = "[0-9]+";
    
    static List<String> reservada = Arrays.asList("div", "or", "and", "not", "if", "then", "else", "of", "Record", "while", "do", "begin", "end", "writeln",
            "readln","read","const", "write", "var", "array", "function", "procedure", "program", "true", "false", "char", "integer", "boolean", "Uses");
    
    static String  INDENTIFICADOR = "[a-zA-Z_]+([0-9_]*[a-zA-Z]*)*";

    private static boolean Reservada(String lexema) {
        for (int i = 0; i < reservada.size() - 1; i++) {
            if (reservada.get(i).equalsIgnoreCase(lexema)) {
                return true;
            }
        }
        return false;
    }

    public static boolean digito(String lexema) {
        return (lexema.matches(DIGITOS));
    }

    static boolean identificador(String lexema) {
        return (lexema.matches(INDENTIFICADOR));
    }

    public String validar(String str1) {
        String lexema = "";  
        if (str1.endsWith(" ")) {
            lexema = lexema.substring(0, str1.length() - 1);
        } else {
            lexema = str1;
        }
        if (Reservada(lexema)) {
            return "Palavra Reservada";
        } else if (digito(lexema)) {
            return "Digito";
        } else if (identificador(lexema)) {
            return "Identificador";
        } else {
            return "Erro";
        }
    }
}
