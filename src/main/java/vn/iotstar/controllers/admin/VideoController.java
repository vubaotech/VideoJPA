package vn.iotstar.controllers.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Video;
import vn.iotstar.service.ICategoryService;
import vn.iotstar.service.IVideoService;
import vn.iotstar.service.impl.CategoryServiceImpl;
import vn.iotstar.service.impl.VideoServiceImpl;
import vn.iotstar.util.UploadUtils;

@MultipartConfig
@WebServlet(urlPatterns = { "/admin/video/list", "/admin/video/add", "/admin/video/edit", "/admin/video/delete",
		"/admin/video/reset" })
public class VideoController extends HttpServlet {
	IVideoService videoService = new VideoServiceImpl();
	ICategoryService categoryService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();

		if (url.contains("list")) {
			findAll(req, resp);
			req.getRequestDispatcher("/views/admin/video/list.jsp").forward(req, resp);
		} else if (url.contains("add")) {
			loadCategories(req);
			req.getRequestDispatcher("/views/admin/video/add.jsp").forward(req, resp);
		} else if (url.contains("edit")) {
			String videoId = req.getParameter("videoId");
			Video video = videoService.findById(videoId);
			req.setAttribute("video", video);
			loadCategories(req);
			req.getRequestDispatcher("/views/admin/video/edit.jsp").forward(req, resp);
		} else if (url.contains("delete")) {
			delete(req, resp);
			findAll(req, resp);
			req.getRequestDispatcher("/views/admin/video/list.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURL().toString();

		if (url.contains("add")) {
			Video video = new Video();

			String videoId = req.getParameter("videoId");
			video.setVideoId(videoId);

			video.setTitle(req.getParameter("title"));
			video.setDescription(req.getParameter("description"));
			video.setActive(Boolean.parseBoolean(req.getParameter("active")));

			int categoryId = Integer.parseInt(req.getParameter("categoryId"));
			video.setCategory(categoryService.findById(categoryId));

			if (req.getPart("poster").getSize() > 0) {
				video.setPoster(UploadUtils.processUpload("poster", req, "/videos/", videoId));
			}

			videoService.insert(video);
			resp.sendRedirect(req.getContextPath() + "/admin/video/list");

		} else if (url.contains("edit")) {
			String videoId = req.getParameter("videoId");
			Video video = videoService.findById(videoId);

			video.setTitle(req.getParameter("title"));
			video.setDescription(req.getParameter("description"));
			video.setActive(Boolean.parseBoolean(req.getParameter("active")));

			int categoryId = Integer.parseInt(req.getParameter("categoryId"));
			video.setCategory(categoryService.findById(categoryId));

			if (req.getPart("poster").getSize() > 0) {
				video.setPoster(UploadUtils.processUpload("poster", req, "/videos/", videoId));
			}

			videoService.update(video);
			resp.sendRedirect(req.getContextPath() + "/admin/video/list");
		}
	}

	protected void findAll(HttpServletRequest req, HttpServletResponse resp) {
		List<Video> videos = videoService.findAll();
		req.setAttribute("videos", videos);
	}

	protected void loadCategories(HttpServletRequest req) {
		List<Category> categories = categoryService.findAll();
		req.setAttribute("categories", categories);
	}

	protected void delete(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String videoId = req.getParameter("videoId");
			videoService.delete(videoId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
