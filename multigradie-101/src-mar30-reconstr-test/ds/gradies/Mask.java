package ds.gradies;

public abstract class Mask 
{
	public abstract boolean get(int x, int y);
	
	public abstract void set(int x, int y, boolean v);

	public abstract int getWidth();

	public abstract int getHeight();
}
