import java.awt.*;

public abstract class Rectangle extends Carre {

    protected int hauteur;

    public Rectangle(int x, int y, int largeur, int hauteur, Color couleur) {
        super(x, y, largeur, couleur);
        this.hauteur = hauteur;
    }
// couleur par défaut Green
    public Rectangle(int x, int y, int largeur, int hauteur) {
        super(x, y, largeur, Color.BLACK);
        this.hauteur = hauteur;
    }

    @Override
    public boolean collision(int x, int y) {
        return x >= this.x
                && x <= this.x+ largeur
                && y > this.y
                && y <= this.y + hauteur;
    }
    @Override
    public int getCenterY() {
        return largeur / 2;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }
}
