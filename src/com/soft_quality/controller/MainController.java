package soft_quality.controller;

import javax.swing.SwingUtilities;

import soft_quality.view.MainView;

/**
 * entry point of the application
 * @author Lauren Letestu, Vincent Francolin
 *
 */
public class MainController {
	/**
	 * private constructor to block instanciation of utility class
	 */
	private MainController(){
	}
	
    public static void main (String[] args) {
        PlaneController planeController = new PlaneController();
        final MainView win = new MainView(planeController);
        planeController.setMyView(win);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	win.setVisible(true);
            }
        });
        
    }

}
