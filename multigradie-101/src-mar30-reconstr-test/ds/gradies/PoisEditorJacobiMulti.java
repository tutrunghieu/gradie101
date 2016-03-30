package ds.gradies;

import java.awt.image.BufferedImage;

import apps.testers.poistesterConsts;

public class PoisEditorJacobiMulti extends PoisEditor {

	public static int MAX_TIME = 500;

	@Override
	public BufferedImage reconstructImage(BufferedImage ik, Mask mk) 
	{
		if(poistesterConsts.DEBUG) return ik;
		
		BufferedImage i1 = shrink(ik);
		Mask m1 = shrink(mk);
		
		BufferedImage i2 = shrink(i1);
		Mask m2 = shrink(m1);
		
		BufferedImage i3 = shrink(i2);
		Mask m3 = shrink(m2);
		
		BufferedImage r3 = reconstruct(i3, m3);
		BufferedImage r2 = reconstruct(i2, m2, r3);
		BufferedImage r1 = reconstruct(i1, m1, r2);
				
		return r1;
	}

	private BufferedImage reconstruct(BufferedImage ik, Mask mk, BufferedImage seed) 
	{
		
		VectorImage U = PoisEditor.outerColor(ik, mk);
		VectorImage G = PoisEditor.innerGrad(ik, mk);
			
		expand(seed, U, mk);
		VectorImage R = PoisEditor.jacobi(U, G, mk, MAX_TIME );
		
		return R.toBufferedImage();
	}

	private VectorImage expand(BufferedImage seed, VectorImage u, Mask mk) 
	{
		int Rx = u.getWidth(), Ry = u.getHeight();
		int Sx = seed.getWidth(), Sy = seed.getHeight();
		
		VectorImage S = new VectorImage(u);
		
		for(int x=0; x<Rx; x++) 
		for(int y=0; y<Ry; y++) 
		if( mk.get(x, y) ) 
		{
			int xt = x*Sx/Rx;
			int yt = y*Sy/Ry;
			u.set(x, y, S.get(xt, yt));
		}
		
		return S;
	}


	private BufferedImage reconstruct(BufferedImage ik, Mask mk) 
	{
		VectorImage U = PoisEditor.outerColor(ik, mk);
		VectorImage G = PoisEditor.innerGrad(ik, mk);
		VectorImage R = PoisEditor.jacobi(U, G, mk, PoisEditorJacobiMulti.MAX_TIME );
		
		return R.toBufferedImage();
	}

	private Mask shrink(Mask mk) {
		// TODO Auto-generated method stub
		return null;
	}

	private BufferedImage shrink(BufferedImage ik) {
		// TODO Auto-generated method stub
		return null;
	}

}
