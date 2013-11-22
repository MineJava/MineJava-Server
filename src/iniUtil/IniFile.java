package iniUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/** Description of IniFile 
*
* @author jython234
*/

public class IniFile {
	
	protected char mode;
	protected String file;
	
	private BufferedReader br;
	private Map sectionData;
	private String[] sections;
	
	/**Constructor for IniFile. (Represents an .ini file) 
	 * 
	 * @param file                  The ini file(or path) 
	 * @param mode                  What mode to open this file in
	 */
	public IniFile(String file, char mode){
		//Constructor
		this.file=file;
		this.mode=mode;
		Map<String,String[]> sectionData = new HashMap<String,String[]>();
		
		if(this.mode=='r'){
			try {
				br = new BufferedReader(new FileReader(this.file));
				String line;
				int currentSection=0;
				while ((line = br.readLine()) != null) {
					if(line.startsWith("[")){
						//Then its a section
						String newSectionTitle=line.replaceAll("[", "");
						newSectionTitle=line.replaceAll("]", "");
						
						sections[currentSection]=newSectionTitle;
						String sectionLine;
						String[] sectionDataForSection = null;
						int currentLine=0;
						while ((sectionLine = br.readLine()) != "") {
							sectionDataForSection[currentLine]=sectionLine;
							currentLine++;
							
						}
						
						sectionData.put(newSectionTitle, sectionDataForSection);
					}
					currentSection++;
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(this.mode=='w'){
			//Working on it
		}
		else{
			
		}
	}
	/**
	 * 
	 * @return                Returns an array of all the sections in the file
	 */
	public Map getSectionData(){
		return sectionData;
	}
	/**Returns the number of sections
	 * 
	 * @return             Returns an integer of the number of sections
	 */
	public int getNumberOfSections(){
		return sections.length;
	}
	/**Returns all the sections
	 * 
	 * @return           Returns a String[] of all the sections
	 */
	public String[] getSections(){
		return sections;
	}
	
	/**Closes the open .ini file
	 * 
	 */
	public void close(){
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("An error has ocurred while trying to close file:");
			e.printStackTrace();
		}
	}

}
