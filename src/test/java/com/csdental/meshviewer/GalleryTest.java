package com.csdental.meshviewer;

import org.testng.Assert;
import org.testng.annotations.*;

@Test(groups={"smoke","function_test"})
public class GalleryTest extends TestManager {
    @Test
    public void checkGalleryDefault() throws Exception {
        MeshViewPage meshViewPage=new MeshViewPage(getWebDriverWrapper());
        if(meshViewPage.isThePage()){
            GalleryPage galleryPage=meshViewPage.clickGallery();
            if(galleryPage.isThePage()){
                getWebDriverWrapper().takeScreenshot(Thread.currentThread().getStackTrace()[1].getMethodName());

                Assert.assertTrue(galleryPage.countOfSnapshot()==0,"default count should be zero.");
            }
        }
    }
}
