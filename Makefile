# Variables
JC = javac
JCFLAGS = -encoding UTF-8 -implicit:none

JVM = java
JVMFLAGS = 

# Règles essentielles
SameGame.class: SameGame.java PanneauJeu.class LogiqueJeu.class Bloc.class EcouteurDeSouris.class SurbrillanceSouris.class ChoixModeJeu.class
	${JC} ${JCFLAGS} SameGame.java

PanneauJeu.class: PanneauJeu.java LogiqueJeu.class Bloc.class EcouteurDeSouris.class SurbrillanceSouris.class ChoixModeJeu.class
	${JC} ${JCFLAGS} PanneauJeu.java

SurbrillanceSouris.class: SurbrillanceSouris.java
	${JC} ${JCFLAGS} SurbrillanceSouris.java
	
LogiqueJeu.class: LogiqueJeu.java Bloc.class
	${JC} ${JCFLAGS} LogiqueJeu.java

EcouteurDeSouris.class: EcouteurDeSouris.java
	${JC} ${JCFLAGS} EcouteurDeSouris.java

Bloc.class: Bloc.java
	${JC} ${JCFLAGS} Bloc.java

ChoixModeJeu.class: ChoixModeJeu.java
	${JC} ${JCFLAGS} ChoixModeJeu.java

# Règles optionnelles
run: SameGame.class
	${JVM} ${JVMFLAGS} SameGame

clean:
	-rm -f *.class

mrproper: clean SameGame.class

# Buts factices
.PHONY: run clean mrproper

# Fin
