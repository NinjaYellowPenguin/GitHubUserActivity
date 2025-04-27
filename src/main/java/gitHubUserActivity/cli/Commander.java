package gitHubUserActivity.cli;

import java.util.ArrayList;
import java.util.List;

import gitHubUserActivity.controller.GithubApiController;
import gitHubUserActivity.dtos.Activity;
import glaciar.commanderpenguin.CommanderPenguin;
import glaciar.commanderpenguin.PenguinComand;
import glaciar.tablapinguino.TablaPinguino;

public class Commander extends CommanderPenguin{
	
	public Commander(String[] args) {
		super(args);
	}

	@PenguinComand(value = "github-activity")
	public void accionA() {
		GithubApiController controller = new GithubApiController();
		List<Activity> activities = new ArrayList<Activity>();
		try {
			activities = controller.getUserEvent(params[0]);
		} catch (Exception e) {
			System.out.println("Me cawen to, usuario no encontrado");
			//e.printStackTrace();
		}
		TablaPinguino tablaPinguino = new TablaPinguino(activities.stream().toArray(Activity[]::new));
		tablaPinguino.sysoTable();
	}

}
