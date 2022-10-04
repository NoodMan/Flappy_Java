import java.awt.*;

public class Oiseau extends Carre  implements Deplacable{

    protected float vitesseVertical;

    public void setVitesseVertical(float vitesseVertical) {
        this.vitesseVertical = vitesseVertical;
    }

    protected  final static int HAUTEUR_OISEAU = 40;

//couleur par défaut de l'oiseau RED
    public Oiseau(int x, int y) {
        super(x, y, HAUTEUR_OISEAU);
        this.vitesseVertical = 0;
    }

    public Oiseau(int hauteurEcran) {
//pour center le carre (divise l'écran/ 2 - Taille de l'oiseau / 2 dans la hauteur
        super(50, getHauteurDepart(hauteurEcran), HAUTEUR_OISEAU);
        this.vitesseVertical = 0;
    }

    public static int getHauteurDepart(int hauteurEcran) {
        return hauteurEcran / 2 - HAUTEUR_OISEAU / 2;
    }

    public void reinitialiser(int hauteurEcran) {
        y = y = hauteurEcran / 2 - HAUTEUR_OISEAU / 2;
        vitesseVertical = 0;
    }
    @Override
    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillRect(x,y, largeur, largeur);
    }
    @Override
    public void deplacer(){
//pour diminuer la vitesse vertical
        if(vitesseVertical % 10 != 0&& vitesseVertical < 0) {
            y-= vitesseVertical - 0.5f ;
        }else {
            y -= vitesseVertical;
        }
        vitesseVertical -= 0.05f;
// pour déplacer l'oiseau vers le haut (touche espace)
        if(y < 0) {
            vitesseVertical = Math.abs(vitesseVertical) * -1;
        }
    }

    public void sauter(){

    }



}
