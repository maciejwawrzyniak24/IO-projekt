package pl.put.poznan.transformer.visitor;

import pl.put.poznan.transformer.logic.Scenario;
import pl.put.poznan.transformer.logic.Step;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OneActorStepsVisitor implements Visitor{

    private List<String> result;

    private String autor;

    public OneActorStepsVisitor(String autor)
    {
        this.autor = autor;
    }

    @Override
    public void visit(Step step) {
        if (step.getText().contains(autor)) {
            result.add(step.getText());
        }
        for (Step subStep : step.getSubSteps()) {
            subStep.accept(this);
        }
    }

    @Override
    public void visit(Scenario scenario) {
        result = new ArrayList<>();
    }

    @Override
    public Object getResult() {
        return result;
    }
}
