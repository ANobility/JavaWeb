import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setHeader("content-type","text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter printWriter=response.getWriter();
        String id=request.getParameter("userId");
        String pwd =request.getParameter("userPwd");
        try {
            Database database=new Database("gem","123456");
            Userdata userdata=database.check(Integer.parseInt(id),pwd);
            database.close();
            if (userdata==null){
                printWriter.write("账号不存在/密码错误");
            }
            else {
                printWriter.write("成功，欢迎"+userdata.getUserName());

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }
}
