package objects;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class CompositeGObject extends GObject {

	private List<GObject> gObjects;

	public CompositeGObject() {
		super(0, 0, 0, 0);
		gObjects = new ArrayList<GObject>();
	}

	public void add(GObject gObject) {
		// TODO: Implement this method.


	}

	public void remove(GObject gObject) {
		// TODO: Implement this method.
	}

	@Override
	public void move(int dX, int dY) {
		// TODO: Implement this method.
		for(GObject item : gObjects) {
			item.move(dX, dY);
		}
		recalculateRegion();
	}

	


	@Override
	public void paintObject(Graphics g) {
		// TODO: Implement this method.
		for (GObject element: gObjects) {
			element.paintObject(g);
		}
	}

	@Override
	public void paintLabel(Graphics g) {
		// TODO: Implement this method.
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawString("Group all",x,y);
	}
	
}
