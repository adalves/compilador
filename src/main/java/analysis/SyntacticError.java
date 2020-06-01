package analysis;

public class SyntacticError extends AnalysisError
{
    public SyntacticError(String symbol, String msg, int position)
    {
        super(symbol, msg, position);
    }

    public SyntacticError(String msg, int position)
	 {
        super(msg, position);
    }

    public SyntacticError(String msg)
    {
        super(msg);
    }
}
