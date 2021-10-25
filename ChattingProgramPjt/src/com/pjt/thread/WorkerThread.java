package com.pjt.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Queue;

import com.pjt.DAO.MemberDAO;
import com.pjt.DTO.Member;

public class WorkerThread extends Thread{
	
	Queue<Socket> access;
	MemberDAO mDAO = new MemberDAO();
	
	//나중에 접속자 목록을 나타내줄 해쉬맵, 현재는 소켓을 받아주지만 스레드로 바꿔줘야한다.(아직 완벽한 이해 불가능.)
	public static HashMap<String, Member> userMap = new HashMap<String, Member>();
	BufferedReader input;
	static PrintWriter output;
	
	public WorkerThread() {
		// TODO Auto-generated constructor stub
	}
	
	//생성자
	public WorkerThread(Queue<Socket> access) {
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
					
					input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
					
					//어떤 작업을 할 지 process를 입력받음
					//1 : 로그인, 2 : 회원가입
					String process = input.readLine();
					
					//로그인
					if(process.equals("login")) {
						//id, pw 만 입력을 받아온다.
						String id = input.readLine();
						String pw = input.readLine();
						
						//id, pw가 각각 일치하는지 확인 후 작업을 처리하고 클라이언트에게도 정보를 날려준다.(61~73)
						//로그인이 성공했을 경우 접속자 userMap에 넣어주어서 홈 패널에서 리스트에 나타나게 해준다.
						int check = mDAO.check(id, pw);
						output.println(check);
						output.flush();
						if(check == 1) {
							System.out.println("로그인 성공!");
							Member m = mDAO.you(id);
							m.setSocket(socket);
							userMap.put(id, m);
							userListUpload(); //유저목록 갱신
							System.out.println("해쉬맵 사이즈 : "+userMap.size());
							userMap.get(id).start();
						}else {
							System.out.println("로그인 실패!");
						}
					}
					
					//회원가입
					if(process.equals("regist")) {
						//회원가입에 필요한 모든 정보를 받아온다.
						String id = input.readLine();
						String pw = input.readLine();
						String name = input.readLine();
						String tel = input.readLine();
						
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
	
	//일단 접속한 모든 유저들의 목록을 갱신시켜주는 메소드
	public static void userListUpload() throws InterruptedException {
		for(String s : userMap.keySet()) {
			try {
				output = new PrintWriter(new OutputStreamWriter(userMap.get(s).getSocket().getOutputStream()));
				output.println("listUpdate");
				output.println(userMap.size());
				for(String s1 : userMap.keySet()) {
					output.println(userMap.get(s1).getuId()+"/"+userMap.get(s1).getuName());
					System.out.print(s1+", ");
				}
				System.out.println("에게 보냄");
				output.flush();
				Thread.sleep(10);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
