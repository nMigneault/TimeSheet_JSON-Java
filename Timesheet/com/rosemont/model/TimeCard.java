package com.rosemont.model;

import java.util.Arrays;

public class TimeCard {
    /*
     * daysOfWeek est tableau des 7 entrées de la carte de l'employé. Chacune de ces entrée correspond
     * à un jour de la semaine courante
     */
    private DayOfWeek[] daysOfWeek;

    public TimeCard()
    {}
    public TimeCard(DayOfWeek[] daysOfWeek)
    {
        this.daysOfWeek = daysOfWeek;
    }

    public DayOfWeek[] getDaysOfWeek()
    {
        return daysOfWeek;
    }

    public void setDaysOfWeek(DayOfWeek[] daysOfWeek)
    {
        this.daysOfWeek = daysOfWeek;
    }

    /*
    * Redéfinition de la méthode toString(). Elle n'est pas obligatoire pour le fonctionnement du
    * programme, mais elle nous été utile pour les tests et le débogage
     */
    @Override
    public String toString() {
        String output = "";
        for(DayOfWeek dayOfWeek : daysOfWeek)
            output += dayOfWeek.toString() + "\n";
        return "TimeCard{\n" +
                "daysOfWeek=\n" + output +
                '}';
    }
}