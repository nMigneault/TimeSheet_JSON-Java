package com.rosemont.utils;

import com.rosemont.model.DayOfWeek;
import com.rosemont.model.Project;
import java.util.HashSet;

public class Calculation
{
    /*
     * Cette méthode calcule le total d'heures de travail de bureau par jour
     */
    public static double calculateTotalOfficeWorkHoursPerDay(DayOfWeek dayOfWeek)
    {
        double total = 0.0;
        HashSet<Project> projectList = dayOfWeek.getProjectList();
        if(!projectList.isEmpty())
        {
            for(Project project : projectList)
            {
                if(project.getProjectType() == Project.ProjectType.OFFICE)
                {
                    total += project.getDuration();
                }
            }
        }
        total /= 60;
        return total;
    }

    /*
     * Cette méthode calcule le total d'heures de travail de bureau pour la semaine
     */
    public static double calculateTotalOfficeWorkHours(DayOfWeek[] daysOfWeek )
    {
        /*Attention : ici on comptabilise le total des HEURES de bureau par jour
        * Le résultat est déjà en heures, non en minutes.
        */
        double total = 0.0;
        for(DayOfWeek dayOfWeek : daysOfWeek)
        {
            total += calculateTotalOfficeWorkHoursPerDay(dayOfWeek);
        }
        return total;
    }

    /*
     * Cette méthode calcule le total d'heures de télétravail par jour
     */
    public static double calculateTotalHomeWorkHoursPerDay(DayOfWeek dayOfWeek)
    {
        double total = 0.0;
        HashSet<Project> projectList = dayOfWeek.getProjectList();
        if(!projectList.isEmpty())
        {
            for(Project project : projectList)
            {
                if(project.getProjectType() == Project.ProjectType.HOMEWORK)
                {
                    total += project.getDuration();
                }
            }
        }
        total /= 60;
        return total;
    }

    /*
     * Cette méthode calcule le total d'heures de télétravail pour la semaine
     */
    public static double calculateTotalHomeWorkHours(DayOfWeek[] daysOfWeek)
    {
        /*Attention : ici on comptabilise le total des HEURES de télétravail par jour
        * Le résultat est déjà en heures, non en minutes.
        */
        
        double total = 0.0;
        for(DayOfWeek dayOfWeek : daysOfWeek)
        {
            total += calculateTotalHomeWorkHoursPerDay(dayOfWeek);
        }
        return total;
        
    }

    /*
     * Cette méthode calcule le total d'heures de travail de bureau pour les jours ouvrables
     */
    public static double calculateTotalOfficeHoursOnWorkdays(DayOfWeek[] daysOfWeek)
    {
        /*Attention : ici on comptabilise le total des HEURES de travail de bureau par jour
        * ouvrable. Le résultat est déjà en heures, non en minutes.
        */
        double total = 0.0;
        for(DayOfWeek dayOfWeek : daysOfWeek)
        {
            if(dayOfWeek.isBusinessDay())
            {
                total += calculateTotalOfficeWorkHoursPerDay(dayOfWeek);
            }
        }
        return total;
    }
    
    //A-t-ont besoin de calculer le total des heures de travail pour la semaine, peut importe le travail?
}