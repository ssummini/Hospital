package com.hospital.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.hospital.service.MemberService;
import com.hospital.vo.MemberVO;

public class MemberUpdateController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Parameter ����
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String birthday = request.getParameter("birthday");
		String phone = request.getParameter("phone");

		// ��ȿ�� üũ
		if (id.isEmpty() || name.isEmpty() || birthday.isEmpty() || phone.isEmpty()) {
			request.setAttribute("error", "모든 항목을 빠짐없이 입력해주시기 바랍니다.");
			HttpUtil.forward(request, response, "/memberUpdate.jsp");
			return;
		}

		// VO��ü�� ����Ÿ ���ε�
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setName(name);
		member.setBirthday(birthday);
		member.setPhone(phone);

		// Service ��ü�� �޼��� ȣ��
		MemberService service = MemberService.getInstance();
		service.memberUpdate(member);

		// Output View �������� �̵�
		request.setAttribute("id", id);
		HttpUtil.forward(request, response, "/result/memberUpdateOutput.jsp");
	}
}
