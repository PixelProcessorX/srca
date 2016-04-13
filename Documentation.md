## SRCA - Student Rec Center Application
#### Project Goals

Vincent Boyd, Dillon Simion, Ryan Lafrence

Use this file to describe the project goals and completion.  
Written in markdown syntax for github compatibility.  
For an introduction, see [here](https://help.github.com/articles/basic-writing-and-formatting-syntax/).  

#### Tips for using GitHub

Git maintains a separate file database than those in windows explorer - git must be told to add changes to files that are done in other programs.
The git shell is much more functional then the windows Git GUI. 
Here are some basic commands to operate it:
```
git status - Shows status of the current repository.
git add - Add new files to the repository database.
git add . -Adds all files to the repository database.
git commit -m "" -Commits current changes, creating a new point in the database. -m creates a message, enter it between the quotes. This message is a short name for the commit, and will appear on the git website.
git pull -Pulls the current repository from GitHub.
git push -Pushes all commits to the Github repository.
git branch <Branch Name> -Creates a new branch from master, with the given name.
git branch -d <Branch Name> -Closes the given branch.
git checkout <Branch Name> -Switches to a different branch.
```
Of course, the system is fairly new to me, so some of this may be incorrect. If you need other commands, the internet is your friend!  
One thing to note: Github does not like files larger than 100MB. Additionally, if you have a 100MB+ file inside a commit, then delete the file and create a new commit, Git will think the file is still there when pushing to github and the push will fail.
Removing files like this from git's file history can be difficult, so try to avoid this scenario in the first place.

The normal procedure to change code on github is to:
1 Create a branch from master.
2 Change the branch.
3 Commit and push the branch.
4 On github, create a pull request to remerge the branch with master.  
This is done so that your changes to the code do not effect the master, multiple people can start from the same master code, and you can revert to the master in case something goes wrong.

#### Current Tasks
What task are you working on?

###### Vincent Boyd
* Android app setup
* App menu design and layout
* Getting a useful demo phone or emulator running on my laptop

###### Dillon Simion
* Database stuff?

###### Ryan Lafrence
* Google Signin
* Android Studio

### Main Project TODO List

**Core Goals**
1 Google signin used by database to authenticate users.
2 Database able to differentiate between regular, instructor, and admin users. (Different permission levels in same app).
3 Regular users can view available events, register for events, and a list of registered events.
4 Instructors can view their assigned classes, and view list of users registered for their classes.
5 Admin can use app to create new events on the calendar, including assigned instructor, date/time, and repetition of the event.
6 Java applet pulls information from the google calendar to the database.

**Research to do**
1 How does the google sign in let our program know who has logged in?
2 Should we look at using a webservice? Preliminary research I (V) have done indicates it is a very good idea and not too difficult. See [here](http://programmers.stackexchange.com/questions/170463/why-to-use-web-services-instead-of-direct-access-to-a-relational-database-for-an), [here](http://stackoverflow.com/questions/2142070/why-should-a-developer-use-web-services-instead-of-direct-connections-to-a-db), and [here](http://www.agiledata.org/essays/implementationStrategies.html) for arguments, and [here](http://android-devblog.blogspot.in/2010/06/soap-on-android.html) or [here](http://www.codeproject.com/Articles/267023/Send-and-receive-json-between-android-and-php) for tutorials on the matter. This is all from a few minutes on google.
3 How is the app going to store or refresh data from the database? For instance: Will it check every time it is opened, or will it update periodically? Will it store a local copy of the data?
4 How the heck does android studio work, and how can we set up a useful emulator or demo phone? How can we make the app look nice?

