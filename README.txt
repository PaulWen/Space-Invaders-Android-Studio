Derzeit ist die komplette APP noch für das Galaxy S3 ausgelegt!
Das bedeutet, das die APP auf jeden anderen Smarphone mit anderen Screen Dimensionen scheiße aussieht und nicht läuft.

1. das Game wiede anzeigen können!! (derzeit ist nur ein Roter Bildschirm zusehen)
	(ich sollte in den alten Workspace gucken und schauen, ob es dort noch eine funktionierende Version gibt, um auszuschließen, dass es es nicht am One PluseOne liegt)
2. sowohl ein TexturedOpenGL-Objektyp als auch ein ColerdOpenGL-Objekttyp anbieten


######################################



Mehrere Waffen mit mehreren Waffenbuttons und reload anzeige:

ShootImageViewButton (erbt von ImageViewButton)

ShootID = ShootImageViewButtonID und View holt sich vom Model die IDs der Waffen vom Spieler
und der button

Button Befehle an Player weiterleiten:
Button zustand wird gesetzt und ist für die SpielerAnimationsKlasse über das Model abrufbar



PROBLEM: die ImageViewButtons dynamisch zurlaufzeit einfügen, da man dann ncht mehr den gewünschten style setzen kann!
Lösungsansatz: Coustome ImageViewKlasse erstellen und in dieser den Style setzen
					ODER
		android style res to attributeset

es soll nichts mehr mit ImageViews erledigt werden im Spiel! statdessen sollen die Buttons OpenGL Grafiken sein!
das Problem ist nur das man die Positionen der Buttons dann selber angeben muss...



EINARBEITEN:

Falls es noch mehr Objekte auf Basis einer ImageView geben wird als es nicht nur den ImageViewButton gibt, dann
soll es die Oberklassen TexturedOpenGlEntity und ImageViewEnity geben!


TODO ALS NÄCHSTES:
	1. Fehler beheben der beim verändern der Lautstärke die App zum Abbrechen bringt
	2. Aliens können nach unten aus dem Spielfeld "abhauen" und "fallen" dann unendlich tief runter!!
