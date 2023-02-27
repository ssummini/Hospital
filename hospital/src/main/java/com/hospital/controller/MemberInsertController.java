package com.hospital.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.hospital.service.MemberService;
import com.hospital.vo.MemberVO;
//컨트롤러
public class MemberInsertController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Parameter 추출
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String birthday = request.getParameter("birthday");
		String phone = request.getParameter("phone");

		// 유효성 체크
		if (id.isEmpty() || name.isEmpty() || birthday.isEmpty() || phone.isEmpty()) {
			request.setAttribute("error", "모든 항목을 빠짐없이 입력해주시기 바랍니다."); //오류가 발생하여 입력 페이지로 이동했을때 보여 줄 오류 메시지를 request 에 저장
			HttpUtil.forward(request, response, "/memberInsert.jsp");
			return;
		}

		// VO 객체에 데이터 바인딩(저장)
		MemberVO member = new MemberVO(); 
		member.setId(id); //클라이언트로부터 전달된 입력값들을 setter 메소드를 호출하여 MemberVO 객체의 멤버변수에 저장
		member.setName(name);
		member.setBirthday(birthday);
		member.setPhone(phone);

		// Service 객체의 메소드 호출
		MemberService service = MemberService.getInstance();  	//회원 관리 서비스를 처리하는 모델인 MemberService 객체를 추출한후 회원정보 생성 서비스를 처리하는 memberInsert() 메소드를 호출 
		service.memberInsert(member);

		// Output View 페이지로 이동
		request.setAttribute("id", id); 		// id 값을 저장한후 페이지 이동
		HttpUtil.forward(request, response, "/result/memberInsertOutput.jsp");
	}
}
