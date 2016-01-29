package next.web.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class ShowQuestionModifyController extends AbstractController{


	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

	long questionId = ServletRequestUtils.getRequiredLongParameter(request, "questionId");
		
		ModelAndView mav = jstlView("/qna/formModify.jsp");
		mav.addObject("questionId", questionId);
		
		return mav;
	}
}
