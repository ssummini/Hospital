package com.hospital.controller;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import com.hospital.service.MemberService;
import com.hospital.vo.MemberVO;

public class MemberListController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = MemberService.getInstance();
		ArrayList<MemberVO> list = service.memberList();

		request.setAttribute("list", list);
		HttpUtil.forward(request, response, "/result/memberListOutput.jsp");
	}
}
