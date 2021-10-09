package com.pjt.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class WorkerTherad extends Thread{
	
	Queue<Socket> access = new LinkedList<Socket>();
	
	BufferedReader read;
	PrintWriter output;
	
	//생성자
	public WorkerTherad(Queue<Socket> access) {
		this.access = access;
	}
	
	//스레드에서 할 일
	//1.로그인
	//ID,PW 확인하고 로그인 성공인지 실패인지 회신.
	//성공일 경우, 커넥트 유지! 실패일 경우 커넥트 끊고 처음 창으로.
	
	//2.회원등록
	//ID, PW, 이름, 전화번호를 받음.
	//ID중복확인 후 회원등록 OR 알림 회신
	//
	@Override
	public void run() {
		while(true) {
			// 큐가 비어있지 않다면 로그인 or 회원가입 실행
			if(!access.isEmpty()) {
				try {
					Socket socket = access.poll();
					
					read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
					
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			//스레드를 0.1초마다 실행
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	
}
