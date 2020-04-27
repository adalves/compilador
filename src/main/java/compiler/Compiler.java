package compiler;

import analysis.LexicalError;
import analysis.Lexico;
import analysis.Token;

public class Compiler {
    Lexico lexical = new Lexico();

    public String compile(String text) {
        lexical.setInput(text);
        boolean containsLexeme = false;
        String result = String.format("%-10s %-25s %s %n", "linha", "classe", "lexema");
        try {
            Token t;
            while ((t = lexical.nextToken()) != null) {
                result += String.format("%-10s %-25s %s %n", getLine(text, t.getPosition()), t.getTokenClass(), t.getLexeme());
                containsLexeme = true;
            }
            result += "Programa compilado com sucesso";
        }
        catch (LexicalError e) {
            result = "Erro na linha " + getLine(text, e.getPosition()) + " - ";
            if (e.getMessage().contains("símbolo inválido")) result += e.getSymbol() + " ";
            result += e.getMessage();
            containsLexeme = true;
        }
        if (!containsLexeme) result = "nenhum programa para compilar";
        return result;
    }

    private int getLine(String text, int pos) {
        int line = 1;
        for (int i = 0; i <= pos; ++i) {
            if(text.charAt(i) == '\n'){
                ++line;
            }
        }
        return line;
    }
}
