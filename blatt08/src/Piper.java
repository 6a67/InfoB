import java.io.IOException;
import java.io.InputStreamReader;

public class Piper {

    public static void main(String[] args) {
        DecoReader decoReader = new DecoReader(new InputStreamReader(System.in), args[0]);

        String line = null;
        try {
            line = decoReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(line != null) {
            int matches = decoReader.getAmountOfMatches();

            if(matches >= 1) {
                System.out.printf("%-32s","Line " + decoReader.getLineNumber() + " with " + matches + " matches:");
                System.out.println(line);
            }

            try {
                line = decoReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
