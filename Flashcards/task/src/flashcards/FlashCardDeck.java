package flashcards;

import java.util.*;

class FlashCardDeck {
    LinkedHashMap<Pair, Integer> map;


    public FlashCardDeck(LinkedHashMap<Pair, Integer> map) {
        this.map = map;
    }


    public FlashCardDeck() {
        this.map = new LinkedHashMap<>();
    }


    void addError(String term) {
        for (Pair pair : map.keySet()) {
            if (pair.getTerm().equals(term)) {
                int value = map.get(pair);
                map.put(pair, value + 1);
            }
        }
    }

    void addCard(String term, String def) {
        Pair pair = new Pair(term, def);
        map.put(pair, 0);
    }


    int size() {
        return this.map.size();
    }

    Set<String> getSetTerm() {
        Set<String> setOfTerms = new TreeSet<>();
        for (Pair pair : map.keySet()
        ) {
            setOfTerms.add(pair.getTerm());
        }
        return setOfTerms;
    }

    Set<String> getDefs() {
        Set<String> setOfDefs = new TreeSet<>();
        for (Pair pair : map.keySet()
        ) {
            setOfDefs.add(pair.getDef());
        }
        return setOfDefs;
    }

    String getTermByDef(String def) {
        for (Pair pair : map.keySet()
        ) {
            if (pair.getDef().equals(def)) {
                return pair.getTerm();
            }
        }
        return null;
    }


    boolean termIsCreated(String term) {
        for (Pair pair : map.keySet()
        ) {
            if (pair.getTerm().equals(term)) {
                return true;
            } else return false;
        }
        return false;
    }

    boolean defIsCreated(String def) {
        for (Pair pair : map.keySet()
        ) {
            if (pair.getDef().equals(def)) {
                return true;
            } else return false;
        }
        return false;
    }

    boolean isEmpty() {
        return this.map.isEmpty();
    }


    String printEntryForExport(String term) {
        for (Pair pair : map.keySet()) {
            if (pair.getTerm().equals(term)) {
                String summary = pair.getTerm() + "|" + pair.getDef() + "|" + map.get(pair) + "\r\n";
                return summary;
            }
        }
        return null;
    }

    void removeCard(String term) {
        for (Pair pair : map.keySet()) {
            if (pair.getTerm().equals(term)) {
                this.map.remove(pair);
            }
        }
    }

    String getDefinition(String term) {
        for (Pair pair : map.keySet()
        ) {
            if (pair.getTerm().equals(term)) {
                return pair.getDef();
            }
        }
        return null;
    }

    void updateOrAddCard(String term, String def, Integer errors) {
        Pair newPair = new Pair(term, def);
        for (Pair pair : map.keySet()) {
            if (pair.getTerm().equals(term)) {
                map.remove(pair);
                map.put(newPair, errors);
                break;
            }
        }
        map.put(newPair, errors);


    }


    String getRandomTerm() {
        if (map.isEmpty()) {
            return null;
        } else {
            Random random = new Random();
            int randomCardNumber = random.nextInt(this.map.size());
            Object[] terms = this.getSetTerm().toArray();
            return terms[randomCardNumber].toString();
        }
    }

    void resetStat() {
        map.replaceAll((p, v) -> 0);
    }

    Map<String, Integer> hardestCard() {

        Map<String, Integer> cardsWithErrors = new TreeMap<>();
        int maxValue = 0;
        for (Pair pair : map.keySet()
        ) {
            if (map.get(pair) >= maxValue) {
                maxValue = map.get(pair);
            }
        }

        for (Pair pair : map.keySet()) {
            if (map.get(pair) == maxValue) {
                cardsWithErrors.put(pair.getTerm(), maxValue);

            }
        }
        return cardsWithErrors;
    }

    String readyString(Map<String, Integer> hardestCards) {
        String cards = "";
        for (int i = 0; i <= hardestCards.size(); i++) {

        }


        for (Pair pair : map.keySet()) {
            if (map.get(pair).equals(getMaxErrors())) {
                cards = cards + "\"" + pair.getTerm() + "\", ";
            }
        }
        cards = cards.substring(0, cards.length() - 2);
        return cards;
    }

    Integer getMaxErrors() {
        int maxValue = 0;
        for (Pair pair : map.keySet()
        ) {
            if (map.get(pair) >= maxValue) {
                maxValue = map.get(pair);
            }
        }
        return maxValue;
    }


}

class Pair {
    String term;
    String def;

    public Pair(String term, String def) {
        this.term = term;
        this.def = def;
    }

    public String getDef() {
        return def;
    }

    public String getTerm() {
        return term;
    }

}