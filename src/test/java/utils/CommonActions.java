package utils;

import java.nio.file.Paths;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.MouseButton;
import com.microsoft.playwright.options.WaitForSelectorState;
import utils.ConfigReader;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Page;

import base.BaseTest;
import utils.CommonActions;
import java.nio.file.Paths;

public class CommonActions {

    private Page page;

    private ConfigReader cong = new ConfigReader();

    private final int ELEMENT_TIMEOUT = Integer.parseInt(cong.get("elementtimeout"));

    private final int ACTION_DELAY = Integer.parseInt(cong.get("actiondelay"));

    public CommonActions(Page page) {
        this.page = page;
    }

    /**
     * Common delay after each action.
     */
    private void pause() {

        if (ACTION_DELAY > 0) {

            page.waitForTimeout(ACTION_DELAY);
        }
    }

    /**
     * Wait for page to stabilize.
     */
    private void waitForPageReady() {

        try {

            page.waitForLoadState(
                    LoadState.DOMCONTENTLOADED);

        } catch (Exception e) {

            ReportManager.info(
                    "Page ready wait warning : "
                            + e.getMessage());
        }
    }

    /**
     * Wait until element is visible.
     */
    private Locator getVisibleElement(
            String locator,
            String action) {

        try {

            Locator element = page.locator(locator);

            element.waitFor(
                    new Locator.WaitForOptions()
                            .setState(
                                    WaitForSelectorState.VISIBLE)
                            .setTimeout(
                                    ELEMENT_TIMEOUT));

            return element;

        } catch (Exception e) {

            String errorMessage = "FAILED : Element not visible within "
                    + ELEMENT_TIMEOUT
                    + " ms | Action : "
                    + action
                    + " | Locator : "
                    + locator;

            ReportManager.fail(errorMessage);

            ScreenshotUtil.captureAndAttach(
                    page,
                    "ELEMENT_NOT_FOUND");

            throw new RuntimeException(
                    errorMessage,
                    e);
        }
    }

    public void navigatetopage(
            String url,
            String stepName) {

        ReportManager.info(
                "Navigating to URL : "
                        + url);

        page.navigate(url);

        waitForPageReady();

        pause();

        ScreenshotUtil.captureAndAttach(
                page,
                stepName);
    }

    public void click(
            String locator,
            String stepName) {

        ReportManager.info(
                "Clicking element : "
                        + locator);

        Locator element = getVisibleElement(
                locator,
                "CLICK");

        element.click();

        waitForPageReady();

        pause();

        ScreenshotUtil.captureAndAttach(
                page,
                stepName);
    }

    public void enterText(
            String locator,
            String value,
            String stepName) {

        ReportManager.info(
                "Entering text : "
                        + value
                        + " | Locator : "
                        + locator);

        Locator element = getVisibleElement(
                locator,
                "ENTER_TEXT");

        element.fill(value);

        pause();

        ScreenshotUtil.captureAndAttach(
                page,
                stepName);
    }

    public void selectDropdown(
            String locator,
            String value,
            String stepName) {

        ReportManager.info(
                "Selecting option : "
                        + value);

        Locator element = getVisibleElement(
                locator,
                "SELECT_DROPDOWN");

        element.selectOption(value);

        pause();

        ScreenshotUtil.captureAndAttach(
                page,
                stepName);
    }

    public void hover(
            String locator,
            String stepName) {

        ReportManager.info(
                "Hovering : "
                        + locator);

        Locator element = getVisibleElement(
                locator,
                "HOVER");

        element.hover();

        pause();

        ScreenshotUtil.captureAndAttach(
                page,
                stepName);
    }

    public void doubleClick(
            String locator,
            String stepName) {

        ReportManager.info(
                "Double Click : "
                        + locator);

        Locator element = getVisibleElement(
                locator,
                "DOUBLE_CLICK");

        element.dblclick();

        pause();

        ScreenshotUtil.captureAndAttach(
                page,
                stepName);
    }

