package stv.app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketListener {
	
	private CommandProcessor proc;
	
	public SocketListener(CommandProcessor proc)
	{
		this.proc = proc;
	}
	
	public void listen()
	{
		Runnable serverTask = new Runnable()
		{
			@Override
			public void run() 
			{
				ServerSocket socket = null;
				try 
				{
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
							System.out.println("Waiting for client input");
							clientSocket = socket.accept();
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
						}
						proc.process(clientSocket);
					}
				}
			}
		};
		
		Thread serverThread = new Thread(serverTask);
		serverThread.start();
	}
}
