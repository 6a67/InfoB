import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecoReader extends BufferedReader {

    private int lineNumber = 0;
    private final String expr;
    private String lastLine = null;

    public DecoReader(Reader in, String expr) {
        super(in);
        this.expr = expr;
    }

    /**
     * Returns the next line
     * @return next line or null if there is no next line
     * @throws IOException
     */
    @Override
    public String readLine() throws IOException {
        lastLine = super.readLine();
        if(lastLine != null) lineNumber++;
        return lastLine;
    }

    /**
     * Return the line number of the last line returned by readLine()
     * @return the line number
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * Matches the last read line with the regex pattern and returns how often per line it is matched
     * @return matches per line
     */
    public int getAmountOfMatches() {
        if(expr == null) return 0;
        if(lastLine == null) return 0;
        Matcher matcher = Pattern.compile(expr).matcher(lastLine);
        return (int) matcher.results().count();
    }
}
