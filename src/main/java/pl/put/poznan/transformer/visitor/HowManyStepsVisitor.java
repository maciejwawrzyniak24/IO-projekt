package pl.put.poznan.transformer.visitor;

import pl.put.poznan.transformer.logic.Scenario;
import pl.put.poznan.transformer.logic.Step;


/**
 * Wizytator ktory liczy wszystkie kroki w scenariuszu
 * Zwraca liczba krokow w scenariuszu
 * @author Maciej Wawrzyniak
 * @version 1.0
 */
public class HowManyStepsVisitor implements Visitor{

    /**
     * Przechowuje liczbe krokow w scenariuszu
     */
    private int numberOfSteps;

    /**
     * Funkcja inkrementuje liczbe krokow i odwiedza podkroki
     * @param step krok scenariusza
     */
    @Override
    public void visit(Step step) {
        numberOfSteps++;
        for(Step subStep : step.getSubSteps()){
            subStep.accept(this);
        }
    }

    /**
     * Funkcja ustawia wartosc zmiennej "numberOfSteps" na 0 i sprawdza kroki scenariusza
     * @param scenario caly scenariusz
     */
    @Override
    public void visit(Scenario scenario) {
        numberOfSteps = 0;
    }

    @Override
    public Object getResult() {
        return numberOfSteps;
    }
}
