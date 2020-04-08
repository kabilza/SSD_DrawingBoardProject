package main;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import objects.*;

public class DrawingBoard extends JPanel {

	private MouseAdapter mouseAdapter; 
	private List<GObject> gObjects;
	private GObject target;
	
	private int gridSize = 10;
	
	public DrawingBoard() {
		gObjects = new ArrayList<GObject>();
		mouseAdapter = new MAdapter();
		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);
		setPreferredSize(new Dimension(800, 600));
	}
	
	public void addGObject(GObject gObject) {
		// TODO: Implement this method.
		gObjects.add(gObject);
		repaint();
	}
	
	public void groupAll() {
		// TODO: Implement this method.
		CompositeGObject compositeGObject = new CompositeGObject();
		for (GObject object : gObjects) {
			object.deselected();
			compositeGObject.add(object);
			repaint();
		}
	}

	public void deleteSelected() {
		// TODO: Implement this method.
		gObjects.remove(target);
	}
	
	public void clear() {
		// TODO: Implement this method.
		gObjects.clear();
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		paintBackground(g);
		paintGrids(g);
		paintObjects(g);
	}

	private void paintBackground(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	private void paintGrids(Graphics g) {
		g.setColor(Color.lightGray);
		int gridCountX = getWidth() / gridSize;
		int gridCountY = getHeight() / gridSize;
		for (int i = 0; i < gridCountX; i++) {
			g.drawLine(gridSize * i, 0, gridSize * i, getHeight());
		}
		for (int i = 0; i < gridCountY; i++) {
			g.drawLine(0, gridSize * i, getWidth(), gridSize * i);
		}
	}

	private void paintObjects(Graphics g) {
		for (GObject go : gObjects) {
			go.paint(g);
		}
	}

	class MAdapter extends MouseAdapter {

		// TODO: You need some variables here

		private int mouseX = 0;
		private int mouseY = 0;

		private void deselectAll() {
			// TODO: Implement this method.
			if(target != null){
				target.deselected();
			}
			target = null;
			repaint();
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO: Implement this method.

			int x = e.getX();
			int y = e.getY();
			this.mouseX = x;
			this.mouseY = y;

			boolean collision = false;
			target = null;

			for (GObject go : gObjects) {
				if (go.pointerHit(mouseX, mouseY)) {
					if(target != null){
						target.deselected();
					}
					go.selected();
					target = go;
					gObjects.remove(go);
					gObjects.add(go);
					collision = true;
				}
			}
			mouseX = e.getX();
			mouseY = e.getY();

			if(!collision){
				deselectAll();
			}
			repaint();
			System.out.println("Mouse Pressed on ");
			System.out.println(mouseX);
			System.out.println("and ");
			System.out.println(mouseY);

		}


		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO: Implement this method.

			int mouseX = e.getX();
			int mouseY = e.getY();

			if(target != null){
				target.move( mouseX-this.mouseX, mouseY-this.mouseY);
			}
			this.mouseX = mouseX;
			this.mouseY = mouseY;
			repaint();
		}
	}
	
}