    public void rightClick(
            String locator,
            String stepName) {

        ReportManager.info(
                "Right Click : "
                        + locator);

        Locator element = getVisibleElement(
                locator,
                "RIGHT_CLICK");

        element.click(
                new Locator.ClickOptions()
                        .setButton(
                                MouseButton.RIGHT));

        pause();

        ScreenshotUtil.captureAndAttach(
                page,
                stepName);
    }

    public void check(
            String locator,
            String stepName) {

        ReportManager.info(
                "Checking : "
                        + locator);

        Locator element = getVisibleElement(
                locator,
                "CHECK");

        element.check();

        pause();

        ScreenshotUtil.captureAndAttach(
                page,
                stepName);
    }

    public void uncheck(
            String locator,
            String stepName) {

        ReportManager.info(
                "Unchecking : "
                        + locator);

        Locator element = getVisibleElement(
                locator,
                "UNCHECK");

        element.uncheck();

        pause();

        ScreenshotUtil.captureAndAttach(
                page,
                stepName);
    }

    public void selectRadio(
            String locator,
            String stepName) {

        ReportManager.info(
                "Selecting radio button : "
                        + locator);

        Locator element = getVisibleElement(
                locator,
                "SELECT_RADIO");

        element.check();

        if (!element.isChecked()) {

            String message = "Radio button selection failed : "
                    + locator;

            ReportManager.fail(message);

            throw new RuntimeException(message);
        }

        pause();

        ScreenshotUtil.captureAndAttach(
                page,
                stepName);
    }

    public void uploadFile(
            String locator,
            String filePath,
            String stepName) {

        ReportManager.info(
                "Uploading file : "
                        + filePath);

        Locator element = getVisibleElement(
                locator,
                "UPLOAD_FILE");

        element.setInputFiles(
                Paths.get(filePath));

        pause();

        ScreenshotUtil.captureAndAttach(
                page,
                stepName);
    }

    public String getText(
            String locator) {

        Locator element = getVisibleElement(
                locator,
                "GET_TEXT");

        String text = element.textContent();

        ReportManager.info(
                "Retrieved text : "
                        + text);

        return text;
    }

    public boolean isVisible(
            String locator) {

        try {

            Locator element = page.locator(locator);

            boolean visible = element.isVisible();

            ReportManager.info(
                    "Visibility Check : "
                            + visible);

            return visible;

        } catch (Exception e) {

            ReportManager.info(
                    "Visibility Check Failed : "
                            + locator);

            return false;
        }
    }

    public void waitForVisible(
            String locator) {

        ReportManager.info(
                "Waiting for visibility : "
                        + locator);

        getVisibleElement(
                locator,
                "WAIT_FOR_VISIBLE");
    }

    public void dragAndDrop(
            String sourceLocator,
            String targetLocator,
            String stepName) {

        ReportManager.info(
                "Drag and Drop : "
                        + sourceLocator
                        + " -> "
                        + targetLocator);

        getVisibleElement(
                sourceLocator,
                "DRAG_SOURCE");

        getVisibleElement(
                targetLocator,
                "DROP_TARGET");

        page.dragAndDrop(
                sourceLocator,
                targetLocator);

        pause();

        ScreenshotUtil.captureAndAttach(
                page,
                stepName);
    }
    public void mouseOver(
        String locator,
        String stepName) {

    ReportManager.info(
            "Mouse Over on element : "
                    + locator);

    Locator element =
            getVisibleElement(
                    locator,
                    "MOUSE_OVER");

    element.hover();

    pause();

    ScreenshotUtil.captureAndAttach(
            page,
            stepName);
}
public void takeScreenshot(String stepName) {

    ScreenshotUtil.captureAndAttach(
            page,
            stepName);
}
public void checkFirst(
        String locator,
        String stepName) {

    Locator element =
            page.locator(locator).first();

    element.check();

    ScreenshotUtil.captureAndAttach(
            page,
            stepName);
}
}