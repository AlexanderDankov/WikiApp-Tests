package com.simbirsoft.tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import io.appium.java_client.MobileBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class WikiAppTests extends TestBase {

    @Test
    @DisplayName("Check Russian language in settings list of app")
    void checkRussianLanguageInSettingsList() {
        step("Open language choose options", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/addLangContainer")).click();
            $$(MobileBy.id("org.wikipedia.alpha:id/wiki_language_title"))
                    .findBy(Condition.text("ADD LANGUAGE")).click();
            $(MobileBy.id("org.wikipedia.alpha:id/menu_search_language")).click();
        });

        step("Choose Russian language option", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).setValue("Русский");
            $$(MobileBy.id("org.wikipedia.alpha:id/localized_language_name"))
                    .findBy(Condition.text("Русский")).click();
        });


        step("Check Russian language in option list", () -> {
            $$(MobileBy.id("org.wikipedia.alpha:id/wiki_language_title"))
                    .shouldHave(CollectionCondition.itemWithText("Русский"));
        });
    }

    @Test
    @DisplayName("Check text on second screen")
    void checkSecondScreenText() {
        step("Open second screen", () -> {
            $(MobileBy
                    .xpath("//android.widget.HorizontalScrollView[@content-desc=\"Page 1 of 4\"]/android.widget.LinearLayout/android.widget.LinearLayout[2]")).click();
        });

        step("Check title", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(Condition.text("New ways to explore"));
        });

        step("Check main text", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/secondaryTextView"))
                    .shouldHave(Condition.text("Dive down the Wikipedia rabbit hole with a constantly updating Explore feed.\n" +
                            " Customize the feed to your interests – whether it’s learning about historical events On this day, or rolling the dice with Random."));
        });
    }

    @Test
    @DisplayName("Check text on third screen")
    void checkThirdScreenText() {
        step("Open third screen", () -> {
            $(MobileBy
                    .xpath("//android.widget.HorizontalScrollView[@content-desc=\"Page 1 of 4\"]/android.widget.LinearLayout/android.widget.LinearLayout[3]")).click();
        });

        step("Check title", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/primaryTextView"))
                    .shouldHave(Condition.text("Reading lists with sync"));
        });

        step("Check main text", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/secondaryTextView"))
                    .shouldHave(Condition.text("You can make reading lists from articles you want to read later, even when you’re offline.\n" +
                            " Login to your Wikipedia account to sync your reading lists. Join Wikipedia"));
        });
    }

    @Test
    @DisplayName("Check switch toggle options")
    void checkSwitchMenu() {
        step("Open fourth screen", () -> {
            $(MobileBy
                    .xpath("//android.widget.HorizontalScrollView[@content-desc=\"Page 1 of 4\"]/android.widget.LinearLayout/android.widget.LinearLayout[4]")).click();
        });

        step("Check toggle checked", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/switchView"))
                    .shouldHave(Condition.attribute("checked", "true"));
        });

        step("Switch toggle to unchecked", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/switchView")).click();
        });

        step("Check toggle unchecked", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/switchView"))
                    .shouldHave(Condition.attribute("checked", "false"));
        });
    }
}
