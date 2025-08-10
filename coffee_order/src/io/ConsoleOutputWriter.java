package io;

public final class ConsoleOutputWriter implements OutputWriter {

    @Override
    public void println(String text) {
        System.out.println(text);
    }
}
