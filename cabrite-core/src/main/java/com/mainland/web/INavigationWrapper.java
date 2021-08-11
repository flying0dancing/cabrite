package com.mainland.web;

import java.net.URL;

public interface INavigationWrapper {
    INavigationWrapper back();
    INavigationWrapper forward();
    INavigationWrapper to(String url);
    INavigationWrapper to(URL url);
    INavigationWrapper refresh();
}
