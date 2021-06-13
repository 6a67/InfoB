package AltLösungen;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CustomReader extends LineNumberReader {

    private int amount_of_matches = 0;
    private String express = null;
    /**
     * Create a new line-numbering reader, using the default input-buffer
     * size.
     *
     * @param in A Reader object to provide the underlying stream
     */
    public CustomReader(Reader reader, String express) {
        super(reader);
        this.express = express;
    }

    /**
     * Get the current line number.
     *
     * @return The current line number
     * @see #setLineNumber
     */
    @Override
    public int getLineNumber() {
        return super.getLineNumber();
    }

    /**
     * Read a line of text.  Whenever a <a href="#lt">line terminator</a> is
     * read the current line number is incremented.
     *
     * @return A String containing the contents of the line, not including
     * any <a href="#lt">line termination characters</a>, or
     * {@code null} if the end of the stream has been reached
     * @throws IOException If an I/O error occurs
     */
    @Override
    public String readLine() throws IOException {
        return super.readLine();
    }


    public int getAmountOfMatches() {
        Pattern p = Pattern.compile(this.express);
        try {
            Matcher m = p.matcher(this.readLine());
            if (m.matches()) {
                this.amount_of_matches++;
            }
        }
        catch (IOException e) {
            System.err.println("Error while reading line in getAmountofMatches method");
        }
        return this.amount_of_matches;
    }
}
