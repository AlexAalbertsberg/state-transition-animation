package stv.command;

import javafx.stage.Stage;
import stv.json.JSONObject;

public class MoveCommand extends AbsCommand {

	public MoveCommand(JSONObject json, Stage stage) {
		super(json, stage);
	}

	@Override
	public void performCommand() {
		// TODO read params and execute command operations
		System.out.println("Called Move Command Perform");
	}

}
