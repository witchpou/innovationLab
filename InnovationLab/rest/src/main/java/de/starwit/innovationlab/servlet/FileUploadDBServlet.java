package de.starwit.innovationlab.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import de.starwit.innovationlab.ejb.IdeaService;
import de.starwit.innovationlab.ejb.impl.IdeaServiceImpl;
 
@WebServlet("/uploadFile")
@MultipartConfig(maxFileSize = 1048576)    // upload file's size up to 1MB
public class FileUploadDBServlet extends HttpServlet {
     
	private static final long serialVersionUID = 1L;
	private static Logger LOG = Logger.getLogger(IdeaServiceImpl.class);

	@Inject
    IdeaService ideaService;
     
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	try {
    		// gets values of text fields
    		Long ideaId = Long.parseLong(request.getParameter("id"));
    		
            InputStream imageStream = null; // input stream of the upload file
            for (Part part : request.getParts()) {
	            // obtains the upload file part in this multipart request
	            if (part != null && part.getContentType().toLowerCase().startsWith("image")) {
                 
	                // obtains input stream of the upload file
	                imageStream = part.getInputStream();
	                
	                ideaService.setImageByIdeaId(ideaId, imageStream);
	                return;
	            }
            }
    		
    	} catch(NumberFormatException e) {
    		LOG.info("No valid id was transfered in the request. Request-Parameter 'id' has to be set to a number.");
    	}
    }
}