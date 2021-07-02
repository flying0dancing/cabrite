package com.csdental.meshviewer;

public interface ITool {
    String getLanguage();
    Boolean changeLanguage(String language);

    Boolean clickFront();
    Boolean clickBack();
    Boolean clickTop();
    Boolean clickBottom();
    Boolean clickRight();
    Boolean clickLeft();
    Boolean clickZoomFit();
    Boolean clickSnapshot();
    Boolean clickTrueColor();
    Boolean clickLight();
    Boolean clickReset();

    DisplayPage clickDisplay();
    OrientationAdjustment clickOrientationAdjustment();
    GalleryPage clickGallery();
    ImageInformation clickImageInformation();
    AboutDialog clickAbout();
    ExportPage clickExport();
    /**
     * check top bar is displayed, if one button of top bar provided is missing return false.
     * @param name value in {"ViewFront","ViewBack","ViewTop","ViewBottom","ViewRight","ViewLeft","ZoomFit","TakeSnapshot","ToggleTrueColor","ToggleLight","Reset"}
     * @return
     */
    Boolean isDisplayedTopBar(String... name);
    /**
     * check top bar is displayed, if one button of top bar is missing return false.
     * default displayed buttons are {"ViewFront","ViewBack","ViewTop","ViewBottom","ViewRight","ViewLeft","ZoomFit","TakeSnapshot","ToggleTrueColor","ToggleLight","Reset"};
     * @return
     */
    Boolean isDisplayedTopBar();
    /**
     * check top bar is presented, if one button of top bar provided is missing return false.
     * @param name value in {"ViewFront","ViewBack","ViewTop","ViewBottom","ViewRight","ViewLeft","ZoomFit","TakeSnapshot","ToggleTrueColor","ToggleLight","Reset"}
     * @return
     */
    Boolean isPresentedTopBar(String... name);
    Boolean isPresentedTopBar();
    /**
     * check top bar is enabled, if one button of top bar is missing return false.
     * buttons are {"ViewFront","ViewBack","ViewTop","ViewBottom","ViewRight","ViewLeft","ZoomFit","TakeSnapshot","ToggleTrueColor","ToggleLight","Reset"};
     *
     * @return
     */
    Boolean isEnabledTopBar(String... name);
    /**
     * get top bar is enabled, if one button of top bar is missing return false.
     * default enabled buttons are {"ViewFront","ViewBack","ViewTop","ViewBottom","ViewRight","ViewLeft","ZoomFit","TakeSnapshot","ToggleTrueColor","Reset"};
     * "ToggleLight" button is disabled.
     * @return
     */
    Boolean getDefaultEnabledStatusTopBar();

    /**
     *
     * @param name in {"Display","OrientationAdjustment","Gallery","ImageInfo","Info","Help"}
     * @return
     */
    Boolean isDisplayedLeftBar(String... name);
    Boolean isDisplayedLeftBar();
    Boolean isPresentedLeftBar(String... name);
    Boolean isPresentedLeftBar();

    Boolean isEnabledLeftBar(String... name);
    Boolean isEnabledLeftBar();
    Boolean getDefaultPresentedStatusLeftBar();

}
