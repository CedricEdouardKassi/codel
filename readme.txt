CODEL
C�dric KASSI 3303968
Mathieu LABAR 2900641
--------------------------------------------------------------
INSTALLATION :

- JAVA VERSION : SDK 8.0
- TOMCAT : 8.0
- MySQL

CONFIGURATION dans le fichier WEB-INF/applicationContext.xml:

- Sp�cifier la classe de driver de la base de donn�e, (driverClassName par d�faut com.mysql.jdbc.Driver)
- Sp�cifier l'url du sgbd (url)
- Sp�cifier le nom d'utilisateur de la bd (username)
- Sp�cifier le mot de passe de la bd (password)

UTILISATION :

-- D�marrage de tomcat

1. Ouvrir une console, et rendez-vous dans le dossier tomcat suivant :
$ /opt/apache-tomcat-8.0.14/bin
2. Lancer le serveur avec la commande :
$ ./startup.sh
3. Le serveur est d�sormais lanc�, et disponible dans un navigateur internet,
� l'adresse suivante : http://localhost:8080/

-- Importer le projet dans tomcat

1. Rendez-vous sur la page : http://localhost:8080/manager/html
2. D�ployer le fichier war sur le serveur.

