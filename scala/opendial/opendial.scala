import opendial.readers.XMLDomainReader;
import opendial.domains.Domain;
import opendial.DialogueSystem;
import opendial.gui.TextOnlyInterface;

object OpenDial {
    def main(args: Array[String]) : Unit = {
        val domain: Domain = XMLDomainReader.extractDomain("example-flightbooking.xml")
        val system: DialogueSystem = new DialogueSystem(domain)

        // Adding new domain modules (optional)
        system.attachModule(new TextOnlyInterface(system));

        // When used as part of another application, we often want to switch off the OpenDial GUI
        system.getSettings().showGUI = false;

        // Finally, start the system
        system.startSystem();
    }
}
