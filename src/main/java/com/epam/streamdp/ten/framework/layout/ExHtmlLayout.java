package com.epam.streamdp.ten.framework.layout;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.*;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;
import org.apache.logging.log4j.core.layout.HtmlLayout;
import org.apache.logging.log4j.core.util.Transform;
import org.apache.logging.log4j.util.Strings;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

@Plugin(
        name = "exHtmlLayout",
        category = "Core",
        elementType = "layout",
        printObject = true
)

public final class ExHtmlLayout extends AbstractStringLayout {
    public static final String DEFAULT_FONT_FAMILY = "arial,sans-serif";
    private static final String TRACE_PREFIX = "<br />&nbsp;&nbsp;&nbsp;&nbsp;";
    private static final String REGEXP;
    private static final String DEFAULT_TITLE = "Log4j Log Messages";

    static {
        REGEXP = Strings.LINE_SEPARATOR.equals("\n") ? "\n" : Strings.LINE_SEPARATOR + "|\n";
    }

    private final long jvmStartTime;
    private final boolean locationInfo;
    private final String title;
    private final String contentType;
    private final String font;
    private final String fontSize;
    private final String headerSize;

    private ExHtmlLayout(boolean locationInfo, String title, String contentType, Charset charset, String font, String fontSize, String headerSize) {
        super(charset);
        this.jvmStartTime = ManagementFactory.getRuntimeMXBean().getStartTime();
        this.locationInfo = locationInfo;
        this.title = title;
        this.contentType = this.addCharsetToContentType(contentType);
        this.font = font;
        this.fontSize = fontSize;
        this.headerSize = headerSize;
    }

    @PluginFactory
    public static ExHtmlLayout createLayout(@PluginAttribute("locationInfo") boolean locationInfo, @PluginAttribute(value = "title", defaultString = "Log4j Log Messages") String title, @PluginAttribute("contentType") String contentType, @PluginAttribute(value = "charset", defaultString = "UTF-8") Charset charset, @PluginAttribute("fontSize") String fontSize, @PluginAttribute(value = "fontName", defaultString = "arial,sans-serif") String font) {
        ExHtmlLayout.FontSize fs = FontSize.getFontSize(fontSize);
        fontSize = fs.getFontSize();
        String headerSize = fs.larger().getFontSize();
        if (contentType == null) {
            contentType = "text/html; charset=" + charset;
        }

        return new ExHtmlLayout(locationInfo, title, contentType, charset, font, fontSize, headerSize);
    }

    public static ExHtmlLayout createDefaultLayout() {
        return newBuilder().build();
    }

