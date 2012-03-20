package unifier.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <p>Title: Unifier</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: IPI RAS</p>
 *
 * @author Sergey A. Stupnikov
 * @version 1.0
 */
public class TextFile {

    public TextFile (String name, String content){
        this.name = name;
        this.content = content;
    }

    private String name;
    private String content;

    public String getName(){
        return name;
    }

    public String getContent(){
        return content;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void save(String path) throws IOException {
        FileWriter writer = new FileWriter(new File(path + name));

        writer.write(content);
        writer.close();
    }
}
