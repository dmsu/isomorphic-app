package com.turbo;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class Launcher {
    public static void main(String[] args) throws Exception {
    	Server server = new Server(8889);
    	WebAppContext context = new WebAppContext("src/main/webapp", "/");
    	
    	if (System.getProperty("os.name").toLowerCase().contains("windows")) {
    		// Fix for Windows, so Jetty doesn't lock files
            context.getInitParams().put("org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false");
        }
    	
    	server.setHandler(context);
    	server.start(); 
    }
}