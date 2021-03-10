package com.bit.emaillist.test;

import java.util.*;

import com.bit.emaillist.dao.EmaillistDao;
import com.bit.emaillist.vo.EmaillistVo;

public class EmaillistDaoTest {
	
	
	public static void main(String[] args) {
		// insert test
		testInsert();
		
		
		// findAll test
		testFindAll();
	}
	
	public static void testFindAll() {
		List<EmaillistVo> list = new EmaillistDao().findAll();
		
		for(EmaillistVo vo : list) {
			System.out.println(vo);
		}
	}
	
	public static void testInsert() {
		EmaillistVo vo = new EmaillistVo();
		vo.setFirstName("이콜");
		vo.setLastName("마");
		vo.setEmail("micall@micall.com");
		
		new EmaillistDao().insert(vo);
	}
}
