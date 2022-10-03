package com.rosemont.model;

public class Project {

    public enum ProjectType
    {
        HOMEWORK,
        OFFICE
    }
    private int projectId;
    private int duration;
    private ProjectType projectType;         //Type du projet : énumération de type ProjectType

    public Project()
    {}

    public Project(int projectId, int duration)
    {
        if(projectId > 0 && duration > 0)
        {
            this.projectId = projectId;
            this.duration = duration;
            //Mise à jour automatique du type de projet
            updateProjectType();
        }

    }

    public void setProjectId(int projectId)
    {
        if(projectId > 0)
        {
            this.projectId = projectId;
            updateProjectType();
        }
    }
    public int getProjectId()
    {
        return projectId;
    }

    /*
     * Cette méthode est une méthode interne qui permet de mettre à jour le type d'un projet
     * à chaque modifiaction du code de ce dernier.
     */
    private void updateProjectType()
    {

        if(projectId <= 900) {
            projectType = ProjectType.OFFICE;
        }else
        {
            projectType = ProjectType.HOMEWORK;
        }

    }


    public ProjectType getProjectType()
    {
        return projectType;
    }

    public void setDuration(int duration)
    {
        if(duration > 0)
        {
            this.duration = duration;
            updateProjectType();
        }
    }

    public long getDuration()
    {
        return duration;
    }

    //N'existe que pour les test de débogage
    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", duration=" + duration +
                ", projectType=" + projectType +
                '}';
    }
}
