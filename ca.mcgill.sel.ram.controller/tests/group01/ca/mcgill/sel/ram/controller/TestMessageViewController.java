package group01.ca.mcgill.sel.ram.controller;

import java.util.ArrayList;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.junit.*;

import ca.mcgill.sel.ram.impl.*;
import ca.mcgill.sel.ram.provider.RamItemProviderAdapterFactory;
import ca.mcgill.sel.ram.util.RamResourceFactoryImpl;
import ca.mcgill.sel.commons.emf.util.AdapterFactoryRegistry;
import ca.mcgill.sel.commons.emf.util.ResourceManager;
import ca.mcgill.sel.ram.AbstractMessageView;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.AspectMessageView;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.FragmentContainer;
import ca.mcgill.sel.ram.Interaction;
import ca.mcgill.sel.ram.LayoutElement;
import ca.mcgill.sel.ram.Lifeline;
import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.MessageView;
import ca.mcgill.sel.ram.MessageViewReference;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.RamFactory;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.Reference;
import ca.mcgill.sel.ram.TypedElement;
import ca.mcgill.sel.ram.controller.MessageViewController;
import ca.mcgill.sel.ram.controller.ControllerFactory;

/**
 * TestMessageViewController.
 * 
 * Our tests are ran using the node coverage method
 * 
 * @author ricanare
 */
public class TestMessageViewController {
    
    private static MessageViewController msgViewController;
    private static String modelBaseFolder = "test_models/";
    
    /**
     * Helper.
     */
    public static void assertMessagesInLifelines(Lifeline ll, Message m, int index) {

        Message lifelineMessage = 
                ((MessageOccurrenceSpecificationImpl) ll.getCoveredBy().get(index)).getMessage();
        
        Assert.assertEquals(m, lifelineMessage);
    }
    
    @BeforeClass
    public static void setUpBeforeClass() {
        // Initialize ResourceManager.
        ResourceManager.initialize();
        // Initialize packages.
        RamPackage.eINSTANCE.eClass();
        // Register resource factories.
        ResourceManager.registerExtensionFactory("ram", new RamResourceFactoryImpl());
        // Initialize adapter factories.
        AdapterFactoryRegistry.INSTANCE.addAdapterFactory(RamItemProviderAdapterFactory.class);
        
        msgViewController = ControllerFactory.INSTANCE.getMessageViewController();
    }
    
    /**
     * The setup function for each test. Get the instance of the controller.
     */
    @Before
    public void setUp() {   
        //aspect = (Aspect) ResourceManager.loadModel("models/demos/Bank/BBank.ram");
        msgViewController = ControllerFactory.INSTANCE.getMessageViewController();
    }
    
    /**
     * TestCreateLifeLine.
     */
    @Test
    public void testCreateLifeline() {
        Aspect aspect = (Aspect) ResourceManager.loadModel(modelBaseFolder + "Test1.ram");
        MessageView messageView = (MessageView) aspect.getMessageViews().get(0);
        
        Interaction owner = messageView.getSpecification();
        TypedElement represents = (TypedElement) aspect.getStructuralView()
                .getClasses().get(0).getAssociationEnds().get(0);
        float x = 0;
        float y = 0;
        
        msgViewController.createLifeline(owner, represents, x, y);
        
        Lifeline newLifeline = owner.getLifelines().get(1);
        
        String expectedClass = "testclass2";
        String actualClass = newLifeline.getRepresents().getName();
        
        Assert.assertNotNull("Lifeline not created", newLifeline);
        Assert.assertEquals("Invalid lifeline class", expectedClass, actualClass);
    }
    
    /**
     * TestCreateLifelineWithMessage.
     * Path #1 of node coverage
     */
    @Test
    public void testCreateLifelineWithMessage1() {
        Aspect aspect = (Aspect) ResourceManager.loadModel(modelBaseFolder + "Test1.ram");
        MessageView messageView = (MessageView) aspect.getMessageViews().get(0);
        
        Interaction owner = messageView.getSpecification();
        Lifeline lifelineFrom = owner.getLifelines().get(0);
        
        Reference staticReference = RamFactory.eINSTANCE.createReference();
        Classifier c = aspect.getStructuralView().getClasses().get(0);
        staticReference.setType(c);
        staticReference.setStatic(true);
        TypedElement represents = staticReference;
        
        float x = 320;
        float y = 100;
        
        Operation signature = c.getOperations().get(0);
        int addAtIndex = 1;
        
        msgViewController.createLifelineWithMessage(
                owner, 
                represents, 
                x, 
                y, 
                lifelineFrom, 
                owner, 
                signature, 
                addAtIndex);
        
        Lifeline newLifeline = owner.getLifelines().get(addAtIndex);
        Assert.assertNotNull("Lifeline not created", newLifeline);
        
        Message createdMessage = owner.getMessages().get(2);
        
        assertMessagesInLifelines(lifelineFrom, createdMessage, 2);
        assertMessagesInLifelines(newLifeline, createdMessage, 0);
    }
    
    /**
     * TestCreateLifelineWithMessage.
     * Path #2 of node coverage
     */
    @Test
    public void testCreateLifelineWithMessage2() {
                
        Aspect aspect = (Aspect) ResourceManager.loadModel(modelBaseFolder + "Test1.ram");
        MessageView messageView = (MessageView) aspect.getMessageViews().get(0);
        
        Interaction owner = messageView.getSpecification();
        Lifeline lifelineFrom = owner.getLifelines().get(0);
        
        Reference staticReference = RamFactory.eINSTANCE.createReference();
        Classifier c = aspect.getStructuralView().getClasses().get(0);
        staticReference.setType(c);
        staticReference.setStatic(true);
        TypedElement represents = staticReference;
        
        float x = 320;
        float y = 100;
        
        Operation signature = c.getOperations().get(0);
        int addAtIndex = 1;
        
        msgViewController.createLifelineWithMessage(
                owner, 
                represents, 
                x, 
                y, 
                lifelineFrom, 
                owner, 
                signature, 
                addAtIndex);
        
        Lifeline newLifeline = owner.getLifelines().get(addAtIndex);
        Assert.assertNotNull("Lifeline not created", newLifeline);
        
        Message createdMessage = owner.getMessages().get(2);
        
        assertMessagesInLifelines(lifelineFrom, createdMessage, 2);
        assertMessagesInLifelines(newLifeline, createdMessage, 0);
    }
    
    /**
     * TestMoveLifeline.
     * 
     */
    @Test
    public void testMoveLifeline() {
        
    }
    
    /**
     * TestCreateMessage.
     * 
     */
    @Test
    public void testCreateMessage() {
        
    }
    
    /**
     * TestCreateReplyMessage.
     * 
     */
    @Test
    public void testCreateReplyMessage() {
        
    }
    
    /**
     * TestRemoveMessages.
     * 
     */
    @Test
    public void testRemoveMessages() {

    }
}
