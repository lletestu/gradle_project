import soft_quality.controller.PlaneController;
import soft_quality.view.MainView;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlaneControllerTest extends TestCase {

    PlaneController myPlaneController;
    MainView myView;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        myPlaneController = new PlaneController();
        myView = new MainView(myPlaneController);
    }

    @After
    @Override
    public void tearDown() throws Exception {

    }

    /**
     * Test setting view of my controller
     * @throws Exception
     */
    @Test
    public void testSetMyView() throws Exception {
        assertFalse(myPlaneController.isSettingView());
        myPlaneController.setMyView(myView);
        assertTrue(myPlaneController.isSettingView());
    }

    /**
     * Test executing action
     * @throws Exception
     */
    @Test
    public void testExecuteActionWithTriggeredTime() throws Exception {

    }

    @Test
    public void testOutWheels() throws Exception {

    }

    @Test
    public void testInWheels() throws Exception {

    }

    @Test
    public void testUpdateFinalState() throws Exception {

    }

    @Test
    public void testInterruptPreviousActions() throws Exception {

    }
}