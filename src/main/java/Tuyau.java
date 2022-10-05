import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tuyau extends Rectangle implements Deplacable{

    protected BufferedImage image;
    public Tuyau(int hauteur, int hauteurEcran, int largeurEcran) {
        super(largeurEcran - 100, hauteurEcran - hauteur, 100, hauteur);
//création tuyau haut et bas


        try {
            image = ImageIO.read(new File("src/main/resources/tuyau.png"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

//crée dessin d'un tuyau
    @Override
    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
//        dessin.fillRect(x, y, largeur, hauteur);
        dessin.drawImage(image, x, y,null);
    }
    @Override
    public void deplacer(int largeurEcran, int hauteurEcran) {
        // pour régler la vitesse du tuyau
        x -= 2;
    }

    public void reinitialiser(int largeurEcran, int hauteurEcran) {
        x = largeurEcran;
    }
}
