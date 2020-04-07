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
		}
	}

	public void deleteSelected() {
		// TODO: Implement this method.
		gObjects.remove(target);
	}
	
	public void clear() {
		// TODO: Implement this method.
		gObjects.clear();
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

		int mouseX = 0;
		int mouseY = 0;

		private void deselectAll() {
			// TODO: Implement this method.
			for (GObject go : gObjects) {
				go.deselected();
			}
			repaint();
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO: Implement this method.

			deselectAll();
			target = null;

			for (GObject go : gObjects) {
				if (go.pointerHit(e.getX(), e.getY())) {
					go.selected();
					target = go;
				}
			}
			mouseX = e.getX();
			mouseY = e.getY();
			repaint();
			System.out.println("Mouse Pressed on ");
			System.out.println(mouseX);
			System.out.println("and ");
			System.out.println(mouseY);
		}


		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO: Implement this method.

			int deltaX = 0;
			int deltaY = 0;
			deltaX = e.getX() - deltaX;
			deltaY = e.getY() - deltaY;
			target.move(deltaX, deltaY);
			repaint();
		}
	}
	
}