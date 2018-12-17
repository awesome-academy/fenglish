package vn.framgia.taglib;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PaginationTaglib extends SimpleTagSupport {
	private String uri;
	private int offset;
	private int count;
	private int steps = 10;
	private String previous = "Previous";
	private String next = "Next";

	private Writer getWriter() {
		JspWriter out = getJspContext().getOut();

		return out;
	}

	@Override
	public void doTag() throws JspException, IOException {
		Writer out = getWriter();
		
		if (offset < 1) {
			offset = 1;
		}
		
		try {
			out.write("<nav>");
			out.write("<ul class=\"pagination\">");

			if (offset * 10 <= steps)
				out.write(constructLink(0, previous, "disabled", true));
			else
				out.write(constructLink(offset - 1, previous, null, false));

			for (int itr = 0; itr < count; itr += steps) {
				if (offset == itr / 10 + 1)
					out.write(constructLink(itr / 10 + 1, String.valueOf(itr / 10 + 1), "active", true));
				else
					out.write(constructLink(itr / 10 + 1, String.valueOf(itr / 10 + 1), null, false));
			}

			if ((offset - 1) * 10 + steps >= count)
				out.write(constructLink(1, next, "disabled", true));
			else
				out.write(constructLink(offset + 1, next, null, false));

			out.write("</ul>");
			out.write("</nav>");
		} catch (java.io.IOException ex) {
			throw new JspException("Error in Paginator tag", ex);
		}
	}

	private String constructLink(int page, String text, String className, boolean disabled) {
		StringBuilder link = new StringBuilder("<li");
		if (className != null) {
			link.append(" class=\"");
			link.append(className);
			link.append("\"");
		}
		if (disabled)
			link.append(">").append("<a href=\"#\">" + text + "</a></li>");
		else
			link.append(">").append("<a href=\"" + uri + "/page=" + page + "/\">" + text + "</a></li>");
		return link.toString();
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}
}