    @PluginBuilderFactory
    public static ExHtmlLayout.Builder newBuilder() {
        return new ExHtmlLayout.Builder();
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isLocationInfo() {
        return this.locationInfo;
    }

    @Override
    public boolean requiresLocation() {
        return this.locationInfo;
    }

    private String addCharsetToContentType(String contentType) {
        if (contentType == null) {
            return "text/html; charset=" + this.getCharset();
        } else {
            return contentType.contains("charset") ? contentType : contentType + "; charset=" + this.getCharset();
        }
    }

    public String toSerializable(LogEvent event) {
        StringBuilder sbuf = getStringBuilder();
        sbuf.append(Strings.LINE_SEPARATOR).append("<tr>").append(Strings.LINE_SEPARATOR);
        sbuf.append("<td>");
        sbuf.append(event.getTimeMillis() - this.jvmStartTime);
        sbuf.append("</td>").append(Strings.LINE_SEPARATOR);
        String escapedThread = Transform.escapeHtmlTags(event.getThreadName());
        sbuf.append("<td title=\"").append(escapedThread).append(" thread\">");
        sbuf.append(escapedThread);
        sbuf.append("</td>").append(Strings.LINE_SEPARATOR);
        sbuf.append("<td title=\"Level\">");
        if (event.getLevel().equals(Level.DEBUG)) {
            sbuf.append("<font color=\"#339933\">");
            sbuf.append(Transform.escapeHtmlTags(String.valueOf(event.getLevel())));
            sbuf.append("</font>");
        } else if (event.getLevel().isMoreSpecificThan(Level.WARN)) {
            sbuf.append("<font color=\"#993300\"><strong>");
            sbuf.append(Transform.escapeHtmlTags(String.valueOf(event.getLevel())));
            sbuf.append("</strong></font>");
        } else {
            sbuf.append(Transform.escapeHtmlTags(String.valueOf(event.getLevel())));
        }

        sbuf.append("</td>").append(Strings.LINE_SEPARATOR);
        String escapedLogger = Transform.escapeHtmlTags(event.getLoggerName());
        if (Strings.isEmpty(escapedLogger)) {
            escapedLogger = "root";
        }

        sbuf.append("<td title=\"").append(escapedLogger).append(" logger\">");
        sbuf.append(escapedLogger);
        sbuf.append("</td>").append(Strings.LINE_SEPARATOR);
        if (this.locationInfo) {
            StackTraceElement element = event.getSource();
            sbuf.append("<td>");
            sbuf.append(Transform.escapeHtmlTags(element.getFileName()));
            sbuf.append(':');
            sbuf.append(element.getLineNumber());
            sbuf.append("</td>").append(Strings.LINE_SEPARATOR);
        }

        sbuf.append("<td title=\"Message\">");
        //Let's leave the HTML characters alone
        sbuf.append(event.getMessage().getFormattedMessage().replaceAll(REGEXP, "<br />"));
        //
        sbuf.append("</td>").append(Strings.LINE_SEPARATOR);
        sbuf.append("</tr>").append(Strings.LINE_SEPARATOR);
        if (event.getContextStack() != null && !event.getContextStack().isEmpty()) {
            sbuf.append("<tr><td bgcolor=\"#EEEEEE\" style=\"font-size : ").append(this.fontSize);
            sbuf.append(";\" colspan=\"6\" ");
            sbuf.append("title=\"Nested Diagnostic Context\">");
            sbuf.append("NDC: ").append(Transform.escapeHtmlTags(event.getContextStack().toString()));
            sbuf.append("</td></tr>").append(Strings.LINE_SEPARATOR);
        }

        if (event.getContextData() != null && !event.getContextData().isEmpty()) {
            sbuf.append("<tr><td bgcolor=\"#EEEEEE\" style=\"font-size : ").append(this.fontSize);
            sbuf.append(";\" colspan=\"6\" ");
            sbuf.append("title=\"Mapped Diagnostic Context\">");
            sbuf.append("MDC: ").append(Transform.escapeHtmlTags(event.getContextData().toMap().toString()));
            sbuf.append("</td></tr>").append(Strings.LINE_SEPARATOR);
        }

        Throwable throwable = event.getThrown();
        if (throwable != null) {
            sbuf.append("<tr><td bgcolor=\"#993300\" style=\"color:White; font-size : ").append(this.fontSize);
            sbuf.append(";\" colspan=\"6\">");
            this.appendThrowableAsHtml(throwable, sbuf);
            sbuf.append("</td></tr>").append(Strings.LINE_SEPARATOR);
        }

        return sbuf.toString();
    }

    @Override
    public String getContentType() {
        return this.contentType;
    }

    private void appendThrowableAsHtml(final Throwable throwable, final StringBuilder sbuf) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        try {
            throwable.printStackTrace(pw);
        } catch (final RuntimeException ex) {
            // Ignore the exception.
        }
        pw.flush();
        final LineNumberReader reader = new LineNumberReader(new StringReader(sw.toString()));
        final ArrayList<String> lines = new ArrayList<>();
        try {
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } catch (final IOException ex) {
            if (ex instanceof InterruptedIOException) {
                Thread.currentThread().interrupt();
            }
            lines.add(ex.toString());
        }
        boolean first = true;
        for (final String line : lines) {
            if (!first) {
                sbuf.append(TRACE_PREFIX);
            } else {
                first = false;
            }
            sbuf.append(Transform.escapeHtmlTags(line));
            sbuf.append(Strings.LINE_SEPARATOR);
        }
    }

    private StringBuilder appendLs(StringBuilder sbuilder, String s) {
        sbuilder.append(s).append(Strings.LINE_SEPARATOR);
        return sbuilder;
    }

    private StringBuilder append(StringBuilder sbuilder, String s) {
        sbuilder.append(s);
        return sbuilder;
    }

