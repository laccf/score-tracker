package br.com.itengine.score.entity;

/**
 * Created by thiag on 24/05/2016.
 */
public enum Role {
    ROOT("ROLE_ROOT"),
    LEAGUE("ROLE_LEAGUE"),
    TEAM("ROLE_TEAM"),
    DELEGATE("ROLE_DELEGATE");

    private final String label;

    private Role(String label) {
        this.label = label;
    }

    public String toString() {
        return this.label;
    }
}
