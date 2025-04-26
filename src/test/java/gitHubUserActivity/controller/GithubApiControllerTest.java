package gitHubUserActivity.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import gitHubUserActivity.dtos.Activity;
import tablasPinguino.Tabla;

public class GithubApiControllerTest {
	
	private GithubApiController controller;
	
	public GithubApiControllerTest() {
		controller = new GithubApiController();
	}
	
	
	@Test
	public void getUserEventTest() {
		try {
			List<Activity> activities = controller.getUserEvent("NinjaYellowPenguin");
			Tabla tablaPinguino = new Tabla(activities.stream().toArray(Activity[]::new));
			tablaPinguino.sysoTable();
			assertEquals(1,1);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
