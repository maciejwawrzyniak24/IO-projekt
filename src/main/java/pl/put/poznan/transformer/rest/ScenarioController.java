package pl.put.poznan.transformer.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.transformer.logic.Scenario;
import pl.put.poznan.transformer.visitor.ExampleVisitor;

/**
 * Klasa odpowiadajaca za implementacje endpointow API
 * @author Przemyslaw Szymczak
 * @version 1.1
 */
@RestController
public class ScenarioController {

    /**
     * Funkcja umozliwia odebranie z API scenariusza w formacie json
     */
    @RequestMapping(method = RequestMethod.POST, value = "/example-visitor")
    public Object setScenario(@RequestBody Scenario scenario) {
        var visitor = new ExampleVisitor();
        scenario.accept(visitor);
        return visitor.getResult();
    }

}
