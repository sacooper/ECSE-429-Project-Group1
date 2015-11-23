package group01.ca.mcgill.sel.ram.controller;

import org.eclipse.emf.common.util.EList;
import org.junit.*;

import ca.mcgill.sel.ram.impl.*;
import ca.mcgill.sel.ram.provider.RamItemProviderAdapterFactory;
import ca.mcgill.sel.ram.util.RamResourceFactoryImpl;
import ca.mcgill.sel.commons.emf.util.AdapterFactoryRegistry;
import ca.mcgill.sel.commons.emf.util.EMFModelUtil;
import ca.mcgill.sel.commons.emf.util.ResourceManager;
import ca.mcgill.sel.ram.Aspect;
import ca.mcgill.sel.ram.Classifier;
import ca.mcgill.sel.ram.FragmentContainer;
import ca.mcgill.sel.ram.Interaction;
import ca.mcgill.sel.ram.LayoutElement;
import ca.mcgill.sel.ram.Lifeline;
import ca.mcgill.sel.ram.Message;
import ca.mcgill.sel.ram.MessageOccurrenceSpecification;
import ca.mcgill.sel.ram.MessageView;
import ca.mcgill.sel.ram.Operation;
import ca.mcgill.sel.ram.OperationType;
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
    public void assertMessages(Lifeline ll, Message m, int index) {
        Message llm = ((MessageOccurrenceSpecificationImpl) ll.getCoveredBy().get(index)).getMessage();
        Assert.assertEquals(m, llm);
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
        
        Assert.assertNotNull("Lifeline not created", newLifeline);
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
        Lifeline ll = owner.getLifelines().get(0);
        
        Reference reference = RamFactory.eINSTANCE.createReference();
        Classifier classifier = aspect.getStructuralView().getClasses().get(0);
        reference.setType(classifier);
        reference.setStatic(true);
        TypedElement represents = reference;
        
        float x = 0;
        float y = 0;
        
        Operation signature = classifier.getOperations().get(0);
        int i = 1;
        
        msgViewController.createLifelineWithMessage(owner, represents, x, y, 
                ll, owner, signature, i);
        
        Lifeline createdLifeLine = owner.getLifelines().get(i);
        Assert.assertNotNull("Lifeline not created", createdLifeLine);
        
        Message createdMessage = owner.getMessages().get(2);
        
        assertMessages(ll, createdMessage, 2);
        assertMessages(createdLifeLine, createdMessage, 0);
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
        Lifeline ll = owner.getLifelines().get(0);
        
        Reference reference = RamFactory.eINSTANCE.createReference();
        Classifier classifier = aspect.getStructuralView().getClasses().get(0);
        reference.setType(classifier);
        reference.setStatic(true);
        TypedElement represents = reference;
        
        float x = 0;
        float y = 0;
        
        Operation signature = classifier.getOperations().get(0);
        signature.setOperationType(OperationType.get(2));
        int i = 1;        
        
        msgViewController.createLifelineWithMessage(owner, represents, x, y, 
                ll, owner, signature, i);
        
        Lifeline createdLineLine = owner.getLifelines().get(i);
        Assert.assertNotNull("Lifeline not created", createdLineLine);
        
        Message createdMessage = owner.getMessages().get(2);
        
        assertMessages(ll, createdMessage, 2);
        assertMessages(createdLineLine, createdMessage, 0);
    }
    
    /**
     * TestMoveLifeline.
     * 
     */
    @Test
    public void testMoveLifeline() {

        Aspect aspect = (Aspect) ResourceManager.loadModel(modelBaseFolder + "Test1.ram");
        MessageView messageView = (MessageView) aspect.getMessageViews().get(0);
        
        Interaction owner = messageView.getSpecification();
        
        Lifeline ll = owner.getLifelines().get(0);
        ContainerMapImpl layout = EMFModelUtil.getEntryFromMap(aspect.getLayout().getContainers(), messageView);
        LayoutElement layoutElement = layout.getValue().get(ll);
        
        Assert.assertTrue("X position should be 100", layoutElement.getX() == 100);
        Assert.assertTrue("Y position should be 100", layoutElement.getY() == 100);
        
        msgViewController.moveLifeline(ll, 0, 0);
        
        Lifeline modifiedLl = owner.getLifelines().get(0);
        ContainerMapImpl modifieLayout = EMFModelUtil.getEntryFromMap(aspect.getLayout().getContainers(), messageView);
        LayoutElement modifieLayoutElement = modifieLayout.getValue().get(ll);
        
        Assert.assertNotNull("Lifeline wasn't moved properly", modifiedLl);
        
        Assert.assertTrue("X position should be 0", modifieLayoutElement.getX() == 0);
        Assert.assertTrue("Y position should be 0", modifieLayoutElement.getY() == 0);
    }
    
    /**
     * TestCreateMessage.
     * 
     */
    @Test
    public void testCreateMessage() {
        Aspect aspect = (Aspect) ResourceManager.loadModel(modelBaseFolder + "Test1.ram");
        MessageView messageView = (MessageView) aspect.getMessageViews().get(0);
        
        Interaction owner = messageView.getSpecification();
        EList<Lifeline> lifelines = owner.getLifelines();
        Lifeline ll1 = lifelines.get(0);
        Lifeline ll2 = lifelines.get(1);
        FragmentContainer container = (FragmentContainer) owner;
        Operation signature = aspect.getStructuralView().getClasses().get(1).getOperations().get(0);
        int i = 1;
        
        int ll1MessageCount = ll1.getCoveredBy().size();
        Assert.assertTrue("ll1 should have had exactly 8 messages", ll1MessageCount == 8);
        
        int ll2MessageCount = ll2.getCoveredBy().size();
        Assert.assertTrue("ll2 should have had 4 messages", ll2MessageCount == 4);
        
        msgViewController.createMessage(owner, ll1, ll2, container, signature, i);
        
        ll1MessageCount = ll1.getCoveredBy().size();
        ll2MessageCount = ll2.getCoveredBy().size();
        
        Assert.assertTrue("ll1 should have had exactly 9 messages", ll1MessageCount == 9);
        Assert.assertTrue("ll2 should have had exactly 5 messages", ll2MessageCount == 5);
        
        Message m1 = owner.getMessages().get(2);
        Message m2 = owner.getMessages().get(3);
        
        assertMessages(ll1, m1, 2);
        assertMessages(ll1, m2, 3);
        
        assertMessages(ll2, m1, 0);
        assertMessages(ll2, m2, 1);
    }
    
    /**
     * TestCreateReplyMessage.
     * 
     */
    @Test
    public void testCreateReplyMessage() {
        Aspect aspect = (Aspect) ResourceManager.loadModel(modelBaseFolder + "Test1.ram");
        MessageView messageView = (MessageView) aspect.getMessageViews().get(0);
        
        Interaction owner = messageView.getSpecification();
        EList<Lifeline> lifelines = owner.getLifelines();
        Lifeline ll1 = lifelines.get(0);
        Lifeline ll2 = null;
        
        FragmentContainer container = owner;
        
        Operation signature = aspect.getStructuralView().getClasses().get(0).getOperations().get(0);
        int addAtIndex = 1;
        
        Assert.assertTrue(owner.getMessages().size() == 8);
        
        msgViewController.createReplyMessage(owner, ll1, ll2, container, signature, addAtIndex);
        
        Message newMessage = owner.getMessages().get(8);
        String newMessageName = newMessage.getMessageSort().getName();
        
        assertMessages(ll1, newMessage, 8);
        Assert.assertEquals("reply", newMessageName);
    }
    
    /**
     * TestRemoveMessages.
     * 
     */
    @Test
    public void testRemoveMessages() {

        Aspect aspect = (Aspect) ResourceManager.loadModel(modelBaseFolder + "Test1.ram");
        MessageView messageView = (MessageView) aspect.getMessageViews().get(0);
        
        Interaction owner = messageView.getSpecification();
        
        Message message = owner.getMessages().get(1);
        MessageOccurrenceSpecification sendEvent = (MessageOccurrenceSpecification) message.getSendEvent();
        
        EList<Message> messages = owner.getMessages();
        
        Assert.assertNotNull(messages.get(1));
        Assert.assertNotNull(messages.get(2));
        
        msgViewController.removeMessages(owner, owner, sendEvent);
        
        Assert.assertTrue(messages.size() == 8);
    }
}
