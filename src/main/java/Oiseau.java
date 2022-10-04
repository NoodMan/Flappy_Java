import java.awt.*;

public class Oiseau extends Carre {

    protected int vitesseVertical;



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
