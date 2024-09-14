package com.example.lab12;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Vyrprinttable extends TagSupport{
    private static final datebase db = new datebase();
    private String table;
    public void setNameTable(String nameTable) {
        this.table = nameTable;
    }
    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.write("<h1>" + table + "</h1>");
            out.write("<table>");
            out.write("<tr>");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        db.getConnection();
        ResultSet rs = db.ExecuteQuery(
        "select count(*) as count from universities where rating = 3\n");
        int columns_count = 0;
        while (true) {
            try {
                if (!rs.next()) break;
                columns_count = rs.getInt("count");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (columns_count != 0) {
            ResultSet rows = db.ExecuteQuery("select * from " + table);
            try {
                while (rows.next()) {
                    for (int j = 0; j < columns_count; j++) {
                        pageContext.getOut().write("<td>");
                        pageContext.getOut().write(rows.getString(j + 1));
                        pageContext.getOut().write("</td>");
                    }
                    pageContext.getOut().write("</tr><tr>");
                }
            } catch (IOException e) {
                throw new JspException(e.getMessage());
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return EVAL_BODY_AGAIN;
        }
        else
            return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.write("</tr>");
            out.write("</table>");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return EVAL_PAGE;
    }
}
