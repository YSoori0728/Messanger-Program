package com.pjt.DAO;

import java.util.HashMap;

import com.pjt.DTO.Member;

//DB연결 전 임시로 만든 객체(나중에 DB와 연결하도록 바꿔줘야한다!)
public class MemberDAO {
	public static HashMap<String, Member> list = new HashMap<String, Member>();

	public MemberDAO() {
		list.put("kim", new Member("kim", "1111", "김경환", "01012345678"));
		list.put("yang", new Member("yang", "2222", "양지현", "01022223333"));
		list.put("woo", new Member("woo", "3333", "우한영", "01033334444"));
		list.put("lee", new Member("lee", "4444", "이동호", "01044445555"));
	}

	public int check(String id, String pw) {
		for (String a : list.keySet()) {
			if (a.equals(id) && list.get(a).getPw().equals(pw)) {

				return 1;

			}
		}
		return -1;
	}

	public int check(String id) {
		for (String a : list.keySet()) {
			if (a.equals(id)) {
				return -1;
			}
		}
		return 1;
	}

	public void regist(String id, String pw, String name, String tel) {
		list.put(id, new Member(id, pw, name, tel));
	}
}