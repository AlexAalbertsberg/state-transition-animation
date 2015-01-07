package stv.command;

import javafx.application.Platform;
import javafx.stage.Stage;
import stv.app.Main;
import stv.json.JSONObject;

public class InitCommand extends AbsCommand
{
	private double height;
	private double width;
	
	public InitCommand(JSONObject params, Stage stage)
	{
		super(params, stage);
	}

	@Override
	public String performCommand()
	{	
		if(json.has(CommandConstants.STAGE_WIDTH))
		{
			width = json.getDouble(CommandConstants.STAGE_WIDTH);
		}
		if(json.has(CommandConstants.STAGE_HEIGHT))
		{
			height = json.getDouble(CommandConstants.STAGE_HEIGHT);
		}
		
		Platform.runLater(() ->
		{
			Main.init(width, height);
		});
		
		return "Init command execution completed successfully.";
	}
}
