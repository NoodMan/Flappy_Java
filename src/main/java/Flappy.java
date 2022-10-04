import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Flappy possede un oiseau


public class Flappy extends Canvas implements KeyListener {

    protected int largeurEcran = 700;
    protected int hauteurEcran = 400;

    protected boolean pause = false;
    protected Oiseau oiseau;

    public Flappy() throws InterruptedException {
        JFrame fenetre = new JFrame("Flappy");
        //On récupère le panneau de la fenetre principale
        JPanel panneau = (JPanel) fenetre.getContentPane();
        //On définie la hauteur / largeur de l'écran
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

//On indique que le raffraichissement de l'ecran doit être fait manuellement.
        createBufferStrategy(2);
        setIgnoreRepaint(true);
        this.setFocusable(false);


// appel de la methode demarrer
        demarrer();
    }
    public void initialiser(){
        oiseau = new Oiseau(hauteurEcran);
        oiseau.setVitesseVertical(-1);
        pause = false;
    }

    public void demarrer() throws InterruptedException {

        long indexFrame = 0;

        initialiser();


        while (true) {
            indexFrame++;
            Graphics2D dessin = (Graphics2D) getBufferStrategy().getDrawGraphics();

            //-----------------------------
            //reset dessin
            dessin.setColor(Color.WHITE);
            dessin.fillRect(0, 0, largeurEcran, hauteurEcran);

            oiseau.dessiner(dessin);

            if(!pause) {

//si jamais l'oiseau est tombé par terre
                if (oiseau.getY() > hauteurEcran - oiseau.getLargeur()) {
                    System.out.println("PERDU!! 😝");
                    pause = true;
                } else {
// sinon si le jeu continu / pour que l'oiseau descende
                    oiseau.deplacement();
                }
            }else  {
                //pour que la fenetre change de couleur quand le jeu est en pause et si on perd
                dessin.setColor(new Color(0, 0, 0, 0.1f));
                dessin.fillRect(0, 0, largeurEcran, largeurEcran);
            }
            //LE CODE ICI <----

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
    public void keyReleased(KeyEvent e){
//sur la fenetre faire espace plusieur fois sinon on n'as perdu !!!
        if(e.getKeyCode()== KeyEvent.VK_SPACE){
           oiseau.setVitesseVertical(2);
        }
//appuyer sur enter pour reinitialiser le jeu!!
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            initialiser();
        }
// appuyer sur P pour mettre en pause le jeu et P pour reprendre
        if(e.getKeyCode() == KeyEvent.VK_P){
            //inverser un boolean
            pause = !pause;
        }
//test pour verifier si ecouteur fonctionne (bien mettre la souris sur la fenetre Java!!)
//        if(e.getKeyCode()== KeyEvent.VK_SPACE){
//            System.out.println("Ca maaaarche 🙃!!!");
//        }
//        System.out.println(e.getKeyChar());
//        System.out.println(e.getKeyCode());
    }
}
