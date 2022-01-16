package pl.put.poznan.transformer.visitor;

import pl.put.poznan.transformer.logic.Scenario;
import pl.put.poznan.transformer.logic.Step;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Wizytator, ktory sprawdza, ktore kroki nie rozpoczynaja sie od aktora
 * Zwraca liste Stringow, zawierajaca kroki, ktore nie rozpoczynaja sie od aktora
 * @author Wiktoria Szymańska
 * @version 1.0
 */
public class StepsWithoutAuthorVisitor implements Visitor{

    /**
     * Lista Stringow przechowujaca wynik dzialania wizytytora
     */
    private List<String> result;

    /**
     * Lista Stringow przechowujaca slowa od ktorych nie moze sie zaczynac krok, czyli nazwy aktorow oraz slowa klucze
     */
    private List<String> words;

    /**
     * Funkcja dodaje do listy "result" tekst kroku scenariusza, jezeli nie zaczyna sie od aktora lub slowa kluczowego,
     * a nastepnie przechodzi po wszystkich podscenariuszach danego kroku
     * @param step krok scenariusza
     */
    @Override
    public void visit(Step step) {
        boolean addToResult = true;
        for (String word : words) {
            if (step.getText().startsWith(word)) {
                addToResult = false;
                break;
            }
        }
        if (addToResult) {
            result.add(step.getText());
        }
        for (Step subStep : step.getSubSteps()) {
            subStep.accept(this);
        }
    }

    /**
     * Funkcja czysci liste "result" i dodaje do listy "words" slowa, od ktorych nie moze sie zaczynac krok,
     * czyli nazwy aktorow i slowa klucze.
     * @param scenario caly scenariusz
     */
    @Override
    public void visit(Scenario scenario) {
        result = new ArrayList<>();
        words = scenario.getAuthors();
        words.addAll(Arrays.asList("IF", "ELSE", "FOR EACH"));
    }

    @Override
    public List<String> getResult() {
        return result;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> w) {
        words = w;
    }

    public void setResult(List<String> r) {
        result = r;
    }
}
