package translation;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CanadaTranslatorTest {

    @Test
    void testGetLanguageCodes() {
        CanadaTranslator translator = new CanadaTranslator();
        List<String> codes = translator.getLanguageCodes();
        assertTrue(codes.contains("es"));
        assertTrue(codes.contains("fr"));
    }

    @Test
    void testTranslateCanada() {
        CanadaTranslator translator = new CanadaTranslator();

        assertEquals("Canada", translator.translate("can", "en"));
        assertEquals("Kanada", translator.translate("can", "de"));
        assertEquals("加拿大", translator.translate("can", "zh"));
        assertEquals("Canadá", translator.translate("can", "es"));
        assertEquals("Canada", translator.translate("can", "fr"));
    }

    @Test
    void testInvalidInputs() {
        CanadaTranslator translator = new CanadaTranslator();

        assertNull(translator.translate("usa", "en")); // wrong country
        assertNull(translator.translate("can", "ru")); // unsupported language
    }
}

