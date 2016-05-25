package br.com.itengine.score.entity;

/**
 * Created by thiag on 24/05/2016.
 */
public enum ActionType {

    GOAL("GOAL"),
    FAUL("FAUL"),
    ASIST("ASIST"),
    SUSPENSION("SUSPENSION");

    private final String label;

    private ActionType(String label) {
        this.label = label;
    }

    public String toString() {
        return this.label;
    }

}
