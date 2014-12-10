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
			@SuppressWarnings("resource")
			@Override
			public void run() {
				ServerSocket socket = null;
				try {
					socket = new ServerSocket(13337);
				} catch (IOException e) {
					e.printStackTrace();
				}
				while(true)
				{
					System.out.println("Oh god");
					Socket clientSocket = null;
					try {
						clientSocket = socket.accept();
					} catch (IOException e) {
						e.printStackTrace();
					}
					proc.process(clientSocket);
				}
			}
		};
		
		Thread serverThread = new Thread(serverTask);
		serverThread.start();
	}
}
