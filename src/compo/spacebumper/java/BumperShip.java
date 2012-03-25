package compo.spacebumper.java;

public class BumperShip {
	public Vector position;
	public Vector velocity;
	public float vapor; //Wtf?
	/**
	 * 
	 * @param x X position
	 * @param y Y position
	 * @param vx X velocity
	 * @param vy Y velocity 
	 * @param score Score
	 */
	public BumperShip(float x, float y, float vx, float vy, float score)
	{
		this.position = new Vector(x, y);
		this.velocity = new Vector(vx, vy);
		this.vapor = score;
	}
}
