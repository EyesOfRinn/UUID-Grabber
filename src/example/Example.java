import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.json.simple.parser.ParseException;

import xyz.rinn.uuidgrabber.UUIDGrabber;

public class Example {
	public static void main(String[] args) {
	    //Create a new instance of UUIDGrabber
		UUIDGrabber grabber = new UUIDGrabber("Shoot");
		//Create a list of UUIDs
		List<UUID> uuids = null;
		
		try {
		    //Get all of the UUIDs
			uuids = grabber.call();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		//Print your response, in this case, it is "All of the UUIDs for Shoot are: [afa1ce8a-7f6c-4018-aa1c-f1f92fff1704]"
		System.out.println("All of the UUIDs for Shoot are: " + uuids);
	}
}