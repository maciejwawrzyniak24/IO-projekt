package pl.put.poznan.transformer.visitor;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.Scenario;
import pl.put.poznan.transformer.logic.Step;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


/**
 * Testy wizytatora, ktory zwraca scenariusz w formie tekstu z numeracją kroków.
 * @author Maciej Wawrzyniak
 * @version 1.0
 */
public class GetScenarioTextVisitorTest {

    /**
     * Test z uzyciem pustego scenariusza
     */
    @Test
    void testVisitScenarioEmpty(){
        GetScenarioTextVisitor visitor = new GetScenarioTextVisitor();
        Scenario mockScenario = mock(Scenario.class);
        List<Step> steps = new ArrayList<>();
        steps.add(new Step("Step", new ArrayList<>()));
        when(mockScenario.getSteps()).thenReturn(steps);

        visitor.visit(mockScenario);

        assertEquals(new ArrayList<>(), visitor.getResult());
    }

    /**
     * Test z uzyciem przykładowego scenariusza z trzema krokami
     */
    @Test
    void testVisitScenario(){
        GetScenarioTextVisitor visitor = new GetScenarioTextVisitor();

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

        List<String> result = new ArrayList<>();
        result.add("1. step1");
        result.add("2. step2");
        result.add("3. step3");
        assertEquals(result, visitor.getResult());
    }

    /**
     * Test z uzyciem scenariusza z krokami i podkrokami
     */
    @Test
    void testVisitScenarioWithSubSteps(){
        GetScenarioTextVisitor visitor = new GetScenarioTextVisitor();

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

        List<String> result = new ArrayList<>();
        result.add("1. step1");
        result.add("2. step2");
        result.add("2.1. substep1");
        result.add("2.2. substep2");
        result.add("3. step3");
        assertEquals(result, visitor.getResult());
    }
}
