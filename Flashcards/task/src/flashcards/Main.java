package flashcards;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        var map = new TreeMap<>();
        map.put("Niggers", "Black");
        map.put("Russian", "V stoilo");
        map.put("Kavkaz", "Sila");
        map.put("Alpi", "Lovkost");
        FlashCardDeck testMap = new FlashCardDeck(map);
        testMap.containsOfMap();
        testMap.addCard("Chlen", "Pizda");
        testMap.containsOfMap();

        testMap.removeCard("Alpi");
        testMap.containsOfMap();
        testMap.getEntry("Chlen");


        /*
        String term = scanner.nextLine();
        String def = scanner.nextLine();
        forTest.addCard(term,def); */


        //  тут уже играешь
        //  String ans = scanner.next();
        //  game(flashCard, ans);

    }


    public static void game(FlashCard flashCard, String ans) {
        if (flashCard.checkAnswer(ans)) {             //Заглушка для прохождения задания
            System.out.println("Your answer is right!");
        } else {
            System.out.println("Your answer is wrong...");
        }
    }
}


class FlashCard {
    String term;
    String definition;

    public FlashCard(String card, String definition) {
        this.term = card;
        this.definition = definition;
    }

    public String getTerm() {
        return term;
    }

    public String getDefinition() {
        return definition;
    }

    public boolean checkAnswer(String ans) {
        return ans.equals(getDefinition());
    }

    public String getCardInfo() {
        return "Card:\n" + term + "\nDefinition:\n" + definition;
    }
}

class FlashCardDeck<K, V> {
    TreeMap<String, String> map;

    public FlashCardDeck(TreeMap<String, String> map) {
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

    Map.Entry getEntry(String term) {
        Map.Entry<String, String> q =  map.ceilingEntry(term);
        System.out.println(q.toString());
        return q;
    }

    void removeCard(String term) {
        this.map.remove(term);
    }

    String getDefinition(String term) {
        return map.get(term);
    }


}