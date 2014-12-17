package softquality.controller;

import javax.swing.SwingUtilities;

import softquality.view.MainView;

/**
 * entry point of the application
 * @author Lauren Letestu, Vincent Francolin
 *
 */
public final class MainController {
	/**
	 * private constructor to block instanciation of utility class
	 */
	private MainController(){
	}

    /**
     * Software entry point
     * @param args argument command line
     */
    public static void main (String[] args) {
        PlaneController planeController = new PlaneController();
        final MainView win = new MainView(planeController);
        planeController.setMyView(win);
        SwingUtilities.invokeLater(new Runnable() {
            /**
             * main run
             */
            public void run() {
            	win.setVisible(true);
            }
        });
        
    }

}
