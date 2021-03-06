package com.pickfl.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pickfl.admin.vo.MemberSearchMVo;
import com.pickfl.member.model.service.MemberService;
import com.pickfl.member.model.vo.MemberVo;

@WebServlet("/memberInfo")
public class MemberInfoController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String page = req.getParameter("currentPage");
		MemberVo user = (MemberVo) req.getSession().getAttribute("loginUser");
		
		if(user.getId().equals("admin1")) {
		List<MemberVo> list = null;
		
		MemberSearchMVo vo = new MemberSearchMVo(page);
		
		req.setAttribute("searchVo", vo);
		
		list = new MemberService().allMemberList(vo);
		
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/WEB-INF/views/admin/memberInfo.jsp").forward(req, resp);
		} else {
			req.setAttribute("msg", "회원 조회 권한이 없습니다");
			req.getRequestDispatcher("/WEB-INF/views/common/errorPageM.jsp").forward(req, resp);
		}
	}
}
