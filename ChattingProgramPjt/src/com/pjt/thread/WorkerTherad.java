package com.pjt.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Queue;

import com.pjt.DAO.MemberDAO;
import com.pjt.DTO.Member;

public class WorkerTherad extends Thread{
	
	Queue<Socket> access;
	MemberDAO mDAO = new MemberDAO();
	
	//나중에 접속자 목록을 나타내줄 해쉬맵, 현재는 소켓을 받아주지만 스레드로 바꿔줘야한다.(아직 완벽한 이해 불가능.)
	static HashMap<String, Socket> userMap = new HashMap<String, Socket>();
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
					
					//어떤 작업을 할 지 process를 입력받음
					//1 : 로그인, 2 : 회원가입
					String process = read.readLine();
					
					//로그인
					if(process.equals("1")) {
						//id, pw 만 입력을 받아온다.
						String id = read.readLine();
						String pw = read.readLine();
						
						//id, pw가 각각 일치하는지 확인 후 작업을 처리하고 클라이언트에게도 정보를 날려준다.(61~73)
						//로그인이 성공했을 경우 접속자 userMap에 넣어주어서 홈 패널에서 리스트에 나타나게 해준다.
						int check = mDAO.check(id, pw);
						output.println(check);
						output.flush();
						if(check == 1) {
							System.out.println("로그인 성공!");
							userMap.put(id, socket);
							output.println(check);
							output.println(id);
							output.flush();
						}else {
							System.out.println("로그인 실패!");
						}
					}
					
					//회원가입
					if(process.equals("2")) {
						//회원가입에 필요한 모든 정보를 받아온다.
						String id = read.readLine();
						String pw = read.readLine();
						String name = read.readLine();
						String tel = read.readLine();
						
						//id 중복확인을 하고 해당 작업을 처리한다.(85~96)
						//나중에 DB와의 연동을 통해 회원가입 정보를 모든 가입자 DB에 접속을 시켜주어야한다.
						int check = mDAO.check(id);
						output.println(check);
						output.flush();
						if(check ==1) {
							System.out.println("회원가입 성공!");
							MemberDAO.list.put(id, new Member(id, pw, name, tel));
						}else {
							System.out.println("회원가입 실패!");
							System.out.println("이미 존재하는 아이디입니다.");
						}
						output.close();
					}
					
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
