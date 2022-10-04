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
// couleur par defaut RED (constructeur Sprite)
    public Carre(int x, int y, int largeur) {
        super(x, y);
        this.largeur = largeur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }
}
