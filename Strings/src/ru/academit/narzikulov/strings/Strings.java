package ru.academit.narzikulov.strings;

import java.util.Arrays;

/**
 * Created by tim on 11.06.2016.
 */
public class Strings {
    private StringBuilder builder = new StringBuilder();

    public Strings(int begin, int end) {
        for (int i = begin; i <= end; ++i) {
            builder.append(i).append(", ");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.deleteCharAt(builder.length() - 1);
    }

    public Strings(String str) {
        builder.append(Arrays.toString(str.split(", ")));
    }

    public StringBuilder getStringBuilder() {
        return builder;
    }

    public String getString() {
        return builder.toString();
    }

    public int builderElementsSum() {
        for (int i = 0; i < builder.length(); ++i) {
            //builder.;
        }
        return 1;
    }
}
