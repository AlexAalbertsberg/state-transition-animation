package stv.command;

import javafx.stage.Stage;
import stv.json.JSONObject;

public abstract class AbsCommand {
	protected JSONObject json;
	protected Stage stage;
	
	public AbsCommand(JSONObject params, Stage stage)
	{
		this.json = params;
		this.stage = stage;
	}
	
	public abstract String performCommand();
	
}
