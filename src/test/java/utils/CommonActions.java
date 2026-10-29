package utils;

import java.nio.file.Paths;
import org.json.JSONObject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CommonActions {

    private Page page;

    public CommonActions(Page page) {
        this.page = page;
    }

    /**
     * Helper method to add a small delay (in milliseconds) for visual pacing.
     */
    private void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void navigatetopage(String URL, String stepName) {
        ReportManager.info("Navigating to URL: " + URL + " | Step: " + stepName);
        page.navigate(URL);
        pause(1000); // Wait 1 second after navigation to let page load fully
        ScreenshotUtil.captureAndAttach(page, stepName);
    }

    public void click(String locator, String stepName) {
        ReportManager.info("Performing click on element: [" + locator + "] | Step: " + stepName);
        page.locator(locator).click();
        pause(500); // Wait half a second after click
        ScreenshotUtil.captureAndAttach(page, stepName);
    }

    public void enterText(String locator, String value, String stepName) {
        ReportManager.info("Entering text '" + value + "' into element: [" + locator + "] | Step: " + stepName);
        page.locator(locator).fill(value);
        pause(500); // Wait half a second after typing
        ScreenshotUtil.captureAndAttach(page, stepName);
    }

    public void selectDropdown(String locator, String value, String stepName) {
        ReportManager
                .info("Selecting dropdown option '" + value + "' from element: [" + locator + "] | Step: " + stepName);
        page.locator(locator).selectOption(value);
        pause(500);
        ScreenshotUtil.captureAndAttach(page, stepName);
    }

    public void hover(String locator, String stepName) {
        ReportManager.info("Hovering over element: [" + locator + "] | Step: " + stepName);
        page.locator(locator).hover();
        pause(500);
        ScreenshotUtil.captureAndAttach(page, stepName);
    }

    public void doubleClick(String locator, String stepName) {
        ReportManager.info("Double clicking on element: [" + locator + "] | Step: " + stepName);
        page.locator(locator).dblclick();
        pause(500);
        ScreenshotUtil.captureAndAttach(page, stepName);
    }

    public void rightClick(String locator, String stepName) {
        ReportManager.info("Right clicking on element: [" + locator + "] | Step: " + stepName);
        page.locator(locator).click(
                new Locator.ClickOptions()
                        .setButton(com.microsoft.playwright.options.MouseButton.RIGHT));
        pause(500);
        ScreenshotUtil.captureAndAttach(page, stepName);
    }

    public void check(String locator, String stepName) {
        ReportManager.info("Checking checkbox/radio button: [" + locator + "] | Step: " + stepName);
        page.locator(locator).check();
        pause(500);
        ScreenshotUtil.captureAndAttach(page, stepName);
    }

    public void uncheck(String locator, String stepName) {
        ReportManager.info("Unchecking checkbox: [" + locator + "] | Step: " + stepName);
        page.locator(locator).uncheck();
        pause(500);
        ScreenshotUtil.captureAndAttach(page, stepName);
    }

    public void uploadFile(String locator, String filePath, String stepName) {
        ReportManager.info("Uploading file '" + filePath + "' using element: [" + locator + "] | Step: " + stepName);
        page.locator(locator).setInputFiles(Paths.get(filePath));
        pause(1000);
        ScreenshotUtil.captureAndAttach(page, stepName);
    }

    public String getText(String locator) {
        String text = page.locator(locator).textContent();
        ReportManager.info("Retrieved text '" + text + "' from element: [" + locator + "]");
        return text;
    }

    public boolean isVisible(String locator) {
        boolean visible = page.locator(locator).isVisible();
        ReportManager.info("Checked visibility for element: [" + locator + "] -> Is Visible: " + visible);
        return visible;
    }

    public void waitForVisible(String locator) {
        ReportManager.info("Waiting for element to be visible: [" + locator + "]");
        page.locator(locator).waitFor();
    }

    public void dragAndDrop(String sourceLocator, String targetLocator, String stepName) {
        ReportManager.info("Dragging from element: [" + sourceLocator + "] to target: [" + targetLocator + "] | Step: "
                + stepName);
        page.dragAndDrop(sourceLocator, targetLocator);
        pause(500);
        ScreenshotUtil.captureAndAttach(page, stepName);
    }

}