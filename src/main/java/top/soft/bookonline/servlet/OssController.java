package top.soft.bookonline.servlet;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import top.soft.bookonline.entity.User;
import top.soft.bookonline.service.Impl.UserServiceImpl;
import top.soft.bookonline.service.UserService;
import top.soft.bookonline.util.OssUtils;
import top.soft.bookonline.util.PartToMultipartFileConverter;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

@MultipartConfig
@WebServlet("/uploadImage")
public class OssController extends HttpServlet {
    public OssUtils ossUtils;
    public UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ossUtils = new OssUtils();
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Part filePart = request.getPart("file"); // 获取上传的文件Part

        MultipartFile multipartFile = PartToMultipartFileConverter.convert(filePart);

        // 现在你可以使用multipartFile就像使用Spring的MultipartFile一样
        String url = ossUtils.upload(multipartFile);
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        // 构造 JSON 格式的响应内容
        String jsonResponse = "{\"message\": \"文件上传成功！\", \"fileUrl\": \"" + url + "\"}";

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        System.out.println(user.getAccount());

        int i = userService.updataImage(user.getAccount(), url);
        if(i > 0) {
            User user1 = userService.userInfo(user.getAccount());
            session.setAttribute("user",user1);
            request.getRequestDispatcher("user.jsp").forward(request,response);

        }else {

        }
        // 输出 JSON 响应
        out.print(jsonResponse);
        out.flush(); // 确保所有内容都被写出
    }

}
