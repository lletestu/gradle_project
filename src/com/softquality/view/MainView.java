package softquality.view;

import softquality.controller.PlaneController;
import softquality.model.Doors;
import softquality.model.Handle;
import softquality.model.Leds;
import softquality.model.Wheel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

/**
 * View for the plane board
 * @author Lauren Letestu, Vincent Francolin
 *
 */
public class MainView extends JFrame {

    /**
     * Attributes
     */
    private static final long serialVersionUID = -6629841697151115610L;
    private static final int NUMBER_WHEELS = 3;

    private JPanel cockpitPanel;
    private JPanel gearsPanel;

    private JLabel imgLeds;
    private JLabel[] imgWheelState;
    private JSlider handle;
    //private JButton emergencyButton;

    private final PlaneController myPlaneController;

    /**
     * constructor, init the view and assign the controller
     * @param controller the plane controller
     */
    public MainView(PlaneController controller){
        myPlaneController = controller;
        initView();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * initialize the whole view
     */
    private void initView(){
        JPanel contentPanel = new JPanel();
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        contentPanel.setBorder(padding);

        contentPanel.setLayout(new BorderLayout());

        this.initCockpitPanel();
        this.initGearsPanel();
        contentPanel.add(cockpitPanel, BorderLayout.PAGE_START);
        contentPanel.add(gearsPanel, BorderLayout.PAGE_END);

        this.setContentPane(contentPanel);

        pack();
    }

    /**
     * Initialize cockpit panel
     */
    private void initCockpitPanel() {
        cockpitPanel = new JPanel();
        cockpitPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        cockpitPanel.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        cons.insets = new Insets(10,10,10,10);
        cons.weightx = 1.0;

        cons.gridx = 0;
        cons.gridy = 0;
        JLabel title = new JLabel("COCKPIT");
        Font f = title.getFont();
        title.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        cockpitPanel.add(title, cons);

        // Set handle
        cons.gridx = 0;
        cons.gridy = 1;
        cockpitPanel.add(new JLabel("Handle"), cons);

        handle = new JSlider(JSlider.VERTICAL, 0,1,0);
        @SuppressWarnings("serial")
        Hashtable<Integer, JLabel> labels = new Hashtable<Integer, JLabel>() {
            { put(0, new JLabel("OUT"));
                put(1, new JLabel("IN"));
            }
        };
        handle.setLabelTable(labels);
        handle.setPaintLabels(true);
        handle.addChangeListener(sliderListerner);
        cons.gridx = 0;
        cons.gridy= 2;
        cockpitPanel.add(handle, cons);

        // Set emergency
        /*cons.gridx = 1;
        cons.gridy = 1;
        cockpitPanel.add(new JLabel("Emergency"), cons);

        emergencyButton = new JButton("Stop");
        emergencyButton.setBackground(Color.red);
        emergencyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO emergency button listener
                System.out.println("Emergency listener !");
            }
        });
        cons.gridx = 1;
        cons.gridy = 2;
        cockpitPanel.add(emergencyButton, cons);*/

        // Set leds
        cons.gridx = 2;
        cons.gridy = 1;
        cockpitPanel.add(new JLabel("Leds state"), cons);

        imgLeds = new JLabel(loadImgRessource("led_green2.png"));
        cons.gridx = 2;
        cons.gridy = 2;
        cockpitPanel.add(imgLeds, cons);
    }

    /**
     * initialize the panel that displays the gear state
     */
    private void initGearsPanel() {
        gearsPanel = new JPanel();
        gearsPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        gearsPanel.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        cons.insets = new Insets(10,10,10,10);
        cons.weightx = 1.0;

        cons.gridx = 0;
        cons.gridy = 0;
        JLabel title = new JLabel("GEARS CONTROL");
        Font f = title.getFont();
        title.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        gearsPanel.add(title, cons);

        // Initalize array
        imgWheelState = new JLabel[NUMBER_WHEELS];
        for (int i = 0; i < imgWheelState.length; ++i) {
            imgWheelState[i] = new JLabel(loadImgRessource("wheel_down.png"));
        }

        // Initialize label
        JLabel[] labelWheel = {new JLabel("front"), new JLabel("Left"), new JLabel("Right")};

        for (int i = 0; i < imgWheelState.length; ++i) {
            // Set label
            cons.gridx = i;
            cons.gridy = 1;
            gearsPanel.add(labelWheel[i], cons);

            // Set image
            cons.gridx = i;
            cons.gridy = 2;
            gearsPanel.add(imgWheelState[i], cons);
        }
    }

    /***
     * Listener when change state of the handle
     */
    private ChangeListener sliderListerner = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider source = (JSlider)e.getSource();

            if(source.getValue() == 1) {
                myPlaneController.executeActionWithTriggeredTime(false);
            } else {
                myPlaneController.executeActionWithTriggeredTime(true);
            }
        }
    };

    /**
     * set the led state
     * @param color the new led state
     */
    public void setLed(Leds color){
        switch(color){
            case GREEN:
                imgLeds.setIcon(loadImgRessource("led_green2.png"));
                break;
            case NONE:
                imgLeds.setIcon(loadImgRessource("led_empty2.png"));
                break;
            case ORANGE:
                imgLeds.setIcon(loadImgRessource("led_orange2.png"));
                break;
            case RED:
                imgLeds.setIcon(loadImgRessource("led_red2.png"));
                break;
            default:
                imgLeds.setIcon(loadImgRessource("led_empty2.png"));
                break;
        }
    }

    /**
     * change the wheels state
     * @param stateList the list of a wheels
     */
    public void setWheelState(HashMap<Integer,Wheel> stateList){
        Set<Integer> myKeys = stateList.keySet();
        Iterator<Integer> it = myKeys.iterator();
        int index;
        String path = "";

        while (it.hasNext()) {
            Object key = it.next();
            index = (Integer) key;
            if (index > -1 && index <imgWheelState.length) {
                switch (stateList.get(key)) {
                    case OUT:
                        path = "wheel_down.png";
                        break;
                    case TRANSITION:
                        path = "wheel_transition.png";
                        break;
                    case IN:
                        path = "wheel_up.png";
                        break;
                    default:
                        //TODO what is default case?
                        break;
                }
                imgWheelState[index].setIcon(loadImgRessource(path));
            }
        }
    }

    /**
     * change the wheels state
     * @param stateList the list of a wheels
     * @throws IOException
     * @throws URISyntaxException
     */
    public void setWheelAndDoorState(HashMap<Integer,Wheel> stateList,
                                     HashMap<Integer,Doors> doorsList) throws IOException, URISyntaxException{
        Set<Integer> myKeys = stateList.keySet();
        Iterator<Integer> it = myKeys.iterator();
        int index;

        while (it.hasNext()) {
            Object key = it.next();
            index = (Integer) key;
            if (index > -1 && index <imgWheelState.length) {
                BufferedImage finalImage =  new BufferedImage(95,95,
                        BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = finalImage.createGraphics();
                switch (stateList.get(key)) {
                    case OUT:
                        g.drawImage(loadImage("wheel_down.png")
                                ,null,0,0);
                        break;
                    case TRANSITION:
                        g.drawImage(loadImage("wheel_transition.png")
                                ,
                                null,0,0);
                        break;
                    case IN:
                        g.drawImage(loadImage("wheel_up.png")
                                ,
                                null,0,0);
                        break;
                    default:
                        //TODO what is default case?
                        break;
                }

                switch(doorsList.get(key)){
                    case CLOSE:
                        g.drawImage(loadImage("door_close.png"),
                                null,0,0);
                        break;
                    case OPEN:
                        g.drawImage(loadImage("door_open.png"),
                                null,0,0);
                        break;
                    default:
                        break;

                }

                imgWheelState[index].setIcon(new ImageIcon(finalImage));
            }
        }
    }

    /**
     * change handle state
     * @param position the new position of the handle
     */
    public void setHandle(Handle position){

        switch(position){
            case DOWN:
                handle.setValue(0);
                break;
            case UP:
                handle.setValue(1);
                break;
            default:
                break;
        }
    }

    /**
     * disable the handle from user action
     */
    public void disableHandle(){
        handle.setEnabled(false);
    }

    /**
     * enable the handle from user action
     */
    public void enableHandle(){
        handle.setEnabled(true);
    }

    /**
     * Update views according data sending by controller
     * @param ledsState state of leds
     * @param wheelList state of wheel
     */
    public void updateData(Leds ledsState, HashMap<Integer,Wheel> wheelList,
                           HashMap<Integer,Doors> doorList) {
        try {
            setWheelAndDoorState(wheelList,doorList);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Fail to refresh view : "+e.getMessage());
            e.printStackTrace();
        }
        setLed(ledsState);
    }

    /**
     * Utility function to load correctly image
     * @param path filepath
     * @return imageIcon or null
     */
    private ImageIcon loadImgRessource(String path) {
        java.net.URL imgURL = getClass().getClassLoader().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    private BufferedImage loadImage(String path) throws IOException, URISyntaxException {
        java.net.URL imgURL = getClass().getClassLoader().getResource(path);
        if (imgURL != null) {
            return ImageIO.read(new File(imgURL.toURI()));
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
