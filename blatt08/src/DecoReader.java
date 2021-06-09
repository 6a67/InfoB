import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecoReader extends BufferedReader {

    private int lineNumber = 0;
    private String expr = null;
    private String lastLine = null;

    public DecoReader(Reader in, String expr) {
        super(in);
        this.expr = expr;
    }

    @Override
    public String readLine() throws IOException {
        lineNumber++;
        lastLine = super.readLine();
        return lastLine;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getAmountOfMatches() {
        if(expr == null) return 0;
        if(lastLine == null) return 0;
        Matcher matcher = Pattern.compile(expr).matcher(lastLine);
        return (int) matcher.results().count();
    }
}
