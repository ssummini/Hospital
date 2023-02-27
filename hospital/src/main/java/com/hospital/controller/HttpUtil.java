package com.hospital.controller;
//처리결과 페이지로 이동하는 작업 페이지
import javax.servlet.*;
import javax.servlet.http.*;

public class HttpUtil {
	public static void forward(HttpServletRequest request, HttpServletResponse response, String path) { //다른페이지로 이동하기 위한 기능
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		} catch (Exception ex) {
			System.out.println("forward 오류 : " + ex);
		}
	}
}
