import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("content-type","text/html;charset=utf-8");
        PrintWriter printWriter=response.getWriter();
        String name =request.getParameter("userName");
        String id=request.getParameter("userId");
        String pw1=request.getParameter("userPwd1");
        String pw2=request.getParameter("userPwd2");
        if (pw1.equals(pw2)){
            try {
                Database database=new Database("gem","123456");
                database.insert(Integer.parseInt(id),name,pw1);
                database.close();
                printWriter.write("注册成功");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                printWriter.write("糟糕，注册失败,请重试");
            }
        }
        else {

            printWriter.write("两次密码不一致");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }
}
