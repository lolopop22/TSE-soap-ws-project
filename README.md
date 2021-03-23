# TSE-soap-ws-project
A soap web service project on teams and players

Le client se lance sur le port 9090.

#### Quelques remarques:
- Pour créer un nouveau joueur ou une nouvelle équipe, il faut mettre l'id (le tId pour les équipes et le pid pour les joueurs) à 0;
- Pour modifier une équipe, il faut tout d'abord récupérer l'équipe (faire une requête GET avec l'identifiant de l'équipe), puis récupérer toutes les informations retournées et enfin modifier ces données. Si vous ne procédez pas ainsi, vous écraserez les données de l'équipe déjà existant dans la base de données;
- À partir du joueur, on ne peut pas modifier une équipe;
- Avant d'utilsier [le client soap](https://github.com/lolopop22/TSE-soap-client-project) bien vouloir lancer les tests avant...