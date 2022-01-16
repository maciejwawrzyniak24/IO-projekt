package pl.put.poznan.transformer.visitor;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.Scenario;
import pl.put.poznan.transformer.logic.Step;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StepsWithKeywordsVisitorTest {

    @Test
    void testVisitScenario() {
        StepsWithKeywordsVisitor visitor = new StepsWithKeywordsVisitor();
        Step step = new Step("Step", new ArrayList<>());
        List<Step> steps = new ArrayList<>();
        steps.add(step);
        List<String> authors = new ArrayList<>();
        authors.add("John Smith");
        Scenario scenario = new Scenario("Title", authors, steps);
        visitor.visit(scenario);
        assertEquals(0, visitor.getResult());
    }

    @Test
    void testVisitStep() {
        StepsWithKeywordsVisitor visitor = new StepsWithKeywordsVisitor();
        Step substep1 = new Step("IF ELSE FOR EACH", new ArrayList<>());
        Step substep2 = new Step("FOR QWE EACH", new ArrayList<>());
        Step step = new Step("IF Step", Arrays.asList(substep1, substep2));
        visitor.visit(step);
        assertEquals(2, visitor.getResult());
    }

    @Test
    void testVisitStepEmpty() {
        StepsWithKeywordsVisitor visitor = new StepsWithKeywordsVisitor();
        Step step = new Step("", new ArrayList<>());
        visitor.visit(step);
        assertEquals(0, visitor.getResult());
    }
}