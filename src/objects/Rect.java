package objects;

import java.awt.*;

public class Rect extends GObject  {

	private Color color;
	
	public Rect(int x, int y, int width, int height, Color color) {
		super(x, y, width, height);
		this.color = color;
	}

	@Override
	public void paintObject(Graphics g) {
		// TODO: Implement this method.
		g.setColor(this.color);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(3));
		g2d.drawRect(x, y, width, height);
		g2d.fillRect(x, y, width, height);


	}
	
	@Override
	public void paintLabel(Graphics g) {
		// TODO: Implement this method.
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawString("Rec",x,y);
		}
	}
	

