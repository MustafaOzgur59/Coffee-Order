package io;

import dto.OrderSummaryDTO;
import service.FormattingService;
import service.MenuService;
import service.OrderService;


import java.util.Optional;

public class ConsoleUI {

    private final MenuService menuService;
    private final OrderService orderService;
    private final FormattingService formatting;
    private final InputReader in;
    private final OutputWriter out;

    public ConsoleUI(MenuService menuService, OrderService orderService,
                     FormattingService formatting, InputReader in, OutputWriter out) {
        this.menuService = menuService;
        this.orderService = orderService;
        this.formatting = formatting;
        this.in = in;
        this.out = out;
    }

    public void run() {
        out.println(formatting.formatMenu(menuService.getMenu()));
        out.println(formatting.formatPrompt());

        Optional<OrderSummaryDTO> summaryOpt = Optional.empty();
        while (summaryOpt.isEmpty()) {
            String line = in.readLine();
            try {
                int choice = Integer.parseInt(line.trim());
                summaryOpt = orderService.summarizeByNumber(choice);
                if (summaryOpt.isEmpty()) {
                    out.println("Geçersiz seçim, tekrar deneyin.");
                }
            } catch (NumberFormatException e) {
                out.println("Lütfen geçerli bir sayı giriniz.");
            }
        }

        out.println(formatting.formatThanks());
        out.println(formatting.formatPreparation(summaryOpt.get()));
    }
}
