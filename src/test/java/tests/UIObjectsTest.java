package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import base.BaseTest;
import utils.CommonActions;

@Listeners(listeners.TestListener.class)
public class UIObjectsTest extends BaseTest {

    @Test(dataProvider = "browsers", dataProviderClass = utils.DataProviders.class, testName = "Handle Multiple UI Objects")
    public void handleMultipleUIObjects(String browser) {

        CommonActions comm = new CommonActions(page);

        // ==========================================
        // CHECKBOX
        // ==========================================

        comm.navigatetopage(
                "https://the-internet.herokuapp.com/checkboxes",
                "Navigate To Checkboxes");

        // page.locator("input[type='checkbox']").first().check();
        comm.checkFirst("input[type='checkbox']", "Checkbox Selected");
        Assert.assertTrue( page.locator("input[type='checkbox']").first() .isChecked());

        comm.takeScreenshot("Checkbox Validation");

        // ==========================================
        // DROPDOWN
        // ==========================================

        comm.navigatetopage(
                "https://the-internet.herokuapp.com/dropdown",
                "Navigate To Dropdown");

        comm.selectDropdown(
                "#dropdown",
                "1",
                "Dropdown Option Selected");

        Assert.assertEquals(
                page.locator("#dropdown").inputValue(),
                "1");

        comm.takeScreenshot("Dropdown Validation");

        // ==========================================
        // ALERT
        // ==========================================

        comm.navigatetopage(
                "https://the-internet.herokuapp.com/javascript_alerts",
                "Navigate To Alerts");

        page.onceDialog(dialog -> {
            dialog.accept();
        });

        comm.click(
                "button[onclick='jsAlert()']",
                "Alert Handled");

        Assert.assertTrue(
                page.locator("#result")
                        .textContent()
                        .contains("successfully"));

        // ==========================================
        // FRAME
        // ==========================================

        comm.navigatetopage(
                "https://the-internet.herokuapp.com/iframe",
                "Navigate To Frame");

        page.frameLocator("#mce_0_ifr")
                .locator("body")
                .fill("Playwright Framework Demo");

        comm.takeScreenshot("Frame Text Entered");

        Assert.assertTrue(
                page.frameLocator("#mce_0_ifr")
                        .locator("body")
                        .textContent()
                        .contains("Playwright"));

        // ==========================================
        // MULTIPLE WINDOW
        // ==========================================

        comm.navigatetopage(
                "https://the-internet.herokuapp.com/windows",
                "Navigate To Multiple Windows");

        Page popup = page.waitForPopup(() -> {

            page.getByRole(
                    AriaRole.LINK,
                    new Page.GetByRoleOptions()
                            .setName("Click Here"))
                    .click();

        });

        comm.takeScreenshot("Popup Window Opened");

        Assert.assertEquals(
                popup.locator("h3")
                        .textContent()
                        .trim(),
                "New Window");

        popup.close();

        // ==========================================
        // FILE UPLOAD
        // ==========================================

        comm.navigatetopage(
                "https://the-internet.herokuapp.com/upload",
                "Navigate To Upload");

        comm.uploadFile(
                "#file-upload",
                "src/test/resources/testdata/sampleupload.txt",
                "Upload File");

        comm.click(
                "#file-submit",
                "Submit Uploaded File");

        Assert.assertTrue(
                page.locator("h3")
                        .textContent()
                        .contains("Uploaded"));

        // ==========================================
        // DRAG AND DROP
        // ==========================================

        comm.navigatetopage(
                "https://the-internet.herokuapp.com/drag_and_drop",
                "Navigate To Drag And Drop");

        comm.dragAndDrop(
                "#column-a",
                "#column-b",
                "Drag And Drop Executed");

        comm.takeScreenshot("Drag And Drop Validation");

        // ==========================================
        // FINAL
        // ==========================================

        System.out.println(
                "All UI Objects Handled Successfully");

        comm.takeScreenshot(
                "All UI Objects Completed");
    }
}