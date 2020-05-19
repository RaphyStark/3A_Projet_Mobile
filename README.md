# Projet de programmation mobile : API des armures d'Iron Man

### Présentation
Cette application mobile premet de visualiser une liste non exhaustive (pas encore) des armures Iron-Man visibles dans les films éponymes et dans l'ensemble du MCU (Marvel Cinematic Universe).
Elle présente deux écrans : le premier pour l'affichage de la liste et le second pour l'affichage des details de l'armure sur laquelle l'utilisateur a cliqué.


### Consignes respectées
* Liste affichée dans une première activity (main_activity)
* Detail lors du click utilisateur dans la deuxième activity (detail_activity)
* Appel sur une API créée pour le projet et sur ce repo GitHub
* Données stockées dans le cache
* Architecture MVC (+data)
* Singletons utilisés pour les objets : 
*Gson
*SharedPreferences
*MarkApi
* Principe SOLID respecté notamment S et D :
*S (Responsabilité unique) : chaque méthode n'a qu'une responsabilité, idem pour les classes
*D (Inversion des dépendances) : des interfaces ont été crée justement dans le but de dépendre d'abstractions et non d'implémentation spécifique à chaque méthode.
* 1 controller par activity
* Divers design patterns
* Gitflow : 
*un commit par grande avancée
*une branche créee pour reprendre plus tard le projet en clean architecture (cf. dernier paragraphe du readme)

### Fonctionalités
|Premier écran|Deuxième écran|
|------------|-------------|
| <img src="https://raw.githubusercontent.com/RaphyStark/ProjetMobile/master/img_readme/v1.png" width="100"> | <img src="https://raw.githubusercontent.com/RaphyStark/ProjetMobile/master/img_readme/Mark1.png" width="100"> |

### A améliorer dans de prochaines versions
* Rajouter des informations dans l'API afin d'afficher plus d'imformations sur l'armure dans la detail_activity
Il serai par exemple intéressant d'ajouter des informations sur les armures que ce soit dans les films du MCU ou dans les comics, les informations qui auraient été laissé de côté dans les films mais que les fans des comics aimeraient retrouver dans l'application.

* Reprendre la branche laissée de côté afin de reprendre le travail sur la clean architecture
Comme la branche master et la branche MarkRepository ne sont pas au même avancement, il faudra récupérer les idées principales (les fichiers java MarkRepository et le fichier de Callback et l'intégrer au master)
