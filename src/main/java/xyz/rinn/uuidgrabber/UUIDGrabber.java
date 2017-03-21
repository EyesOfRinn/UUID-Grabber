package xyz.rinn.uuidgrabber;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class UUIDGrabber implements Callable<List<UUID>> {
    private final String name;

    public UUIDGrabber(final String name) {
    	this.name = name;
    }
    
    public List<UUID> call() throws IOException, ParseException {
        final List<UUID> list = new ArrayList<>();
        
        // Create a connection
        final HttpURLConnection connection = (HttpURLConnection) new URL("https://api.mojang.com/profiles/minecraft").openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setUseCaches(false);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        
        // Write to the connection
        final OutputStream stream = connection.getOutputStream();
        stream.write(new String("[\"" + this.name + "\"]").getBytes());
        
        // Flush and close the output stream
        stream.flush();
        stream.close();
        
        // Convert the response into an array of objects
        final JSONArray array = (JSONArray) new JSONParser().parse(new InputStreamReader(connection.getInputStream()));
        
        // Loop through each profile in the array
        for (final Object profile : array) {
            // UUIDs returned do not have hyphens('-') in them, so we must add them in order to convert a String to a UUID
            list.add(this.getUUID(((JSONObject) profile).get("id").toString()));
        }
        return list;
    }

    private UUID getUUID(String id) {
    	final StringBuilder string = new StringBuilder();
    	string.append(id.substring(0, 8)).append('-').append(id.substring(8, 12)).append('-').append(id.substring(12, 16)).append('-').append(id.substring(16, 20)).append('-').append(id.substring(20, 32));
    	
        return UUID.fromString(string.toString());
    }
}