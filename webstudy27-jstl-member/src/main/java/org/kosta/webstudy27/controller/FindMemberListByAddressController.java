package org.kosta.webstudy27.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.webstudy27.model.MemberDAO;
import org.kosta.webstudy27.model.MemberVO;

public class FindMemberListByAddressController implements Controller {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String address=request.getParameter("address");
		ArrayList<MemberVO> list=MemberDAO.getInstance().findMemberByAddress(address);
		request.setAttribute("mvo", list);
		return "findbyaddress-result.jsp";
	}

}
