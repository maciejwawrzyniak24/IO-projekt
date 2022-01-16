package pl.put.poznan.transformer.visitor;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.Scenario;
import pl.put.poznan.transformer.logic.Step;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HowManyStepsVisitorTest {

    @Test
    void testVisitScenario() {
        HowManyStepsVisitor visitor = new HowManyStepsVisitor();
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
        HowManyStepsVisitor visitor = new HowManyStepsVisitor();
        Step substep1 = new Step("Substep1", new ArrayList<>());
        Step substep2 = new Step("Substep2", new ArrayList<>());
        Step step = new Step("Step", Arrays.asList(substep1, substep2));
        visitor.visit(step);
        assertEquals(3, visitor.getResult());
    }

    @Test
    void testVisitStepEmpty() {
        HowManyStepsVisitor visitor = new HowManyStepsVisitor();
        Step step = new Step("", new ArrayList<>());
        visitor.visit(step);
        assertEquals(1, visitor.getResult());
    }
}