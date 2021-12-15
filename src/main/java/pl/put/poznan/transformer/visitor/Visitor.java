package pl.put.poznan.transformer.visitor;

import pl.put.poznan.transformer.logic.Scenario;
import pl.put.poznan.transformer.logic.Step;

/**
 * Interfejs implementowany przez wszyskich wizytatorow
 * @author Przemysław Szymczak
 * @version 1.1
 */

public interface Visitor {
    /**
     * Funkcja wizytujaca krok scenariusza
     * @param step krok scenariusza
     */
    public void visit(Step step);

    /**
     * Funkcja wizytujaca caly scenariusz
     * @param scenario caly scenariusz
     */
    public void visit(Scenario scenario);

    public Object getResult();

}
