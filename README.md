
## IntelliJ IDEA settings

`Settings -> Compiler -> Annotation Processors`

Make sure you have `Enable annotation processing`


## Tasks

`https://github.com/GoogleCloudPlatform/gradle-appengine-plugin`

The App Engine plugin defines the following tasks:

* `appengineLogs`: Retrieves log data for the application running on App Engine.
* `appengineRollback`: Undoes a partially completed update for the given application.
* `appengineRun`: Starts a local development server running your project code. By default the WAR file is created, exploded and used as
web application directory each time you run this task. This behavior can be changed by setting the convention property
`warDir`.
* `appengineStop`: Stops the local development server.
* `appengineUpdateCron`: Updates the schedule task (cron) configuration for the app, based on the cron.xml file.
* `appengineUpdateDispatch`: Updates the dispatch configuration for the app, based on the dispatch.xml file.
* `appengineUpdateDos`: Updates the DoS protection configuration for the app, based on the dos.xml file.
* `appengineUpdateIndexes`: Updates datastore indexes in App Engine to include newly added indexes.
* `appengineUpdateQueues`: Updates the task queue configuration (queue.xml) in App Engine.
* `appengineUpdate`: Uploads files for an application given the application's root directory. The application ID and version are taken from the appengine-web.xml file.
* `appengineVacuumIndexes`: Deletes unused indexes in App Engine server.
* `appengineVersion`: Prints detailed version information about the SDK, Java and the operating system.

## User object
{
  "id" : 1234,
  "fN" : "Luigi",
  "lN" : "Pelati",
  "p" : 100,
  "os" : [ {
    "id" : 123,
    "n" : "Hood Burger 10/11",
    "d" : "Buy 10 and get one free!",
    "dF" : 1476222970776,
    "dT" : 1476222970776,
    "dP" : 11,
    "pC" : 222,
    "t" : "1",
    "c" : 33
  } ],
  "cs" : [ {
    "id" : 321,
    "n" : "Paying Streak",
    "d" : "Pay wit Hal mBills 10 consecutive days!",
    "pV" : 555,
    "dF" : 1476222970777,
    "dT" : 1476222970777,
    "t" : "2",
    "c" : 444,
    "sI" : 4321,
    "p" : 6
  } ]
}

## Endpoints
pelati.appspot.com/rest/user              (create)       - POST (list of offers in body)
pelati.appspot.com/rest/user              (delete)       - DELETE (?uId=1234)
pelati.appspot.com/rest/user              (get)          - GET (?uId=4321)
pelati.appspot.com/rest/user/list         (get all)      - GET

pelati.appspot.com/rest/offer             (create)       - POST (list of offers in body)
pelati.appspot.com/rest/offer             (delete one)   - DELETE (?oId=1234)
pelati.appspot.com/rest/offer             (get for user) - GET (?uId=4321)
pelati.appspot.com/rest/offer/delete      (delete list)  - POST (list of offers in body)
pelati.appspot.com/rest/offer/list        (get all)      - GET

pelati.appspot.com/rest/challenge         (create)       - POST (list of challenges in body)
pelati.appspot.com/rest/challenge         (delete one)   - DELETE (?cId=1234)
pelati.appspot.com/rest/challenge         (get for user) - GET (?uId=4321)
pelati.appspot.com/rest/challenge/delete  (delete list)  - POST (list of challenges in body)
pelati.appspot.com/rest/challenge/list    (get all)      - GET
