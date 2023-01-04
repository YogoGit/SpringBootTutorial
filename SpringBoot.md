# Welcome to the SpringBoot for Java!

This tutorial will help teach you how to build Java web applications using
SpringBoot 3.

Before you get started, you will need to install [java](https://www.oracle.com/java/technologies/downloads/) and [git](https://git-scm.com/downloads).

I recommend installing Java 21, as it's the most compatible LTS (Long Term Support)
release version of Java as of the time this file was written, which should
be valid throughout the entire semester.

These installs may require you to update your PATH, which is done differently
in Windows or the Mac. Those details are not provided in this tutorial.

Test that the `java` command works by running:

    java -version

You should see text displaying the version of java installed. If you do not,
ensure you have java installed and referenced in your PATH.

Optionally, you can also install/use an IDE such as [Eclipse](https://eclipseide.org/)
or [IntelliJ](https://www.jetbrains.com/idea/download/) to provide an more
robust programming environment. Note, I recommend the Ultimate version of the
latter, which is use by many commercial developers, but is free for
students/teachers, see [here](https://www.jetbrains.com/community/education/#students)
for details on obtaining an educational license.

Alright! You are ready to go!


Create a new project
--------------------

The initial documentation is heavily influenced by the following sites:

* [quickstart](https://spring.io/quickstart)
* [spring-boot](https://spring.io/guides/gs/spring-boot/)
* [IntelliJ](https://spring.io/guides/gs/intellij-idea/)

Or, you can choose to follow this guide, which is MUCH more comprehensive.

---

Create a new Spring Boot Project by going to <https://start.spring.io/>

* Project - Gradle - Groovy
* Language - Java
* Spring Boot - 3.5.4
* Project Metadata
  - Group: edu.carroll
  - Artifact: cs341-demo
  - Name: cs341
  - Description: CS341 project for Spring Boot
  - Package name: edu.carroll.cs341
  - Packaging: Jar
  - Java: 21

Next, in the upper-left corner of the screen, Click on the 'Add dependencies'
button, and scroll down until you see 'Spring Web', and then click to add it.

Finally, click the 'GENERATE' button at the bottom of the page, which should
create and then download the file cs341-demo.zip onto your laptop.

Bring up a command line window and browse to the directory for projects
in this class) (either create a new directory or use an existing directory).

Once in that directory, unpack the file in the top-level directory, which
should create a new folder named cs341-demo containing the basic configuration
for your demo project.

In the newly created `cs341-demo` directory create a new git repository by running:

    cd cs341-demo
    git init

```sh
git add .
git commit -m "Initial commit"
```

At this point, I would *HIGHLY* recommend creating a new Github repository
associated with your personal Github account, and then upload this project
to github before proceeding. Instructions on how to accomplish this are
not included in this tutorial.

Throughout this tutorial you will be able to check your progress against the
official tutorial.  To do this add the official tutorial as a new git remote
named `upstream` by running:

    git remote add upstream https://github.com/YogoGit/SpringBootTutorial.git


Fetch the remote repository:

    git fetch upstream --tags


Verify your repo is up-to-date with the remote repository code:

```sh
git diff spring_start
```

Set up an IDE
-------------

Before we do anything else, let's bring the project into our IDE.

Since the preferred IDE for this class is IntelliJ due to it being the more
likely IDE used by professional Java developes, I'll only provide the
most basic instructions to use it.

In IntelliJ, select 'File -> New -> Projects from Existing Sources', and then
browse to the folder where the **cs341-demo** folder repository is located.
Click on the **cs341-demo** name (do NOT double-click it), then select
'Open', which should allow IntelliJ to setup and configure the project.

In the next screen, choose 'Import project from external model', and choose
the 'Gradle' icon below it, then finally, select 'Create'.

![Import Image](image/GradleImport.jpg?raw=true "GradleImport")

You *may* have to enable gradle on the project by selecting the bell icon
on the far left, and then enable Gradle (why, who knows?).

For Eclipse, there are more instructions and plugins needed. The following
article should get you started <https://www.eclipse.org/community/eclipse_newsletter/2018/february/springboot.php>.

Create a Simple Web Application
-------------------------------

Now you can create a web controller for this simple web application, as the
following (src/main/java/edu/carroll/cs341/HelloController.java):

    package edu.carroll.cs341;

    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class HelloController {
        @GetMapping("/")
        public String index() {
            return "Greetings from Spring Boot!";
        }
    }

Add the class to git with a proper commit message

```sh
git add src/main/java/edu/carroll/cs341/HelloController.java
git commit -m "Controller for responding to root context requests"
```

Verify your repository is up-to-date with no uncommitted files by running
a `git status` command

    On branch main
    nothing to commit, working tree clean

Finally, verify your project is up-to-date by comparing it against the
source repo

```sh
git diff hello_controller
```


Running Application
-------------------

To run the application from the command line, it's a simple matter of running
gradle in the project directory

    gradlew bootRun

The command should be the same whether you are using Windows or MacOS.  (Note,
on MacOS, you *may* need to provide the path as './gradlew' to start it up.

The FIRST TIME you do this, it will download all of the dependencies for
the project, which may take a bit. However, startup time for subsequent runs
will be much faster as the downloads are all cached locally.

After starting, you should eventually see a line that looks similar to the
following.

    2022-12-28T13:26:17.102-07:00  INFO 9483 --- [           main] edu.carroll.cs341.Cs341Application       : Started Cs341Application in 0.998 seconds (process running for 1.219)

Open the following URL in your browser to verify the app is working:
<http://localhost:8080>

This should now return the text from the HelloController we coded above, as
well as verify that your environment and dependencies are all setup properly.

Routes
------

Spring routes HTTP requests to a controller using the annotation defined in
the Controller class.  In the case of the HelloController, we use the following
annotation:

    @GetMapping("/")

This indicates that the type of operation (GET) and the context ("/") will be
mapped to the method just below the annotation (the index() method).

There are other types of operations (PUT, POST, DEL, etc...), and each request
type has a unique annotation associated with the HTTP type.  You can also
specify the routing using a different annotation, similar to the following

    @RequestMapping(value = "/", method = RequestMethod.GET)

This alternate syntax allows for mapping multiple requesst types to the same
method.

    @RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })

Note, if you don't provide any method to the RequestMapping annotation, it
responds to all HTTP operations. The syntax can get as complicated or as simple
as you'd like, so see the documentation for more examples.

---

So, let's quickly change the mapping from the root context to a different
context by modifying the annotation.

In the HelloController class, modify the annotation on the index method to
look like the following:

    @GetMapping("/hello")
    public String index() {
        return "Hello from Spring Boot!";
    }

Restart the application, and open the following URL in your browser to verify
the app is working:
<http://localhost:8080/hello>

With the updated context, you should get a slightly different message.

Commit the updated controller to git, and compare against the reference
repository.

```sh
git add src/main/java/edu/carroll/cs341/HelloController.java
git commit -m "Update route and changed output"
git diff hello_route
```

Note, if you go back to the root context (/), you will receive an error as
there is no longer any controller associated with that route any longer.

<http://localhost:8080/>

This should return a __Whitelabel Error Page__.

Unit Test
---------

Now that we have a controller that listens for a route, let's write a
test to verify it works properly to ensure that future modifications don't
break the application.


First, the project should already contain a file to ensure the Spring
application context is created properly for unit tests (src/test/java/edu/carroll/cs341/Cs341ApplicationTests.java).

Note, by convention, the name of the test class is in the exact same package
as the class that's being tested, and the class name is similar but simply
appends __Test__ to the end of the class name. In this case, __Tests__ (plural)
was appened to the end, for historical reasons.

Add a new controller test class to your project (src/test/java/edu/carroll/cs341/HelloControllerTest.java).

    package edu.carroll.cs341;

    import static org.hamcrest.Matchers.equalTo;
    import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
    import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

    import org.junit.jupiter.api.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
    import org.springframework.test.web.servlet.MockMvc;

    @WebMvcTest(HelloController.class)
    public class HelloControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @Test
        public void indexTest() throws Exception {
            mockMvc.perform(get("/hello")).andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(equalTo("Hello from Spring Boot!")));
        }
    }

After adding to the project, run your test using gradle:

```sh
gradlew test
```

    BUILD SUCCESSFUL in 7s
    4 actionable tasks: 2 executed, 2 up-to-date

Success.  Add the test file to your repo, commit it, and once completed, verify
your code against the reference repository.

```sh
git diff unit_test
```

Once you've verified you have a valid unit test, either modify the controller
class or the unit test class to change the string to no longer match, and re-run
the test again. You should get a result similar to the following:

    HelloControllerTest > indexTest() FAILED
        java.lang.AssertionError at HelloControllerTest.java:23


HTML Response
-------------

Till now, we've generated simple text as responses to our requests, however
normal websites return entire HTML pages. There are MANY ways to do this,
including creating the browser's HTML interface with a javascript front-end
that interacts with the backend using REST calls (or similar), such as
[Angular](https://angular.io/) or [React](https://reactjs.org/). Or, you can
render the pages on the back-end and simply serve them to browser directly.
For simplicity sakes, we're going to do the latter, but feel free to
investigate full-fledged front-end environments that you can use to create
the UI the browser interacts with.

Now that it's been decided to create all the HTML content on the backend,
we've still got a few frameworks to decide on. There are quite a few, but
we have to choose one, and for this tutorial we're going to be using
[Thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/thymeleafspring.html).

First, we need to add the dependency to our project in the build.gradle
file.  In the dependencies sections, add the following line:

    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'

Note, we could have also simple selected this additional dependency when we
were first creating the project on <https://start.spring.io/>.

Thymeleaf stores it's resources file in the resources folder, which is located
in src/main/resources/templates.

Create a template file as the following following (src/main/resources/templates/index.html):

    <!DOCTYPE HTML>
    <html xmlns:th="http://www.thymeleaf.org">
      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Serving HTML Content</title>
      </head>
      <body>
        <p>Hello World!<p>
      </body>
    </html>

Next, modify the code for HelloController to change the class from a
@RestController to a normal @Controller, and instead of returning the
content as the String, return the name of the template.  The file should
look like the following:

    package edu.carroll.cs341;

    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;

    @Controller
    public class HelloController {
        @GetMapping("/hello")
        public String index() {
            return "index";
        }
    }

Restart the application, and open the following URL in your browser to verify
the app is returning the correct content:
<http://localhost:8080/hello>

Commit your updated code to your repo, and verify your repo against the
remote repository code:

```sh
git diff thymeleaf_template
```

Dynamic HTML rendering
----------------------

While this is nice, wouldn't it be nice if we could render the HTML
using content generated by the Java code.  Let's do that next!

First, let's change our index method a bit to take an optional
parameter.

Let's make two changes.  First, change the context on the page from
'/hello' to '/' (as index pages are usually on the root context).

    @GetMapping("/")

Next, change the signature of the method to take an optional parameter,
and add the ability to send data to the template file.

    public String index(@RequestParam(value="name", required=false, defaultValue="Student")String name, Model model) {

Note, @RequestParam has 3 attributes, **value**, **required**,
and **defaultValue**. By default, all parameters are required unless
required is set to false.  Or, you can just set the defaultValue to a
value, which implies that required=false.  Also, we just added a new
**Model** parameter.

Let's pass the parameter to our template using the model in the index() method.

    model.addAttribute("name", name);

Once done, your method should look like the following:

    @GetMapping("/")
    public String index(@RequestParam(value="name", required=false, defaultValue="Student")String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

Note, you'll need to make sure all of your imports are done properly for
this to work properly (I won't mention imports any more, with the
assumption the user's IDE will aid in getting the imports done
properly.)

At this point, we've setup our Java code to send in the data to the
template, so let's make a change to the HTML template code to expect the
name parameter.

Update the index template file to change the paragraph to look like the
following:

    <p th:text="'Hello, ' + ${name} + '!'"/>

Note, that we're using a special **th:text** attribute in the **p** tag
to expand the content of the name parameter.

After updating the template, save your changes to both the Java and
template code, and test your application make sure it works fine.

Here are a couple of URLs to test things are working:

* <http://localhost:8080/>
* <http://localhost:8080/?name=You>

Once you get everything working properly, commit the changes to your
repo, and verify your repo against the remote repository code:

```sh
git diff request_param
```

Updated Unit test
-----------------

Unfortunately, we now have a problem in that the Unit test is now
failing. Try modifying your test to see if you can fix the unit test to
be both flexible yet still able to test the actual index method.

Ensure your tests pass, and then commit your code.  Once done, you can
compare your implementation with the author's updated test.

```sh
git diff template_tests
```

Congratulations, you've now create a basic Java backend application, and
with a bit of work, you can create both front and back end pages
dynamically based on the user input.

Spring Boot Devtools
--------------------

Once useful ability when developing web applications is to avoid the
update/restart/refresh cycle whenever making changes. This process can
eat up a lot of time.  To speed up this cycle, you can add the
[spring-boot-devools](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using.devtools)
module to your application.

This enables a number of tools which speeds up the development
process. I recommend adding this to your project by adding the following
line to the build.gradle file in the dependencies section:

    developmentOnly 'org.springframework.boot:spring-boot-devtools'

Update the file, verify if works by restarting your application, commit
your code, and compare your file with the reference repository.

```sh
git diff devtools
```

Note, there are additional configuration steps you may need to take for this
to work properly. Here is an older article that may provide some information
to allow things to work better.

<https://www.youtube.com/watch?v=BvIM9gNTDM4&t=9s>

Form Validation
---------------

Now that we have a working web application, let's do something a bit
more complex. Specifically, let's traverse between a couple of web
pages, and ask the user to supply some data to us and have the
application provide some sort of validation that the data is correct.

First thing, let's remove the HelloController and HelloControllerTest
from our project (git rm et. al), and create a more aptly named
IndexController and IndexControllerTest in their place, but this time,
let's create some sub-packages to break out the application into
different functional units. Note, by convention, the package names are
not plural, and neither are the class names.

src/main/java/edu/carroll/cs341/web/controller/IndexController.java:

    package edu.carroll.cs341.web.controller;

    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;

    @Controller
    public class IndexController {
        @GetMapping("/")
        public String index() {
            return "index";
        }
    }

src/test/java/edu/carroll/cs341/web/controller/IndexControllerTest.java:

    package edu.carroll.cs341.web.controller;

    import static org.hamcrest.Matchers.containsString;

    import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
    import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

    import org.junit.jupiter.api.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
    import org.springframework.test.web.servlet.MockMvc;

    @WebMvcTest(IndexController.class)
    public class IndexControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @Test
        public void indexTest() throws Exception {
            mockMvc.perform(get("/")).andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("Welcome to the application!")))
                    .andExpect(content().string(containsString("login")));
        }
    }

Finally, modify the index template file at src/main/resources/templates/index.html:

    <!DOCTYPE HTML>
    <html xmlns:th="http://www.thymeleaf.org">
      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Welcome page</title>
      </head>
      <body>
        <p>Welcome to the application!</p>
        <br/>
        <p><a href="#">login</a> to continue</p>
      </body>
    </html>

Ensure your applications runs (login doesn't do anything yet) and your
unit tests passed, and commit the code.

```sh
git diff simple_init
```
