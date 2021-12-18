package pl.put.poznan.transformer.visitor;

import pl.put.poznan.transformer.logic.Scenario;
import pl.put.poznan.transformer.logic.Step;

import java.util.Arrays;
import java.util.List;


/**
 * Wizytator, ktory liczy ile krokow zawiera slowa kluczowe
 * Zwraca liczbe krokow zawierajacych slowa kluczowe
 * @author Wiktoria Szymańska
 * @version 1.0
 */
public class StepsWithKeywordsVisitor implements Visitor{

    /**
     * Przechowuje liczbe krokow zawierajacych slowa kluczowe
     */
    private int numberOfStepsWithKeywords;

    /**
     * Przechowuje liste slow kluczowych
     */
    private final List<String> keywords = Arrays.asList("IF", "ELSE", "FOR EACH");

    /**
     * Funkcja inkrementuje liczbe krokow jezeli dany krok zawiera slowo klucz,
     * a nastepnie przechodzi po wszystkich podscenariuszach danego kroku
     * @param step krok scenariusza
     */
    @Override
    public void visit(Step step) {
        for (String keyword : keywords) {
            if (step.getText().startsWith(keyword)) {
                numberOfStepsWithKeywords++;
                break;
            }
        }
        for (Step subStep : step.getSubSteps()) {
            subStep.accept(this);
        }
    }

    /**
     * Funkcja ustawia wartosc zmiennej "numberOfStepsWithKeywords" na 0 i sprawdza kroki scenariusza
     * @param scenario caly scenariusz
     */
    @Override
    public void visit(Scenario scenario) {
        numberOfStepsWithKeywords = 0;
    }

    @Override
    public Object getResult() {
        return numberOfStepsWithKeywords;
    }
}
