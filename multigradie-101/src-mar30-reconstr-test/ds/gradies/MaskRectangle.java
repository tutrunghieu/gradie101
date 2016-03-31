package ds.gradies;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class MaskRectangle extends Mask 
{
	private Rectangle mask;
	private int W;
	private int H;

	public void setRectangle(int x, int y, int w, int h) 
	{
		mask = new Rectangle(x, y, w, h);
	}
	
	public Rectangle getRectangle()
	{
		return mask;
	}
	
	public MaskRectangle(BufferedImage ik) 
	{
		int cx = 10, cy = 10;
		setRectangle(cx, cy, ik.getWidth()-2*cx, ik.getHeight()-2*cy);
		W = ik.getWidth();
		H = ik.getHeight();
	}

	@Override
	public boolean get(int x, int y) 
	{
		return mask.contains(x, y);
	}

	@Override
	public int getWidth() 
	{
		return W;
	}

	@Override
	public int getHeight() {
		return H;
	}

	@Override
	public void set(int x, int y, boolean v) {
		// TODO Auto-generated method stub
		
	}
	
}
