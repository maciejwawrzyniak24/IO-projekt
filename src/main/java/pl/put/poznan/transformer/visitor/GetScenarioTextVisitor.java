package pl.put.poznan.transformer.visitor;

import pl.put.poznan.transformer.logic.Scenario;
import pl.put.poznan.transformer.logic.Step;

import java.util.ArrayList;
import java.util.List;


/**
 * Wizytator, ktory zwraca scenariusz w formie tekstu z numeracją kroków.
 * @author Wiktoria Szymańska
 * @version 1.0
 */
public class GetScenarioTextVisitor implements Visitor{

    /**
     * Lista Stringow przechowujaca wynik dzialania wizytytora
     */
    private List<String> result;

    /**
     * Lista przechowujaca numer kroku i podkroków.
     */
    private List<Integer> stepNum;

    /**
     * Funkcja dodaje do listy "result" tekst i numer kroku scenariusza,
     * a nastepnie przechodzi po wszystkich podscenariuszach danego kroku
     * @param step krok scenariusza
     */
    @Override
    public void visit(Step step) {
        stepNum.set(stepNum.size() - 1, stepNum.get(stepNum.size() - 1) + 1);
        result.add(stepNum.toString().replace("[", "").replace("]", ". ")
                .replace(", ", ".") + step.getText());
        if (!step.getSubSteps().isEmpty()) {
            stepNum.add(0);
            for (Step subStep : step.getSubSteps()) {
                subStep.accept(this);
            }
            stepNum.remove(stepNum.size() - 1);
        }
    }

    /**
     * Funkcja czysci liste "result" i inicjalizuje numeracje kroków.
     * @param scenario caly scenariusz
     */
    @Override
    public void visit(Scenario scenario) {
        result = new ArrayList<>();
        stepNum = new ArrayList<>();
        stepNum.add(0);
    }

    @Override
    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> r) {
        result = r;
    }
}
