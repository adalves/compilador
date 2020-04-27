package analysis;

public class Token
{
    private int id;
    private String lexeme;
    private int position;

    public Token(int id, String lexeme, int position)
    {
        this.id = id;
        this.lexeme = lexeme;
        this.position = position;
    }

    public final int getId()
    {
        return id;
    }

    public final String getLexeme()
    {
        return lexeme;
    }

    public final int getPosition()
    {
        return position;
    }

    public String getTokenClass() {
        String str = "";
        str = id == 2 ? "identificador" :
                id == 3 ? "constante inteira" :
                id == 4 ? "constante real" :
                id == 5 ? "constante binária" :
                id == 6 ? "constante hexadecimal" :
                id == 7 ? "constante string" :
                id >= 8 && id <= 28 ? "palavra reservada" :
                id >= 29 && id <= 48 ? "símbolo especial" :
                    "classe inválida";
        return str;
    }

    public String toString()
    {
        return id+" ( "+lexeme+" ) @ "+position;
    };
}
