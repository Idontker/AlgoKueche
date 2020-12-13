import javax.swing.*;
import java.awt.*;

import java.util.HashMap;

public class GUI {

	// static fields
	private final static String[] methods = new String[]{"wirKochenJetzt", "nimmAusSchrank", "stellZurueck", "schneide", "wirfInTopf", "koche", "istGewuerzt", "serviere", "reactionHappy", "reactionSad" };
	private final static Color[] colors = new Color[]{Color.blue, Color.cyan, Color.gray, Color.green, Color.magenta, Color.orange, Color.red, Color.yellow, new Color(50,200,10), new Color(200,100,50)};

	// static values
	private static final int height = 720/2;
	private static final int width = height/9*16;


	// GUI Main
	public static void main(String args[]){
	        GUI g = new GUI();
		while(true){
			g.slideShow();
		}
	}


	// GUI attributes
	private JFrame frame;
	private JPanel actionPanel;

	private HashMap<String,Slide> map;

	public GUI() {
		initFrame();
		initActionPanel();		

		frame.add(actionPanel);	
		frame.setVisible(true);

		initMap();
	}

	// Init Methods
	private void initFrame() {
		frame = new JFrame("Hallo Wetlt");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width,height);
	}

	private void initActionPanel() {	
		actionPanel = new JPanel();
		//actionPanel.setSize(200,200);
		actionPanel.setVisible(true);
		actionPanel.setBackground(Color.red);
	}

	private void initMap() {
		map = new HashMap<String,Slide>();
		for(int i = 0; i < methods.length; i++){
			map.put(methods[i], new Slide(methods[i], colors[i]));
		}
	}

	// public methods 
	

	public void samSays() {
		//TODO: implement the (random) occurences of useless and funny comments of Sam
	}

	public void setComment() {
		//TODO: implement
	}

	//TODO: rename method
	public void goToFrame(String slideName) {
		Slide next = map.get(slideName);
		if(next != null) {
			System.out.println(next);
			actionPanel.setBackground(next.c);
		} else {
			System.out.println("Slide: " + slideName + " not found in Database");
			actionPanel.setBackground(Color.black);
			
		}
	}

	// methods for testing.

	private void slideShow(){
		for(String key: methods){
			goToFrame(key);
			try {
				Thread.sleep(800);
			} catch (Exception e) {
			}
		}
		goToFrame("NaN");
		try {
			Thread.sleep(500);
		} catch (Exception e) {
		}	
	}
}

class Slide {
	String method;
	Color c;

	Slide(String method, Color c) {
		this.method = method;
		this.c = c;
	}

	@Override
	public String toString(){
		return "[name=" + method + ", color= ("+c.getRed() + ", " + c.getGreen() + ", " + c.getBlue() + ") ]";
	}
}
