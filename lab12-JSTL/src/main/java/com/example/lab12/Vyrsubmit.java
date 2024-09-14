package com.example.lab12;


import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Vyrsubmit extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        String buttons = "<input type ='submit' value='Вход'/></br></br>" +
                "<input type ='submit' value='Регистрация'/>";
        try {
            JspWriter out = pageContext.getOut();
            out.write(buttons);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
