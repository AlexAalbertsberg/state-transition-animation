package stv.app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The SocketListener class handles the connection between the client application and this server application.
 * @author Alex Aalbertsberg
 */
public class SocketListener 
{
	
	private CommandProcessor proc;
	private ServerSocket socket;
	
	/**
	 * Constructor
	 * @param proc - Server application's command processor
	 */
	public SocketListener(CommandProcessor proc)
	{
		this.proc = proc;
	}
	
	/**
	 * Creates a new server socket on a (currently preset) port.
	 * This socket will listen for incoming connections and messages from a client application.
	 */
	public void listen()
	{
		Runnable serverTask = new Runnable()
		{
			@Override
			public void run() 
			{
				try 
				{
					// Create socket
					socket = new ServerSocket(13337);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				if(socket != null && !socket.isClosed())
				{
					while(true)
					{
						Socket clientSocket = null;
						try 
						{
							// Wait for client input
							System.out.println("Waiting for client input");
							clientSocket = socket.accept();
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
						}
						System.out.println("Client input received");
						// Call the command processor to process incoming message
						proc.process(clientSocket);
					}
				}
			}
		};
		
		// Run server socket in a separate thread so that it does not block GUI execution.
		Thread serverThread = new Thread(serverTask);
		serverThread.start();
	}
}
