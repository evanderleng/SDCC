# Singapore Safety Driving Centre Bot
Bot that camps on the website of ssdcl.com.sg and instantly books slots for you the moment they are released from someone who cancels.

### I will not be responsible for any damages from using this program
This is a very quick and dirty solution coded in Java using htmlunit, and bugs may be present.

No transactions are involved in the code, make sure your account has sufficient credit to buy the slots you want when the bot is running.

### Setting up the bot (only needs to be performed once)
1. Edit lines 54 and 55 (and 145 if you want to book lessons from ang mo kio) of src/sdcc/loginv3.java and replace the placeholder credentials with your own SSDCL credentials
2. cd to the root folder (SDCC)
3. Compile with `javac -cp "./htmlunit-2.34.1-bin/htmlunit-2.34.1/lib/*" ./JavaApplication12/src/sdcc/loginv3.java`
4. The class file has been created and you can now run the bot

### Running the bot
1. Input your desired dates and timeslots in dates.txt, located in src/sdcc, seperating the date and timeslot with a colon. An example is as follows:
```
03 Sep 2019:5
21 Jul 2019:1
01 Dec 2019:6
```
Timeslot reference is included below:


| Timeslot| Weekday | Weekend  |
| :------------- |:-------------|:-----|
| 1| 8.15 am - 10.15 am | 8.15 am - 10.15 am * |
| 2| 10.30 am - 12.30 pm |10.30 am - 12.30 pm * |
| 3 | 1.05 pm - 3.05 pm | 1.05 pm - 3.05 pm * |
| 4 | 3.20 pm - 5.20 pm  | 3.20 pm - 5.20 pm * |
| 5 | 6:10pm - 8:10pm * | - |
| 6 | 8:20pm - 10:20pm * | - |
* Peak Hour

2. cd to the root folder (SDCC)
3. Start the program with `cd ./JavaApplication12/src && nohup java -Xmx2048m -Xdiag -cp "../../htmlunit-2.34.1-bin/htmlunit-2.34.1/lib/*":. sdcc.loginv3 &` The program will run forever until it runs out of dates in dates.txt or runs into an error.
4. To kill the program, run `jps` and find the process id of loginv3. Kill with `kill <process id>`


### Logs
Logs are located in log.txt as well as nohup.out
Here is a sample:
```Tue Oct 22 15:50:22 ICT 2019 : Round 2227 finished. Starting round 2228
Tue Oct 22 15:50:29 ICT 2019 : Round 2228 finished. Starting round 2229
Tue Oct 22 15:50:37 ICT 2019 : Round 2229 finished. Starting round 2230
Tue Oct 22 15:50:44 ICT 2019 : Round 2230 finished. Starting round 2231
Tue Oct 22 15:50:51 ICT 2019 : This is the test: 
                                                                                8:20 PM ✔
                                                                            :a
Tue Oct 22 15:50:56 ICT 2019 : 8:20 PM at 24 Oct 2019 has been found and booked
Tue Oct 22 15:50:56 ICT 2019 : Round 2231 finished. Starting round 2232
Tue Oct 22 15:51:01 ICT 2019 : no more entries```
