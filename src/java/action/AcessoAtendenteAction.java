
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AcessoAtendenteAction implements controller.Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
         response.sendRedirect("acesso-atendente.jsp");
    }
    
}
