package EffectiveJava.Chapter6;

import java.util.Set;

public class _01_EnumSets {
    public static void main(String ... args) {
        /*
         * Text text = new Text("Click me!");
         * text.applyStyles(Text.STYLE_BOLD | Text.STYLE_UNDERLINE);
         */

        /*
        * Text_Enum text2 = new Text_Enum("Click me instead!");
        * text2.applyStyles(EnumSet.of(Text_Enum.Style.BOLD, Text_Enum.Style.UNDERLINE));
        */
    }

    // This is BAD. Never use bit fields!
    @SuppressWarnings("unused")
    static abstract class Text {
        private static final int STYLE_BOLD = 1 << 0;
        private static final int STYLE_ITALIC = 1 << 1;
        private static final int STYLE_UNDERLINE = 1 << 2;
        private static final int STYLE_STRIKETHROUGH = 1 << 3;

        private final String text;

        Text(String text) { this.text = text; }

        // In this method declared styles are somehow used
        // Bit fields allow for such expressions as 'STYLE_BOLD | STYLE_ITALIC'
        abstract void applyStyles(int styles);
    }

    // This is the right way
    @SuppressWarnings("unused")
    static abstract class Text_Enum {
        enum Style { BOLD, ITALIC, UNDERLINE, STRIKETHROUGH }

        private final String text;

        Text_Enum(String text) { this.text = text; }

        abstract void applyStyles(Set<Style> styles);
    }
}
