package lab.heisenbug.sandbox.jsoup;


import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created with IntelliJ IDEA.
 * User: parker
 * Date: 5/1/14
 * Time: 11:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class FixListParser {

    private static String V7510 = "http://www.ibm.com/support/fixcentral/swg/quickorder?parent=ibm%7EWebSphere&product=ibm/WebSphere/IBM+Business+Process+Manager+Advanced&release=7.5.1.0&platform=Linux+64-bit,x86_64&function=all&source=fc";

    private static String V8012 = "http://www.ibm.com/support/fixcentral/swg/quickorder?parent=ibm%7EWebSphere&product=ibm/WebSphere/IBM+Business+Process+Manager+Advanced&release=8.0.1.2&platform=Linux+64-bit,x86_64&function=all&source=fc";

    private static String BASE = "http://www-933.ibm.com/support/fixcentral/swg/";

    private static String DOWNLOAD_PATTERN = "http://www.ibm.com/support/fixcentral/swg/quickorder?parent=ibm%7EWebSphere&product=ibm/WebSphere/IBM+Business+Process+Manager+Advanced&release=7.5.1.0&platform=Linux+64-bit,x86_64&function=fixId&fixids=${fixid}&includeRequisites=0&includeSupersedes=0&downloadMethod=http&source=fc";

    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1964.4 Safari/537.36";

    private Map<String, String> cookies = loginIBM();

    @Test
    public void shouldParseFixList() throws Exception {

        List<Fix> fixList = new LinkedList<Fix>();

        System.out.println("Load iFix list page...");

        File input = new File("/Users/parker/Downloads/BPM.v7510.Fix.List.html");
        //Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
        Connection.Response response = Jsoup.connect(V8012)
                .userAgent(USER_AGENT)
                .followRedirects(true)
                .cookies(cookies)
                .timeout(600000)
                .method(Connection.Method.GET)
                .execute();

        //this.cookies = response.cookies();

        Document doc = response.parse();
        System.out.println(doc.title());

        Elements sections = doc.select("div.fc-display > table > tbody > tr");
        System.out.println(sections.size());


        List<FixTask> tasks = new LinkedList<FixTask>();
        for (Element section : sections) {
            //System.out.println(section.html());
            tasks.add(new FixTask(section));
        }

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<Fix>> results = executorService.invokeAll(tasks);

        for(Future<Fix> result: results){
            fixList.add(result.get());
        }

        for (Fix fix : fixList) {
            System.out.println(fix.getId());
            System.out.println("\t" +  fix.getReadmeLink());
            System.out.println("\t" + fix.getPackageLink());
            System.out.println("\t" + fix.getAparLink());
        }

    }


    private Map<String, String> loginIBM() {
        try {
            String loginPageUrl = "https://www.ibm.com/gss/instantprofile/InstantSignin?offAutoSignin=undefined&iwmnoquestions=&iwmnouserid=&iwmsource=&link=%23&country=us&language=en&origin=&displayLayer=&iptrace=sgn_creating_instantsignin_iframe_2";

            Connection.Response loginPage = Jsoup.connect(loginPageUrl)
                    .userAgent(USER_AGENT)
                    .method(Connection.Method.GET)
                    .execute();

            String loginFormUrl = "https://www-947.ibm.com/pkmslogin.form?iptrace=sgn_authenticating_signin_user_4";

            Connection.Response loginResult = Jsoup.connect(loginFormUrl)
                    .userAgent(USER_AGENT)
                    .data("username", "parker.y.wang@gmail.com")
                    .data("password", "43174848")
                    .data("password_text", "Password*")
                    .data("login-form-type", "pwd")
                    .cookies(loginPage.cookies())
                    .method(Connection.Method.POST)
                    .execute();

            return loginResult.cookies();
        } catch (IOException e) {
            throw new ContextedRuntimeException(e);
        }


    }

    private class FixTask implements Callable<Fix> {

        private Element section;

        private FixTask(Element section) {
            this.section = section;
        }

        @Override
        public Fix call() throws Exception {
            Fix fix = new Fix();

            //System.out.println(section.select("td input.iform").attr("value"));

            Element publishDate = section.select("td").last();
            fix.setPublishDate(publishDate.text());


            Element aparLink = section.select("td.fc-all > table td").last().select("a").first();
            if (aparLink != null) {
                fix.setAparLink(aparLink.attr("href"));
                try {
                    Document apar = Jsoup.connect(fix.getAparLink()).get();
                    Element title = apar.select("div#ibm-leadspace-body h1").first();
                    fix.setSummary(title.text());
                } catch (IOException e) {
                    fix.setSummary(fix.getAparLink());
                }
            } else {
                fix.setSummary("Not Available");
            }
            //System.out.println(fix.getAparLink());

            Element fixLink = section.select("td.fc-all h2 a").first();
            fix.setId(fixLink.text());

            {
                Map<String, String> values = new HashMap<String, String>();
                values.put("fixid", fix.getId());
                String downloadLink = StrSubstitutor.replace(DOWNLOAD_PATTERN, values);
                //System.out.println(downloadLink);
                Document download = Jsoup.connect(downloadLink)
                        .userAgent(USER_AGENT)
                        .followRedirects(true)
                        .cookies(cookies)
                        .timeout(600000)
                        .get();
                //System.out.println(download);
                Element list = download.select("form#downloadFixes ul.ibm-link-list").first();
                if (list != null) {
                    Element readme = list.select("li a").first();
                    fix.setReadmeLink(readme.attr("href"));
                    //System.out.println(fix.getReadmeLink());
                    Element pkg = list.select("li a").last();
                    fix.setPackageLink(pkg.attr("href"));
                    //System.out.println(fix.getPackageLink());
                }
            }
            return fix;
        }
    }

}
