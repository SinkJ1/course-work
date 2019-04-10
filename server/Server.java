package test2;
import java.awt.AWTException;
import java.awt.Robot;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class Server {
	private static Socket clientSocket;
	private static ServerSocket server;
	private static BufferedReader in;
	private static BufferedWriter out;
	
	public static void main(String[] args) throws AWTException{
		
		CoursourInfo coursourInfo = new CoursourInfo();
		Robot robot = new Robot();
		
		int x =0;
		int y =0;
		double xxx;
		double yyy;
		String ui;
		String word = " ";
		
		try {
			try {
				server = new ServerSocket(4004);
				System.out.println("Server is started!");
				
				clientSocket = server.accept();
				System.out.print("Connection accepted.");
				try {
					in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
					out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
					ui = String.valueOf(coursourInfo.mouzeY()) +":"+ String.valueOf(coursourInfo.mouzeX());
					System.out.println("--------");
				/*	out.write(ui+ "\n");
					out.flush();*/
					while(true) {
					word = in.readLine();
					
					StringTokenizer stk = new StringTokenizer(word,":");
	                    String []ar = new String[stk.countTokens()];
	                    for(int i = 0; i<ar.length; i++)
	                    {
	                        ar[i] = stk.nextToken();
	                    }
	                    String x2 = ar[0];
	                    String y2 = ar[1];
	                    System.out.println(x2 + "   x");
	                    System.out.println(y2 + "   y");
	                    xxx = Double.parseDouble(x2) * 0.2;
	                    yyy = Double.parseDouble(y2) * 0.2;
	                    x  =coursourInfo.mouzeX() + (int)xxx;
	                    y =coursourInfo.mouzeY() + (int)yyy;
	                    robot.mouseMove(x, y);
					}
				}finally {	
					System.out.println("SERVER IS CLOSED");
					clientSocket.close();
					in.close();
					out.close();
				
				}
			}finally {
				System.out.println("Server is closed");
				server.close();
			}
		}catch(IOException e) {
			System.err.println(e);
		}
	}
}
