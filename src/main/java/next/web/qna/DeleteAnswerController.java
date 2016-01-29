package next.web.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Result;

public class DeleteAnswerController extends AbstractController{

	private AnswerDao answerDao = new AnswerDao();
	private QuestionDao questionDao = new QuestionDao();
	
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		
		
		long answerId = ServletRequestUtils.getRequiredLongParameter(request, "answerId");
		long questionId = answerDao.findById(answerId).getQuestionId();
		answerDao.delete(answerId);
		
		questionDao.countofCommentMinus(questionId);
    	int count = questionDao.findById(questionId).getCountOfComment();
		
		ModelAndView mav = jsonView();
		if(answerDao.findById(answerId)==null)
			mav.addObject("result",Result.ok());
		else 
			mav.addObject("result", Result.fail("error message"));
		mav.addObject("count", count);
        return mav;
	}
}
