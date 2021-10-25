package com.pjt.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JTextArea;

import com.pjt.UI.userLogin;

public class ClientThread extends Thread {
	Socket socket;
	BufferedReader input;
	JTextArea textArea;

	public ClientThread(Socket socket, JTextArea textArea) {
		this.socket = socket;
		this.textArea = textArea;
	}

	@Override
	public void run() {

		while (true) {
			try {
				input = new BufferedReader(new InputStreamReader(userLogin.goSocket.getInputStream()));
//				String first = input.readLine();
//				if (first.equals("5")) {
					String message = input.readLine();
					System.out.println(message);
					textArea.append(message + "\n");
//				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
