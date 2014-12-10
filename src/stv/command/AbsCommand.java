package stv.command;

import stv.json.JSONObject;

public abstract class AbsCommand {
	protected JSONObject[] params;
	
	public AbsCommand(JSONObject[] params)
	{
		this.params = params;
	}
	
	public abstract void performCommand();
	
}
