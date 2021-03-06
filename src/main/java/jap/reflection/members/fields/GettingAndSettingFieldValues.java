package jap.reflection.members.fields;

import java.lang.reflect.Field;
import java.util.Arrays;

import static java.lang.System.out;

class GettingAndSettingFieldValues {
    public static void main(String... args) {
        Book book = new Book();
        String fmt = "%6S:  %-12s = %s%n";

        try {
            Class<?> c = book.getClass();

            Field chap = c.getDeclaredField("chapters");
            chap.setAccessible(true);
            out.format(fmt, "before", "chapters", chap.get(book));
            chap.setLong(book, 12);
            out.format(fmt, "after", "chapters", chap.getLong(book));

            Field chars = c.getDeclaredField("characters");
            out.format(fmt, "before", "characters",
                    Arrays.asList(book.characters));
            String[] newChars = {"Queen", "King"};
            chars.set(book, newChars);
            out.format(fmt, "after", "characters",
                    Arrays.asList(book.characters));

            Field t = c.getDeclaredField("twin");
            out.format(fmt, "before", "twin", book.twin);
            t.set(book, Tweedle.DUM);
            out.format(fmt, "after", "twin", t.get(book));

        } catch (NoSuchFieldException | IllegalAccessException x) {
            // ignore
        }
    }
}

class Book {
    static private final long chapters = 0;
    public String[] characters = {"Alice", "White Rabbit"};
    public Tweedle twin = Tweedle.DEE;
}

enum Tweedle {DEE, DUM}
