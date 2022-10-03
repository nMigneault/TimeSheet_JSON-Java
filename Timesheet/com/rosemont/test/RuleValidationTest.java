package com.rosemont.test;

import com.rosemont.model.DayOfWeek;
import com.rosemont.model.Employee;
import com.rosemont.model.Project;
import com.rosemont.model.TimeCard;
import com.rosemont.utils.Message;
import com.rosemont.utils.RuleValidation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RuleValidationTest {
    static Message messagesTest = new Message();
    @Test
    @DisplayName("Test succès 1: méthode verifyMinWorkHoursPerWeek-admin")
    void verifyMinWorkHoursPerWeek() {
       // boolean result = RuleValidation.verifyMinWorkHoursPerWeek(createEmployee1ToTest(), messagesTest);
      //  assertEquals(true,result);
    }

    @Test
    void verifyMaximumWorkHoursPerWeekForAllTypesOfEmployees() {
    }

    @Test
    void verifyMaximumRemoteWorkHoursPerWeekForAdmin() {
    }

    @Test
    void verifyMinimumWorkedHourPerBusinessDay() {
    }

    @Test
    void verifyIfAllRulesRespected() {
    }

    Employee createEmployee1ToTest(){
        DayOfWeek emp1Day1 = new DayOfWeek(DayOfWeek.Day.JOUR1);
        DayOfWeek emp1Day2 = new DayOfWeek(DayOfWeek.Day.JOUR2);
        DayOfWeek emp1Day3 = new DayOfWeek(DayOfWeek.Day.JOUR3);
        DayOfWeek emp1Day4 = new DayOfWeek(DayOfWeek.Day.JOUR4);
        DayOfWeek emp1Day5 = new DayOfWeek(DayOfWeek.Day.JOUR5);
        DayOfWeek emp1Day6 = new DayOfWeek(DayOfWeek.Day.WEEKEND1);
        DayOfWeek emp1Day7 = new DayOfWeek(DayOfWeek.Day.WEEKEND2);
        DayOfWeek [] daysOfWeek1 = {emp1Day1,emp1Day2,emp1Day3,emp1Day4,emp1Day5,emp1Day6,emp1Day7};
        TimeCard timeCard1 = new TimeCard(daysOfWeek1);
        Employee employee1 = new Employee(900,timeCard1);
        emp1Day1.addProject(new Project(450,400));
        emp1Day2.addProject(new Project(950,300));
        emp1Day3.addProject(new Project(450,400));
        emp1Day4.addProject(new Project(450,450));
        emp1Day5.addProject(new Project(450,400));
        emp1Day6.addProject(new Project(450,460));
        emp1Day7.addProject(new Project(450,400));

        return employee1;
    }
}