AmpLib
========

For Developers
--------------

If you're using [Maven](http://maven.apache.org/download.html) to manage project dependencies, simply include the following in your `pom.xml`:

    <dependency>
      <groupId>ninja.amp</groupId>
      <artifactId>amplib</artifactId>
      <version>1.2</version>
      <scope>compile</scope>
    </dependency>

Compilation
-----------

AmpLib uses Maven to handle its dependencies.

* Download and install [Maven 3](http://maven.apache.org/download.html)  
* Checkout this repo and run: `mvn clean install`

The License
-----------

AmpLib is licensed under the [GNU Lesser General Public License Version 3](https://github.com/ampayne2/AmpLib/blob/master/LICENSE.txt)

Contributing
------------

Guidelines:
* All new files must include the license header. This can be done automatically with Maven by running mvn clean install.
* Generally follow the Oracle code conventions and the current style.
* Use four spaces for indentation, not tabs.
* No trailing whitespace (spaces/tabs on new lines and end of lines).
* 200 column limit for readability.