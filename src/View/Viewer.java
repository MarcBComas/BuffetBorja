package View;

import Model.EstadistiquesBuffets;
import Model.EstadistiquesChefs;
import Model.EstadistiquesComensals;
import Model.Rellotge;

import javax.imageio.ImageIO;
import java.awt.Canvas;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class Viewer extends Canvas {
    private Image background;
    private Image[] imgsChef;
    private Image[] imgsComensal;
    private Image[] imgsBuffet;

    private Coordenadas[][] coordinatesChef = {
            {new Coordenadas(365,50), new Coordenadas(485,50), new Coordenadas(610,50), new Coordenadas(420,50),  new Coordenadas(545,50), new Coordenadas(665,50)},
            {new Coordenadas(290, 10), new Coordenadas(350, 10), new Coordenadas(410, 10), new Coordenadas(470, 10), new Coordenadas(530, 10), new Coordenadas(590, 10), new Coordenadas(650, 10), new Coordenadas(710, 10), new Coordenadas(770, 10)},
            {new Coordenadas(350,180), new Coordenadas(390,180), new Coordenadas(430,180), new Coordenadas(470,180), new Coordenadas(510,180), new Coordenadas(550,180), new Coordenadas(590,180), new Coordenadas(350,220), new Coordenadas(590,220)},
    };

    private Coordenadas[][] coordinatesComensal = {
            {new Coordenadas(315, 395), new Coordenadas (385, 395), new Coordenadas(315, 450), new Coordenadas(385, 450), new Coordenadas(315, 515), new Coordenadas(385, 515), new Coordenadas(315, 570), new Coordenadas(385, 570), new Coordenadas(315, 645), new Coordenadas(385, 645), new Coordenadas(315, 705), new Coordenadas(385, 705), new Coordenadas(585, 395), new Coordenadas(655, 395), new Coordenadas(585, 450), new Coordenadas(655, 450), new Coordenadas(585, 515), new Coordenadas(655, 515), new Coordenadas(585, 570), new Coordenadas(655, 570), new Coordenadas(585, 645), new Coordenadas(655, 645), new Coordenadas(585, 705), new Coordenadas(655, 705), new Coordenadas(730, 400), new Coordenadas(770, 360), new Coordenadas(810,400), new Coordenadas(770, 440), new Coordenadas(730,555), new Coordenadas(770, 515), new Coordenadas(810,555), new Coordenadas(770, 595), new Coordenadas(730, 695), new Coordenadas(770, 655), new Coordenadas(810,695), new Coordenadas(770, 735)},
            {new Coordenadas(770, 735), new Coordenadas(810, 695), new Coordenadas(770, 655), new Coordenadas(730, 695), new Coordenadas(770, 595), new Coordenadas(810, 555), new Coordenadas(770, 515), new Coordenadas(730, 555), new Coordenadas(810, 400), new Coordenadas(770, 440), new Coordenadas(730, 400), new Coordenadas(655, 705), new Coordenadas(585, 705), new Coordenadas(655, 645), new Coordenadas(585, 645), new Coordenadas(655, 570), new Coordenadas(585, 570), new Coordenadas(655, 515), new Coordenadas(585, 515), new Coordenadas(655, 450), new Coordenadas(585, 450), new Coordenadas(655, 395), new Coordenadas(585, 395), new Coordenadas(385, 705), new Coordenadas(315, 705), new Coordenadas(385, 645), new Coordenadas(315, 645), new Coordenadas(385, 570), new Coordenadas(315, 570), new Coordenadas(385, 515), new Coordenadas(315, 515), new Coordenadas(385, 450), new Coordenadas(315, 450), new Coordenadas(385, 395), new Coordenadas(315, 395)},
            {new Coordenadas(395,345), new Coordenadas(430,345), new Coordenadas(465,345), new Coordenadas(500,345), new Coordenadas(535,345), new Coordenadas(570,345), new Coordenadas(415,380), new Coordenadas(450,380), new Coordenadas(485,380), new Coordenadas(520,380), new Coordenadas(555,380), new Coordenadas(435,415), new Coordenadas(470,415), new Coordenadas(505,415), new Coordenadas(540,415), new Coordenadas(435,450), new Coordenadas(470,450), new Coordenadas(505,450), new Coordenadas(540,450), new Coordenadas(435,485), new Coordenadas(470,485), new Coordenadas(505,485), new Coordenadas(540,485), new Coordenadas(435,520), new Coordenadas(470,520), new Coordenadas(505,520), new Coordenadas(540,520), new Coordenadas(435,555), new Coordenadas(470,555), new Coordenadas(505,555), new Coordenadas(540,555), new Coordenadas(435,590), new Coordenadas(470,590), new Coordenadas(505,590), new Coordenadas(540,590), new Coordenadas(490, 625)}
    };

    private Coordenadas[][] coordinatesPlatsBuffet = {
            {new Coordenadas(420, 292), new Coordenadas(420, 315), new Coordenadas(450, 292), new Coordenadas(450, 315)},
            {new Coordenadas(485,290), new Coordenadas(485,310), new Coordenadas(510, 290), new Coordenadas(510, 310)},
            {new Coordenadas(540, 285), new Coordenadas(540, 295), new Coordenadas(540, 305), new Coordenadas(540, 315)},
    };

    private Coordenadas[][] coordinatesColaPlats = {
            {new Coordenadas(410, 230), new Coordenadas(438, 230), new Coordenadas(425, 247)},
            {new Coordenadas(490, 230), new Coordenadas(467, 245), new Coordenadas(515, 245)},
            {new Coordenadas(545, 220), new Coordenadas(545, 235), new Coordenadas(545, 250)},
    };

    public Viewer() {
        setBG();
        setImgsBuffet();
        setImgsChef();
        setImgsComensal();
    }

    public Image getBG() {
        return this.background;
    }

    public void setBG() {
        try {
            this.background = ImageIO.read(new File("img/restaurante.png"));
        } catch (IOException e) {
            System.out.println("Error al carregar la imatge");
        }
    }

    public void setImgsChef() {
        try {
            this.imgsChef = new Image[3];
            this.imgsChef[0] = ImageIO.read(new File("img/cocinar.png"));
            this.imgsChef[1] = ImageIO.read(new File("img/descansar.png"));
            this.imgsChef[2] = ImageIO.read(new File("img/entregar.png"));
        } catch (IOException e) {
            System.out.println("Error al carregar la imatge");
        }
    }

    public void setImgsComensal() {
        try {
            this.imgsComensal = new Image[3];
            this.imgsComensal[0] = ImageIO.read(new File("img/comer.png"));
            this.imgsComensal[1] = ImageIO.read(new File("img/tertulia.png"));
            this.imgsComensal[2] = ImageIO.read(new File("img/esperar.png"));
        } catch (IOException e) {
            System.out.println("Error al carregar la imatge");
        }
    }

    public void setImgsBuffet() {
        try {
            this.imgsBuffet = new Image[3];
            this.imgsBuffet[0] = ImageIO.read(new File("img/hamburguesa.png"));
            this.imgsBuffet[1] = ImageIO.read(new File("img/pizza.png"));
            this.imgsBuffet[2] = ImageIO.read(new File("img/pasta.png"));
        } catch (IOException e) {
            System.out.println("Error al carregar la imatge");
        }
    }

    public Image[] getImgsChef() {
        return this.imgsChef;
    }

    public Image[] getImgsComensal() {
        return this.imgsComensal;
    }

    public Image[] getImgsBuffet() {
        return this.imgsBuffet;
    }

    public void draw() {
        this.getGraphics().drawImage(getBG(), 0, 0, null);

        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < EstadistiquesChefs.chefsPerEstat[i]; j++) {
                this.getGraphics().drawImage(this.getImgsChef()[i], this.coordinatesChef[i][j].getX(), this.coordinatesChef[i][j].getY(), null);
            }
            for(int j = 0; j < EstadistiquesComensals.comensalsPerEstat[i]; j++) {
                this.getGraphics().drawImage(this.getImgsComensal()[i], this.coordinatesComensal[i][j].getX(), this.coordinatesComensal[i][j].getY(), null);
            }
            for(int j = 0; j < EstadistiquesBuffets.platsPerAreaBuffet[i]; j++) {
                this.getGraphics().drawImage(this.getImgsBuffet()[i], this.coordinatesPlatsBuffet[i][j].getX(), this.coordinatesPlatsBuffet[i][j].getY(), null);
            }
            for(int j = 0; j < EstadistiquesBuffets.platsEnColaPerAreaBuffet[i]; j++) {
                this.getGraphics().drawImage(this.getImgsBuffet()[i], this.coordinatesColaPlats[i][j].getX(), this.coordinatesColaPlats[i][j].getY(), null);
            }
        }
        this.getGraphics().drawString("Minuto actual: " + String.valueOf(Rellotge.getRellotge().getMinutoActual()), 10, 20);
    }
}
