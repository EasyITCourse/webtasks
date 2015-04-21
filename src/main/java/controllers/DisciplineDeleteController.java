package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DisciplineDeleteController extends AbstractWebtasksServletHandler {
    @Override
    protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // null ?????????
        String strId =  request.getParameter("id");
        int id = Integer.valueOf(strId);
        ///
        // -> SQL
     //   gotoToJSP("/main/discipline/disciplinesList.jsp",request,response);
        forwardRequest("/admin/disciplinesList.php",request,response);
    }
}
