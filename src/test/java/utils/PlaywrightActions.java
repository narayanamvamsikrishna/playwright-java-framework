package utils;

import com.microsoft.playwright.Page;

public class PlaywrightActions {

    private Page page;

    public PlaywrightActions(Page page) {

        this.page = page;
    }

    public void click(
            String locator,
            String stepName) throws Exception {

        page.locator(locator).click();

        ScreenshotUtil.capture(
                page,
                "ACTION",
                stepName,
                0,
                "AFTER_CLICK");
    }
}