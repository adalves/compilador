package analysis;

public class LexicalError extends AnalysisError
{
    public LexicalError(String symbol, String msg, int position)
    {
        super(symbol, msg, position);
    }

    public LexicalError(String msg, int position)
	 {
        super(msg, position);
    }

    public LexicalError(String msg)
    {
        super(msg);
    }
}
