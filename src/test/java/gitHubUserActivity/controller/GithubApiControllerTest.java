package gitHubUserActivity.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import gitHubUserActivity.dtos.Activity;
import glaciar.penguintable.TablaPinguino;

public class GithubApiControllerTest {
	
	private GithubApiController controller;
	
	public GithubApiControllerTest() {
		controller = new GithubApiController();
	}
	
	
	@Test
	public void getUserEventTest() throws Exception {
		List<Activity> activities = controller.getUserEvent("NinjaYellowPenguin");
		TablaPinguino tablaPinguino = new TablaPinguino(activities.stream().toArray(Activity[]::new));
		tablaPinguino.sysoTable();
		assertEquals(1,1);
	}

}
