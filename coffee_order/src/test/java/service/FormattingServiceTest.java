package service;

import dto.MenuItemDTO;
import domain.Ingredient;
import dto.OrderSummaryDTO;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FormattingServiceTest {

    @Test
    void promptAndThanks_exactStrings() {
        FormattingService fs = new FormattingService();
        assertEquals("Lütfen içmek istediğiniz kahvenin numarasını giriniz", fs.formatPrompt());
        assertEquals("Teşekkürler kahveniz hazırlanıyor.", fs.formatThanks());
    }

    @Test
    void menuFormatting_includesNumbersNamesPrices() {
        FormattingService fs = new FormattingService();
        List<MenuItemDTO> menu = List.of(
                new MenuItemDTO(1, "Espresso", 20),
                new MenuItemDTO(2, "Double Espresso", 27)
        );
        String s = fs.formatMenu(menu);
        assertTrue(s.contains("1. Espresso (20 ₺)"));
        assertTrue(s.contains("2. Double Espresso (27 ₺)"));
    }

    @Test
    void preparation_includesDoses_andClosing() {
        FormattingService fs = new FormattingService();
        OrderSummaryDTO sum = new OrderSummaryDTO("Americano", 26,
                Map.of(Ingredient.ESPRESSO, 1, Ingredient.HOT_WATER, 4));
        String s = fs.formatPreparation(sum);
        assertTrue(s.startsWith("Americano seçtiniz."));
        assertTrue(s.contains("1 doz Espresso"));
        assertTrue(s.toLowerCase().contains("4 doz hot water"));
        assertTrue(s.endsWith("Afiyet Olsun."));
    }
}