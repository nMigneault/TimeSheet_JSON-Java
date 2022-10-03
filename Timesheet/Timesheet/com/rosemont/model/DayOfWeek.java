package com.rosemont.model;
import java.util.HashSet;
public class DayOfWeek
{
    public enum Day
    {
        JOUR1,
        JOUR2,
        JOUR3,
        JOUR4,
        JOUR5,
        WEEKEND1,
        WEEKEND2
    }
    private Day day;
    private HashSet<Project> projectList;
    public DayOfWeek()
    {
        projectList = new HashSet<Project>();
    }
    public DayOfWeek(Day day)
    {
        this();
        this.day = day;
    }

    public void addProject(Project project){
        projectList.add(project);
    }

    public Day getDay()
    {
        return day;
    }
    public void setDay(Day day) {
        this.day = day;
    }
    public HashSet<Project> getProjectList()
    {
        return projectList;
    }

    public void setProjectList(HashSet<Project> projectList)
    {
        this.projectList = projectList;
    }

    public boolean isBusinessDay()
    {
        return this.day == Day.JOUR1 ||
                this.day == Day.JOUR2 ||
                this.day == Day.JOUR3 ||
                this.day == Day.JOUR4 ||
                this.day == Day.JOUR5;
    }

    /*
     * Redéfinition de la méthode toString(). Elle n'est pas obligatoire pour le fonctionnement du
     * programme, mais elle nous été utile pour les tests et le débogage
     */
    @Override
    public String toString() {
        String output = "";
        for(Project project : projectList){
            output += project.toString();
        }
        return "DayOfWeek{" +
                "day=" + day +
                ", projectList : \n" +
                output;
    }
}
