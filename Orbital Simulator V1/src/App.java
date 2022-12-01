import javax.swing.*;

//Engine credit to learncodebygaming
class App {

	//Sets up the game engine
    private static void initWindow() {
 
        JFrame window = new JFrame("Physics Simulator");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1920, 1080);
        Board board = new Board();
        window.add(board);
        window.addKeyListener(board);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
    }

	//Starts the game engine
    public static void main(String[] args) {
    
        SwingUtilities.invokeLater(new Runnable() {
        	
            public void run() {
            	
                initWindow();
                
            }
            
        });
        
    }
    
}