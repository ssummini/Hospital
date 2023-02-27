package com.hospital.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.hospital.service.MemberService;

public class MemberDeleteController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Parameter ����
		String id = request.getParameter("id");

		// Service ��ü�� �޼��� ȣ��
		MemberService service = MemberService.getInstance();
		service.memberDelete(id);

		// Output View �������� �̵�
		HttpUtil.forward(request, response, "/result/memberDeleteOutput.jsp");

	}
}
