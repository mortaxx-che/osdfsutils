# osdfsutils
Operating system specific file system helper functions

This classes provide some helper functions for operating system dependent file system operations. 
- Under Mac OS you can detect alias files and resolve there target.
- Under Windows you can detect and resolve lnk-links as well as junctions/sparse points and resolve there target.

This code depends on some native implementation, provided by the Java Natice Access (JNA) project: https://github.com/java-native-access/jna#readme

You need the jna.jar and platform.jar libraries to compile osdfsutils.
