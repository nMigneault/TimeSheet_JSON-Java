package com.rosemont.utils;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.rosemont.model.*;

import com.rosemont.model.DayOfWeek;

import static com.rosemont.model.DayOfWeek.Day;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class ProcessingJsonData
{
    public static JSONObject loadData(String fileName)
    {
        FileReader fileReader = null;
        JSONParser jsonParser = new JSONParser();
        JSONObject timeCardData = null;
        try {
            fileReader = new FileReader(fileName);
            timeCardData = (JSONObject) jsonParser.parse(fileReader);
        } catch (FileNotFoundException e) {
            System.out.println("Impossible de trouver le fichier timesheet.json : " + e.getMessage());
        } catch (IOException e)
        {
            System.out.println("Une erreur est survenue lors de l'ouverture du fichier timesheet.json : "
                    + e.getMessage());
        } catch (ParseException e)
        {
            System.out.println("Le format du fichier timesheet.json est invailde " + e.getMessage());
        }
        return timeCardData;
    }
    
     /**
     * Cette méthode regroupe tous les messages créés par les autres méthodes et les affiche sur un
     * fichier de sortie .json. Les messages sont affichés par jour de travail
     *
     */
    public static void writeData(String outputFileName, Message messages, Employee employee) 
    {
        ArrayList<String> messageList = messages.getMessageList();
        JSONObject messagesJsonData = new JSONObject();
        messagesJsonData.put("numeroEmploye : ", employee.getEmployeeId());
        JSONArray entryListJsonData = new JSONArray();
        for (String entry : messageList) {
            entryListJsonData.add(entry);
        }
        if(entryListJsonData.size() == 0)
        {
            messagesJsonData.put("messages", "La feuille de temps respecte toutes les règles de l'entreprise");
        }else
        {
            messagesJsonData.put("messages", entryListJsonData);
        }
        
        //Écriture dans le fichier de sortie
        try {
            FileWriter outputFile = new FileWriter(outputFileName);
            outputFile.write(messagesJsonData.toJSONString());
            outputFile.close();
        } catch (IOException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    /*
     * Cette méthode revoie le numéro de l'employé propriétaire de la feuille de temps
     * @param timeCardData : objet JSON représentant les données de la carte de temps
     * @return : numéro de l'employé propriétaire de la carte de temps
     */
    public static int getEmployeeIdFromJsonData(JSONObject timeCardData)
    {
        /*
         * L'API JSON-Simple renvoie des objets de type Long pour les données numériques entières. Pour les
         * valeurs à virgule, l'objet renvoyé est de type Double.
         */
        return ((Long)timeCardData.get("numero employe")).intValue();
    }

    /*
     * Cette méthode revoie les données associées aux jours de la semaines de travail
     * @param timeCardData : objet JSON représentant les données de la carte de temps
     * @return : tableau d'objet JSONArray, représentant les données associées aux jours de la
     * semaine de travail pour l'employé courant.
     * NB : les données JSON associé à un jour de semaine sont représentées dans le fichier sous
     * forme d'un tableau. Il s'agit donc d'un objet JSONArray
     */
    public static JSONArray[] getDaysFromJsonData(JSONObject timeCardData){
        /*
        * Tableau de 7 objet de type JSONArray, dont les éléments contiennent les entrées liées
        * au temps de travail de l'employé sur les projets durant la journée en question
         */
        String[] dayStrings = Util.stringsFromDays();
        JSONArray[] daysData = new JSONArray[7];
        int i = 0; //Réinitialisation afin de le réutiliser pour indexer le tableau daysData

        for(String dayString : dayStrings)
        {
            JSONArray dayJsonObject = (JSONArray)timeCardData.get(dayString);
            /*
            * Dans le cas où au il y a au moins une clé erronée pour le jour dans le fichier JSON
            * on retourne null et on arrête le programme du côté de l'appelant.
            */
           
            if(dayJsonObject == null)
            {
                return null;
            }
            daysData[i] = dayJsonObject;
            i++;
        }
        return daysData;
    }
    /*
     * Cette méthode renvoie un tableau de type DayOfWeek qui contiendra toute les informations sur
     * les projets sur lesquels l'employé a travaillé durant la semaine
     */

    public static DayOfWeek[] getDaysOfWeek(JSONArray[] daysJsonData){
        DayOfWeek[] days = new DayOfWeek[daysJsonData.length];
        for(int i = 0; i < daysJsonData.length; i++)
        {
            DayOfWeek day = new DayOfWeek();
            /*
             * On récupère la constante de l'énumération Day correspond à l'index i qui servira à
             * la création d'un objet DayOfWeek.
             * On récupère ensuite l'ensemble des projets correspondant au jour courant :
             * daysJsonData[i] représente les données sur les projets pour le jour courant
             */

            day.setDay(Util.intToEnum(i));
            JSONObject[] projectListJsonData = projectListJsonData(daysJsonData[i]);
            /*
             * On va parcourir le tableau des données JSON correspondant aux projets et on créer
             * les objets Project correspondant au jour courant.
             */

            HashSet<Project> projectList = getProject(projectListJsonData);
            day.setProjectList(projectList);
            days[i] = day;
        }
        return days;
    }

    /*
    * Cette méthode reçoit en entrée un tableau d'objets JSONObject contenant les données JSON
    * concerant les projets sur lesquels un employé à travaillé durant un jour de semaine donné.
    * Elle renvoie en sortie un HashSet<Project> avec ces données.
     */
    public static HashSet<Project> getProject(JSONObject[] projectListJsonData){
        HashSet<Project> projectList = new HashSet<Project>();
        for(JSONObject projectJsonData : projectListJsonData){

            int projectId = ((Long)projectJsonData.get("projet")).intValue() ;
            int duration = ((Long)projectJsonData.get("minutes")).intValue();
            Project project = new Project(projectId, duration);
            projectList.add(project);
        }
        return projectList;
    }

    /*
     * Cette méthode reçoit en entrée un objet JSONArray contenant les données JSON
     * portant sur un jour de la semaine. Elle renvoie en sortie un tableau d'objets
     * JSONObject, représentant les données liées aux projets du jour en question
     */
    public static JSONObject[] projectListJsonData(JSONArray dayJsonData){
        JSONObject[] projectListJsonData = new JSONObject[dayJsonData.size()];
        for(int i = 0; i < projectListJsonData.length; i++){
            //La méthode get() d'un JSONObject renvoie un Objet, d'où le cast vers JSONObject
            JSONObject entryJsonData = (JSONObject) dayJsonData.get(i);
            projectListJsonData[i] = entryJsonData;
        }
        return projectListJsonData;
    }

}
