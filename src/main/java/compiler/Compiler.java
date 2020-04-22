package compiler;

import analysis.LexicalError;
import analysis.Lexico;
import analysis.Token;

public class Compiler {
    Lexico lexical = new Lexico();
    public String compile(String code) {
        lexical.setInput(code);
        String str = "";
        try {
            Token t = null;
            while ((t = lexical.nextToken()) != null) {
                str += t.getLexeme();
                str += t.getId();
                str += t.getPosition();
                str += "\n";
            }
        }
        catch (LexicalError e) {
            str = e.getMessage();
            str += e.getPosition();
        }
        return str;
    }
}
