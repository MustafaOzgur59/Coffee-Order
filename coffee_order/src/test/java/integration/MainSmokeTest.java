package integration;

import app.Main;
import org.junit.jupiter.api.Test;
import service.FormattingService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainSmokeTest {

    @Test
    void main_runs_withValidSelection() {
        InputStream oldIn = System.in;
        PrintStream oldOut = System.out;

        ByteArrayInputStream in =
                new ByteArrayInputStream("6\n".getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outBytes, true, StandardCharsets.UTF_8);

        try {
            System.setIn(in);
            System.setOut(out);
            Main.main(new String[0]);
        } finally {
            System.setIn(oldIn);
            System.setOut(oldOut);
        }

        String output = outBytes.toString(StandardCharsets.UTF_8);

        FormattingService fs = new FormattingService();
        assertTrue(output.contains(fs.formatPrompt()));
        assertTrue(output.contains(fs.formatThanks()));
        assertTrue(output.contains("Americano seçtiniz."));
    }
}
