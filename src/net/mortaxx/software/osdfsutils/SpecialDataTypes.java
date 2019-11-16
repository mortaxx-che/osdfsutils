package net.mortaxx.software.osdfsutils;

/*
Copyright (c) 2009-2019, Christian Hecht. 
All rights reserved.

This software is published under the "Simplified BSD License" (2-clause license)

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

The views and conclusions contained in the software and documentation are those
of the authors and should not be interpreted as representing official policies,
either expressed or implied, of the FTLEdit project.

These class is part of osdfsutils.

osdfsutils includes and uses libraries and source code from some other contributors:

--> Java Native Access (JNA)
Copyright (c) 2007-2019 by Timothy Wall - https://github.com/java-native-access/jna#readme

*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import sun.awt.shell.ShellFolder;

import com.sun.jna.Native;
import com.sun.jna.WString;

public class SpecialDataTypes {

	private static Kernel32 winlibk32 = null;
	private static Boolean s_flg_is_Windows = false;
	private static Boolean s_flg_is_Mac = false;
	private static Boolean s_flg_is_UnixLinux = false;
	private static MXHandleAlias aliashandler;
	
	static  {
	
/* Initialize the global os identifier */
		
		String current_os = System.getProperty("os.name").toLowerCase();;	
		
		if (current_os.indexOf("mac") >= 0) {
			
	  		s_flg_is_Mac = true;
	  		
		}
		else if (current_os.indexOf("win") >= 0) {
			
	  		s_flg_is_Windows = true;
	  		
		}
		else if (current_os.indexOf("nix") >= 0 || current_os.indexOf("nux") >= 0) {

			s_flg_is_UnixLinux = true;
												
		}
	
	}

	public static boolean isMac() {
		return s_flg_is_Mac;
	}
	
	public static boolean isWindows() {
		return s_flg_is_Windows;
	}
	public static boolean isUnixLinux() {
		return s_flg_is_UnixLinux;
	}
	
/* ---------------------------------------------------------------------------------------------
 * Service methods which calls the appropriate platform specific method dependent of the host OS
 * --------------------------------------------------------------------------------------------- */
	public static File handleSpecialData(File link_ref) {
	
/*		if (s_flg_is_Mac) {
			
			if (link_ref.isFile() == true) {
// Ein Alias erscheint f�r Java immer als Datei, daher muss die Pr�fung nur f�r Dateien stattfinden
				File result = resolveAlias(link_ref);
				
				if (result != null) {
					return result;
				} else {
					
					return link_ref;
				}
				
			}
			else {
					
				return link_ref;
			}
		}
		else if (s_flg_is_Windows) {
		
			return resolveTargetPathWin(link_ref);
		}
		else {
			return link_ref;
		} */
		return null;
	}
	
