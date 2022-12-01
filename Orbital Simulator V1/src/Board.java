import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.Dimension;

public class Board extends JPanel implements ActionListener, KeyListener {
	
	private static final long serialVersionUID = 1L;
	private final int DELAY = 1;
    private Timer timer;
    
    ArrayList<Body> Bodies = new ArrayList<>();
    ArrayList<Integer> Red = new ArrayList<>();
    ArrayList<Integer> Green = new ArrayList<>();
    ArrayList<Integer> Blue = new ArrayList<>();
    
    int mass;
    double x_acceleration;
    double y_acceleration;
    double x_velocity;
    double y_velocity;
    
    int x_cordText;
    int y_cordText;
    
    boolean drawBackground = true;
    boolean titleScreen = true;
    
    int future;
    
    double G = 0.000000000066743;
    
    public Board() {
    	
    	//Fills the JFrame
        setPreferredSize(new Dimension(1920, 1080));
        setBackground(new Color(232, 232, 232));
        
        //Initializes the mouse listener component
        MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
        addMouseListener(myMouseAdapter);
        
        //Initializes the timer
        timer = new Timer(DELAY, this);
        repaint();
        
    }
    
    //Every time the mouse is pressed a new object is generated as well as a random corresponding color
    class MyMouseAdapter extends MouseAdapter {
    	
        @Override
        public void mousePressed(MouseEvent e) {
        	
        	timer.stop();
        	drawBackground = false;
        		
        	try {
        			
        		Point P = e.getPoint();
        	
        		x_cordText = (int)(P.getX());
        		y_cordText = (int)(P.getY());
        	
        		mass = (int)(Double.parseDouble(JOptionPane.showInputDialog(null, "What is the mass (max: 2100000000 kg)?")));
        		JOptionPane.getRootFrame().dispose();
        		x_acceleration = Double.parseDouble(JOptionPane.showInputDialog(null, "What is the x acceleration?"));
        		JOptionPane.getRootFrame().dispose();
        		y_acceleration = Double.parseDouble(JOptionPane.showInputDialog(null, "What is the y acceleration?"));
        		JOptionPane.getRootFrame().dispose();
        		x_velocity = Double.parseDouble(JOptionPane.showInputDialog(null, "What is the x velocity?"));
        		JOptionPane.getRootFrame().dispose();
       			y_velocity = Double.parseDouble(JOptionPane.showInputDialog(null, "What is the y velocity?"));
       			JOptionPane.getRootFrame().dispose();
       			Body player = new Body(P.getX(), P.getY(), mass, x_acceleration, y_acceleration, x_velocity, y_velocity);
       			Bodies.add(player);
       			Red.add((int)(100*Math.random()));
           		Green.add((int)(100*Math.random()));
           		Blue.add((int)(100*Math.random()));
           		Bodies.get(Bodies.size()-1).setG(G);
            		
        	}
        		
        	catch(Exception me) {}
        		
        	drawBackground = false;
        	repaint();
        	
        	timer.start();
        	
        }
        
    }

	//Runs the physics calculations for each object and then moves them
    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < Bodies.size(); i++) {
        	
        	for (int j = 0; j < Bodies.size(); j++) {
        		
        		if (i != j) {
        			
        			Bodies.get(i).physics(Bodies.get(j).getX_coordinate(), Bodies.get(j).getY_coordinate(), Bodies.get(j).getMass());
        		
        		}
        	
        	}
        
        } 
        
        for (int i = 0; i < Bodies.size(); i++) {
        	
        	Bodies.get(i).move();
        	Bodies.get(i).setX_accelaration(0);
        	Bodies.get(i).setY_accelaration(0);
        	
        }

        repaint();
        
    }
    
	//Draws each object, the title screen, and a background if applicable
    @Override
    public void paintComponent(Graphics g) {
    	
        g.setColor(new Color(200,200,200));

        if (drawBackground == false) {
        	
        	g.fillRect(0, 0, 1920, 1080);
        	drawBackground = true;
        	
        }
        
        for (int i = 0; i < Bodies.size(); i++) {
        	
        	g.setColor(new Color(Red.get(i), Green.get(i), Blue.get(i)));
        	g.fillOval((int)(Bodies.get(i).getX_coordinate())-5,(int)(Bodies.get(i).getY_coordinate())-5,10,10);
        	
        }
        
        if (titleScreen == true) {
        	
        	g.setColor((new Color(0, 100, 100)));
        	g.setFont(new Font("Times New Roman", Font.BOLD, 40));
        	g.drawString("WELCOME TO THE 2D ORBITAL SIMULATOR", 470, 450);
        	g.drawString("'S' - Start", 820, 500);
        	g.drawString("'Back Space' - Toggle Reset", 670, 550);
        	g.drawString("'Click' - New Object", 720, 600);
        	g.drawString("'Space' - Time Skip", 730, 650);
        	g.drawString("'G' - Gravitational Constant", 665, 700);
        	
        }
        
        g.setColor((new Color(0, 100, 100)));
    	g.setFont(new Font("Times New Roman", Font.BOLD, 20));
        g.drawString("The Current Gravitational Constant is = " + G, 10, 1035);
        
    }
    
    //Adds a time warp function as well as start and stop/reset and changing the gravitation constant functions that occur when specific keys are pressed
	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			
			drawBackground = false;
			
			try {
				
				timer.stop();
				future = (int)(Double.parseDouble(JOptionPane.showInputDialog(null, "How many seconds into the future would you like to simulate?")));
				JOptionPane.getRootFrame().dispose();
				
			}
			
			catch (Exception me) {
				
				future = 0;
				
			}
			
			repaint();
			
				while (future > 0) {
					
			        for (int i = 0; i < Bodies.size(); i++) {
			        	
			        	for (int j = 0; j < Bodies.size(); j++) {
			        		
			        		if (i != j) {
			        			
			        			Bodies.get(i).physics(Bodies.get(j).getX_coordinate(), Bodies.get(j).getY_coordinate(), Bodies.get(j).getMass());
			        		
			        		}
			        	
			        	}
			        
			        } 
			        
			        for (int i = 0; i < Bodies.size(); i++) {
			        	
			        	Bodies.get(i).move();
			        	Bodies.get(i).setX_accelaration(0);
			        	Bodies.get(i).setY_accelaration(0);
			        	
			        }
			        
					future--;
					
				}
			
			timer.start();
			
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			
			for (int i = Bodies.size() - 1 ; i >= 0; i--) {
				
				Bodies.remove(i);
				Red.remove(i);
				Green.remove(i);
				Blue.remove(i);
				
			}
			
			titleScreen = true;
			G = 0.000000000066743;
			this.repaint();
			timer.stop();
			
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_S) {
			
			titleScreen = false;
			timer.start();
			drawBackground = false;
			
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_G) {
			
			drawBackground = false;
			
			try {
				
				timer.stop();
				G = Double.parseDouble(JOptionPane.showInputDialog(null, "What value of G would you like? (6.6743e-11 : Actual, 6.6743e-7 : Recommended)"));
				JOptionPane.getRootFrame().dispose();
				
			}
			
			catch (Exception me) {
				
				future = 0;
				
			}
			
			for (int i = 0; i < Bodies.size(); i++) {
				
				Bodies.get(i).setG(G);
				
			}
			
			repaint();
			timer.start();
			
		}
		
	}
	
    @Override
    public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

    

    







}