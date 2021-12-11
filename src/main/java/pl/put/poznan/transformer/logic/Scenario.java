package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.visitor.Visitor;

import java.util.List;

/**
 * Klasa przechowujaca scenariusz.
 * Sklada sie z tytulu, listy autorow i listy krokow
 * @see Step
 * @author Przemyslaw Szymczak
 * @version 1.0
 */
public class Scenario {

    /**
     * Przechowuje tytul scenariusza
     */
    private String title;

    /**
     * Przechowuje liste wszystkich autorow scenariusza
     */
    private List<String> authors;

    /**
     * Przechowuje liste wszystkich krokow scenariusza
     * @see Step
     */
    private List<Step> steps;

    /**
     * Akceptuje wizytatora, najpierw dla siebie, potem dla kazdego kroku scenariusza
     * @param visitor Klasa implementujaca interfejs "Visitor"
     */
    public void accept(Visitor visitor) {
        visitor.visit(this);
        for (Step step : steps) {
            step.accept(visitor);
        }
    }

    public Scenario(String title, List<String> authors, List<Step> steps) {
        this.title = title;
        this.authors = authors;
        this.steps = steps;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }
}
