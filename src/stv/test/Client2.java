package stv.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import stv.json.JSONObject;

public class Client2 
{
	public static void main(String[] args)
	{
		try
		{
			System.out.println(new File(".").getAbsolutePath());
			Scanner sin = new Scanner(System.in);
			String fileName = sin.nextLine();
			sin.close();
			
			Socket s = new Socket("127.0.0.1", 13337);
	
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter out =  new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())));
		
			BufferedReader br = new BufferedReader(new FileReader("src/scenarios/" + fileName));
			String line;
			
			while((line = br.readLine()) != null)
			{
				JSONObject obj = new JSONObject();
				String[] parts = line.split(";");
				for(String str : parts)
				{
					String[] parts2 = str.split("=");
					String param = parts2[0];
					String content = "";
					if(param.equals("image"))
					{
						String file = parts2[1];
						byte[] lel = Files.readAllBytes(Paths.get(new File("").getAbsolutePath() + "/src/images/" + file));
						
						content = Base64.encode(lel);
					}
					else
					{
						content = parts2[1];
					}
					
					obj.put(param, content);
				}
				
				System.out.println(obj.toString());
				out.println(obj.toString());
				out.flush();
				Thread.sleep(5000);
			}
			
			Thread.sleep(5000); // wait 5 seconds
			in.close();
			out.close();
			s.close();
			br.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
