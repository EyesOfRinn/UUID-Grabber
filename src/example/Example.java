import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.json.simple.parser.ParseException;

import xyz.rinn.uuidgrabber.UUIDGrabber;

public class Example {
	public static void main(String[] args) {
	    List<UUID> uuids = null;
		
		try {
			uuids = new UUIDGrabber("Shoot").call();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		uuids.forEach(uuid -> System.out.println(uuid));
	}
}