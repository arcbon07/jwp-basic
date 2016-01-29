package next.web.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;

public class AddAnswerController extends AbstractController {
    private AnswerDao answerDao = new AnswerDao();
    private QuestionDao questionDao = new QuestionDao();
    
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	long questionId = ServletRequestUtils.getRequiredLongParameter(request, "questionId");
    	
    	questionDao.countofCommentPlus(questionId);
    	int count = questionDao.findById(questionId).getCountOfComment();
        Answer answer = new Answer(
                request.getParameter("writer"),
                request.getParameter("contents"),
                questionId
                );
        Answer savedAnswer = answerDao.insert(answer);
        ModelAndView mav = jsonView();
        mav.addObject("answer", savedAnswer);
        mav.addObject("count", count);
        return mav;
    }

}