    @Override
    public byte[] getHeader() {
        StringBuilder sbuf = new StringBuilder();
        this.append(sbuf, "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" ");
        this.appendLs(sbuf, "\"http://www.w3.org/TR/html4/loose.dtd\">");
        this.appendLs(sbuf, "<html>");
        this.appendLs(sbuf, "<head>");
        this.append(sbuf, "<meta charset=\"");
        this.append(sbuf, this.getCharset().toString());
        this.appendLs(sbuf, "\"/>");
        this.append(sbuf, "<title>").append(this.title);
        this.appendLs(sbuf, "</title>");
        this.appendLs(sbuf, "<style type=\"text/css\">");
        this.appendLs(sbuf, "<!--");
        this.append(sbuf, "body, table {font-family:").append(this.font).append("; font-size: ");
        this.appendLs(sbuf, this.headerSize).append(";}");
        this.appendLs(sbuf, "th {background: #336699; color: #FFFFFF; text-align: left;}");
        this.appendLs(sbuf, "-->");
        this.appendLs(sbuf, "</style>");
        this.appendLs(sbuf, "</head>");
        this.appendLs(sbuf, "<body bgcolor=\"#FFFFFF\" topmargin=\"6\" leftmargin=\"6\">");
        this.appendLs(sbuf, "<hr size=\"1\" noshade=\"noshade\">");
        this.appendLs(sbuf, "Log session start time " + new Date() + "<br>");
        this.appendLs(sbuf, "<br>");
        this.appendLs(sbuf, "<table cellspacing=\"0\" cellpadding=\"4\" border=\"1\" bordercolor=\"#224466\" width=\"100%\">");
        this.appendLs(sbuf, "<tr>");
        this.appendLs(sbuf, "<th>Time</th>");
        this.appendLs(sbuf, "<th>Thread</th>");
        this.appendLs(sbuf, "<th>Level</th>");
        this.appendLs(sbuf, "<th>Logger</th>");
        if (this.locationInfo) {
            this.appendLs(sbuf, "<th>File:Line</th>");
        }

        this.appendLs(sbuf, "<th>Message</th>");
        this.appendLs(sbuf, "</tr>");
        return sbuf.toString().getBytes(this.getCharset());
    }

    @Override
    public byte[] getFooter() {
        StringBuilder sbuf = new StringBuilder();
        this.appendLs(sbuf, "</table>");
        this.appendLs(sbuf, "<br>");
        this.appendLs(sbuf, "</body></html>");
        return this.getBytes(sbuf.toString());
    }

    /**
     * Possible font sizes
     */
    public enum FontSize {
        SMALLER("smaller"), XXSMALL("xx-small"), XSMALL("x-small"), SMALL("small"), MEDIUM("medium"), LARGE("large"),
        XLARGE("x-large"), XXLARGE("xx-large"), LARGER("larger");

        private final String size;

        FontSize(final String size) {
            this.size = size;
        }

        public static FontSize getFontSize(final String size) {
            for (final FontSize fontSize : values()) {
                if (fontSize.size.equals(size)) {
                    return fontSize;
                }
            }
            return SMALL;
        }

        public String getFontSize() {
            return size;
        }

        public FontSize larger() {
            return this.ordinal() < XXLARGE.ordinal() ? FontSize.values()[this.ordinal() + 1] : this;
        }
    }

    public static class Builder implements org.apache.logging.log4j.core.util.Builder<ExHtmlLayout> {
        @PluginBuilderAttribute
        private boolean locationInfo;
        @PluginBuilderAttribute
        private String title;
        @PluginBuilderAttribute
        private String contentType;
        @PluginBuilderAttribute
        private Charset charset;
        @PluginBuilderAttribute
        private HtmlLayout.FontSize fontSize;
        @PluginBuilderAttribute
        private String fontName;

        private Builder() {
            this.locationInfo = false;
            this.title = DEFAULT_TITLE;
            this.contentType = null;
            this.charset = StandardCharsets.UTF_8;
            this.fontSize = HtmlLayout.FontSize.SMALL;
            this.fontName = DEFAULT_FONT_FAMILY;
        }

        public ExHtmlLayout.Builder withLocationInfo(boolean locationInfo) {
            this.locationInfo = locationInfo;
            return this;
        }

        public ExHtmlLayout.Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public ExHtmlLayout.Builder withContentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public ExHtmlLayout.Builder withCharset(Charset charset) {
            this.charset = charset;
            return this;
        }

        public ExHtmlLayout.Builder withFontSize(HtmlLayout.FontSize fontSize) {
            this.fontSize = fontSize;
            return this;
        }

        public ExHtmlLayout.Builder withFontName(String fontName) {
            this.fontName = fontName;
            return this;
        }

        public ExHtmlLayout build() {
            if (this.contentType == null) {
                this.contentType = "text/html; charset=" + this.charset;
            }

            return new ExHtmlLayout(this.locationInfo, this.title, this.contentType, this.charset, this.fontName, this.fontSize.getFontSize(), this.fontSize.larger().getFontSize());
        }
    }
}