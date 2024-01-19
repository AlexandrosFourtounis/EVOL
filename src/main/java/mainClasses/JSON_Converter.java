/* csd5031 , csd5020 , csd4845
* HY-360 JSON_Converter.java
 */
package mainClasses;

import java.io.BufferedReader;
import java.io.IOException;

public class JSON_Converter {


    public String getJSONFromAjax(BufferedReader reader) throws IOException{
	StringBuilder buffer = new StringBuilder();
	String line;
	while ((line = reader.readLine()) != null) {
		buffer.append(line);
	}
	String data = buffer.toString();
	return data;
}

    
    
}
