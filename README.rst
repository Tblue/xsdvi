XsdVi
=====

XsdVi is a "Java application [...] to transform W3C XML Schema
instances into interactive diagrams in SVG format" (see the
`XsdVi SourceForge project page`_).

This repository is an *unmaintained* fork of the original
XsdVi source code; it contains some minor improvements to the build
process.

Please refer to the `XsdVi SourceForge project page`_ and the
`XsdVi website`_ for more information on XsdVi.

Quickstart
----------

Build-time dependencies
+++++++++++++++++++++++

- Java Development Kit (JDK) 6 or newer. Tested with `OpenJDK`_ 8.
  Will probably work with `Oracle JDK`_, too.
- `Apache Ant`_ to build the project.

Run-time dependencies
+++++++++++++++++++++

- Java Runtime Environment (JRE) 6 or newer. A complete JDK (see
  above) will work, too.

Quickstart
++++++++++

To obtain a list of build targets, run the following command in the
directory that contains this README file::

    ant -p

The default target is ``dist``, so you can just run the following
command to build XsDvi::

    ant

Now you can run XsDvi, e. g.::

    java -jar dist/lib/xsdvi.jar dist/examples/xsd/faktura.xsd
    firefox faktura.svg

For a short help message, just run XsDvi without any arguments::

    java -jar dist/lib/xsdvi.jar

Copyright
---------

See the file `COPYING <./COPYING>`_ in this repository for details.


.. _Apache Ant:
    https://ant.apache.org/
.. _OpenJDK:
    http://openjdk.java.net/
.. _Oracle JDK:
    http://www.oracle.com/technetwork/java/javase/downloads/index.html
.. _XsdVi SourceForge project page:
    https://sourceforge.net/projects/xsdvi/
.. _XsDvi website:
    http://xsdvi.sourceforge.net/
