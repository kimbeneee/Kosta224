package org.kosta.webstudy27.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.webstudy27.model.MemberDAO;
import org.kosta.webstudy27.model.MemberVO;

public class UpdateMemberController implements Controller {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 체크
		HttpSession session=request.getSession(false);
		if(session==null||session.getAttribute("mvo")==null) { // 로그인 상태가 아니면 index로 이동시킨다
			return "redirect:index.jsp";
		}
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		String birthday=request.getParameter("birthday");
		MemberVO vo=new MemberVO(id,password,name,address,birthday,null);
		MemberDAO.getInstance().UpdateMember(vo);
		session.setAttribute("mvo", vo);
		return "redirect:update-result.jsp";
	}

}
