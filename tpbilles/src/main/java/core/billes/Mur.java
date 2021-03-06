package core.billes;

import core.Environnement;
import core.SMA;

import java.awt.*;

public class Mur extends AgentBille {

	private TypeMur type;

	public enum TypeMur {
		VERTICAL {
			public Directions rebond(Directions origine) {
				return origine.getOpposeX();
			}
		},
		HORIZONTAL {
			public Directions rebond(Directions origine) {
				return origine.getOpposeY();
			}
		};

		public Directions rebond(Directions origine) {
			return origine.getOpposeX().getOpposeY();
		}
	}

	public Mur(Environnement env, SMA sma, int posX, int posY, TypeMur type) {
		super(env, sma, posX, posY, Directions.IMMOBILE);
		this.type = type;
	}

	@Override
	public void decide() {
		// Stand still
	}

	@Override
	public void dessine(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(posX * taille, posY * taille, taille, taille);
	}

	@Override
	public Directions estRencontrePar(AgentBille autre) {
		return this.type.rebond(autre.getDirection());
	}

}
