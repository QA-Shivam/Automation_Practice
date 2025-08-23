package TestCases;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SpellChecker {

    // Icons for better visual feedback
    private static final String CHECK_MARK = "✅";
    private static final String WARNING = "⚠️";
    private static final String ERROR = "❌";
    private static final String BULLET = "•";
    private static final String MAGNIFYING_GLASS = "🔍";
    private static final String LINK = "🔗";
    private static final String LIGHT_BULB = "💡";
    private static final String SCROLL = "📜";
    private static final String DIVIDER = "──────────────────────────────────────────────";

    public static void main(String[] args) {
        WebDriver driver = null;
        try {
            // Setup browser
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://www.dummytextgenerator.com/#jump");

            // Extract visible text
            String pageText = driver.findElement(By.tagName("body")).getText()
                    .replaceAll("\\s+", " ").trim();

            // Spell check configuration
            JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());
            langTool.disableRule("EN_COMPOUNDS");
            langTool.disableRule("MORFOLOGIK_RULE_EN_US");

            List<RuleMatch> matches = langTool.check(pageText);

            if (matches.isEmpty()) {
                System.out.println("\n" + CHECK_MARK + " No spelling or grammar issues found.");
            } else {
                System.out.println("\n" + MAGNIFYING_GLASS + " SPELL CHECK RESULTS " + MAGNIFYING_GLASS);
                System.out.println(LINK + " Scanned URL: " + driver.getCurrentUrl());
                System.out.println(WARNING + " Found " + matches.size() + " potential issue(s)\n");

                for (RuleMatch match : matches) {
                    String error = pageText.substring(match.getFromPos(), match.getToPos());
                    String context = getCleanContext(pageText, match.getFromPos(), match.getToPos());

                    System.out.println(ERROR + " ISSUE: " + match.getMessage());
                    System.out.println(BULLET + " Found: \"" + error + "\"");

                    if (!match.getSuggestedReplacements().isEmpty()) {
                        System.out.println(LIGHT_BULB + " Suggestions: " +
                                String.join(" / ", match.getSuggestedReplacements().subList(0,
                                        Math.min(3, match.getSuggestedReplacements().size()))));
                    }

                    System.out.println(SCROLL + " Context: " + context);
                    System.out.println(DIVIDER);
                }
            }
        } catch (Exception e) {
            System.err.println(ERROR + " Error: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    private static String getCleanContext(String text, int from, int to) {
        int contextSize = 20;
        int start = Math.max(0, from - contextSize);
        int end = Math.min(text.length(), to + contextSize);

        String context = text.substring(start, end)
                .replace("\n", " ").replaceAll("\\s+", " ");

        return (start > 0 ? "[...]" : "") +
                context +
                (end < text.length() ? "[...]" : "");
    }
}