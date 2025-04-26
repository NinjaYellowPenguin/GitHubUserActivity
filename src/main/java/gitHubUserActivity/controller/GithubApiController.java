package gitHubUserActivity.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import gitHubUserActivity.dtos.Activity;

public class GithubApiController {

    private static final String GITHUB_API_URL = "https://api.github.com/users/?/events";
    
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper mapper;
    
    public GithubApiController() {
    	mapper = new ObjectMapper();
    	mapper.registerModule(new JavaTimeModule());
    	
    }
    
    public List<Activity> getUserEvent(String user) throws Exception {    	
    	
        String endpoint = GITHUB_API_URL.replace("?", user);
        URI uri = URI.create(endpoint);
        HttpRequest request = createRequest(uri);        
        HttpResponse<String> response = sendRequest(request);
        checkStatus(response);
              
        return mapper.readValue(response.body(), new TypeReference<List<Activity>>() {});
        
    }

	private void checkStatus(HttpResponse<String> response) throws Exception {
		if(response.statusCode() == 404) {
			throw new Exception("User not found");
		}
		if (response.statusCode() != 200) {
            throw new RuntimeException("Error: " + response.statusCode() + " - " + response.body());
        }
		
	}

	private HttpResponse<String> sendRequest(HttpRequest request) throws IOException, InterruptedException {
		return httpClient.send(
                request, 
                HttpResponse.BodyHandlers.ofString()
        );
	}

	private HttpRequest createRequest(URI uri) {
		return HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .header("Accept", "application/vnd.github.v3+json")
                .build();
	}
    
}
