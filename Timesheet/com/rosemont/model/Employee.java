package com.rosemont.model;
public class Employee {

    public enum EmployeeType {
        NORMAL,
        ADMINISTRATION
    }

    private int employeeID;
    private EmployeeType employeeType;
    private TimeCard timeCard;
    public Employee()
    {}
    public Employee(int employeeID, TimeCard timeCard) {
        this(employeeID);
        this.timeCard = timeCard;
    }
    public Employee(int employeeID)
    {
        this.employeeID = employeeID;
        updateEmployeeType();

    }

    public TimeCard getTimeCard() {
        return timeCard;
    }
    public void setTimeCard(TimeCard timeCard) {
        this.timeCard = timeCard;
    }
    public void setEmployeeId(int employeeID)
    {
        if(employeeID > 0)
        {
            this.employeeID = employeeID;
        }
        updateEmployeeType();
    }
    public int getEmployeeId()
    {
        return employeeID;
    }

    public EmployeeType getEmployeeType()
    {
        return employeeType;
    }

    private void updateEmployeeType()
    {
        if(this.employeeID < 1000)
        {
            employeeType = EmployeeType.ADMINISTRATION;
        }else
        {
            employeeType = EmployeeType.NORMAL;
        }
    }

    /*
     * Redéfinition de la méthode toString(). Elle n'est pas obligatoire pour le fonctionnement du
     * programme, mais elle nous été utile pour les tests et le débogage
     */
    @Override
    public String toString() {
        return "Employee : \n" +
                "employeeID=" + employeeID +
                ", employeeType=" + employeeType +
                "\nCarte de temps : \n" + timeCard +
                '\n';
    }
}
