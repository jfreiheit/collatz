import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenerateSRG
{
	public static List<String> readFile(String file)
	{
		BufferedReader reader;
		List<String> lines = new ArrayList<>();

		try {
			reader = new BufferedReader(new FileReader("assets/" + file));
			String line = reader.readLine();

			
			while (line != null) {
				
				lines.add(line);
				line = reader.readLine();
			}

			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
	
	public static SRG generateSRG() {
		List<String> input = readFile("collatz");
		return new SRG();		// toDo
	}
}
