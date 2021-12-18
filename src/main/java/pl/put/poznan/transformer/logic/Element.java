package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.visitor.Visitor;

/**
 * Interfejs implementowany przez wszystkie możliwe elementy scenariusza
 * @author Przemyslaw Szymczak
 * @version 1.0
 */
public interface Element {
    /**
     * Obsluga wizytatora
     * @param visitor Klasa implementujaca interfejs "Visitor"
     */
    public void accept(Visitor visitor);

}
