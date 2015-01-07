package stv.test;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import stv.json.JSONObject;


public class Client
{
	private static Socket s;
	private static PrintWriter out;
	private static BufferedReader in;
	private static BufferedReader br;
	
	public static void main(String[] args)
	{
		try{
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Target ip: ");
			String ip = br.readLine();
			System.out.println("Target port: ");
			int port = Integer.parseInt(br.readLine());
			
			System.out.println(ip + ":" + port);
			s = new Socket(ip, port);

			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out =  new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())));
			
			while(true)
			{
				String input = br.readLine();
				
				switch(input.trim())
				{
					case "init":
						SendInit();
						break;
					case "1":
						SendBall1();
						break;
					case "2":
						SendBall2();
						break;
					case "3":
						SendBall3();
						break;
					case "4":
						SendBall4();
						break;
					case "5":
						SendBall5();
						break;
					case "move1":
						MoveBall1();
						break;
					case "r3":
						RemoveBall3();
						break;
					case "image":
						SendImage();
						break;
					case "q":
						System.out.println("Quitting");
						br.close();
						s.close();
						System.exit(0);
						break;
					default:
						System.out.println("test");
						break;
				}
				
				final String result = in.readLine();
				System.out.println(result);
				if(result != null && !result.trim().isEmpty())
				{
					System.out.println("Response: " + result);
				}
			}
		}
		catch(Exception e)
		{
			
		}
	}

	private static void SendImage()
	{
		byte[] lel = null;
		try
		{
			lel = Files.readAllBytes(Paths.get("C:/Users/Alex/workspace/STV/src/tree.jpg"));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		if(lel != null)
		{
			String test = Base64.encode(lel);
			
			JSONObject json = new JSONObject();
			json.put("command","set");
			json.put("id", "6");
			json.put("posx",100);
			json.put("posy",100);
			json.put("image", test);
			
			Send(json);
		}
	}

	private static void SendInit()
	{
		JSONObject json = new JSONObject();
		json.put("command", "init");
		json.put("width", 800.0);
		json.put("height", 800.0);
		
		Send(json);
	}

	private static void RemoveBall3()
	{
		JSONObject json = new JSONObject();
		json.put("command", "remove");
		json.put("id", "3");
		
		Send(json);
	}

	private static void MoveBall1()
	{
		JSONObject json = new JSONObject();
		json.put("command", "move");
		json.put("id", "1");
		json.put("posx", 50);
		json.put("posy", 50);
		
		Send(json);
		
	}

	private static void SendBall5()
	{
		JSONObject json = new JSONObject();
		json.put("command", "set");
		json.put("id", "5");
		json.put("posx", 500);
		json.put("posy", 200);
		json.put("shape", "circle");
		json.put("colorCode", "ff0000");
		
		Send(json);
	}

	private static void SendBall4()
	{
		JSONObject json = new JSONObject();
		json.put("command", "set");
		json.put("id", "4");
		json.put("posx", 300);
		json.put("posy", 300);
		json.put("shape", "circle");
		json.put("colorCode", "ff0000");
		
		Send(json);
	}

	private static void SendBall3()
	{
		JSONObject json = new JSONObject();
		json.put("command", "set");
		json.put("id", "3");
		json.put("posx", 600);
		json.put("posy", 500);
		json.put("shape", "circle");
		json.put("colorCode", "ffd700");
		
		Send(json);
	}

	private static void SendBall2()
	{
		JSONObject json = new JSONObject();
		json.put("command", "set");
		json.put("id", "2");
		json.put("posx", 400);
		json.put("posy", 400);
		json.put("shape", "circle");
		json.put("colorCode", "ffd700");
		
		Send(json);
	}

	private static void SendBall1()
	{
		JSONObject json = new JSONObject();
		json.put("command", "set");
		json.put("id", "1");
		json.put("posx", 300);
		json.put("posy", 600);
		json.put("shape", "circle");
		json.put("colorCode", "ff0000");
		
		Send(json);
	}
	
	private static void Send(JSONObject json)
	{
		System.out.println("Sending");

		out.println(json.toString());
		
		out.flush();
		
	}
}
