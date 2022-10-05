import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Nuage extends Rectangle implements Deplacable{

    protected BufferedImage image;
    protected BufferedImage image1;
    protected BufferedImage image2;
    public Nuage(int largeurEcran, int hauteurEcran) {
        super(0, 0, 0, 0, new Color(0, 0, 0, 0.2f));
        reinitialiser(largeurEcran,hauteurEcran);
        x -= (int)(Math.random() * largeurEcran);

        try {
            image = ImageIO.read(new File("src/main/resources/nuage1.png"));
            image1 = ImageIO.read(new File("src/main/resources/nuage2.png"));
            image2 = ImageIO.read(new File("src/main/resources/nuage3.png"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
//        dessin.fillRect(x, y, largeur, hauteur);
        dessin.drawImage(image, x, y,null);
        dessin.drawImage(image1, x, y,null);
        dessin.drawImage(image2, x, y,null);
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
