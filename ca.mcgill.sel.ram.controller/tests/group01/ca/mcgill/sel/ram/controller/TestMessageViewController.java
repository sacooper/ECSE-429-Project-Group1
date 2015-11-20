package group01.ca.mcgill.sel.ram.controller;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.junit.*;

import ca.mcgill.sel.ram.impl.*;
import ca.mcgill.sel.commons.emf.util.ResourceManager;
import ca.mcgill.sel.ram.AbstractMessageView;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.AspectMessageView;
import ca.mcgill.sel.ram.FragmentContainer;
import ca.mcgill.sel.ram.Interaction;
import ca.mcgill.sel.ram.Lifeline;
import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.RamPackage;
import ca.mcgill.sel.ram.TypedElement;
import ca.mcgill.sel.ram.controller.MessageViewController;
import ca.mcgill.sel.ram.controller.ControllerFactory;

/**
 * TestMessageViewController.
 * 
 * @author ricanare
 */
public class TestMessageViewController {
    
    private MessageViewController msgViewController;
    
    //private static Aspect aspect;
    
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
     * 
     */
    @Test
    public void testCreateLifeLine() {
        RamFactoryImpl ramImpl = new RamFactoryImpl();
        
        Message m = ramImpl.createMessage();
        Operation op = ramImpl.createOperation();
        
        m.setInteraction(ramImpl.createInteraction());
        m.setSignature(op);
        
        AspectMessageView amv = ramImpl.createAspectMessageView();
        
        Interaction owner = m.getInteraction();
        TypedElement represents = ramImpl.createParameter();
        
        float x = 0f;
        float y = 0f;
        
        // (Interaction owner, TypedElement represents, float x, float y)
        msgViewController.createLifeline(owner, represents, x, y);        
    }
    
    /**
     * TestCreateLifelineWithMessage.
     * 
     */
    @Test
    public void testCreateLifelineWithMessage() {
     
        // (Interaction owner, TypedElement represents, float x, float y,
        // Lifeline lifelineFrom, FragmentContainer container, Operation signature, int addAtIndex)
        // msgViewController.createLifelineWithMessage();
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
