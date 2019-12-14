package com.wework.suites;

import com.wework.config.TagConstant;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages("com.wework.testcase.contact")
@ExcludeTags({TagConstant.FAST})
public class Junit5Suites {
}
