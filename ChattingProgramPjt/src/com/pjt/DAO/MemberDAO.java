package com.pjt.DAO;

import java.util.HashMap;

import com.pjt.DTO.Member;

//DB���� �� �ӽ÷� ���� ��ü(���߿� DB�� �����ϵ��� �ٲ�����Ѵ�!)
public class MemberDAO {
	public static HashMap<String, Member> list = new HashMap<String, Member>();

	public MemberDAO() {
		list.put("kim", new Member("kim", "1111", "���ȯ", "01012345678"));
		list.put("yang", new Member("yang", "2222", "������", "01022223333"));
		list.put("woo", new Member("woo", "3333", "���ѿ�", "01033334444"));
		list.put("lee", new Member("lee", "4444", "�̵�ȣ", "01044445555"));
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