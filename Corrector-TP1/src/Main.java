/*
TP1: Correcteur
Fait par:
    -   Kamen Damov 20102811
    -   Ibrahim Melzi 20066033
Derniere mise a jour: 29 octobre 2022

--------------------------------------

Ce programme est une interface graphique pour corriger
les fautes d'ortographes. Lorsque le programme est lancé on peut
voir 4 boutons et 2 textarea.
Pour les boutons:
    - Choisir: Importer une fichier texte dans l'interface afin qu'il soit corrigé.
    - Dictionnaire: Importer un dictionnaire qui va servir de mesure de comparaison pour les mots écrit ou importés.
    - Écrire: Sauvegarder uun fichier txt.
    - Vérifier: Lancer la correction du texte écrit ou importé.

Pour les textarea:
    - Le premier textarea est utilisé pour écrire ou importé du texte.
        La correction sera fait sur celui-ci.
    - Le deuxième textarea sert à proposer les 5 mots les plus proche.

Déroulement du programme:
Lorsque l'utilisateur rentre un dictionnaire, écrit ou importe du texte, et appuit sur vérifier,
les mots qui ne se retrouvent pas dans le dictionnaire sont surlignés en rouge. On peut visualiser les mots
similaires en cliquant à droite sur la souris. On peut ensuite seléctionner un mot proposé
en cliquant à gauche sur la souris, ce qui placera le mot choisit dans le texte.

Survol structurel:
Ce programme contient 3 classes:
    - GUI: Déclaration des éléments graphiques de l'interface. Inclut des méthodes pour charger, sauvegarder, et afficher.
        Inclut une méthode qui va lancer les processus plus précis de traitement de texte
    - Dico: Produit une structure interne du dictionnaire. Cette classe inclut des méthodes de nettoyage de texte,
        de vectorisation, de recherche des mots les plus proches.
    - Corrector: Corrige les mots et se charge de produire les données à ajouter à l'interface. Inclut également les
        méthodes nécéssairse pour surligner le texte.

*/

public class Main {
    public static void main(String[] args) {
        //Partir le programme
        gui.GUI g = new gui.GUI();
    }
}
