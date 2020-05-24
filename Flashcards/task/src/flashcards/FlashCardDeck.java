package flashcards;

import java.util.*;

class FlashCardDeck {
    LinkedHashMap<String, String> map;

    public FlashCardDeck(LinkedHashMap<String, String> map) {
        this.map = map;
    }

    public FlashCardDeck() {
        this.map = new LinkedHashMap<>();
    }


    void addCard(String term, String def) {
        map.put(term, def);
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

    Collection<String> getSetOfDefinitions() {
        return this.map.values();
    }

    boolean termIsCreated(String term) {
        return map.containsKey(term);
    }

    boolean defIsCreated(String def) {
        return map.containsValue(def);
    }

    boolean isEmpty() {
        return this.map.isEmpty();
    }


    String printEntryForExport(String term) {
        return (term + "|" + map.get(term) + "\r\n");
    }

    void removeCard(String term) {
        this.map.remove(term);
    }

    String getDefinition(String term) {
        return map.get(term);
    }

    void updateOrAddCard(String term, String def) {
        if (map.containsKey(term)) {
            map.remove(term);
            map.put(term, def);
        } else {
            map.put(term, def);
        }
    }

    String getRandomTerm() {
        Random random = new Random();
        int randomCardNumber = random.nextInt(this.map.size());
        Object[] terms = this.getSetTerm().toArray();
        return terms[randomCardNumber].toString();
    }


}