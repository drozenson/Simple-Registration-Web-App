<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Register for Architech Confirmed</title>
    </head>
        <style>
body{width:414px;font-family:Arial;font-size:14px;}

label{color:#6c6c6c;}

input{line-height:31px;}

input,textarea{width:288px;-webkit-border-radius:3px;-moz-border-radius:3px;border-radius:3px;background-color:rgba(255,255,255,.6);border:solid 1px #b6c7cb;}

#contact_form{height:317px;background-color:#e1e9eb;border:solid 1px #e5e5e5;padding:10px 20px 50px 20px;  -webkit-border-radius:5px;-moz-border-radius:5px;border-radius:5px;}

#submit_button{width:109px;height:34px;-webkit-border-radius:3px;-moz-border-radius:3px;border-radius:3px;background-color:#86c5fa;-webkit-box-shadow:inset 0 2px rgba(255,255,255,.29);-moz-box-shadow:inset 0 2px rgba(255,255,255,.29);box-shadow:inset 0 2px rgba(255,255,255,.29);border:solid 1px #77a4cb;font-weight:bold;color:#fff;margin-left:7px;}

label.required:after{content:'*';color:red;}

.row{margin:5px;}
</style>
    <body>
       
        <form name="registration" action="RegServlet" method="post" id="contact_form">
            <input type="hidden" id="pagename" name="pagename" value="confirm"/>
            <table cellpadding="5" cellspacing="5">
                <tr>
            </tr>
                              <%
    for (int i = 0 ; i < 10; i++){
   // String message  = request.getParameter( "message" );
        String message  = request.getParameter( "message" + i);
        
    if ( message != null )
    {
        %> <tr><td colspan="5" style="red"><font size="3" color="red" type="text">
<%= message.toString() %>
<%
%> </font></td></tr><%   }
    }
%> 
              
                 <tr>
                    <td><button type="" onclick="javascript:window.close();document.getElementById('contact_form').submit()">Close</button></td>
                    <td><button onclick="document.getElementById('pagename').value = 'new';document.getElementById('contact_form').submit()">New</button></td>
                </tr>       
            </table>
        </form>
    </body>
</html>