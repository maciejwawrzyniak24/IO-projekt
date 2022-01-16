package pl.put.poznan.transformer.rest;

import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.Scenario;
import pl.put.poznan.transformer.visitor.*;
import pl.put.poznan.transformer.visitor.GetScenarioTextVisitor;
import pl.put.poznan.transformer.visitor.HowManyStepsVisitor;
import pl.put.poznan.transformer.visitor.StepsWithKeywordsVisitor;
import pl.put.poznan.transformer.visitor.StepsWithoutAuthorVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Klasa odpowiadajaca za implementacje endpointow API
 * @author Przemyslaw Szymczak
 * @version 1.2
 */
@RestController
public class ScenarioController {
    final Logger logger = LoggerFactory.getLogger(ScenarioController.class);
    /**
     * Funkcja umozliwia policzenie liczby kroków w scenariuszu
     */
    @RequestMapping(method = RequestMethod.POST, value = "/example-howmanystepsvisitor")
    public Object setScenario1(@RequestBody Scenario scenario) {
        logger.debug("setScenario1");
        var visitor = new HowManyStepsVisitor();
        scenario.accept(visitor);
        logger.info("HowManyStepsVisitor result: {}", visitor.getResult());
        return visitor.getResult();
    }

    /**
     * Funkcja umożliwia policzenie krokow w scenariuszu, zaczynajacych sie od slow kluczowych
     * @param scenario
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/stepswithkeywords-visitor")
    public Object setScenario2(@RequestBody Scenario scenario) {
        logger.debug("setScenario2");
        var visitor = new StepsWithKeywordsVisitor();
        scenario.accept(visitor);
        logger.info("StepsWithKeywordsVisitor result: {}", visitor.getResult());
        return visitor.getResult();
    }

    /**
     * Funkcja umożliwia sprawdzenie czy w danym scenariuszu są kroki nie zaczynajace sie od slow kluczowych ani aktorow
     * @param scenario
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/stepswithoutauthor-visitor")
    public Object setScenario3(@RequestBody Scenario scenario) {
        logger.debug("setScenario3");
        var visitor = new StepsWithoutAuthorVisitor();
        scenario.accept(visitor);
        logger.info("StepsWithoutAuthorVisitor result: {}", visitor.getResult());
        return visitor.getResult();
    }

    /**
     * Funkcja zwracająca scenariusz w formie tekstu z numeracją kroków
     * @param scenario
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getscenariotext-visitor")
    public Object setScenario4(@RequestBody Scenario scenario) {
        logger.debug("setScenario4");
        var visitor = new GetScenarioTextVisitor();
        scenario.accept(visitor);
        logger.info("GetScenarioTextVisitor result: {}", visitor.getResult());
        return visitor.getResult();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/one-actor-steps-visitor")
    public Object setScenario5(@RequestBody Scenario scenario, @RequestParam(value="actor") String actor) {
        logger.debug("setScenario5");
        var visitor = new OneActorStepsVisitor(actor);
        scenario.accept(visitor);
        logger.info("OneActorStepsVisitor result: {}", visitor.getResult());
        return visitor.getResult();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/scenario-max-level-visitor")
    public Object setScenario6(@RequestBody Scenario scenario, @RequestParam(value="level") int level) {
        logger.debug("setScenario6");
        var visitor = new ScenarioMaxLevelVisitor(level);
        scenario.accept(visitor);
        logger.info("ScenarioMaxLevelVisitor result: {}", visitor.getResult());
        return visitor.getResult();
    }
}
