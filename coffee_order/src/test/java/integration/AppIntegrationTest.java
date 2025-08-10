package integration;

import app.Main;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import service.FormattingService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AppIntegrationTest {

    private static String nfc(String s) {
        return Normalizer.normalize(s, Normalizer.Form.NFC);
    }

    private static String runMainWithInput(String input) {
        InputStream oldIn = System.in;
        PrintStream oldOut = System.out;

        ByteArrayInputStream fakeIn =
                new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));

        ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
        PrintStream fakeOut =
                new PrintStream(outBytes, true, StandardCharsets.UTF_8);

        try {
            System.setIn(fakeIn);
            System.setOut(fakeOut);
            Main.main(new String[0]);
        } finally {
            System.setIn(oldIn);
            System.setOut(oldOut);
        }

        return outBytes.toString(StandardCharsets.UTF_8);
    }

    @DisplayName("E2E: each valid selection runs and prints preparation")
    @ParameterizedTest
    @CsvSource({
            "1,Espresso",
            "2,Double Espresso",
            "3,Cappuccino",
            "4,Caffe Latte",
            "5,Mocha",
            "6,Americano",
            "7,Hot Water"
    })
    void e2e_validSelections(String choice, String drinkName) {
        String out = nfc(runMainWithInput(choice + "\n"));

        FormattingService fs = new FormattingService();
        String prompt = nfc(fs.formatPrompt());
        String thanks = nfc(fs.formatThanks());

        assertTrue(out.contains("1. Espresso (20"), "Menu should be printed");
        assertTrue(out.contains(prompt), "Prompt should be printed");
        assertTrue(out.contains(thanks), "Thank-you line should be printed");
        assertTrue(out.contains(drinkName + " seçtiniz."), "Preparation uses chosen drink");
        assertTrue(out.contains("Afiyet Olsun."), "Closing phrase present");
    }

    @Test
    void e2e_invalidThenValid() {
        String out = nfc(runMainWithInput("abc\n9\n3\n"));

        assertTrue(out.contains("Lütfen geçerli bir sayı giriniz."),  "Warn on non-numeric input");
        assertTrue(out.contains("Geçersiz seçim, tekrar deneyin."),  "Warn on out-of-range input");

        FormattingService fs = new FormattingService();
        assertTrue(out.contains(nfc(fs.formatThanks())), "Thank after valid selection");
        assertTrue(out.contains("Cappuccino seçtiniz."), "Prepares Cappuccino");
    }
}
