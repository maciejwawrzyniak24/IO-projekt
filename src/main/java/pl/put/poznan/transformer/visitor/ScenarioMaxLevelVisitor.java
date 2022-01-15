package pl.put.poznan.transformer.visitor;

import pl.put.poznan.transformer.logic.Scenario;
import pl.put.poznan.transformer.logic.Step;

import java.util.ArrayList;
import java.util.List;

public class ScenarioMaxLevelVisitor implements Visitor{

    private Scenario scenario;

    private int level;

    private int actLevel = 0;

    public ScenarioMaxLevelVisitor(int level){
        this.level = level;
    }

    @Override
    public void visit(Step step) {
        actLevel++;
        if(actLevel >= level){
            step.setSubSteps(new ArrayList<>());
        }
        for (Step subStep : step.getSubSteps()) {
            subStep.accept(this);
        }
        actLevel--;
    }

    @Override
    public void visit(Scenario scenario) {
        this.scenario = scenario;
    }

    @Override
    public Object getResult() {
        return scenario;
    }
}
