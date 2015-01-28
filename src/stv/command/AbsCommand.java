package stv.command;

import javafx.stage.Stage;
import stv.json.JSONObject;

/**
 * The abstract class AbsCommand defines a framework to which implemented commands must adhere.
 * @author Alex Aalbertsberg
 */
public abstract class AbsCommand 
{
	protected JSONObject json;
	protected Stage stage;
	
	/**
	 * Constructor
	 * @param params - The JSONObject that has been receiveds
	 * @param stage - This server application's stage.
	 */
	public AbsCommand(JSONObject params, Stage stage)
	{
		this.json = params;
		this.stage = stage;
	}
	
	/**
	 * The performCommand method will execute any commands that the command class is implemented to support.
	 * In this method, you should parse the JSONObject and act according to what it should contain.
	 * @return A message to return to the client, indicating success or failure and additional specifics.
	 */
	public abstract String performCommand();
	
}
