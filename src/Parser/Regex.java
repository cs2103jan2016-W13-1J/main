package Parser;
/* @@author A0121535R
* class  that contains the general Regex
*/
public class Regex {
static final String REGEX_SPACE = "\\s+";
static final String START = "(?<=^|\\s)";
static final String END = "(?=\\s|$)";
static final String MISC_REGEX ="(at?|from?|to?|til? |till? |until?|by?|at?|on?|next?|this?|by next?|by this?)"+"\\s";
static final String MONTHNAME = "(jan(?:uary)?|feb(?:ruary)?|mar(?:ch)?|apr(?:il)?|may|jun(?:e)?|jul(?:y)?|aug(?:ust)?|sep(?:tember)?|oct(?:ober)?|nov(?:ember)?|dec(?:ember)?)";
static final String DATES = "(\\d?\\d)(?:st|rd|nd|th)?";
static final String YEAR = "(\\d{2}|\\d{4})";

static final String DATE_MONTH_REGEX = "(?<=\\s|^)" + DATES + "\\s" + MONTHNAME + "(?=\\s|$)";
static final String MONTH_DATE_REGEX = "(?<=\\s|^)" + MONTHNAME + "\\s" + DATES + "(?=\\s|$)";
static final String DATE_MONTH_YEAR_REGEX = "(?<=\\s|^)" + DATES + "\\s" + MONTHNAME + "?\\s?" + YEAR + "(?=\\s|$)";
static final String MONTH_DATE_YEAR_REGEX = "(?<=\\s|^)" + MONTHNAME + "\\s" + DATES + "?\\s?" + YEAR + "(?=\\s|$)";

static final String TMR_REGEX = "((today?|tmr?|tomorrow?).*(?=\\s|$))";

static final String DAYNAME = "(week?|month?|mon(?:day)?|tue(?:sday)?|wed(?:nesday)?|thur(?:sday)?|fri(?:day)?|sat(?:urday)?|sun(?:day)?)";
static final String TWELVE_HR_REGEX = "([0-9]?[0-9])([.:][0-9][0-9])?\\s?(am|pm)?(\\s?(?:-|to|until|til|till)\\s?([0-9]?[0-9])([.:][0-9][0-9])?\\s?)?(am|pm)(?=\\s|$)";
static final String TWENTYFOUR_HR_REGEX = "(([0-9]?[0-9])[:]([0-9][0-9]))\\s?[?:h|H]?\\s?((?:-|to|until|til|till)?\\s?(([0-9]?[0-9])[:]([0-9][0-9])))?\\s?[?:h|H]?(?=\\s|$)";

static final String DAY = "(\\d?\\d)";
static final String MONTH = "(\\d?\\d)";
static final String DATE_SEP = "[-/]";


}