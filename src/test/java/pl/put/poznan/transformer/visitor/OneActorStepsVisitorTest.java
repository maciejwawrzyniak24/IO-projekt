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
 * Testy wizytatora, który zwraca kroki dla podanego actora
 * @author Maciej Wawrzyniak
 * @version 1.0
 */
public class OneActorStepsVisitorTest {

    /**
     * Test z uzyciem pustego scenariusza
     */
    @Test
    void testVisitScenarioEmpty(){
        OneActorStepsVisitor visitor = new OneActorStepsVisitor("Laptop");
        Scenario mockScenario = mock(Scenario.class);
        List<Step> steps = new ArrayList<>();
        steps.add(new Step("Step", new ArrayList<>()));
        when(mockScenario.getSteps()).thenReturn(steps);

        visitor.visit(mockScenario);

        assertEquals(new ArrayList<>(), visitor.getResult());
    }

    /**
     * Test z uzyciem scenariusza z 3 krokami
     */
    @Test
    void testVisitScenario(){
        OneActorStepsVisitor visitor = new OneActorStepsVisitor("Laptop");
        Step mockStep1 = mock(Step.class);
        when(mockStep1.getSubSteps()).thenReturn(new ArrayList<>());
        when(mockStep1.getText()).thenReturn("Laptop step1");
        Step mockStep2 = mock(Step.class);
        when(mockStep2.getSubSteps()).thenReturn(new ArrayList<>());
        when(mockStep2.getText()).thenReturn("step2");
        Step mockStep3 = mock(Step.class);
        when(mockStep3.getSubSteps()).thenReturn(new ArrayList<>());
        when(mockStep3.getText()).thenReturn("Laptop step3");
        List<Step> steps = Arrays.asList(mockStep1, mockStep2, mockStep3);

        Scenario mockObject = mock(Scenario.class);
        visitor.visit(mockObject);
        for (Step step: steps){
            visitor.visit(step);
        }

        List<String> result = new ArrayList<>();
        result.add("Laptop step1");
        result.add("Laptop step3");
        assertEquals(result, visitor.getResult());
    }

    /**
     * Test z uzyciem scenariusza z 3 krokami, ale zaden z nich nie zawiera Autora
     */
    @Test
    void testVisitScenarioNoAuthorsSteps(){
        OneActorStepsVisitor visitor = new OneActorStepsVisitor("Laptop");
        Step mockStep1 = mock(Step.class);
        when(mockStep1.getSubSteps()).thenReturn(new ArrayList<>());
        when(mockStep1.getText()).thenReturn("Telefon step1");
        Step mockStep2 = mock(Step.class);
        when(mockStep2.getSubSteps()).thenReturn(new ArrayList<>());
        when(mockStep2.getText()).thenReturn("step2");
        Step mockStep3 = mock(Step.class);
        when(mockStep3.getSubSteps()).thenReturn(new ArrayList<>());
        when(mockStep3.getText()).thenReturn("Aplikacja step3");
        List<Step> steps = Arrays.asList(mockStep1, mockStep2, mockStep3);

        Scenario mockObject = mock(Scenario.class);
        visitor.visit(mockObject);
        for (Step step: steps){
            visitor.visit(step);
        }

        assertEquals(new ArrayList<>(), visitor.getResult());
    }

    /**
     * Test z uzyciem scenariusza z krokami i podkrokami
     */
    @Test
    void testVisitScenarioWithSubsteps(){
        OneActorStepsVisitor visitor = new OneActorStepsVisitor("Laptop");
        Step mockStep1 = mock(Step.class);
        when(mockStep1.getSubSteps()).thenReturn(new ArrayList<>());
        when(mockStep1.getText()).thenReturn("Laptop step1");
        List<Step> substeps = new ArrayList<>();
        substeps.add(new Step("substep1 Laptop", new ArrayList<>()));
        substeps.add(new Step("substep2", new ArrayList<>()));
        Step mockStep2 = mock(Step.class);
        when(mockStep2.getSubSteps()).thenReturn(substeps);
        when(mockStep2.getText()).thenReturn("step2");
        Step mockStep3 = mock(Step.class);
        when(mockStep3.getSubSteps()).thenReturn(new ArrayList<>());
        when(mockStep3.getText()).thenReturn("Laptop step3");
        List<Step> steps = Arrays.asList(mockStep1, mockStep2, mockStep3);

        Scenario mockObject = mock(Scenario.class);
        visitor.visit(mockObject);
        for (Step step: steps){
            visitor.visit(step);
        }

        List<String> result = new ArrayList<>();
        result.add("Laptop step1");
        result.add("substep1 Laptop");
        result.add("Laptop step3");
        assertEquals(result, visitor.getResult());
    }

    /**
     * Test z uzyciem scenariusza z krokami i podkrokami, ale zaden z nich nie zawiera autora
     */
    @Test
    void testVisitScenarioWithSubstepsNoAuthorsSteps(){
        OneActorStepsVisitor visitor = new OneActorStepsVisitor("Laptop");
        Step mockStep1 = mock(Step.class);
        when(mockStep1.getSubSteps()).thenReturn(new ArrayList<>());
        when(mockStep1.getText()).thenReturn("step1 aplikacja");
        List<Step> substeps = new ArrayList<>();
        substeps.add(new Step("substep1", new ArrayList<>()));
        substeps.add(new Step("substep2 serwer", new ArrayList<>()));
        Step mockStep2 = mock(Step.class);
        when(mockStep2.getSubSteps()).thenReturn(substeps);
        when(mockStep2.getText()).thenReturn("step2 telefon");
        Step mockStep3 = mock(Step.class);
        when(mockStep3.getSubSteps()).thenReturn(new ArrayList<>());
        when(mockStep3.getText()).thenReturn("step3");
        List<Step> steps = Arrays.asList(mockStep1, mockStep2, mockStep3);

        Scenario mockObject = mock(Scenario.class);
        visitor.visit(mockObject);
        for (Step step: steps){
            visitor.visit(step);
        }

        assertEquals(new ArrayList<>(), visitor.getResult());
    }
}
