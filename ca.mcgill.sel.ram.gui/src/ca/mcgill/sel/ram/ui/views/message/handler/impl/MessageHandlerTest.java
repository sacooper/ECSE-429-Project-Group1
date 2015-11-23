package ca.mcgill.sel.ram.ui.views.message.handler.impl;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.event.MouseEvent;
import java.util.Arrays;

import org.eclipse.emf.common.util.EList;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mt4j.input.inputProcessors.componentProcessors.tapAndHoldProcessor.TapAndHoldEvent;
import org.mt4j.sceneManagement.ISceneChangeListener;
import org.mt4j.sceneManagement.SceneChangeEvent;
import org.mt4j.util.math.Vector3D;

import ca.mcgill.sel.commons.emf.util.AdapterFactoryRegistry;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.commons.emf.util.ResourceManager;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.LayoutElement;
import ca.mcgill.sel.ram.Lifeline;
import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.MessageView;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.impl.ContainerMapImpl;
import ca.mcgill.sel.ram.impl.LifelineImpl;
import ca.mcgill.sel.ram.provider.RamItemProviderAdapterFactory;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.scenes.DisplayAspectScene;
import ca.mcgill.sel.ram.ui.views.message.LifelineView;
import ca.mcgill.sel.ram.ui.views.message.MessageCallView;
import ca.mcgill.sel.ram.ui.views.message.MessageViewView;
import ca.mcgill.sel.ram.ui.views.message.handler.MessageViewHandlerFactory;
import ca.mcgill.sel.ram.util.RamResourceFactoryImpl;

/**
 * Test class for MessageHandler.java
 */

public class MessageHandlerTest {

	private Aspect aspect;
    private MessageView message;
	private static EList < Lifeline > refList;
	private static Lifeline firstLifeline;
	private static Lifeline secondLifeline;
	private static ContainerMapImpl containerMap;
	private static LayoutElement layoutFrom;
	private static LayoutElement layoutTo;
	private static RamApp ramApp;
	private static Message messageForCall;

    private int xPos = 345;
    private int yPos = 200;
    private int yPosDelete = 210;
    private static final int actualSize = 2;


	/** 
	 * Method taken from discussion board
	 * @throws Exception 
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ResourceManager.initialize();
		RamPackage.eINSTANCE.eClass();
		ResourceManager.registerExtensionFactory("ram", new RamResourceFactoryImpl());
		AdapterFactoryRegistry.INSTANCE.addAdapterFactory(RamItemProviderAdapterFactory.class);
		RamApp.initialize(new Runnable() {
		    @Override
			public void run() {}
		});
		Thread.sleep(10000);
	}

	/** 
	 * Method taken from discussion board
	 */
	@SuppressWarnings("javadoc")
	@Before
	public void setUp() throws Exception {
		if (aspect != null) {
			RamApp.getApplication().invokeLater(new Runnable() {
			    @Override
				public void run() {
					RamApp.getApplication().closeAspectScene(RamApp.getActiveAspectScene());
				}
			});
		}
		aspect = (Aspect) ResourceManager.loadModel("models/demos/Bank/BBank.ram");
		RamApp.getApplication().addSceneChangeListener(new ISceneChangeListener() {
		    @Override
			public void processSceneChangeEvent(SceneChangeEvent sc) {
				if (sc.getNewScene() instanceof DisplayAspectScene) {
					RamApp.getApplication().removeSceneChangeListener(this);
				}
			}
		});
		RamApp.getApplication().loadAspect(aspect);
		Thread.sleep(10000);
	}
	/**
	 * Test method: processTapAndHoldEvent(TapAndHoldEvent tapAndHoldEvent)
	 * Node Coverage
	 */
	@Test
	public void processTapAndHoldEventTest() {
		message = (MessageView) aspect.getMessageViews().get(0);
		refList = message.getSpecification().getLifelines();
		firstLifeline = (LifelineImpl) refList.get(0);
		secondLifeline = (LifelineImpl) refList.get(1);
		containerMap = EMFModelUtil.getEntryFromMap(aspect.getLayout().getContainers(), message);
		layoutFrom = containerMap.getValue().get(firstLifeline);
		layoutTo = containerMap.getValue().get(secondLifeline);
		ramApp = RamApp.getApplication();
		messageForCall = message.getSpecification().getMessages().get(2);
		Message[] messageArray = (Message[]) message.getSpecification().getMessages().toArray();

		ramApp.invokeLater(new Runnable() {
		    @Override
			public void run() {
				MessageViewView view = new MessageViewView(message, containerMap, 1024, 768);
				MessageHandler messageHandler = (MessageHandler) MessageViewHandlerFactory.INSTANCE.getMessageHandler();
				LifelineView firstView = new LifelineView(view, firstLifeline, layoutFrom);
				LifelineView secondView = new LifelineView(view, secondLifeline, layoutTo);
				MessageCallView messageCallView = new MessageCallView(messageForCall, firstView, secondView);

				TapAndHoldEvent tapAndHoldEvent = new TapAndHoldEvent(null, 0, messageCallView, null, true, new Vector3D(xPos, yPos), 0, 0, 0);
				messageHandler.processTapAndHoldEvent(tapAndHoldEvent);
				MouseEvent release = new MouseEvent(ramApp, MouseEvent.MOUSE_RELEASED, 0, MouseEvent.BUTTON1_MASK, xPos, yPosDelete, xPos, yPosDelete, 1, false, MouseEvent.BUTTON1);
				MouseEvent down = new MouseEvent(ramApp, MouseEvent.MOUSE_PRESSED, 0, MouseEvent.BUTTON1_MASK, xPos, yPosDelete, xPos, yPosDelete, 1, false, MouseEvent.BUTTON1);
				ramApp.dispatchEvent(down);
				ramApp.dispatchEvent(release);
			}
		});
		try {
			Thread.sleep(1000);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		Message[] messageArrayTwo = (Message[]) message.getSpecification().getMessages().toArray();
		assertFalse(Arrays.equals(messageArray, messageArrayTwo));
		assertTrue(messageArrayTwo.length == actualSize);
	}
}