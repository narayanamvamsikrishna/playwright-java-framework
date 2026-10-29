package utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.microsoft.playwright.Page;

public class ScreenshotUtil {

    public static String capture(
            Page page,
            String className,
            String methodName,
            int lineNumber,
            String eventType)
            throws Exception {

        // Build path dynamically based on execution folder
        String dirPath = System.getProperty("user.dir")
                + "/reports/"
                + ExtentManager.executionFolder
                + "/screenshots/";

        // Automatically create directories if they don't exist
        Files.createDirectories(Paths.get(dirPath));

        String timestamp =
                LocalDateTime.now()
                        .format(
                                DateTimeFormatter.ofPattern(
                                        "yyyyMMdd_HHmmss"));

        String fileName =
                className
                + "_"
                + methodName
                + "_"
                + eventType
                + "_L"
                + lineNumber
                + "_"
                + timestamp
                + ".png";

        String path = dirPath + fileName;

        page.screenshot(
                new Page.ScreenshotOptions()
                        .setPath(Paths.get(path)));

        return path;
    }

    public static void capture(Page page, String stepName) {
        try {
            capture(page, "MANUAL", stepName, 0, "SCREENSHOT");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void captureAndAttach(Page page, String stepName) {
        try {
            String path = capture(page, "MANUAL", stepName, 0, "STEP");

            // Automatically logs the message and attaches the screenshot inline
            ReportManager.info(stepName, path);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}