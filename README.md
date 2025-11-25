# Simulateur de Particules en Scala

Un simulateur de particules développé en Scala utilisant des principes de programmation fonctionnelle.

## Description

Ce projet implémente un système de simulation de particules en mouvement avec détection de collisions. Les particules se déplacent dans un espace 2D et changent de direction lorsqu'elles entrent en collision avec une autre particule.

## Fonctionnalités

- **Mouvement des particules** : Chaque particule se déplace selon sa direction courante
- **Détection de collisions** : Après chaque déplacement, le périmètre autour de chaque particule est vérifié
- **Gestion des collisions** : Lorsque deux particules se heurtent, elles changent de direction aléatoirement
- **Programmation fonctionnelle** : Implémenté entièrement en Scala avec des principes FP

## Démonstration

https://github.com/user-attachments/assets/ae47a924-3809-4c52-9dac-d7dd82593471

## Architecture

### Fichiers principaux

- `Direction.scala` : Gestion des directions et des vecteurs de mouvement
- `Particle.scala` : Définition et logique des particules
- `Main.scala` : Point d'entrée et boucle de simulation

##### Principes de programmation fonctionnelle

## Compilation et exécution

```bash
sbt compile
sbt run
