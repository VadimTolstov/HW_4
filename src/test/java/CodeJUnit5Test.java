
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CodeJUnit5Test {

    @BeforeAll
    static void setUp() {
//        Configuration.browser = "firefox";
        Configuration.browserSize = "1920x1080";
//          Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://github.com";
    }

    String sourceСode = """
            @ExtendWith({SoftAssertsExtension.class})
            class Tests {
              @Test
              void test() {
                Configuration.assertionMode = SOFT;
                open("page.html");
                
                $("#first").should(visible).click();
                $("#second").should(visible).click();
              }
            }""";

    @Test
    void codeDisplayJUnit5() {
        open("/selenide/selenide");
        $("a#wiki-tab").click();
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $("#wiki-pages-box[role=navigation]").shouldHave(text("SoftAssertions"));
        $(byText("SoftAssertions")).click();

        String actualCode =
                $("#user-content-3-using-junit5-extend-test-class").ancestor("h4")
                        .sibling(0).$("pre").getText();

        assertEquals(sourceСode,
                actualCode,
                "JUnit5 source code is different");

    }

}
