package ca.mcgill.sel.ram.ui.views.message.handler.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mt4j.components.MTComponent;
import org.mt4j.components.interfaces.IMTComponent3D;
import org.mt4j.input.inputProcessors.IInputProcessor;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.unistrokeProcessor.UnistrokeEvent;
import org.mt4j.sceneManagement.ISceneChangeListener;
import org.mt4j.sceneManagement.SceneChangeEvent;

import ca.mcgill.sel.commons.emf.util.AdapterFactoryRegistry;
import ca.mcgill.sel.commons.emf.util.ResourceManager;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.MessageView;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.impl.ContainerMapImpl;
import ca.mcgill.sel.ram.impl.MessageViewImpl;
import ca.mcgill.sel.ram.impl.RamFactoryImpl;
import ca.mcgill.sel.ram.provider.RamItemProviderAdapterFactory;
import ca.mcgill.sel.ram.ui.RamApp;
import ca.mcgill.sel.ram.ui.scenes.DisplayAspectScene;
import ca.mcgill.sel.ram.ui.views.message.LifelineView;
import ca.mcgill.sel.ram.ui.views.message.MessageViewView;
import ca.mcgill.sel.ram.ui.views.message.handler.MessageViewHandlerFactory;
import ca.mcgill.sel.ram.util.RamResourceFactoryImpl;

/**
 * Test class for MessageViewHandler.
 * White box testing
 * Only public methods using branch coverage.
 * 
 * @author Raymond
 */
public class TestMessageViewHandler {

    private static Aspect aspect;
    private MessageViewHandler object;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        // Initialize ResourceManager.
        ResourceManager.initialize();
        // Initialize packages.
        RamPackage.eINSTANCE.eClass();
        
        // Register resource factories.
        ResourceManager.registerExtensionFactory("ram", new RamResourceFactoryImpl());
    
        // Initialize adapter factories.
        AdapterFactoryRegistry.INSTANCE.addAdapterFactory(RamItemProviderAdapterFactory.class);
        
        RamApp.initialize(new Runnable() {
            
            @Override
            public void run() {
            }
        });
        
        // Wait for RamApp to be initialized.
        Thread.sleep(10000);
    }

    @Before
    public void setUp() throws Exception {
        // Close current aspect.
        if (aspect != null) {
            RamApp.getApplication().invokeLater(new Runnable() {
                @Override
                public void run() {
                    RamApp.getApplication().closeAspectScene(RamApp.getActiveAspectScene());                    
                }
            });
        }
        
        // Load model to use in test.
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
        
        // Wait for UI to be updated.
        Thread.sleep(5000);
        object = (MessageViewHandler) MessageViewHandlerFactory.INSTANCE.getMessageViewHandler();
    }
        
    /**
     * Test processUnistrokeEvent method, using branch coverage.
     */
    @Test
    public void testProcessUnistrokeEvent() {
//        MessageView msgview = (MessageView) aspect.getMessageViews().get(0);
//        System.out.println(msgview);
//        ContainerMapImpl containerMap = (ContainerMapImpl) ramfactory.createContainerMap();
//        IMTComponent3D target = new MessageViewView(msgview, containerMap, 0, 1);
//        UnistrokeEvent event = new UnistrokeEvent(null, MTGestureEvent.GESTURE_STARTED, target, null, null, null);
//        object.processUnistrokeEvent(event);
    }
    
    /**
     * Test handleCreateFragment method.
     */
    @Test
    public void testHandleCreateFragment() {
//        RamFactoryImpl ramfactory = (RamFactoryImpl) RamFactoryImpl.init();
//        MessageView msgview = ramfactory.createMessageView();
//        ContainerMapImpl containerMap = (ContainerMapImpl) ramfactory.createContainerMap();
//        MessageViewView msgviewview = new MessageViewView(msgview, containerMap, 100, 100);
//        LifelineView lifeline = new LifelineView(msgviewview, null, null);
//        System.out.println("sdfasdfdfdfdasdfs" + lifeline.getFragmentBefore(null));
//        object.handleCreateFragment(null, lifeline, null, null);
    }
    
}