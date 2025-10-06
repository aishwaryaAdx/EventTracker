package com.EventTracker.EventTracker.Controller;

import java.io.IOException;
import java.net.InetAddress;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EventTracker.EventTracker.Service.userService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class controller {
    public controller(userService service) {
        this.service = service;
    }

    @Autowired
    private final userService service;


//End-Point
    @GetMapping("/event")
    public void eventTrack(HttpServletRequest request, HttpServletResponse response) throws IOException{

        String sessionId=null;

    // Cheaks for the existing cookie...
        if(request.getCookies() != null){
            for(Cookie cookie : request.getCookies()){
                if("sessionId".equals(cookie.getName())){
                    sessionId=cookie.getValue();
                }
            }
        }

    // Creation of new cookie if not avilable...
        if(sessionId == null){
            sessionId=UUID.randomUUID().toString();
            Cookie cookie = new Cookie("sessionId",sessionId);
            cookie.setPath("/");
            cookie.setMaxAge(30*24*60*60);
            response.addCookie(cookie);
        }

    // request to get the refrer for this...
        String referer = request.getHeader("referer");

    // to get the page and the URL of referer...demoProjectApplication
        String page = request.getRequestURI();
    
    // To get the IP Address and Device info...
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty()) {
            ipAddress = request.getRemoteAddr();
        }
        if (ipAddress == null || ipAddress.isEmpty() || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
            ipAddress = InetAddress.getLocalHost().getHostAddress();
        }

        String deviceID = request.getHeader("User-Agent");


    // UTM params (if ad campaign passes them)
        String utmSource = request.getParameter("utm_source");
        String utmMedium = request.getParameter("utm_medium");
        String utmCampaign = request.getParameter("utm_campaign");

    // Call to service layer to save the event occured...
        service.dataLog(sessionId,page,referer,ipAddress,deviceID,utmSource,utmMedium,utmCampaign);



    // Return 1x1 transparent pixel...
        response.setContentType("image/gif");
        byte[] pixel = new byte[]{
                71,73,70,56,57,97,1,0,1,0,-128,0,0,
                -64,-64,-64,0,0,0,33,-7,4,1,0,0,0,0,44,
                0,0,0,0,1,0,1,0,0,2,2,68,1,0,59
        };
        response.getOutputStream().write(pixel);

    }
}
