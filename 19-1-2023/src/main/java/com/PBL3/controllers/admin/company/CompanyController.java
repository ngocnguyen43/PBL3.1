package com.PBL3.controllers.admin.company;

import com.PBL3.utils.Constants.Constants;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
@MultipartConfig
@WebServlet(urlPatterns = {Constants.URL_V1 + Constants.PRIVATE + "/company"})
public class CompanyController extends HttpServlet {

}
