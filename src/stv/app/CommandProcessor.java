package stv.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javafx.stage.Stage;
import stv.command.AbsCommand;
import stv.command.MoveCommand;
import stv.command.SetCommand;
import stv.json.JSONObject;

public class CommandProcessor 
{
	private Stage stage;
	
	public CommandProcessor()
	{
		
	}
	
	public void process(Socket clientSocket)
	{
		if(clientSocket != null)
		{
			try
			{
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				//BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
				while(true)
				{
					final String result = in.readLine();
					if(result == null || result.trim().equals("")) break;
					System.out.println("Result: " + result);
					JSONObject json = new JSONObject(result);
					
					if(json.has("command"))
					{
						String commandId = json.getString("command");
						AbsCommand command = null;
						
						if(commandId.toLowerCase().equals("init")) command = null;
						else if(commandId.toLowerCase().equals("set")) command = new SetCommand(json, stage);
						else if(commandId.toLowerCase().equals("move")) command = new MoveCommand(json, stage);
						
						if(command != null)
						{
							command.performCommand();
						}
						else
						{
							System.out.println("Command not recognized");
						}
					}
					else
					{
						System.out.println("JSON object does not include a command field");
					}
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void setStage(Stage stage)
	{
		this.stage = stage;
	}
}
