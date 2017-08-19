import opendial.readers.XMLDomainReader;
import opendial.domains.Domain;
import opendial.DialogueSystem;
import opendial.gui.TextOnlyInterface;

public class OpenDial {
    public static void main(String args[]) {
        Domain domain = XMLDomainReader.extractDomain("example-step-by-step_params.xml");
        DialogueSystem system =new DialogueSystem(domain);

        System.out.println("Initialized dialogue system");
        // Adding new domain modules (optional)
        //system.attachModule(OneExampleOfNewModule.class);
        system.attachModule(new TextOnlyInterface(system));

        // When used as part of another application, we often want to switch off the OpenDial GUI
        system.getSettings().showGUI = false;

        System.out.println("Starting dialogue system");
        // Finally, start the system
        system.startSystem();
        System.out.println("Stopped dialogue system");
    }
}
