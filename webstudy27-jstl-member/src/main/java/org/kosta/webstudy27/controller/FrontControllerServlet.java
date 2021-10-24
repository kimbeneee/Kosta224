package org.kosta.webstudy27.controller;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontControllerServlet
 */
//    "*.do" 를 사용할때는 "/"이거 무조건 빼줘야 함
@WebServlet("*.do")
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String uri=request.getRequestURI(); // 		/webstudy27-jstl-member/FindMemberByIdController.do       form태그 액션쪽에서 넘어온 URI
		String contextPath=request.getContextPath();// 	/webstudy27-jstl-member
		String command=uri.substring(contextPath.length()+1,uri.length()-3);//Controller class명만 추출 :FindMemberByIdController
		Controller controller=HandlerMapping.getInstance().create(command);
		String path=controller.excute(request, response);
				if(path.startsWith("redirect:")) {
					response.sendRedirect(path.substring(9));// redirect:  을 제외한 경로로 이동시킨다
				}else {
					request.getRequestDispatcher(path).forward(request, response);
				}
		}catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doDispatch(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		this.doDispatch(request, response);
	}

}
