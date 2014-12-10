package stv.command;

import stv.json.JSONObject;

public class SetCommand extends AbsCommand{

	public SetCommand(JSONObject[] params) {
		super(params);
	}

	@Override
	public void performCommand() {
		// TODO read params
		JSONObject test = params[0];
	}

}
