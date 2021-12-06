package pl.put.poznan.transformer.logic;

import org.springframework.stereotype.Service;
import pl.put.poznan.transformer.visitor.Visitor;

import java.util.Arrays;
import java.util.List;


/**
 * Implementacja logiki biznesowej skupiajacej sie na przetwarzaniu i zapisywaniu scenariusza
 */
@Service
public class ScenarioService {

    /**
     * Pole przechowujace scenariusz w formie klasy "Scenario"
     * Po uruchomieniu API inicjalizowane przykladowym scenariuszem
     * @see Scenario
     */
    private Scenario scenario = getExScenario();

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    /**
     * Wywolanie na scenariuszu funkcji akceptujacej wizytytora
     * @param visitor Klasa implementujaca interfejs "Visitor"
     */
    public void accept(Visitor visitor)
    {
        scenario.accept(visitor);
    }

    /**
     * Funkcja generujaca przykladowy scenariusz w postaci klasy "Scenario"
     * @return Zwraca przykladowy scenariusz
     */
    public Scenario getExScenario(){
        List<String> authors = Arrays.asList(new String[]{"Uzytkownik", "Komputer", "Aplikacja"});

        List<Step> thirdLayer = Arrays.asList();
        Step third = new Step("Krok poziom drugi", thirdLayer);
        Step third2 = new Step("Nastepny krok poziom drugi", thirdLayer);

        List<Step> secondLayer = Arrays.asList(new Step[]{third, third2});
        Step second = new Step("Krok poziom pierwszy", secondLayer);

        List<Step> firstLayer = Arrays.asList(new Step[]{second});

        return new Scenario("Przyklad", authors, firstLayer);
    }
}
