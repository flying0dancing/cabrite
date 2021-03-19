package com.csdental.meshviewer;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

@Test(groups={"smoke","function_test"})
public class GalleryTest extends TestManager {
    @Test
    public void checkGalleryDefault() {
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){
            Reporter.log("click Gallery");
            GalleryPage galleryPage=meshViewPage.clickGallery();
            if(galleryPage.isThePage()){
                embededScreenShot(Thread.currentThread().getStackTrace()[1].getMethodName());
                Assert.assertTrue(galleryPage.countOfSnapshot()==0,"default count should be zero.");
            }
        }
    }
}
