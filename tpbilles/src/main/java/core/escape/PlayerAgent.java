package core.escape;

import core.AgentPhysique;
import core.Directions;
import core.Environnement;
import core.SMA;
import core.billes.AgentBille;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.MainEscape;

/**
 * Created by leemans on 27/01/16.
 */
public class PlayerAgent extends AgentPhysique {
	private Directions direction;
	private Rectangle rectangle;

	public PlayerAgent(Environnement environnement, SMA sma, int posX, int posY) {
		super(environnement, sma, posX, posY);

		rectangle = new Rectangle(5, 5, Color.CHARTREUSE);
		MainEscape.canvas.getChildren().add(rectangle);
		rectangle.relocate(posX * 5, posY * 5);
		direction = Directions.IMMOBILE;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}
	
	public void setDirection(Directions direction){
		this.direction = direction;
	}

	@Override
	public void decide() {
		System.out.println("je d�cide");
		 int nextPosX = posX + direction.getDirX();
	        int nextPosY = posY + direction.getDirY();
	        AgentPhysique[][] locations = environnement.getLocations();

	        if (locations[nextPosY][nextPosX] != null) {
	            this.direction = Directions.IMMOBILE;
	            return;
	        }


	        locations[posY][posX] = null;
	        posX = nextPosX;
	        posY = nextPosY;
	        locations[posY][posX] = this;
	        this.getRectangle().relocate(posX, posY);
	}

}