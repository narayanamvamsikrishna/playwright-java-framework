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

    Files.createDirectories(
            Paths.get("screenshots"));

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

  String path =
        System.getProperty("user.dir")
        + "/reports/"
        + ExtentManager.executionFolder
        + "/screenshots/"
        + fileName;

    page.screenshot(
            new Page.ScreenshotOptions()
                    .setPath(Paths.get(path)));

    return path;
}
}