package ds.gradies;

import java.awt.image.BufferedImage;

public class PoisEditorJacobi extends PoisEditor {

	public static int MAX_TIME = 5000;

	@Override
	public BufferedImage reconstructImage(BufferedImage ik, Mask mk) 
	{
		VectorImage U = PoisEditor.outerColor(ik, mk);
		VectorImage G = PoisEditor.innerGrad(ik, mk);
		VectorImage R = PoisEditor.jacobi(U, G, mk, MAX_TIME );
		return R.toBufferedImage();
	}

}
