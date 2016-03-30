package ds.gradies;

import java.awt.Color;
import java.awt.image.BufferedImage;

public abstract class PoisEditor 
{
	public abstract BufferedImage reconstructImage(BufferedImage ik, Mask mk);

	public static double similar(BufferedImage a, BufferedImage b) 
	{
		int mW = Math.max(a.getWidth(), b.getWidth());
		int mH = Math.max(a.getHeight(), b.getHeight());
		
		double diff = 0;
		for(int x=0; x<mW; x++)
		for(int y=0; y<mH; y++)
		{
			Color ak = new Color(a.getRGB(x, y));
			Color bk = new Color(b.getRGB(x, y));
			diff += colorDiff(ak, bk);
		}
		
		int n = mW*mH;
		return Math.exp(-diff / (n==0 ? 1 : n) );
	}

	private static double colorDiff(Color ak, Color bk) 
	{
		int r = ak.getRed() - bk.getRed();
		int g = ak.getGreen() - bk.getGreen();
		int b = ak.getBlue() - bk.getBlue();
		//int a = ak.getAlpha() - bk.getAlpha();
		
		return Math.sqrt( (r*r + g*g + b*b) / 3 );
	} 

}
