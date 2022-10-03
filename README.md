Timesheet
=========

Projet scolaire, équipe de 3 personnes, durée 14 jours à temps partiel.

Description
-----------

L’application est un logiciel qui effectue la validation du temps travaillé par des employés dans le
respect des règles de l’entreprise.

Dans l’organisation du client, chaque employé doit remplir une feuille de temps hebdomadaire. Les employés doivent
suivre plusieurs règles par rapport à leur temps travaillé qui sont dictées par l’entreprise. En fait, il y a tellement
de règles à valider que les responsables de l’entreprise n’arrivent plus à s’y retrouver. Ils ont besoin d’un logiciel qui
leur permettra de valider les règles de l’entreprise et qui génèrera les messages d’erreurs appropriés si une feuille de
temps viole les règles.

Le programme prendre un fichier JSON comme argument lors de l’exécution du logiciel dans une console. Le fichier source 
et résultat sont spécifiés à la console.

Spécifications
--------------

Le projet a été compilé avec la JDK 11, minimum requis pour exécuter le programme.

Contenu des répertoires
-----------------------

**Documentation**

Modélisation UML

**TimeSheet**

Contient le code source du projet, réalisée avec l'IDE ItelliJ.

**exécutable**

Le dossier exécutable contient l'exécutable TimeSheet.jar, qui doit être exécuté en ligne de commande, comme suit : 

__java -jar TimeSheet.jar inputfile.json result.json__, avec

_inputfile.json_ : fichier d'entrées au format JSON, contenant les données de la feuille de temps à valider.

_result.json_ : fichier de sortie contenant les résultats de la validation

**Feuilles_de_temps(tests)** 

Contient des feuilles de temps au format JSON à utiliser comme inputs pour tester l'application.
