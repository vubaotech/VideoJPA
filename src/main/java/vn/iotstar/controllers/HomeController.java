package vn.iotstar.controllers;

import java.io.IOException;
import java.util.List;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.dao.impl.VideoDao;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Video;
import vn.iotstar.service.ICategoryService;
import vn.iotstar.service.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = {"/home"})
public class HomeController extends HttpServlet {
	ICategoryService categoryService = new CategoryServiceImpl();

	private static final long serialVersionUID = 1L;

	@Override

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			// lấy tất cả video

			VideoDao dao = new VideoDao();

			List<Video> list = dao.findAll();

			req.setAttribute("videos", list);

			// lấy tất cả category

			// khai báo danh sách và gọi hàm findAll() trong dao

			List<Category> listc = categoryService.findAll();

			// thông báo

			req.setAttribute("categorys", listc);
			
			// Tạo Map để đếm số video cho mỗi category
		    Map<Category, Integer> count = new HashMap<>();
		    for (Category cat : listc) {
		        int categoryCount = 0;
		        for (Video vid : list) {
		            // Giả sử Video có phương thức getCategory() trả về đối tượng Category
		            // và Category có phương thức getId() trả về int
		            if (vid.getCategory() != null && vid.getCategory().getId() == cat.getId()) {
		                categoryCount++;
		            }
		        }
		        count.put(cat, categoryCount);
		    }
		    
		    // Đặt Map count vào request
		    req.setAttribute("count", count);

		} catch (Exception e) {

			e.printStackTrace();

			req.setAttribute("error", e.getMessage());

		}

		findAll(req, resp);

		RequestDispatcher rq = req.getRequestDispatcher("/views/web/home.jsp");

		rq.forward(req, resp);

	}

	protected void findAll(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

		try {

			// khởi tạo DAO

			VideoDao dao = new VideoDao();

			// khai báo danh sách và gọi hàm findAll() trong dao

			List<Video> list = dao.findAll();

			// thông báo

			request.setAttribute("listP", list);

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("error", "Eror: " + e.getMessage());

		}

	}

	@Override

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// doGet(req, resp);

	}
}
