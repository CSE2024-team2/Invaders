package entity;

import java.awt.Color;
import engine.DrawManager.SpriteType;

public class Boss extends Entity{
    
    /** Checks if the ship has been hit by a bullet. */
	private boolean isDestroyed;
	/** Values of the ship, in points, when destroyed. */
	private int pointValue;

    public Boss (final int positionX, final int positionY) {
        super(positionX, positionY, 36 * 2, 24 * 2, Color.WHITE);

        this.spriteType = SpriteType.BossShip;
        this.isDestroyed = false;
        this.pointValue = 1000;
    }

    /**
	 * Getter for the score bonus if this ship is destroyed.
	 * 
	 * @return Value of the ship.
	 */
	public final int getPointValue() {
		return this.pointValue;
	}

    /**
	 * Destroys the ship, causing an explosion.
	 */
    public final void destroy() {
		this.isDestroyed = true;
    }

    /**
	 * Checks if the ship has been destroyed.
	 * 
	 * @return True if the ship has been destroyed.
	 */
    public final boolean isDestroyed() {
		return this.isDestroyed;
	}
}