/* ---------------------
 * Methods for Mac OS X 
 * ---------------------*/
	
	public static boolean isAlias(File possibleAlias) {

	    if (aliashandler == null) {
	    	aliashandler = new MXHandleAlias();

	    }
	    String posixpath = possibleAlias.getAbsolutePath();
	    return aliashandler.isAlias(posixpath);
	    
/* Call Finder via applescript to determine if the given file reference
 * is an mac specific alias */
		
/*		InputStream stdout = null;
		BufferedReader brstdout = null;
		int exitval = 1;*/
		
/* Runtime environment for system calls */
		
//	    Runtime runtime = Runtime.getRuntime();

/* Generate the necessary command and applescript lines into a string array */
	    
/*	    String posixpath = "'" + possibleAlias.getAbsolutePath() + "'";

	    String cmdline = "set theItem to (POSIX file \"" + posixpath + "\") as alias";
	    String[] args = { "osascript",
	    					"-e", 
	    					"tell application \"Finder\"",
	    					"-e",
	    					cmdline,
	    					"-e",
	    					"if the kind of theItem is \"alias\" then",
	    					"-e",
	    					"return the posix path of (original item of theItem as text)",
	    					"-e",
	    					"end if", 
	    					"-e",
	    					"end tell"}; 
/*	    String[] args = { "osascript",
	    					"-e", 
	    					"/Users/mortaxx/Desktop/Alias.scpt",
	    					"-e",
	    					posixpath}; */
/*	    String command = "/Users/mortaxx/getTrueName " + posixpath;
	    try {*/
	    	
/* Execute the applescript via the osascript tool */
	    	
//	      Process proc = runtime.exec(command);
	      
/* If the applescript has determined a valid alias then the target of the alias is written to stdout.
 * So now we read the input stream from the process - if there is any content, an alias was detected
 * and the method returns true */
	      
/*	      try {
			exitval = proc.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	      stdout = proc.getInputStream();
//	      brstdout = new BufferedReader(new InputStreamReader(stdout));
	      
			if (exitval == 0) {
				return true;
			} else {
				return false;
			}
/*	      if ((brstdout.readLine()) != null) {
	    	  return true;
	      }
	      else {
	    	  return false;
	      }*/
/*
	    }
	    catch (IOException e)
	    {
	      e.printStackTrace();
	      return false;
	    }*/

	}
	
	public static File resolveAlias(File alias) {
		
// Ueber JNI natives Mac OS X Coding aufrufen, um zu pruefen ob die uebergebene
// Dateireferenz ein Alias ist
		
/*		InputStream stdout = null;
		BufferedReader brstdout = null;
		String l_var_result;
		int exitval = 1; */
		File resolved_alias = null;

/* Runtime environment for system calls */
		
//	    Runtime runtime = Runtime.getRuntime();	    

	    String posixpath = alias.getAbsolutePath();
	    
	    if (aliashandler == null) {
	    	aliashandler = new MXHandleAlias();
	    	resolved_alias = null;
	    }
	    if (aliashandler.isAlias(posixpath) == true) {
	    	System.out.println("Mac OS X --> Alias gefunden");
	    	String resolved_alias_path = aliashandler.resolveAlias(posixpath);
	    	resolved_alias = new File(resolved_alias_path);
		}

	    return resolved_alias;
	    
// Obsolete coding
	    /* Call Finder via applescript to determine if the given file reference
	     * is an mac specific alias 
	     * 
	     * Returns the alias target as file reference */
/* Generate the necessary command and applescript lines into a string array */
/*	    String[] args = { "osascript",
	    					"-e", 
	    					"tell application \"Finder\"",
	    					"-e",
	    					cmdline,
	    					"-e",
	    					"if the kind of theItem is \"alias\" then",
	    					"-e",
	    					"return the posix path of (original item of theItem as text)",
	    					"-e",
	    					"end if", 
	    					"-e",
	    					"end tell"}; 
/*	    String[] args = { "osascript",
				"-e", 
				"/Users/mortaxx/Desktop/Alias.scptd",
				"-e",
				posixpath}; */
/*	    String command = "/Users/mortaxx/getTrueName " + posixpath;
	    try
	    {
	      Process process = runtime.exec(command);
	      try {
				exitval = process.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		      stdout = proc.getInputStream();
//		      brstdout = new BufferedReader(new InputStreamReader(stdout));
		      
				if (exitval == 0) {
					return alias;
				} else {
					return null;
				}

/* If the applescript has determined a valid alias then the target of the alias is written to stdout.
 * So now we read the input stream from the process - if there is any content, an alias was detected
 * and the method returns the a file reference to first line which contains the alias target */
	      
/*	      if ((l_var_result = brstdout.readLine()) != null) {
	    	  return new File(l_var_result);
	      }
	      else {
	    	  return null;
	      }*/

/*	    }
	    catch (IOException e)
	    {
	      e.printStackTrace();
	      return null;
	    }	*/	
	}

/* --------------------
 * Methods for Windows 
 * --------------------*/
	
	public static boolean isJunctionOrSymlink(File f) throws IOException {
		
/* Determines if the given file reference is a symbolic link or file junction 
 * via jna call of the kernel32.dll library
 */
		return (f.exists() && (0x400 & getWin32FileAttributes(f)) != 0);
	}

	public static File isWindowsLinkFile(File f) {

		ShellFolder folder = null;
		
		if (f.isFile()) {
			try {
				
				folder = ShellFolder.getShellFolder(f);
				
			} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (folder.isLink() == true ) {
				try {
					
					System.out.println( folder.getLinkLocation()  + "--> WIN -> is lnk. file");
					return folder.getLinkLocation();
				} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				} // C:\WINDOWS\system32\control.exe
			}
		}
		return null;
	}
	public static File isHiddenJunctionOrSymlink(File link) {
		
/*	    try {
		  Process p = Runtime.getRuntime().exec( "cmd /c dir" );
	    }
		catch (IOException e) {
			
		      e.printStackTrace();
		      return null;
		      
		}*/
		
		try {
			if (isJunctionOrSymlink(link) == true) {
				if (link.isHidden() == false) {
					System.out.println(link.getAbsolutePath() + "--> WIN -> is not hidden Junction or Symlink");
					return link;
				} else {
					return null;
				}
			} 
/*			if ((link.exists() && (0x400 & getWin32FileAttributes(link)) != 0) == true) {
				System.out.println(link.getAbsolutePath() + "-> is Junction or Symlink");
				if (link.isHidden() == true) {
					System.out.println(link.getAbsolutePath() + "-> is hidden Junction or Symlink");
				}
			}*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


// Wenn es keine lnk-Verkn�pfung ist - Nichts zur�ckgeben
		
		return null;
	}
	
	private static int getWin32FileAttributes(File f) throws IOException {
		
/* JNA call to kernel32.dll to get the needed attributes */
		
	  if (winlibk32 == null) {
	    synchronized (Kernel32.class) {
	      winlibk32 = (Kernel32) Native.loadLibrary("kernel32", Kernel32.class);
	    }
	  }
	  return winlibk32.GetFileAttributesW(new WString(f.getCanonicalPath()));
	}

}
