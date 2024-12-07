package io.weconnect.automation.Loginpage;

import org.testng.annotations.DataProvider;

import io.weconnect.automation.ExcelUtils.ExcelUtils;

public class LoginTestData {
	@DataProvider(name = "loginData")
    public Object[][] provideLoginData() throws Exception {
        String filePath = "C:\\javaWorkSpace\\io.weconnect.automation\\TestData\\TestData.xlsx";
        String sheetName = "LoginTestData"; // Sheet name
        return ExcelUtils.GetExcelData(filePath, sheetName);
    }
}
