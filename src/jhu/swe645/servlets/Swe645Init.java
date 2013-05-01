package jhu.swe645.servlets;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

import jhu.swe645.utils.ApplicationUtil;

/**
 * Servlet implementation class Swe645Init
 */
public class Swe645Init extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(Swe645Init.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public void init(ServletConfig config){
    	logger.info("Initializing SWE 645 Enterprise Application");
    	ResourceBundle rb = ResourceBundle
				.getBundle("jhu.swe645.servlets.appresources");
		Enumeration<String> keys = rb.getKeys();
		Map<String, String> applicationProperties = new HashMap<String,String>();
        while (keys.hasMoreElements()) {
        	String key = keys.nextElement();
        	String value = rb.getString(key);
        	logger.info("Setting application resource property -- " + key + " : " + value);
        	applicationProperties.put(key, value);
        }
        ApplicationUtil.init(applicationProperties);
    }
 }
