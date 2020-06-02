package flashcards;

import java.util.Formatter;
import java.util.LinkedList;
import java.util.List;

public class Audit {
    private final StringBuilder stringBuilder = new StringBuilder();
    private final Formatter formatter = new Formatter(stringBuilder);
    List<String> log = new LinkedList<>();

}
