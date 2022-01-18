package pl.put.poznan.transformer.visitor;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.Scenario;
import pl.put.poznan.transformer.logic.Step;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testy wizytatora, ktory sprawdza, ktore kroki nie rozpoczynaja sie od aktora
 * @author Kajetan Wencierski
 * @version 1.0
 */
class StepsWithoutAuthorVisitorTest {

    /**
     * Test z uzyciem pustego scenariusza
     */
    @Test
    void testVisitScenarioResult() {
        StepsWithoutAuthorVisitor visitor = new StepsWithoutAuthorVisitor();
        Step step = new Step("Step", new ArrayList<>());
        List<Step> steps = new ArrayList<>();
        steps.add(step);
        List<String> authors = new ArrayList<>();
        authors.add("John Smith");
        Scenario scenario = new Scenario("Title", authors, steps);
        visitor.visit(scenario);
        assertTrue(visitor.getResult().isEmpty());
    }

    /**
     * Test z uzyciem scenariusza
     */
    @Test
    void testVisitScenarioWords() {
        StepsWithoutAuthorVisitor visitor = new StepsWithoutAuthorVisitor();
        Step step = new Step("Step", new ArrayList<>());
        List<Step> steps = new ArrayList<>();
        steps.add(step);
        List<String> authors = new ArrayList<>();
        authors.add("John Smith");
        Scenario scenario = new Scenario("Title", authors, steps);
        visitor.visit(scenario);
        List<String> expectedString = new ArrayList<>();
        expectedString.add("John Smith");
        expectedString.add("IF");
        expectedString.add("ELSE");
        expectedString.add("FOR EACH");
        assertEquals(expectedString, visitor.getWords());
    }

    /**
     * Test z uzyciem kroku ze slowem kluczowym
     */
    @Test
    void testVisitStepEmpty() {
        StepsWithoutAuthorVisitor visitor = new StepsWithoutAuthorVisitor();
        Step step = new Step("IF", new ArrayList<>());
        List<String> words = new ArrayList<>(Arrays.asList("IF", "ELSE", "FOR EACH"));
        visitor.setWords(words);
        visitor.setResult(new ArrayList<>());
        visitor.setStepNum(new ArrayList<>(Arrays.asList(0)));
        visitor.visit(step);
        assertEquals(new ArrayList<>(), visitor.getResult());
    }

    /**
     * Test z uzyciem kroku bez slowa kluczowego
     */
    @Test
    void testVisitStepWithoutSubSteps() {
        StepsWithoutAuthorVisitor visitor = new StepsWithoutAuthorVisitor();
        Step step = new Step("Step", new ArrayList<>());
        List<String> words = new ArrayList<>(Arrays.asList("IF", "ELSE", "FOR EACH"));
        visitor.setWords(words);
        visitor.setResult(new ArrayList<>());
        visitor.setStepNum(new ArrayList<>(Arrays.asList(0)));
        visitor.visit(step);
        assertEquals(new ArrayList<>(Arrays.asList("1. Step")), visitor.getResult());
    }

    /**
     * Test z uzyciem kroku z podkrokami
     */
    @Test
    void testVisitStep() {
        StepsWithoutAuthorVisitor visitor = new StepsWithoutAuthorVisitor();
        Step substep1 = new Step("Substep1", new ArrayList<>());
        Step substep2 = new Step("Substep2", new ArrayList<>());
        Step step = new Step("Step", Arrays.asList(substep1, substep2));
        List<String> words = new ArrayList<>(Arrays.asList("IF", "ELSE", "FOR EACH"));
        visitor.setWords(words);
        visitor.setResult(new ArrayList<>());
        visitor.setStepNum(new ArrayList<>(Arrays.asList(0)));
        visitor.visit(step);
        assertEquals(new ArrayList<>(Arrays.asList("1. Step", "1.1. Substep1", "1.2. Substep2")), visitor.getResult());
    }
}