package ds.gradies;

import java.awt.Color;
import java.awt.image.BufferedImage;

public abstract class PoisEditor 
{
	public abstract BufferedImage reconstructImage(BufferedImage ik, Mask mk);

	public static double similar(BufferedImage a, BufferedImage b) 
	{
		int mW = Math.min(a.getWidth(), b.getWidth());
		int mH = Math.min(a.getHeight(), b.getHeight());
		
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

	
	public static VectorImage outerColor(BufferedImage img, Mask m) 
	{
		int Rx = img.getWidth(), Ry = img.getHeight();
		
		VectorImage res = new VectorImage(Ry, Rx);
		double[][][] U = res.getImageData();
		
		for(int y=0; y<Ry; y++)
		for(int x=0; x<Rx; x++)
		if(!m.get(x, y))
		{
			 Color c = new Color( img.getRGB(x, y) );
			 U[y][x][0] = c.getRed();
			 U[y][x][1] = c.getGreen();
			 U[y][x][2] = c.getBlue();
		}
		
		return res;		
	}
	
	public static VectorImage innerGrad(BufferedImage img, Mask m) 
	{
		int Rx = img.getWidth(), Ry = img.getHeight();
		
		double[][][] U = new double[Ry][Rx][3];
		for(int y=0; y<Ry; y++)
		for(int x=0; x<Rx; x++)
		{
			 Color c = new Color( img.getRGB(x, y) );
			 U[y][x][0] = c.getRed();
			 U[y][x][1] = c.getGreen();
			 U[y][x][2] = c.getBlue();
		}
		
		VectorImage res = new VectorImage(Ry, Rx);
		double[][][] G = res.getImageData(); 
		
		for(int y=1; y<Ry-1; y++)
		for(int x=1; x<Rx-1; x++)
		if(m.get(x, y))
		{
			G[y][x][0] = (U[y-1][x][0] + U[y+1][x][0] + U[y][x+1][0] + U[y][x-1][0]) - 4*U[y][x][0]; 
			G[y][x][1] = (U[y-1][x][1] + U[y+1][x][1] + U[y][x+1][1] + U[y][x-1][1]) - 4*U[y][x][1]; 
			G[y][x][2] = (U[y-1][x][2] + U[y+1][x][2] + U[y][x+1][2] + U[y][x-1][2]) - 4*U[y][x][2]; 
		}
		
		return res;
	}
	
	
	public static VectorImage jacobi(VectorImage imgU, VectorImage imgG, Mask m, int maxtimes)
	{
		int Rx = imgU.getWidth(), Ry = imgU.getHeight(), Rl = imgU.getDepth();
		
		double[][][] U = imgU.getImageData();
		double[][][] G = imgG.getImageData();
	
		for(int tt=0; tt<maxtimes; tt++)
		{	
			for(int y=1; y<Ry-1; y++)
			for(int x=1; x<Rx-1; x++)
			if( m.get(x, y) )
			for(int k=0; k<Rl; k++)
			{
				U[y][x][k] = (U[y-1][x][k] + U[y+1][x][k] 
						      + U[y][x+1][k] + U[y][x-1][k] - G[y][x][k])/4;
			}
		}
		
		return imgU;
	}
		
}
