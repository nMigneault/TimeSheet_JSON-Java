package com.rosemont.utils;
import static com.rosemont.model.DayOfWeek.Day;

public class Util {
    /**
     * Cette méthode retoune un tableau String[] contenant les représentations en chaines de caractères
     * des constantes de l'énumération Day. Ce tableau de chaines servira à itérer l'objet JSON afin
     * d'extraire les entrées liées aux jours de la semaine
     * Comme les entrées liéses aux jours sont indexées par des clés qui peuvent être mappées
     * avec les constantes de l'énumération de la classe DayOfWeek (DayOfWeek.Day), il est judicieux
     * d'utiliser cette dernière
     */
    public static String[] stringsFromDays()
    {
        String[] dayStrings = new String[Day.values().length];
        int i = 0;
        for(Day item : Day.values())
        {
            //On convertit la constante courante de l'énumération en minuscules
            dayStrings[i] = item.toString().toLowerCase();
            i++;
        }
        return dayStrings;
    }

    /**
     * Cette méthode retoune la constante Day associé à l'entier reçu en paramètre
     */
    public static Day intToEnum(int index)
    {
        Day day = null;
        switch(index)
        {
            case 0: day = Day.JOUR1;
                    break;
            case 1: day = Day.JOUR2;
                    break;
            case 2: day = Day.JOUR3;
                    break;
            case 3: day = Day.JOUR4;
                    break;
            case 4: day =  Day.JOUR5;
                    break;
            case 5: day = Day.WEEKEND1;
                    break;
            case 6: day = Day.WEEKEND2;
                    break;
        }
        return day;
    }
}
