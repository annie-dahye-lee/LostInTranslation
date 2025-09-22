package translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * This class provides the services of: <br/>
 * - converting language codes to their names <br/>
 * - converting language names to their codes
 */
public class LanguageCodeConverter {

    private final Map<String, String> languageCodeToLanguage = new HashMap<>();
    private final Map<String, String> languageToLanguageCode = new HashMap<>();

    /**
     * Default constructor that loads the language codes from "language-codes.txt"
     * in the resources folder.
     */
    public LanguageCodeConverter() {
        this("language-codes.txt");
    }

    /**
     * Overloaded constructor that allows us to specify the filename to load the language code data from.
     * @param filename the name of the file in the resources folder to load the data from
     * @throws RuntimeException if the resources file can't be loaded properly
     */
    public LanguageCodeConverter(String filename) {

        try {
            List<String> lines = Files.readAllLines(Paths.get(getClass()
                    .getClassLoader().getResource(filename).toURI()));

            Iterator<String> iterator = lines.iterator();
            iterator.next(); // skip the first line
            while (iterator.hasNext()) {
                String line = iterator.next();
                // Find the position of the last space or tab; this space separates the full names from the codes.

                // Iterate line from right to left.
                int pos_break = line.length() - 1;

                for (; pos_break != -1; pos_break--) {

                    // If the current position is a space or tab, this is the last instance of space or tab, so break the loop & record the value.
                    if (line.charAt(pos_break) == ' ' || line.charAt(pos_break) == '\t') {
                        break;
                    }
                }

                // Get the trimmed substrings of the full names & codes with pos_break.
                String lang = line.substring(0, pos_break).trim();
                String code = line.substring(pos_break).trim();

                // Populate languageCodeToLanguage & languageToLanguageCode with these.
                this.languageCodeToLanguage.put(code, lang);
                this.languageToLanguageCode.put(lang, code);
            }

        } catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Return the name of the language for the given language code.
     * @param code the 2-letter language code
     * @return the name of the language corresponding to the code
     */
    public String fromLanguageCode(String code) {
        return this.languageCodeToLanguage.get(code);
    }

    /**
     * Return the code of the language for the given language name.
     * @param language the name of the language
     * @return the 2-letter code of the language
     */
    public String fromLanguage(String language) {
        return this.languageToLanguageCode.get(language);
    }

    /**
     * Return how many languages are included in this language code converter.
     * @return how many languages are included in this language code converter.
     */
    public int getNumLanguages() {
        return languageCodeToLanguage.size();
    }
}
