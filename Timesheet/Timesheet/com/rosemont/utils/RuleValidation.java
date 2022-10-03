package com.rosemont.utils;

import com.rosemont.model.DayOfWeek;
import com.rosemont.model.Employee;
import static com.rosemont.model.Employee.EmployeeType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RuleValidation
{

    /**
     * Règles 1 et 2 : Cette methode verifie si l'employé a travaillé le nombre d'heures
     * minimales au bureau par semaine excluant le télétravail.
     * Remrque : les règles 1 et 2 sont mutuellement exclusive, et c'est ce qui justifie
     * leur vérification dans une même méthode
     */
    public static void verifyMinWorkHoursPerWeek(Employee employee, Message messages)
    {

        final double MIN_WORK_HOURS_A_WEEK_NORMAL_EMPLOYEE = 38;
        final double MIN_WORK_HOURS_A_WEEK_ADMIN_EMPLOYEE = 36;
        DayOfWeek[] daysOfWeek = employee.getTimeCard().getDaysOfWeek();
        double totalHours = Calculation.calculateTotalOfficeWorkHours(daysOfWeek);
        System.out.println("Total des heures de travail de bureau par semaine : " + totalHours);
        Employee.EmployeeType employeeType = employee.getEmployeeType();
        if ((employeeType == EmployeeType.NORMAL) && (totalHours < MIN_WORK_HOURS_A_WEEK_NORMAL_EMPLOYEE))
        {
            messages.addMessage("Nombre minimal d'heures de travail au bureau par semaine NON ATTEINT");
        }else if(totalHours < MIN_WORK_HOURS_A_WEEK_ADMIN_EMPLOYEE)
        {
            messages.addMessage("Nombre minimal d'heures de travail au bureau par semaine NON ATTEINT");
        }

    }

    /**
     * Règle 3 : Cette methode vérifie qu'aucun employé ne dépasse plus de 43 heures au bureau par semaine.
     */
    public static void verifyMaximumWorkHoursPerWeekForAllTypesOfEmployees(Employee employee,Message messages)
    {
        final double MAXIMUM_WORK_HOURS_A_WEEK = 43;
        DayOfWeek[] daysOfWeek = employee.getTimeCard().getDaysOfWeek();
        double totalHours = Calculation.calculateTotalOfficeWorkHours(daysOfWeek);
        System.out.println("Total des heures de travail de bureau par semaine : " + totalHours);
        if (totalHours > MAXIMUM_WORK_HOURS_A_WEEK)
        {
            messages.addMessage("Nombre maximal d'heures de travail au bureau par semaine DÉPASSÉ");
        }
    }

    /**
     * Règle 4 : Cette methode verifie que les employés de l’administration ne doivent pas faire
     * plus de 10 heures de télétravail par semaine.
     */
    public static void verifyMaximumRemoteWorkHoursPerWeekForAdmin(Employee employee, Message messages)
    {

        double MAXIMUM_REMOTE_WORK_HOURS_A_WEEK = 10;
        DayOfWeek[] daysOfWeek = employee.getTimeCard().getDaysOfWeek();
        EmployeeType employeeType = employee.getEmployeeType();
        if (employeeType == EmployeeType.ADMINISTRATION)
        {
            
            double totalHours = Calculation.calculateTotalHomeWorkHours(daysOfWeek);
            System.out.println("Total des heures de télétravail par semaine : " + totalHours);
            if (totalHours > MAXIMUM_REMOTE_WORK_HOURS_A_WEEK)
            {
                messages.addMessage("Nombre maximal d'heures de télétravail par semaine DÉPASSÉ");
            }
        }
    }

    /**
     * Règles 6 et 7 : Cette methode verifie si l'employé a travaillé le nombre d'heures
     * minimales au bureau par jour ouvrable incluant les jours fériés.
     */
    public static void verifyMinimumWorkedHourPerBusinessDay(Employee employee,Message messages) {
        double MINIMUM_WORKED_HOURS_A_DAY = 6;
        EmployeeType employeeType = employee.getEmployeeType();
        if (employeeType == EmployeeType.ADMINISTRATION)
        {
            MINIMUM_WORKED_HOURS_A_DAY = 4;
        }
        DayOfWeek[] daysOfWeek = employee.getTimeCard().getDaysOfWeek();
        for (int i = 0; i < daysOfWeek.length; ++i)
        {
            DayOfWeek aDayOfWeek = daysOfWeek[i];
            if (aDayOfWeek.isBusinessDay())
            {
                double totalHours = Calculation.calculateTotalOfficeWorkHoursPerDay(aDayOfWeek);
                System.out.println("Total des heures de travail de bureau par jour ouvrable : " + totalHours);
                if (totalHours < MINIMUM_WORKED_HOURS_A_DAY)
                {
                    String message = " : Nombre minimal d'heures de travail au bureau par jour ouvrable NON ATTEINT";
                    messages.addMessage(aDayOfWeek.getDay() + message);

                }
            }
        }
    }
}