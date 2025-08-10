package service;


import domain.CoffeeType;
import dto.OrderSummaryDTO;

import java.util.Objects;
import java.util.Optional;

public final class OrderService {

    public OrderSummaryDTO summarize(CoffeeType coffee) {
        Objects.requireNonNull(coffee, "coffee");

        return new OrderSummaryDTO(
                coffee.getDisplayName(),
                coffee.getPriceTl(),
                coffee.getRecipe().asMap()
        );
    }

    public Optional<OrderSummaryDTO> summarizeByNumber(int number) {
        return CoffeeType.fromNumber(number).map(this::summarize);
    }
}
