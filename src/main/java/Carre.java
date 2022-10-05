import java.awt.*;

public abstract class Carre extends Sprite{

    protected int largeur;

    @Override
    public int getCenterX() {
        return largeur / 2;
    }

    @Override
    public int getCenterY() {
        return largeur / 2 ;
    }

    public Carre(int x, int y, int largeur, Color couleur) {
        super(x, y, couleur);
        this.largeur = largeur;
    }
// couleur par défaut RED (constructeur Sprite)
    public Carre(int x, int y, int largeur) {
        super(x, y);
        this.largeur = largeur;
    }

    @Override
    public boolean collision(int x, int y) {
        return x >= this.x
                && x <= this.x+ largeur
                && y > this.y
                && y <= this.y + largeur;
    }


    @Override
    public boolean collision(Sprite sprite) {

        if(sprite instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) sprite;
// pour la collision entre oiseau et tuyau en haut a gauche puis droite puis bas gauche puis droite
            return this.collision(rectangle.getX(), rectangle.getY())
                    || this.collision(rectangle.getX() + rectangle.getLargeur(), rectangle.getY()) //vérification Horizontal
                    || this.collision(rectangle.getX(),rectangle.getY() + rectangle.getHauteur()) //vérification Vertical
                    || this.collision(
                    rectangle.getX() + rectangle.getLargeur(),
                    rectangle.getY() + rectangle.getHauteur());

        } else  if(sprite instanceof Carre){
            Carre carre = (Carre)sprite;

            return this.collision(carre.getX(), carre.getY())
                    || this.collision(carre.getX() + carre.getLargeur(), carre.getY()) //vérification Horizontal
                    || this.collision(carre.getX(),carre.getY() + carre.getLargeur()) //vérification Vertical
                    || this.collision(
                    carre.getX() + carre.getLargeur(),
                    carre.getY() + carre.getLargeur());

        }
       return false;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }
}
