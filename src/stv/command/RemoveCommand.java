package stv.command;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import stv.json.JSONObject;

public class RemoveCommand extends AbsCommand
{	
	private String id;
	
	public RemoveCommand(JSONObject json, Stage stage)
	{
		super(json, stage);
	}

	@Override
	public String performCommand()
	{
		if(json.has(CommandConstants.IDENTIFIER))
		{
			id = json.getString(CommandConstants.IDENTIFIER);
		}
		
		Node node = null;
		
		for(Node n : stage.getScene().getRoot().getChildrenUnmodifiable())
		{
			if(n.getId() != null && n.getId().equals(id))
			{
				node = n;
			}
		}
		
		final Node nRemove = node;
		
		if(nRemove != null)
		{
			Platform.runLater(() ->
			{
				((Pane)stage.getScene().getRoot()).getChildren().remove(nRemove);
			});
			return "Remove command executed successfully.";
		}
		else
		{
			return "Specified node does not exist.";
		}
	}	
}
