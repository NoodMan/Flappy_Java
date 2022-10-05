import java.awt.*;

public class Nuage extends Rectangle implements Deplacable{

    public Nuage(int largeurEcran, int hauteurEcran) {
        super(0, 0, 0, 0, new Color(0, 0, 0, 0.2f));
        reinitialiser(largeurEcran,hauteurEcran);
    }

    @Override
    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillRect(x, y, largeur, hauteur);
    }

    @Override
    public void deplacer(int largeurEcran, int hauteurEcran) {
//        x = x -1; ou  x -= 1;
        x--;
        if(x < - largeur) {
            reinitialiser(largeurEcran, hauteurEcran);
            x += largeurEcran;
        }
    }

    @Override
    public void reinitialiser(int largeurEcran, int hauteurEcran) {
//pour que les nuages se déplace dans la fenêtre
        x = (int)(Math.random() * largeurEcran );
//pour que le nuage reste en haut de la fenêtre
        y = (int)(Math.random() * (hauteurEcran / 2));
//taille aléatoire les nuages
        largeur = (int)(Math.random() * 40 + 80);
        hauteur = (int)(Math.random() * 20 + 20);
    }
}
