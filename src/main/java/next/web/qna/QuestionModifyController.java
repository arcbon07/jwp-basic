package next.web.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import next.model.User;


public class QuestionModifyController extends AbstractController{
	
	private QuestionDao questiondao = new QuestionDao();
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		User curuser = (User)request.getSession().getAttribute("user");
		Question question = new Question(
				request.getParameter("writer"),
				request.getParameter("title"),
				request.getParameter("contents")
				);
		List<Question> questions = questiondao.findAll();
		
		//questiondao.modify(question);
		ModelAndView mav = jstlView("redirect:/qna/list.next");
		mav.addObject("questions",questions);
		return mav;
	}
	
}
