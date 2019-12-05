package sdcc;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.gargoylesoftware.htmlunit.javascript.host.dom.Node;
import com.gargoylesoftware.htmlunit.javascript.host.dom.NodeList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.io.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;



public class loginv3 {

    public static void main(String[] args) throws Exception {

        for (int never = 0; never < 9; never++) {
            try {

                logger("Program started.");
                WebClient webClient = new WebClient(BrowserVersion.FIREFOX_60);
                webClient.getOptions().setCssEnabled(false);

                Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);

                webClient.getOptions().setJavaScriptEnabled(true);
                webClient.getOptions().setThrowExceptionOnScriptError(false);
                webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);

                HtmlPage page1 = (HtmlPage) webClient.getPage("https://www.ssdcl.com.sg/User/Login");

                HtmlForm form = page1.getFirstByXPath("//form[@action='/Account/Login']");

                form.getInputByName("UserName").setValueAttribute("s0000000s");
                form.getInputByName("Password").setValueAttribute("000000");
                
                //this is referral code
                //HtmlButton button = page1.getFirstByXPath("//button[@type='submit' and span[text()=='Search']]");
                HtmlButton button = page1.getFirstByXPath("//button[@type='submit']");
                
                page1 = button.click();
                //System.out.println(page1.asText());
                logger("Login successful!");
             
		HtmlPage bookPage = (HtmlPage) webClient.getPage("https://www.ssdcl.com.sg/User/Booking/BookingList");

   
		HtmlAnchor newBooking = bookPage.getAnchorByText("New Booking");
                HtmlPage page2 = newBooking.click();
                
                HtmlCheckBoxInput agree =  page2.getHtmlElementById("chkProceed");
                HtmlAnchor proceed = page2.getAnchorByText("Proceed");
                
                
                
                

                boolean dateFound;
                boolean found;

                for (int i = 0;; i++) {                    //at this point the program loops forever
                    dateFound = false;
                    /*
                    _             _            __    __ _ _                 _       _           _ 
                   | |           | |          / _|  / _(_) |               | |     | |         | |
                ___| |_ __ _ _ __| |_    ___ | |_  | |_ _| | ___   _ __ ___| | __ _| |_ ___  __| |
               / __| __/ _` | '__| __|  / _ \|  _| |  _| | |/ _ \ | '__/ _ \ |/ _` | __/ _ \/ _` |
               \__ \ || (_| | |  | |_  | (_) | |   | | | | |  __/ | | |  __/ | (_| | ||  __/ (_| |
               |___/\__\__,_|_|   \__|  \___/|_|   |_| |_|_|\___| |_|  \___|_|\__,_|\__\___|\__,_|
                     */
                    File file = new File("./sdcc/dates.txt"); //read dates from file
                    
	 	    BufferedReader br = new BufferedReader(new FileReader(file));

                    if (file.length() < 4) {
                        logger("no more entries");
                        return;
                    }

                    File file2 = new File("./sdcc/datesNew.txt"); //read dates from file
                    if (!file2.exists()) {
                        file2.createNewFile();
                    }
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file2));

