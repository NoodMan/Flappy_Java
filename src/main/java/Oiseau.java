import java.awt.*;

public class Oiseau extends Carre {

    protected int vitesseVertical;

//couleur par defaut de l'oiseau RED
    public Oiseau(int x, int y) {
        super(x, y, 40);
        this.vitesseVertical = 0;
    }

    @Override
    public void dessiner(Graphics2D dessin) {

    }

    public void sauter(){

    }

    public int getVitesseVertical() {
        return vitesseVertical;
    }

    public void setVitesseVertical(int vitesseVertical) {
        this.vitesseVertical = vitesseVertical;
    }
}
