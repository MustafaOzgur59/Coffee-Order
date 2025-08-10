package dto;

import domain.Ingredient;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DtoValidationTest {

    @Test
    void orderSummary_validation() {
        assertThrows(IllegalArgumentException.class,
                () -> new OrderSummaryDTO(" ", 0, Map.of()));
        assertThrows(IllegalArgumentException.class,
                () -> new OrderSummaryDTO("Latte", -1, Map.of()));
        assertThrows(IllegalArgumentException.class,
                () -> new OrderSummaryDTO("Latte", 10, null));

        OrderSummaryDTO ok = new OrderSummaryDTO("Latte", 28, Map.of(Ingredient.ESPRESSO, 1));
        assertEquals("Latte", ok.drinkName());
    }

    @Test
    void menuItem_recordHoldsValues() {
        MenuItemDTO m = new MenuItemDTO(3, "Cappuccino", 28);
        assertEquals(3, m.number());
        assertEquals("Cappuccino", m.name());
        assertEquals(28, m.priceTl());
    }
}