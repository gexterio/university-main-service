package ua.com.foxminded.university.util.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;


@Component
public class RequestAndResponseLoggingFilter extends OncePerRequestFilter {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final List<String> RELEVANT_HEADERS = Arrays.asList(
            "user-agent",
            "accept",
            "accept-Charset",
            "accept-Datetime",
            "accept-Language",
            "content-Length",
            "content-Type",
            "date"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        doFilterWrapped(wrapRequest(request), wrapResponse(response), filterChain);
    }

    protected void doFilterWrapped(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response, FilterChain filterChain) throws ServletException, IOException {
        StringBuilder msg = new StringBuilder();
        try {
            doBeforeRequest(request, msg);
            filterChain.doFilter(request, response);
        } finally {
            doAfterRequest(request, response, msg);
            if (log.isInfoEnabled()) {
                log.info(msg.toString());
            }
            response.copyBodyToResponse();
        }
    }

    private void doBeforeRequest(ContentCachingRequestWrapper request, StringBuilder msg) {
        msg.append("\n ——— REQUEST ———\n");
        logRequestHeader(request, msg);
    }

    private static void logRequestHeader(ContentCachingRequestWrapper request, StringBuilder msg) {
        String queryString = request.getQueryString();
        if (queryString == null) {
            msg.append(String.format("%s %s", request.getMethod(), request.getRequestURL())).append("\n");
        } else {
            msg.append(String.format("%s %s?%s", request.getMethod(), request.getRequestURL(), queryString)).append("\n");
        }
        msg.append(request.getContentType()).append("\n");
        Collections.list(request.getHeaderNames())
                .forEach(headerName ->
                        Collections.list(request.getHeaders(headerName))
                                .forEach(headerValue -> {
                                    if (isRelevantHeader(headerName)) {
                                        msg.append(String.format("%s: %s", headerName, headerValue)).append("\n");
                                    }
                                }));
    }

    protected void doAfterRequest(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response, StringBuilder msg) {
        logRequestBody(request, msg);
        msg.append("\n——— RESPONSE ———\n");
        logResponseHeaders(response, msg);
        logResponseBody(response, msg);
    }

    private static void logResponseHeaders(ContentCachingResponseWrapper response, StringBuilder msg) {
        int status = response.getStatus();
        msg.append(String.format("%s: %s", status, HttpStatus.valueOf(status).getReasonPhrase())).append("\n");
        msg.append(response.getContentType()).append("\n");
        response.getHeaderNames()
                .forEach(headerName ->
                        response.getHeaders(headerName)
                                .forEach(headerValue -> {
                                            if (isRelevantHeader(headerName)) {
                                                msg.append(String.format("%s: %s", headerName, headerValue)).append("\n");
                                            }
                                        }
                                ));
    }

    private static void logRequestBody(ContentCachingRequestWrapper request, StringBuilder msg) {
        byte[] content = request.getContentAsByteArray();
        if (content.length > 0) {
            logContent(content, request.getCharacterEncoding(), msg);
        } else {
            msg.append("no body");
        }
        msg.append("\n");
    }

    protected static void logResponseBody(ContentCachingResponseWrapper response, StringBuilder msg) {
        byte[] content = response.getContentAsByteArray();
        if (content.length > 0) {
            logContent(content, response.getCharacterEncoding(), msg);
        } else {
            msg.append("no body");
        }
        msg.append("\n");
    }


    private static void logContent(byte[] content, String characterEncoding, StringBuilder msg) {
        try {
            String contentString = new String(content, characterEncoding);
            Stream.of(contentString.split("\r\n|\r|\n")).forEach(line -> msg.append(line).append("\n"));
        } catch (UnsupportedEncodingException e) {
            msg.append(String.format("[bytes content] length: %d", content.length)).append("\n");
        }
        msg.append(String.format("[bytes content] length: %d", content.length));

    }

    private static boolean isRelevantHeader(String headerName) {
        return RELEVANT_HEADERS.contains(headerName.toLowerCase());
    }


    private static ContentCachingRequestWrapper wrapRequest(HttpServletRequest request) {
        if (request instanceof ContentCachingRequestWrapper) {
            return (ContentCachingRequestWrapper) request;
        } else {
            return new ContentCachingRequestWrapper(request);
        }
    }

    private static ContentCachingResponseWrapper wrapResponse(HttpServletResponse response) {
        if (response instanceof ContentCachingResponseWrapper) {
            return (ContentCachingResponseWrapper) response;
        } else {
            return new ContentCachingResponseWrapper(response);
        }
    }


}