import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//Flappy possède un oiseau

public class Flappy extends Canvas implements KeyListener {

    //(propriétés)
    protected int largeurEcran = 1000;
    protected int hauteurEcran = 600;

    protected BufferedImage image;
    protected  BufferedImage imageBas= ImageIO.read(new File("src/main/resources/tuyau.png"));
    protected BufferedImage imageHaut = ImageIO.read(new File("src/main/resources/tuyauHaut.png"));
    protected boolean pause = false;

    protected boolean enCours = true;

    protected Oiseau oiseau;
    //(propriété)création de la collection déplaçable et sprite
    protected ArrayList<Deplacable> listeDeplacable = new ArrayList<>();
    protected ArrayList<Sprite> listeSprite = new ArrayList<>();
    protected ArrayList<Tuyau> listeTuyau = new ArrayList<>();

//    protected Tuyau tuyau;
      protected int distanceTuyau = 50;
      protected int ecartTuyau = 60;

    public Flappy() throws InterruptedException, IOException {
        JFrame fenetre = new JFrame("Flappy");
        //On récupère le panneau de la fenêtre principale
        JPanel panneau = (JPanel) fenetre.getContentPane();
        //on définit la hauteur / largeur de l'écran
        panneau.setPreferredSize(new Dimension(largeurEcran, hauteurEcran));
        setBounds(0, 0, largeurEcran, hauteurEcran);
        //On ajoute cette classe (qui hérite de Canvas) comme composant du panneau principal
        panneau.add(this);

        fenetre.pack();
        fenetre.setResizable(false);
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.requestFocus();
        fenetre.addKeyListener(this);

//On indique que le rafraîchissement de l'écran doit être fait manuellement.
        createBufferStrategy(2);
        setIgnoreRepaint(true);
        this.setFocusable(false);


//appel de la methode démarrer
        demarrer();
    }

    public void initialiser() {

        pause = false;
        enCours = true;

//si c'est la première initialisation des oiseaux
        if (oiseau == null) {
            oiseau = new Oiseau(hauteurEcran);
            listeDeplacable.add(oiseau);
            listeSprite.add(oiseau);

//            Tuyau tuyau = new Tuyau(190, hauteurEcran, largeurEcran, 100);
//            listeDeplacable.add(tuyau);
//            listeSprite.add(tuyau);


//ajout plusieurs nuages!!
            for (int i = 0; i < 30; i++) {
                Nuage nuage = new Nuage(largeurEcran, hauteurEcran);
                listeDeplacable.add(nuage);
                listeSprite.add(nuage);
            }

            int nbrTuyau = 3;
            int decalage = (largeurEcran + 100) / nbrTuyau;

            for (int i = 0; i < nbrTuyau; i++) {
                Tuyau tuyau = new Tuyau(190, hauteurEcran, largeurEcran, i*decalage, imageBas);
                Tuyau tuyauHaut = new Tuyau(190, 190, largeurEcran, i*decalage, imageHaut);
                listeTuyau.add(tuyau);
                listeDeplacable.add(tuyau);
                listeSprite.add(tuyau);
                listeTuyau.add(tuyauHaut);
                listeDeplacable.add(tuyauHaut);
                listeSprite.add(tuyauHaut);
            }


        } else {
            for (Deplacable deplacable : listeDeplacable) {
                deplacable.reinitialiser(largeurEcran, hauteurEcran);
            }
        }
    }

    public void demarrer() throws InterruptedException {

        long point = 0;

        Font police = new Font("Calibri", Font.BOLD, 30);

        initialiser();

//        System.out.println(tuyau);
//        System.out.println(listeDeplacable.get(1));

        while (true) {


            Graphics2D dessin = (Graphics2D) getBufferStrategy().getDrawGraphics();

            //-----------------------------
            //reset dessin // ici pour changer le fond
            dessin.setColor(Color.WHITE);
//            dessin.fillRect(0, 0, largeurEcran, hauteurEcran);
            dessin.drawImage(image, 0, 0, null);
            try {
                image = ImageIO.read(new File("src/main/resources/background.jpg"));

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            for (Sprite sprite : listeSprite) {
                sprite.dessiner(dessin);
            }

            //affichage HUD
            dessin.setColor(Color.BLACK);
            dessin.setFont(police);
            dessin.drawString(
                    String.valueOf(point),
                    largeurEcran - 100,
                    50);

            if (enCours) {


                if (!pause) {

//si jamais l'oiseau est tombé par terre
                    if (oiseau.getY() > hauteurEcran - oiseau.getLargeur()) {
                        System.out.println("PERDU!! 😝");
                        enCours = false;

                    } else {
// sinon si le jeu continu / pour que l'oiseau descende
                        point ++;

                        // pour déplacer tout ce qui bouge
                        for (Deplacable deplacable : listeDeplacable) {
                            deplacable.deplacer(largeurEcran, hauteurEcran);
                        }

                        for (Tuyau tuyau : listeTuyau) {
                            if (Sprite.testCollision(oiseau, tuyau)) {
                                System.out.println("LOOSER 🤭");
                                enCours = false;

                            }
                        }



                    }
                } else {
//pour que la fenêtre change de couleur quand le jeu ait en pause et si on perd
                    dessin.setColor(new Color(0, 0, 0, 0.5f));
                    dessin.fillRect(0, 0, largeurEcran, largeurEcran);

//pour l'affichage du mot PAUSE
                    dessin.setColor(Color.BLACK);
                    dessin.setFont(police);
                    String String = "PAUSE";
                    dessin.drawString(
                            String.valueOf("---> PAUSE 😴 <---"),
                            largeurEcran - 660,
                            150);
                }
            } else {

                dessin.setColor(new Color(1f, 0, 0, 0.7f));
                dessin.fillRect(0, 0, largeurEcran, largeurEcran);
                //pour l'affichage du mot Perdu
                dessin.setColor(Color.BLACK);
                dessin.setFont(police);
                String String = "LOSER";
                dessin.drawString(
                        String.valueOf("`LOSER´👎 !! Try again... 😂  "),
                        largeurEcran - 700,
                        150);
            }
            //-----------------------------
            dessin.dispose();
            getBufferStrategy().show();
            Thread.sleep(1000 / 60);
        }

    }

    // creation de l'objet (this)
    public static void main(String[] args) throws InterruptedException, IOException {
        new Flappy();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
//sur la fenêtre faire espace plusieurs fois sinon on a perdu !!!
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            oiseau.sauter();
        }
//appuyer sur entrer pour réinitialiser le jeu!!
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            initialiser();

        }
// appuyer sur P pour mettre en pause le jeu et P pour reprendre
        if (e.getKeyCode() == KeyEvent.VK_P) {
            //inverser un boolean
            pause = !pause;

        }
//test pour verifier si écouteur fonctionne (bien mettre la souris sur la fenêtre Java!!)
//        if(e.getKeyCode()== KeyEvent.VK_SPACE){
//            System.out.println("ça marche 🙃!!!");
//        }
//        System.out.println(e.getKeyChar());
//        System.out.println(e.getKeyCode());
    }
}
