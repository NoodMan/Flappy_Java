import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Flappy extends Canvas implements KeyListener {

    protected int largeurEcran = 700;
    protected int hauteurEcran = 500;

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

    public void demarrer() throws InterruptedException {

        long indexFrame = 0;


        while (true) {
            indexFrame++;
            Graphics2D dessin = (Graphics2D) getBufferStrategy().getDrawGraphics();

            //-----------------------------
            //reset dessin
            dessin.setColor(Color.WHITE);
            dessin.fillRect(0, 0, largeurEcran, hauteurEcran);

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
    public void keyReleased(KeyEvent e) {

//test pour verifier si ecouteur fonctionne (bien mettre la souris sur la fenetre Java!!)
        if(e.getKeyCode()== KeyEvent.VK_SPACE){
            System.out.println("Ca maaaarche 🙃!!!");
        }
//        System.out.println(e.getKeyChar());
//        System.out.println(e.getKeyCode());
    }
}
