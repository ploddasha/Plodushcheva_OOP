
import org.json.simple.parser.*;

public class NoteBook {
    public String fileName;

    public NoteBook(String fileName){
        this.fileName = fileName;
    }

    public cl(){
        Object o = new JSONParser().parse(new FileReader(File.json));

        JSONObject j = (JSONObject) o;

        String Name = (String) j.get("Name");

        String College = (String ) j.get("College");

        System.out.println("Name :" + Name);

        System.out.println("College :" +College);

    }

}
