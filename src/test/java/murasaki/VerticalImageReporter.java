package murasaki;

import com.spun.util.tests.TestUtils;
import org.approvaltests.core.ApprovalFailureReporter;
import org.approvaltests.reporters.ClipboardReporter;

public class VerticalImageReporter implements ApprovalFailureReporter {

    @Override
    public void report(String received, String approved)
    {
        //language=HTML
        String text = "<html>\n"
                + "<script>\n"
                + "    function copyToClipboard() {\n"
                + "        const copyText = document.getElementById(\"moveText\");\n"
                + "        navigator.clipboard.writeText(copyText.textContent);\n"
                + "    }\n"
                + "</script>\n"
                + "<body>\n"
                + "<center>\n"
                + "    <table border=1>\n"
                + "        <tr>\n"
                + "            <td style=\"text-align:center\">approved</td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "            <td><img src=\"file:///%s\"></td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "            <td style=\"text-align:center\">received</td>\n"
                + "        </tr>\n"
                + "        <tr>\n"
                + "            <td><img src=\"file:///%s\"></td>\n"
                + "        </tr>\n"
                + "    </table>\n"
                + "    %s <br/> <b>to approve :</b> copy clipboard to command window <br/> <font size=1 id=\"moveText\">%s</font>\n"
                + "    <br /><button onclick=\"copyToClipboard()\">Copy to clipboard</button>\n"
                + "</center>\n"
                + "</body>\n"
                + "</html>\n";
        String moveText = ClipboardReporter.getAcceptApprovalText(received, approved);
        text = String.format(text, approved, received, received, moveText);
        TestUtils.displayHtml(text);
    }

}
