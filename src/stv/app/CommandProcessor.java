package stv.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.stage.Stage;
import stv.command.AbsCommand;
import stv.command.CommandConstants;
import stv.command.InitCommand;
import stv.command.MoveCommand;
import stv.command.RemoveCommand;
import stv.command.SetCommand;
import stv.json.JSONObject;

/**
 * The CommandProcessor class receives and sends back data into the socket's respective output and input streams.
 * @author Alex Aalbertsberg
 *
 */
public class CommandProcessor 
{
	private Stage stage;
	private BufferedReader in;
	private PrintWriter out;
	
	/**
	 * Handles the input and output of the connected socket.
	 * @param clientSocket - Socket upon which a connection has been established
	 */
	public void process(Socket clientSocket)
	{
		if(clientSocket != null)
		{
			try
			{
				// Initialize socket reader and writer objects
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			while(true)
			{
				String returnMessage = "";
				JSONObject json = null;
				try
				{
					final String result = in.readLine();
					if(result == null || result.trim().equals("")) break;
					//System.out.println("Result: " + result);
					json = new JSONObject(result);
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				
				if(json != null)
				{
					if(json.has(CommandConstants.COMMAND_KEYWORD))
					{
						String commandId = json.getString(CommandConstants.COMMAND_KEYWORD);
						System.out.println(commandId);
						AbsCommand command = null;
						
						// find out which command has been issued
						switch(commandId)
						{
							case CommandConstants.CMD_INIT:
								command = new InitCommand(json, stage);
								break;
							case CommandConstants.CMD_SET:
								command = new SetCommand(json, stage);
								break;
							case CommandConstants.CMD_MOVE:
								command = new MoveCommand(json, stage);
								break;
							case CommandConstants.CMD_REMOVE:
								command = new RemoveCommand(json, stage);
								break;
						}
						
						if(command != null)
						{
							returnMessage = command.performCommand();
						}
						else
						{
							returnMessage = "Command not recognized";
						}
					}
					else
					{
						returnMessage = "JSON object does not include a command field";
					}
				}
				// return message is not empty, this implies that it should be sent back to the client
				if(!returnMessage.isEmpty())
				{
					out.println(returnMessage);
					
					out.flush();
				}
			}
		}
	}

	public void setStage(Stage stage)
	{
		this.stage = stage;
	}
}
