package flashcards;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        var blank = new LinkedHashMap<>();
        blank.put("Niggers", "Black");
        blank.put("Russian", "V stoilo");
        blank.put("Kavkaz", "Sila");
        blank.put("Alpi", "Lovkost");
        FlashCardDeck testMap = new FlashCardDeck(blank);

        //testMap.containsOfMap();
        //testMap.addCard("Chlen", "Pizda");
        //testMap.containsOfMap();

        //testMap.removeCard("Alpi");
        //testMap.printEntry("Kavkaz");
        //testMap.printEntry("Kavkas");

        //initializingOfDeck(testMap);
        testMap.containsOfMap();
        game(testMap);


        //  тут уже играешь
        //  String ans = scanner.next();
        //  game(flashCard, ans);

    }

    public static void initializingOfDeck(FlashCardDeck<String, String> map) {
        System.out.println("Input the number of cards:");
        int howMuch = scanner.nextInt();
        inputString();
        for (int i = 1; i <= howMuch; i++) {
            System.out.printf("The card #%s:\n", i);
            String term = scanner.nextLine();
            if (map.cardIsCreated(term)) {
                while (true) {
                    System.out.printf("The card \"%s\" already exists. Try again:\n", term);
                    term = scanner.nextLine();
                    if (!map.cardIsCreated(term)) {
                        break;
                    }
                }
            }
            System.out.printf("The definition of the card #%s:\n", i);
            String def = scanner.nextLine();
            map.addCard(term, def);
        }
    }

    public static void game(FlashCardDeck<String, String> map) {
        for (String s : map.getKeys()) {
            System.out.printf("Print the definition of \"%s\":\n", s);
            String def = scanner.nextLine();
            if (def.equals(map.getDefinition(s))) {
                System.out.println("Correct answer.");
            } else {
                System.out.println("Wrong answer");
            }
        }
    }


    static String inputString() {
        String empty = scanner.nextLine();
        return empty;
    }
}

class FlashCardDeck<K, V> {
    LinkedHashMap<String, String> map;

    public FlashCardDeck(LinkedHashMap<String, String> map) {
        this.map = map;
    }

    public FlashCardDeck() {
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

    Set<String> getKeys() {
        return this.map.keySet();
    }

    boolean cardIsCreated(String term) {
        return map.containsKey(term);
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