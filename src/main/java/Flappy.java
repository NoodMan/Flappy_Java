import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

//Flappy possède un oiseau

public class Flappy extends Canvas implements KeyListener {

    //(propriétés)
    protected int largeurEcran = 800;
    protected int hauteurEcran = 500;

    protected boolean pause = false;
    protected Oiseau oiseau;
    //(propriété)création de la collection déplaçable et sprite
    protected ArrayList<Deplacable> listeDeplacable = new ArrayList<>();
    protected ArrayList<Sprite> listeSprite = new ArrayList<>();

    protected Tuyau tuyau;

    public Flappy() throws InterruptedException {
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

//si c'est la première initialisation
        if (oiseau == null) {
            oiseau = new Oiseau(hauteurEcran);
            tuyau = new Tuyau(200, hauteurEcran, largeurEcran);

            listeDeplacable.add(tuyau);
            listeDeplacable.add(oiseau);


            listeSprite.add(tuyau);
            listeSprite.add(oiseau);

//ajout plusieurs nuages!!
            for (int i = 0; i < 25; i++) {
                Nuage nuage = new Nuage(largeurEcran, hauteurEcran);
                listeDeplacable.add(nuage);
                listeSprite.add(nuage);
            }
        } else {
            for (Deplacable deplacable : listeDeplacable) {
                deplacable.reinitialiser(largeurEcran, hauteurEcran);
            }
        }
    }

    public void demarrer() throws InterruptedException {

        long indexFrame = 0;

        initialiser();

//        System.out.println(tuyau);
//        System.out.println(listeDeplacable.get(1));

        while (true) {

            indexFrame++;
            Graphics2D dessin = (Graphics2D) getBufferStrategy().getDrawGraphics();

            //-----------------------------
            //reset dessin // ici pour changer le fond
            dessin.setColor(Color.WHITE);
            dessin.fillRect(0, 0, largeurEcran, hauteurEcran);

            for (Sprite sprite : listeSprite) {
                sprite.dessiner(dessin);
            }

            if (!pause) {

//si jamais l'oiseau est tombé par terre
                if (oiseau.getY() > hauteurEcran - oiseau.getLargeur()) {
                    System.out.println("PERDU!! 😝");
                    pause = true;
                } else {
// sinon si le jeu continu / pour que l'oiseau descende
//                    oiseau.deplacer();
// pour que le tuyau bouge
//                    tuyau.deplacer();

                    // pour déplacer tout ce qui bouge
                    for (Deplacable deplacable : listeDeplacable) {
                        deplacable.deplacer(largeurEcran, hauteurEcran);
                    }

                    if(Sprite.testCollision(oiseau, tuyau)) {
                        System.out.println("LOOSER 🤭");
                        pause = true;
                    }
                }
            } else {
//pour que la fenêtre change de couleur quand le jeu ait en pause et si on perd
                dessin.setColor(new Color(0, 0, 0, 0.1f));
                dessin.fillRect(0, 0, largeurEcran, largeurEcran);
            }

            //-----------------------------
            dessin.dispose();
            getBufferStrategy().show();
            Thread.sleep(1000 / 60);
        }
    }

    // creation de l'objet (this)
    public static void main(String[] args) throws InterruptedException {
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
