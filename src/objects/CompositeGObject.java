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
		gObjects.add(gObject);
		recalculateRegion();
	}

	public void remove(GObject gObject) {
		// TODO: Implement this method.
		gObjects.remove(gObject);
		recalculateRegion();
	}

	@Override
	public void move(int dX, int dY) {
		// TODO: Implement this method.
		this.x += dX;
		this.y += dY;
		for(GObject item : gObjects) {
			item.selected();
			item.move(dX, dY);
		}
	}

	public void recalculateRegion() {
		int newX = gObjects.get(0).x;
		int newY = gObjects.get(0).y;
		int newWidth = gObjects.get(0).x + gObjects.get(0).width;
		int newHeight = gObjects.get(0).y + gObjects.get(0).height;
		for (GObject gObject : gObjects) {
			if (gObject.x < newX) {
				newX = gObject.x;
			}
			if (gObject.x + gObject.width > newWidth) {
				newWidth = gObject.x + gObject.width;
			}
			if (gObject.y < newY) {
				newY = gObject.y;
			}
			if (gObject.y + gObject.height > newHeight) {
				newHeight = gObject.y + gObject.height;
			}
		}
		this.x = newX;
		this.y = newY;
		width = newWidth - newX;
		height = newHeight - newY;
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
		g2d.drawString("Group all",x,y-5);
	}
	
}
