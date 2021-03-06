package pl.put.poznan.transformer.visitor;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.Scenario;
import pl.put.poznan.transformer.logic.Step;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Testy wizytatora ktory liczy wszystkie kroki w scenariuszu
 * @author Kajetan Wencierski
 * @version 1.0
 */
class HowManyStepsVisitorTest {

    /**
     * Test z uzyciem pustego scenariusza
     */
    @Test
    void testVisitScenarioEmpty() {
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

    /**
     * Test z uzyciem kroku z podkrokami
     */
    @Test
    void testVisitStep() {
        HowManyStepsVisitor visitor = new HowManyStepsVisitor();
        Step substep1 = new Step("Substep1", new ArrayList<>());
        Step substep2 = new Step("Substep2", new ArrayList<>());
        Step step = new Step("Step", Arrays.asList(substep1, substep2));
        visitor.visit(step);
        assertEquals(3, visitor.getResult());
    }

    /**
     * Test z uzyciem pustego kroku
     */
    @Test
    void testVisitStepEmpty() {
        HowManyStepsVisitor visitor = new HowManyStepsVisitor();
        Step step = new Step("", new ArrayList<>());
        visitor.visit(step);
        assertEquals(1, visitor.getResult());
    }

    /**
     * Test z uzyciem scenariusza z 3 krokami
     */
    @Test
    void testVisitScenario(){
        HowManyStepsVisitor visitor = new HowManyStepsVisitor();

        Step mockStep1 = mock(Step.class);
        when(mockStep1.getSubSteps()).thenReturn(new ArrayList<>());
        when(mockStep1.getText()).thenReturn("step1");
        Step mockStep2 = mock(Step.class);
        when(mockStep2.getSubSteps()).thenReturn(new ArrayList<>());
        when(mockStep2.getText()).thenReturn("step2");
        Step mockStep3 = mock(Step.class);
        when(mockStep3.getSubSteps()).thenReturn(new ArrayList<>());
        when(mockStep3.getText()).thenReturn("step3");
        List<Step> steps = Arrays.asList(mockStep1, mockStep2, mockStep3);

        Scenario mockScenario = mock(Scenario.class);

        visitor.visit(mockScenario);
        for (Step step: steps){
            visitor.visit(step);
        }

        assertEquals(3, visitor.getResult());
    }

    /**
     * Test z uzyciem scenariusza z krokami i podkrokami
     */
    @Test
    void testVisitScenarioWithSubSteps(){
        HowManyStepsVisitor visitor = new HowManyStepsVisitor();

        Step mockStep1 = mock(Step.class);
        when(mockStep1.getSubSteps()).thenReturn(new ArrayList<>());
        when(mockStep1.getText()).thenReturn("step1");
        List<Step> substeps = new ArrayList<>();
        substeps.add(new Step("substep1", new ArrayList<>()));
        substeps.add(new Step("substep2", new ArrayList<>()));
        Step mockStep2 = mock(Step.class);
        when(mockStep2.getSubSteps()).thenReturn(substeps);
        when(mockStep2.getText()).thenReturn("step2");
        Step mockStep3 = mock(Step.class);
        when(mockStep3.getSubSteps()).thenReturn(new ArrayList<>());
        when(mockStep3.getText()).thenReturn("step3");
        List<Step> steps = Arrays.asList(mockStep1, mockStep2, mockStep3);

        Scenario mockScenario = mock(Scenario.class);

        visitor.visit(mockScenario);
        for (Step step: steps){
            visitor.visit(step);
        }

        assertEquals(5, visitor.getResult());
    }
}