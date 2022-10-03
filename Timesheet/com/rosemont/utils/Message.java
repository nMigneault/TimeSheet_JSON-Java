package com.rosemont.utils;

import com.rosemont.model.Project;

import java.util.ArrayList;

/**
 * Cette classe servira de conteneur pour les messages de validation liés aux différentes règles
 * à vérifier sur les données extraites du fichier JSON d'entrée.
 * Elle utilise un tableau de type ArrayList<String> pour stocker ces messages de validation
 */
public class Message
{
    private ArrayList<String> messageList;

    public Message()
    {
        messageList = new ArrayList<String>();
    }

    public void addMessage(String message)
    {
        messageList.add(message);
    }

    public ArrayList<String> getMessageList()
    {
        return messageList;
    }
}