package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RecipeTest {

    @Test
    void of_ignoresZero_andRejectsNegative() {
        Recipe r = Recipe.of(Map.of(
                Ingredient.ESPRESSO, 1,
                Ingredient.HOT_WATER, 0
        ));
        assertEquals(1, r.doseOf(Ingredient.ESPRESSO));
        assertEquals(0, r.doseOf(Ingredient.HOT_WATER));
        assertThrows(IllegalArgumentException.class, () ->
                Recipe.of(Map.of(Ingredient.ESPRESSO, -1)));
    }

    @ParameterizedTest
    @CsvSource({
            "HOT_WATER,0",
            "STEAMED_MILK,0",
            "MILK_FOAM,0",
            "HOT_CHOCOLATE,0"
    })
    void doseOf_returnsZeroWhenAbsent(String ingName, int expected) {
        Recipe r = Recipe.of(Map.of(Ingredient.ESPRESSO, 2));
        assertEquals(expected, r.doseOf(Ingredient.valueOf(ingName)));
    }
}