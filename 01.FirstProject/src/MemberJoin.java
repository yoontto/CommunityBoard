

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanul.member.MemberDTO;

/**
 * Servlet implementation class MemberJoin
 */
@WebServlet("/memberJoin.do")	//자동으로 Mapping 설정
public class MemberJoin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트의 요청을 받는다 : 매개변수를 가져온다 ▶ HttpServletRequest
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		int age = Integer.parseInt(request.getParameter("age"));
		String addr = request.getParameter("addr");
		String tel = request.getParameter("tel");
		
		/*System.out.println("이름 : " + name);
		System.out.println("아이디 : " + id);
		System.out.println("비밀번호 : " + pw);
		System.out.println("나이 : " + age);
		System.out.println("주소 : " + addr);
		System.out.println("전화번호 : " + tel);*/
		
		//비지니스 로직 : DAO를 통해 DB와 연동하여 결과를 리턴받는 작업
		MemberDTO dto = new MemberDTO(name, id, pw, age, addr, tel);
		//DB연동 : MemberDAO.java → insertMember(dto); ▶ 생략
		
		//프리젠테이션 로직 : 리턴받은 결과를 클라이언트에게 응답(html, jsp)
		request.setAttribute("dto", dto);	//바인딩(연결) 객체
		RequestDispatcher rd = request.getRequestDispatcher("result.jsp");	//페이지 호출
		rd.forward(request, response);	//페이지 전환
	}

}






