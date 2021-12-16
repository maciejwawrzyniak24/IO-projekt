package pl.put.poznan.transformer.visitor;

import pl.put.poznan.transformer.logic.Scenario;
import pl.put.poznan.transformer.logic.Step;

import java.util.ArrayList;
import java.util.List;


/**
 * Przykladowa implementacja wizytatora.
 * Zwraca liste Stringow, czyli tytul, autorow i wszystkie kroki wraz z podkrokami
 * @author Przemysław Szymczak
 * @version 1.0
 */
public class ExampleVisitor implements Visitor {

    /**
     * Lista Stringow przechowujaca wynik dzialania wizytytora
     */
    private List<String> result;
    public List<String> getResult() {
        return result;
    }

    /**
     * Funkcja dodaje do listy "result" tekst kroku scenariusza, a nastepnie przechodzi
     * po wszystkich podscenariuszach danego kroku
     * @param step krok scenariusza
     */
    public void visit(Step step) {
        result.add(step.getText());
        for (Step subStep : step.getSubSteps()) {
            subStep.accept(this);
        }
    }

    /**
     * Funkcja czysci liste "result", dodaje do listy tytul scenariusza, a nastepnie wszystkich autorow.
     * @param scenario caly scenariusz
     */
    public void visit(Scenario scenario){
        result = new ArrayList<>();
        String title = scenario.getTitle();
        result.add(title);
        List<String> authors = scenario.getAuthors();
        for (String author : authors)
        {
            result.add(author);
        }
    }

}
