CODEL
Cédric KASSI 3303968
Mathieu LABAR 2900641
--------------------------------------------------------------
INSTALLATION :

- JAVA VERSION : SDK 8.0
- TOMCAT : 8.0
- MySQL

CONFIGURATION dans le fichier WEB-INF/applicationContext.xml:

- Spécifier la classe de driver de la base de donnée, (driverClassName par défaut com.mysql.jdbc.Driver)
- Spécifier l'url du sgbd (url)
- Spécifier le nom d'utilisateur de la bd (username)
- Spécifier le mot de passe de la bd (password)

UTILISATION :

-- Démarrage de tomcat

1. Ouvrir une console, et rendez-vous dans le dossier tomcat suivant :
$ /opt/apache-tomcat-8.0.14/bin
2. Lancer le serveur avec la commande :
$ ./startup.sh
3. Le serveur est désormais lancé, et disponible dans un navigateur internet,
à l'adresse suivante : http://localhost:8080/

-- Importer le projet dans tomcat

1. Rendez-vous sur la page : http://localhost:8080/manager/html
2. Déployer le fichier war sur le serveur.

