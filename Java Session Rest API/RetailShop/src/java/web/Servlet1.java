package web;

import client.RestClient;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import ejb.RetailShopBeanLocal;

public class Servlet1 extends HttpServlet {
    
    RestClient rc = new RestClient();
      @EJB RetailShopBeanLocal al;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<center>");
            out.println("<h1>!! Add User !! </h1>");
//            out.println("<form action='' method='post' enctype='multipart/form-data'>");
//            out.println("<input type='text' placeholder='Enter The Username' name='user'><br/>");
//            out.println("<input type='email' placeholder='Enter The Email' name='email'><br/>");
//            out.println("<input type='text' placeholder='Enter The First Name' name='fname'><br/>");
//            out.println("<input type='text' placeholder='Enter The Last Name' name='lname'><br/>");
//            out.println("<input type='text' placeholder='Enter The Mobile No.' name='mobile'><br/>");
//            out.println("<input type='password' placeholder='Enter The Password' name='password'><br/>");
//            out.println("<input type='file' name='file'><br/>");
//            out.println("<button type='submit'>Submit</button>");
//            out.println("</form>");
            al.addUserByGroup("Abhi", "7vyas7jainish@gmail.com", "1234", "Abhay", "Dedkawala", "jk.jpg", "8264606624", "Admin");
//            if(request.getMethod().equals("POST")){
//                out.println("<h2> " + request.getParameter("file1") + " </h2>");
//                Part uploadedFile = request.getPart("file");
//                String fileName = uploadedFile.getSubmittedFileName();
//                String path = new File(getServletContext().getRealPath("/")).getName();
//                final Path destination;
//                destination = Paths.get(path + "/../admin/resources/image");
//                out.println("<h2> Destination : " + destination + " </h2>");
//                for (Part part : request.getParts()) {
//                part.write(destination + fileName);
//    }
////                String imagename = FileUpload.addFile(uploadedFile, destination.toString());
////                out.println("<h2> Image Name : " + imagename + " </h2>");
//            }
            out.println("</center>");
            out.println("</body>");
            out.println("</html>");
            
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
