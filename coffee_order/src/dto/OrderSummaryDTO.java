package dto;

import domain.Ingredient;

import java.util.Map;

public record OrderSummaryDTO(
        String drinkName,
        int priceTl,
        Map<Ingredient, Integer> consumed
) {

    public OrderSummaryDTO {
        if (drinkName == null || drinkName.isBlank()) {
            throw new IllegalArgumentException("drinkName cannot be null or blank");
        }
        if (priceTl < 0) {
            throw new IllegalArgumentException("priceTl cannot be negative");
        }
        if (consumed == null) {
            throw new IllegalArgumentException("consumed cannot be null");
        }
    }
}
