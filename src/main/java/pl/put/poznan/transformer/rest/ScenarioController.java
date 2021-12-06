package pl.put.poznan.transformer.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.transformer.logic.Scenario;
import pl.put.poznan.transformer.logic.ScenarioService;
import pl.put.poznan.transformer.visitor.ExampleVisitor;

import java.util.List;

/**
 * Klasa odpowiadajaca za implementacje endpointow API
 * @author Przemyslaw Szymczak
 * @version 1.0
 */
@RestController
public class ScenarioController {

    /**
     * Pole odpowiada za logike biznesowa. Przechowuje pole zawierajace caly scenariusz,
     * oraz funkcje do przetwarzania tego pola
     */
    private ScenarioService scenarioService = new ScenarioService();

    /**
     * Funkcja umozliwia przeslanie scenariusza w formacie json
     */
    @RequestMapping(method = RequestMethod.GET, value = "/scenario")
    public Scenario getScenario() {
        return scenarioService.getScenario();
    }

    /**
     * Funkcja umozliwia odebranie z API scenariusza w formacie json
     */
    @RequestMapping(method = RequestMethod.POST, value = "/scenario")
    public void setScenario(@RequestBody Scenario scenario) {
        scenarioService.setScenario(scenario);
    }


    /**
     * Wywolanie przykladowego wizytora
     * @see ExampleVisitor
     */
    @RequestMapping(method = RequestMethod.GET, value = "/visitors/example-visitor")
    public List<String> getExampleVisitor() {
        var visitor = new ExampleVisitor();
        scenarioService.accept(visitor);
        return visitor.getResult();
    }
}
