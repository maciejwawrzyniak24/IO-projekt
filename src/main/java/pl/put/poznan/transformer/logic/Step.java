package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.visitor.Visitor;

import java.util.List;

/**
 * Klasa przechowuje krok scenariusza
 * Oprocz tekstu przechowuje liste podscenariuszy
 * @author Przemyslaw Szymczak
 * @version 1.0
 * @see Scenario
 */
public class Step {

    /**
     * Przechowuje tekst kroku scenariusza
     */
    private String text;

    /**
     * Przechowuje wszystkie podscenariusze danego kroku scenariusza
     */
    private List<Step> subSteps;

    /**
     * Obsluga wizytatora
     * @param visitor Klasa implementujaca interfejs "Visitor"
     */
    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }

    public Step(String text, List<Step> subScenario) {
        this.text = text;
        this.subSteps = subScenario;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Step> getSubSteps() {
        return subSteps;
    }

    public void setSubSteps(List<Step> subScenario) {
        this.subSteps = subScenario;
    }
}
