package az.tezapp.seller.server.manager;

import org.apache.log4j.Logger;

public class LoggerManager {

    private static final Logger infologger = Logger.getLogger(LoggerManager.class);
    private static final Logger errorlogger = Logger.getLogger("LoggerManager_ErrorLogger");

    public static void info(Object... args) {
        StringBuilder sb = new StringBuilder(args[0].toString());
        for (int i = 1; i < args.length; i++) {
            sb.append("\n").append(args[i]);
        }
        infologger.info(sb);
    }

    public static void warn(String message, Throwable e) {
        infologger.warn(message, e);
    }

    public static void warn(String message) {
        infologger.warn(message);
    }

    public static void error(Throwable e) {
        infologger.error(e);
        errorlogger.error(e.getMessage(), e);
    }

    public static void error(String message, Throwable e) {
        infologger.error(message);
        errorlogger.error(message, e);
    }

}
