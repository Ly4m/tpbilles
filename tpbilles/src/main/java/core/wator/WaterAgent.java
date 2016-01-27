package core.wator;

import core.AgentPhysique;
import core.Environnement;
import core.SMA;
import core.Directions;
import javafx.scene.shape.Circle;
import main.MainWater;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class WaterAgent extends AgentPhysique {

    protected Directions direction;


    protected boolean agonie;
    protected Circle Circle;
    protected int age, birthCount, starvingCount;

    public void setCircle(Circle Circle) {
        this.Circle = Circle;
    }

    protected boolean estMangeable;

    public WaterAgent(Environnement env, SMA sma, int posX, int posY, Directions direction) {
        super(env, sma, posX, posY);
        this.direction = direction;
    }

    public boolean getEstMangeable(){
        return this.estMangeable;
    }

    public Circle getCircle() {
        return Circle;
    }

    public Directions getDirection() {
        return direction;
    }

    public abstract Directions estRencontrePar(WaterAgent autre);

    public void meurt() {
        MainWater.canvas.getChildren().remove(this.Circle);
        environnement.removeAgent(this);
        sma.removeAgentApres(this);
        this.agonie = true;
        if(this instanceof Shark){
            MainWater.sharkCount--;
        } else {
            MainWater.nemoCount--;
        }
    }

    public Directions findRandomEmptyCase() {

        List<Directions> listEmpty = new ArrayList<Directions>();

        Directions[] directionses = Directions.values();

        for (int i = 1; i < directionses.length; i++) {

            int newX = (posX + directionses[i].getDirX() + environnement.getLargeur()) % environnement.getLargeur();
            int newY = (posY + directionses[i].getDirY() + environnement.getHauteur()) % environnement.getHauteur();

            if (environnement.getLocations()[newY][newX] == null) {
                listEmpty.add(directionses[i]);
            }
        }

        if (!listEmpty.isEmpty()) {
            Collections.shuffle(listEmpty);
            return listEmpty.get(0);
        }

        return null;
    }
}
