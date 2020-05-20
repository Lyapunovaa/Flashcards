package flashcards;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        /*
        var blank = new LinkedHashMap<>();
        blank.put("Хуй", "Предмет для рта");
        blank.put("Русский", "Человек живущий в России");
        blank.put("Приора", "Автомобиль для работника завода");
        blank.put("Димас", "Ловкий малый");
        FlashCardDeck testMap = new FlashCardDeck(blank); */
        FlashCardDeck testMap = new FlashCardDeck();
        initializingOfDeck(testMap);
        //  testMap.containsOfMap();
        game(testMap);


    }

    public static void initializingOfDeck(FlashCardDeck map) {
        System.out.println("Input the number of cards:");
        int howMuch = scanner.nextInt();
        inputString();
        for (int i = 1; i <= howMuch; i++) {
            System.out.printf("The card #%s:\n", i);
            String term = scanner.nextLine();
            if (map.termIsCreated(term)) {
                while (true) {
                    System.out.printf("The card \"%s\" already exists. Try again:\n", term);
                    term = scanner.nextLine();
                    if (!map.termIsCreated(term)) {
                        break;
                    }
                }
            }

            System.out.printf("The definition of the card #%s:\n", i);
            String def = scanner.nextLine();
            if (map.defIsCreated(def)) {
                while (true) {
                    System.out.printf("The definition \"%s\" already exists. Try again:\n", def);
                    def = scanner.nextLine();
                    if (!map.defIsCreated(def)) {
                        break;
                    }
                }
            }

            map.addCard(term, def);
        }
    }

    public static void game(FlashCardDeck map) {
        for (String s : map.getSetTerm()) {
            System.out.printf("Print the definition of \"%s\":\n", s);
            String def = scanner.nextLine();

            if (def.equals(map.getDefinition(s))) {
                System.out.println("Correct answer.");
            } else {
                if (map.getDefs().contains(def)) {
                    System.out.println("Wrong answer. The correct one is \"" + map.getDefinition(s) + "\", you've just written the definition of \"" + map.getTermByDef(def) + "\".\n");
                } else {
                    System.out.println("Wrong answer. The correct one is \"" + map.getDefinition(s) + "\".");
                }
            }
        }
    }


    static String inputString() {
        String empty = scanner.nextLine();
        return empty;
    }
}

class FlashCardDeck {
    LinkedHashMap<String, String> map;

    public FlashCardDeck(LinkedHashMap<String, String> map) {
        this.map = map;
    }

    public FlashCardDeck() {
        this.map = new LinkedHashMap<>();
    }

    void containsOfMap() {
        System.out.println(map.entrySet().stream().map(e -> e.getKey() + " " + e.getValue()).collect(Collectors.joining(" | ")));
    }

    void addCard(String term, String def) {
        map.put(term, def);
    }

    void addPackOfCard(int countForAdding) {
        for (int i = 0; i < countForAdding; i++) {
            Scanner scanner = new Scanner(System.in);
            String term = scanner.nextLine();
            String def = scanner.nextLine();
            addCard(term, def);
        }
    }

    int size() {
        return this.map.size();
    }

    Set<String> getSetTerm() {
        return this.map.keySet();
    }

    Collection<String> getDefs() {
        return this.map.values();
    }

    String getTermByDef(String def) {
        String key = null;
        if (this.map.containsValue(def)) {
            for (String entry : map.keySet()) {
                if (map.get(entry).equals(def)) {
                    key = entry;
                }
            }
        }
        return key;

    }

    Set<String> getSetOfDefinitions() {
        Set<String> values = new HashSet<>();
        for (String value : map.keySet()
        ) {
            values.add(this.getDefinition(value));
        }
        return values;
    }

    boolean termIsCreated(String term) {
        return map.containsKey(term);
    }

    boolean defIsCreated(String def) {

        if (map.values().contains(def)) {
            return true;
        } else {
            return false;
        }
    }

    boolean isEmpty() {
        return this.map.isEmpty();
    }

    void printEntry(String term) {
        if (map.containsKey(term)) {
            System.out.println(term + " " + map.get(term));
        } else {
            System.out.printf("%s Not founded", term);
        }
    }

    void removeCard(String term) {
        this.map.remove(term);
    }

    String getDefinition(String term) {
        return map.get(term);
    }
}