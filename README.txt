TODO:

1. sowohl ein TexturedOpenGL-Objektyp als auch ein ColerdOpenGL-Objekttyp anbieten


######################################



Mehrere Waffen mit mehreren Waffenbuttons und reload anzeige:

ShootImageViewButton (erbt von ImageViewButton)

ShootID = ShootImageViewButtonID und View holt sich vom Model die IDs der Waffen vom Spieler
und der button

Button Befehle an Player weiterleiten:
Button zustand wird gesetzt und ist f�r die SpielerAnimationsKlasse �ber das Model abrufbar



PROBLEM: die ImageViewButtons dynamisch zurlaufzeit einf�gen, da man dann ncht mehr den gew�nschten style setzen kann!
L�sungsansatz: Coustome ImageViewKlasse erstellen und in dieser den Style setzen
					ODER
		android style res to attributeset

es soll nichts mehr mit ImageViews erledigt werden im Spiel! statdessen sollen die Buttons OpenGL Grafiken sein!
das Problem ist nur das man die Positionen der Buttons dann selber angeben muss...



EINARBEITEN:

Falls es noch mehr Objekte auf Basis einer ImageView geben wird als es nicht nur den ImageViewButton gibt, dann
soll es die Oberklassen TexturedOpenGlEntity und ImageViewEnity geben!


TODO ALS N�CHSTES:
	1. Fehler beheben der beim ver�ndern der Lautst�rke die App zum Abbrechen bringt
	2. Aliens k�nnen nach unten aus dem Spielfeld "abhauen" und "fallen" dann unendlich tief runter!!