                    String st;
                    while ((st = br.readLine()) != null) {                        
                        found = false;

                        String[] output = st.split(":");
                        String date = output[0];
                        String slot = output[1].replaceAll("\\s", "");
                        String slotTime;
                        switch (slot) {
                            case "1":
                                slotTime = "8:15 AM";
                                break;
                            case "2":
                                slotTime = "10:30 AM";
                                break;
                            case "3":
                                slotTime = "1:05 PM";
                                break;
                            case "4":
                                slotTime = "3:20 PM";
                                break;
                            case "5":
                                slotTime = "6:10 PM";
                                break;
                            case "6":
                                slotTime = "8:20 PM";
                                break;
                            default:
                                slotTime = "error";
                        }
                        
                        HtmlPage page3 = proceed.click();
                        form = page3.getFirstByXPath("//form[@action='/User/Booking/AddBooking']");
                
                        HtmlSelect select = (HtmlSelect) page3.getElementByName("BookingType");
                        HtmlOption option = select.getOptionByValue("PL");
                        select.setSelectedAttribute(option, true);

                        HtmlSelect select2 = (HtmlSelect) page3.getElementByName("SelectedLocation");
                        HtmlOption option2 = select2.getOptionByValue("Woodlands");
                        select2.setSelectedAttribute(option2, true);

                        form.getInputByName("IsFiRequired").setValueAttribute("False");
                        
                        form.getInputByName("SelectedDate").setValueAttribute(date);  //3 letters only

                        //logger("Submitting form... checking dates for "+date+" "+slotTime);
                        
                        
                        HtmlAnchor check = page3.getAnchorByText("Check for availability");
                        page3 = check.click();
                        //System.out.println(page3.asText());
                        
                        //DomNodeList<HtmlElement>
                        List<DomNode> nodes2 = page3.getByXPath("//tr");
                        DomNode node2 = nodes2.get(1); //1 is the first date

                        Iterable<HtmlElement> timingOptions = node2.getHtmlElementDescendants();
                        for(HtmlElement to:timingOptions){
                            if (to.getTextContent().contains(slotTime) && to.getNodeName().equals("a")){    //a href has been found
                                dateFound = true;
                                found = true;
				
				logger("This is the test: "+to.getTextContent()+":"+to.getNodeName());
                                
                                HtmlAnchor finalDateSelect = (HtmlAnchor) to;
                                HtmlPage page4 = finalDateSelect.click();       //Slot has been added to cart.



				//saveWebpage(page4.asXml());

				/*HtmlButton close = page4.getFirstByXPath("//button[@type='button' and @class='btn btn-general-short' and @data-dismiss='modal']");
				close.click();*/

				//HtmlPage page6 = (HtmlPage) webClient.getPage("https://www.ssdcl.com.sg/User/Payment/ReviewItems");

				HtmlAnchor paymentPending;
				try {
					paymentPending = page4.getAnchorByHref("/User/Payment/ReviewItems");
				}catch (Exception e){
					saveWebpage(page4.asXml());
					paymentPending = page4.getAnchorByHref("https://www.ssdcl.com.sg/User/Payment/ReviewItems");
				}


                                HtmlPage page6 = paymentPending.click();

                                HtmlAnchor confirmPayment = page6.getAnchorByText("Confirm Purchase");
                                HtmlPage page7 = confirmPayment.click();
                                
                                HtmlAnchor makePayment = page7.getAnchorByText("Make Payment");
                                HtmlPage page8 = makePayment.click();
                                
                                logger(slotTime+" at "+date+" has been found and booked");

				HtmlPage delPage = (HtmlPage) webClient.getPage("https://www.ssdcl.com.sg/User/Booking/BookingList");
                       		if(!delPage.asText().contains("You have no item pending payment")){	//ie there ARE items pending
                                	HtmlAnchor del = delPage.getAnchorByHref("/User/Payment/ReviewItems");
	                                HtmlPage delPage2 = del.click();
                                	HtmlAnchor del2 = delPage2.getAnchorByText("Confirm Purchase");
	                                HtmlPage delPage3 = del2.click();
                              		HtmlAnchor del3 = delPage3.getAnchorByText("Make Payment");
                                	HtmlPage delPage4 = del3.click();					
					logger("re-tried booking. "+slotTime+" at "+date+" has been found and booked");
				}


                                break;
                            }
                        }
                        
                        if (found == false) {
                            bw.append(st);
                            bw.append(System.lineSeparator());
                        }
                    }

                    br.close();
                    bw.close();
                    if (dateFound) {
                        Date date = java.util.Calendar.getInstance().getTime();
                        File archive = new File("./sdcc/delArchive.txt");
                        
                        boolean b = archive.delete();
                        boolean rename = file.renameTo(archive);
                        boolean rename2 = file2.renameTo(file);
                       // System.out.println("archive renaming in progress");
                    }

                    logger("Round " + (i + 1) + " finished. Starting round " + (i + 2));
                    TimeUnit.SECONDS.sleep(5);  //gn sweet prince sleep for 5 secs
                }
            } catch (Exception e) {

                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                logger(sw.toString());
            
          }  }
    }   


    public static void saveWebpage(String content) throws IOException {
        File webp = new File("./sdcc/webpageTest.html");
        BufferedWriter writer = new BufferedWriter(new FileWriter(webp, true));
        if (!webp.exists()) {
            webp.createNewFile();
        }
	writer.write(content);
	writer.close();
    }


    public static void logger(String info) throws IOException {
        File log = new File("./sdcc/log.txt"); //read dates from file
        BufferedWriter logWriter = new BufferedWriter(new FileWriter(log, true));
        if (!log.exists()) {
            log.createNewFile();
        }
        Date date = java.util.Calendar.getInstance().getTime();
        System.out.println(date + " : " + info);
        logWriter.append(date + " : " + info);
        logWriter.append(System.lineSeparator());
        logWriter.close();
    }
}
