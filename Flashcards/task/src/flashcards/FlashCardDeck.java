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
                String summary = pair.getTerm() + " " + pair.getDef();
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

    void updateOrAddCard(String term, String def) {
       for (Pair pair: map.keySet()){
           if (pair.getTerm().equals(term)){
               map.remove(pair);
               Pair newPair = new Pair(term,def);
               map.put(newPair, 0); //TODO надо дописать приём Integer
           }
       }
    }


    String getRandomTerm() {
        Random random = new Random();
        int randomCardNumber = random.nextInt(this.map.size());
        Object[] terms = this.getSetTerm().toArray();
        return terms[randomCardNumber].toString();
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