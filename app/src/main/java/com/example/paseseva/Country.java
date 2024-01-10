package com.example.paseseva;

public class Country {
    private final String name;
    private final String capital;
    private final String flagURL;

    public Country(String name, String capital, String flagURL) {
        this.name = name;
        this.capital = capital;
        this.flagURL = flagURL;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getFlagURL() {
        return flagURL;
    }
}
