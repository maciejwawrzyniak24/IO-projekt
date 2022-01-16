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
 * @version 2.0
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
     * Lista przechowujaca numer kroku i podkroków.
     */
    private List<Integer> stepNum;

    /**
     * Funkcja dodaje do listy "result" tekst kroku scenariusza, jezeli nie zaczyna sie od aktora lub slowa kluczowego,
     * a nastepnie przechodzi po wszystkich podscenariuszach danego kroku
     * @param step krok scenariusza
     */
    @Override
    public void visit(Step step) {
        stepNum.set(stepNum.size() - 1, stepNum.get(stepNum.size() - 1) + 1);
        boolean addToResult = true;
        for (String word : words) {
            if (step.getText().startsWith(word)) {
                addToResult = false;
                break;
            }
        }
        if (addToResult) {
            result.add(stepNum.toString().replace("[", "").replace("]", ". ")
                    .replace(", ", ".") + step.getText());
        }
        if (!step.getSubSteps().isEmpty()) {
            stepNum.add(0);
            for (Step subStep : step.getSubSteps()) {
                subStep.accept(this);
            }
            stepNum.remove(stepNum.size() - 1);
        }
    }

    /**
     * Funkcja czysci liste "result", inicjalizuje numeracje kroków
     * i dodaje do listy "words" slowa, od ktorych nie moze sie zaczynac krok,
     * czyli nazwy aktorow i slowa klucze.
     * @param scenario caly scenariusz
     */
    @Override
    public void visit(Scenario scenario) {
        result = new ArrayList<>();
        words = scenario.getAuthors();
        words.addAll(Arrays.asList("IF", "ELSE", "FOR EACH"));
        stepNum = new ArrayList<>();
        stepNum.add(0);
    }

    @Override
    public List<String> getResult() {
        return result;
    }

    public List<String> getWords() {
        return words;
    }

    public List<Integer> getStepNum() {
        return stepNum;
    }

    public void setWords(List<String> w) {
        words = w;
    }

    public void setResult(List<String> r) {
        result = r;
    }

    public void setStepNum(List<Integer> s) {
        stepNum = s;
    }
}
