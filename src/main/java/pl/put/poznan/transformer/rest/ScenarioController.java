package pl.put.poznan.transformer.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.transformer.logic.Scenario;
import pl.put.poznan.transformer.visitor.HowManyStepsVisitor;
import pl.put.poznan.transformer.visitor.StepsWithKeywordsVisitor;
import pl.put.poznan.transformer.visitor.StepsWithoutAuthorVisitor;

/**
 * Klasa odpowiadajaca za implementacje endpointow API
 * @author Przemyslaw Szymczak
 * @version 1.2
 */
@RestController
public class ScenarioController {

    /**
     * Funkcja umozliwia policzenie liczby kroków w scenariuszu
     */
    @RequestMapping(method = RequestMethod.POST, value = "/example-howmanystepsvisitor")
    public Object setScenario1(@RequestBody Scenario scenario) {
        var visitor = new HowManyStepsVisitor();
        scenario.accept(visitor);
        return visitor.getResult();
    }

    /**
     * Funkcja umożliwia policzenie krokow w scenariuszu, zaczynajacych sie od slow kluczowych
     * @param scenario
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/stepswithkeywords-visitor")
    public Object setScenario2(@RequestBody Scenario scenario) {
        var visitor = new StepsWithKeywordsVisitor();
        scenario.accept(visitor);
        return visitor.getResult();
    }

    /**
     * Funkcja umożliwia sprawdzenie czy w danym scenariuszu są kroki nie zaczynajace sie od slow kluczowych ani aktorow
     * @param scenario
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/stepswithoutauthor-visitor")
    public Object setScenario3(@RequestBody Scenario scenario) {
        var visitor = new StepsWithoutAuthorVisitor();
        scenario.accept(visitor);
        return visitor.getResult();
    }
}
