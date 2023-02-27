package com.hospital.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class FrontController extends HttpServlet {    //컨트롤러를 서블릿으로 개발하는것은 mvc 모델 2의 구조로 개발 ,jsp로 개발하는것은 mvc  모델1 개발

	private static final long serialVersionUID = 1L;

	String charset = null;
	HashMap<String, Controller> list = null;

	@Override
	public void init(ServletConfig sc) throws ServletException {   //현재 서블릿 객체가 최초로 요청이 들어왔을떄 한번만 실행되는 메소드 ,초기화 기능 담당

		charset = sc.getInitParameter("charset");     //web.xml <servlet> 태그 <init-param> 의 <param-name> charset인 태그의 <param-value> 값 utf-8을 추출

		list = new HashMap<String, Controller>();    //list는 클라이언트 요청에 대하여 서브 컨트롤러에 대한 정보를 가지는 HashMap 객체임
		list.put("/memberInsert.do", new MemberInsertController());  // key 값은 url 즉 memberInsert.do value 값은 new MemberInsertController 임 여기서 서브 컨트롤러는 new MemberInsertController
		list.put("/memberSearch.do", new MemberSearchController());
		list.put("/memberUpdate.do", new MemberUpdateController());
		list.put("/memberDelete.do", new MemberDeleteController());
		list.put("/memberList.do", new MemberListController());

	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  //클라이언트로부터 요청이 들어올때마다 실행하는 메소드

		request.setCharacterEncoding(charset);       //post 방식으로 전달된 질의 문자열을 인코딩 처리하는 메소드 ,web.xml 에서 파럼 태그를 사용하여 가져오면 유지보수성이 좋음

		String url = request.getRequestURI();		//url 에서 key를 추출하는 코드 
		String contextPath = request.getContextPath();
		String path = url.substring(contextPath.length());

		Controller subController = list.get(path); 		
		subController.execute(request, response);		//controll 인터페이스 상속 받아 작성하도록 규정 따라서 execute() 메소드를 재정의 하여 controllr 기능 구현
	}
}
