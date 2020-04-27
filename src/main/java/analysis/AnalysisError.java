package analysis;

public class AnalysisError extends Exception
{
    private int position;
    private String symbol;

    public AnalysisError(String symbol, String msg, int position)
    {
        super(msg);
        this.symbol = symbol;
        this.position = position;
    }

    public AnalysisError(String msg, int position)
    {
        super(msg);
        this.position = position;
    }

    public AnalysisError(String msg)
    {
        super(msg);
        this.position = -1;
    }

    public String getSymbol() { return symbol; }

    public int getPosition()
    {
        return position;
    }

    public String toString()
    {
        return super.toString() + ", @ "+position;
    }
}
