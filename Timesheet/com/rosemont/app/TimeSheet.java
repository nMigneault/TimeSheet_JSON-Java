package com.rosemont.app;
import com.rosemont.model.*;
import com.rosemont.utils.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.HashSet;

public class TimeSheet {
    public static void main(String[] argv){

        /*
        * Usage : java -jar TimeSheet.jar inputfile.json messages result.json
        *
        * On vérifie d'abord que le programme a été appelé avec le bon nombre d'arguments.
        * Dans le cas contraire, on affiche le message d'erreur nombre d'arguments insuffisants
        * et on affiche à l'utilisateur la bonne façon de l'exécuter
        *
        * Le programme est appelé avec 2 arguments 
        * argv[0] : inputfile.json
        * argv[1] : result.json
        *
        */

        if (argv.length != 2)
        {
            System.out.println("Erreur : nombre insuffisant d'arguments");
            System.out.println("Usage : java -jar TimeSheet.jar inputfile.json result.json");
            return;
        }
        Message messages = new Message();
        String inputFileName = argv[0];
        String outputFileName = argv[1];
        JSONObject timeCardData = ProcessingJsonData.loadData(inputFileName);
        if(timeCardData == null)
        {
            System.out.println("Une erreur critique s'est produit pendant le chargement des données JSON.");
            System.out.println("Le programme va maintenant se fermer.");
            return;
        }
        
        Employee employee = initializeEmployee(timeCardData);
        if(employee == null){
            System.out.println("Une ou plusieurs clés JSON liées aux jours de la semaine sont erronées.");
            System.out.println("Le programme va maintenant se fermer");
            return;
        }
        RuleValidation.verifyMinimumWorkedHourPerBusinessDay(employee, messages);
        RuleValidation.verifyMinWorkHoursPerWeek(employee, messages);
        RuleValidation.verifyMaximumWorkHoursPerWeekForAllTypesOfEmployees(employee, messages);
        RuleValidation.verifyMaximumRemoteWorkHoursPerWeekForAdmin(employee, messages);
        ProcessingJsonData.writeData(outputFileName, messages, employee);
        System.out.println("La validation de la temps s'est effectuée avec succès");
        System.out.println("Le résultat est dans le fichier result.json");
    }

    public static Employee initializeEmployee(JSONObject timeCardData) {
        JSONArray[] daysJsonData = ProcessingJsonData.getDaysFromJsonData(timeCardData);
        if(daysJsonData == null){
            /*Il y a eu une erreur au niveau d'une clé JSON pour un jour de semaine.
            * On renvoie alors la référence null pour arrêter le programme dans main() 
            (voir la méthode ProcessingJsonData.getDaysFromJsonData()).
            */
            return null;
        }
        Employee employee = new Employee();
        TimeCard timeCard = new TimeCard();
        employee.setEmployeeId(ProcessingJsonData.getEmployeeIdFromJsonData(timeCardData));
        DayOfWeek[] daysOfWeek = ProcessingJsonData.getDaysOfWeek(daysJsonData);

        timeCard.setDaysOfWeek(daysOfWeek);
        employee.setTimeCard(timeCard);
        return employee;
    }

}