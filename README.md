# Singapore Safety Driving Centre Bot
Bot that camps on the website of ssdcl.com.sg and instantly books slots for you the moment they are released from someone who cancels.

This is a very quick and dirty solution coded in Java using htmlunit, no gui use the command line.

No transactions are involved in the code, make sure your account has sufficient credit to buy the slots you want when the bot is running.


### Setting up the bot (only needs to be performed once)
1. Compile with

### Running the bot
Input your desired dates and timeslots in dates.txt, located in src/sdcc, seperating the date and timeslot with a colon. Whitespace should not be a problem. An example is as follows:
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

