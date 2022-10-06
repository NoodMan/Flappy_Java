import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tuyau extends Rectangle implements Deplacable{

    protected BufferedImage image;
    protected BufferedImage image1;

    protected int decalage;
    protected BufferedImage imageTuyau;


//    public Tuyau(int hauteur, int hauteurEcran, int largeurEcran, int decalage) {
//
//        super(largeurEcran - 100, hauteurEcran - hauteur, 100, hauteur);

    public Tuyau(int hauteur, int hauteurEcran, int largeurEcran, int decalage, BufferedImage imageTuyau) {
        super(largeurEcran - 100, hauteurEcran - hauteur, 100, hauteur);
        this.decalage = decalage;
        this.imageTuyau = imageTuyau;
        reinitialiser(largeurEcran,hauteurEcran);



//création tuyau haut et bas


        try {
            image = ImageIO.read(new File("src/main/resources/tuyau.png"));
            image1 = ImageIO.read(new File("src/main/resources/tuyauHaut.png"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

}
//crée dessin d'un tuyau
    @Override
    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
//        dessin.fillRect(x, y, largeur, hauteur);
        dessin.drawImage(imageTuyau, x, y,null);
    }
    @Override
    public void deplacer(int largeurEcran, int hauteurEcran) {
        // pour régler la vitesse du tuyau
        x -= 5;



        if (x < - largeur){
            x = largeurEcran;

        }
    }

    public void reinitialiser(int largeurEcran, int hauteurEcran) {
        x = largeurEcran + decalage;


    }
}
