package ds.gradies;

public class MaskArray extends Mask {

	private boolean[][] arr;
	private int W;
	private int H;
	
	public MaskArray(int tW, int tH)
	{
		W = tW;
		H = tH;
		arr = new boolean[tH][tW];
	}
	
	@Override
	public boolean get(int x, int y) {
		return arr[y][x];
	}

	@Override
	public void set(int x, int y, boolean v) {
		arr[y][x] = v;
	}
	
	@Override
	public int getWidth() {
		return W;
	}

	@Override
	public int getHeight() {
		return H;
	}

}
