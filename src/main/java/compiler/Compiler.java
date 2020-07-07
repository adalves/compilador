package compiler;

import analysis.*;

public class Compiler {
    Lexical lexical = new Lexical();
    Syntactic syntactic = new Syntactic();
    Semantic semantic = new Semantic();

    public String compile(String text) {
        if (text.isEmpty()) {
            return "nenhum programa para compilar";
        }
        semantic.clear();
        lexical.setInput(text);
        String result ="Programa compilado com sucesso";
        try {
            syntactic.parse(lexical, semantic);
        } catch (LexicalError e) {
            result = "Erro na linha " + getLine(text, e.getPosition()) + " - ";
            if (e.getMessage().contains("símbolo inválido")) result += e.getSymbol() + " ";
            result += e.getMessage();
        } catch (SyntacticError e) {
            String symbol = e.getSymbol();
            if (symbol.equals("$")) symbol = "EOF";
            result = "Erro na linha " + getLine(text, e.getPosition()) + " - " +
                    "encontrado " + symbol + " esperado " + e.getMessage();
        } catch (SemanticError e) {
            String symbol = e.getSymbol();
            result = "Erro na linha " + getLine(text, e.getPosition()) + " - " + e.getMessage();
        }

        return result;
    }

    private int getLine(String text, int pos) {
        int line = 1;
        int realPos = text.length() == pos ? pos - 1 : pos;
        for (int i = 0; i <= realPos; ++i) {
            if(text.charAt(i) == '\n'){
                ++line;
            }
        }
        return line;
    }

    public String getCompiledCode() {
        return semantic.getCode();
    }
}
