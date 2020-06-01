package analysis;

public class SemanticError extends AnalysisError
{
    public SemanticError(String symbol, String msg, int position)
    {
        super(symbol, msg, position);
    }

    public SemanticError(String msg, int position)
	 {
        super(msg, position);
    }

    public SemanticError(String msg)
    {
        super(msg);
    }
}
