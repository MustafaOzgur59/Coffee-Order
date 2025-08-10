package io;

import org.junit.jupiter.api.Test;
import service.FormattingService;
import service.MenuService;
import service.OrderService;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleUITest {

    private static class FakeIn implements InputReader {
        private final String[] lines; int i=0;
        FakeIn(String... lines){ this.lines = lines; }
        public String readLine(){ return i<lines.length ? lines[i++] : null; }
    }
    private static class FakeOut implements OutputWriter {
        final StringBuilder sb = new StringBuilder();
        public void println(String s){ sb.append(s).append('\n'); }
        String out(){ return sb.toString(); }
    }

    @Test
    void happyPath_thenExit() {
        FakeOut o = new FakeOut();
        ConsoleUI ui = new ConsoleUI(new MenuService(), new OrderService(), new FormattingService(),
                new FakeIn("6"), o);
        ui.run();

        String all = o.out();
        assertTrue(all.contains("1. Espresso (20 ₺)"));
        assertTrue(all.contains("Lütfen içmek istediğiniz kahvenin numarasını giriniz"));
        assertTrue(all.contains("Teşekkürler kahveniz hazırlanıyor."));
        assertTrue(all.contains("Americano seçtiniz."));
    }

    @Test
    void loopsOnInvalid_thenAcceptsValid() {
        FakeOut o = new FakeOut();
        ConsoleUI ui = new ConsoleUI(
                new MenuService(), new OrderService(), new FormattingService(),
                new FakeIn("abc", "9", "3"), o
        );
        ui.run();

        String all = o.out();
        assertTrue(all.contains("Geçersiz seçim, tekrar deneyin."));   // non-numeric input
        assertTrue(all.contains("Lütfen geçerli bir sayı giriniz."));   // out-of-range (9)
        assertTrue(all.contains("Teşekkürler kahveniz hazırlanıyor")); // success path
    }
}