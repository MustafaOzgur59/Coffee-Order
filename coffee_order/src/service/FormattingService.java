package service;

import dto.MenuItemDTO;
import domain.Ingredient;
import dto.OrderSummaryDTO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public final class FormattingService {

    public String formatMenu(List<MenuItemDTO> menu) {
        return menu.stream()
                .map(item -> item.number() + ". " + item.name() + " (" + item.priceTl() + " ₺)")
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public String formatPrompt() {
        return "Lütfen içmek istediğiniz kahvenin numarasını giriniz";
    }

    public String formatThanks() {
        return "Teşekkürler kahveniz hazırlanıyor.";
    }

    public String formatPreparation(OrderSummaryDTO summary) {
        StringBuilder sb = new StringBuilder();
        sb.append(summary.drinkName()).append(" seçtiniz. ");
        sb.append("Bu içeceğimiz ");
        sb.append(formatIngredients(summary.consumed()));
        sb.append(" içerir. Afiyet Olsun.");
        return sb.toString();
    }


    private String formatIngredients(Map<Ingredient,Integer> consumed) {
        return consumed.entrySet().stream()
                .map(e -> e.getValue() + " doz " + formatIngredientName(e.getKey()))
                .collect(Collectors.joining(" ve "));
    }


    private String formatIngredientName(Ingredient ingredient) {
        // Replace underscores with spaces and lowercase after first letter.
        String name = ingredient.name().replace('_', ' ').toLowerCase();
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
}