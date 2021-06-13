package AltLösungen;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class MainReader {
    public static void main (String[] args) {
        CustomReader c_reader = new CustomReader(new InputStreamReader(System.in), args[0]);
        String curr_line = null;
        try {
            curr_line = c_reader.readLine();
        } catch (IOException e) {
            System.err.println("Error while reading line");
        }

        while (curr_line != null) {
            if (c_reader.getAmountOfMatches() >= 1) {
                System.out.println("Matching expression at line " + c_reader.getLineNumber() +
                        " with currently " + c_reader.getAmountOfMatches() + " amount of matches");
                System.out.println(curr_line);
            }

            try {
                curr_line = c_reader.readLine();
            } catch (IOException e) {
                System.err.println("Error while reading line in while loop");
            }
        }
    }
}
